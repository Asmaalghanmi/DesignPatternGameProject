import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * Gameplay
 *  - State pattern   : Context  -> behaviour depends on currentState
 *  - Observer pattern: Subject  -> notifies observers when a brick is hit
 */
public class Gameplay extends JPanel
        implements KeyListener, ActionListener, MouseMotionListener, GameSubject {

    private int totalBricks;
    private int level = 1;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;

    private final GameFacade facade;

    // State pattern
    private GameState currentState;

    // Observer pattern
    private final List<GameObserver> observers = new ArrayList<>();
    private final ScoreObserver scoreBoard;

    public Gameplay(GameFacade facade) {
        this.facade = facade;
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Register observers
        scoreBoard = new ScoreObserver();
        addObserver(scoreBoard);
        addObserver(new SoundObserver(new ClipSoundAdapter("sounds/hit.wav")));

        timer = new Timer(delay, this);
        applyLevel(createLevel(1));
        setState(new ReadyState());
        timer.start();
    }

    // Client: creates the Builder and Director
    private Level createLevel(int levelNumber) {

        int rows;
        int cols;
        int strongPercent;
        int delay;

        switch (levelNumber) {
            case 1:
                rows = 3;
                cols = 7;
                strongPercent = 0;
                delay = 8;
                break;

            case 2:
                rows = 4;
                cols = 9;
                strongPercent = 30;
                delay = 7;
                break;

            case 3:
                rows = 4;
                cols = 12;
                strongPercent = 50;
                delay = 6;
                break;

            default:
                throw new IllegalArgumentException(
                        "No such level: " + levelNumber);
        }

        LevelBuilder levelBuilder =
                new ConcreteLevelBuilder(rows, cols, strongPercent, delay);

        LevelDirector levelEng = new LevelDirector(levelBuilder);

        levelEng.makeLevel();

        return levelEng.getLevel();
    }

    // Takes the map, brick count and speed from a Level
    private void applyLevel(Level lvl) {
        map = lvl.getMap();
        totalBricks = lvl.getTotalBricks();
        timer.setDelay(lvl.getDelay());
    }

    private void startLevel(int lvl) {
        ballposX = 120;
        ballposY = 350;
        ballXdir = -1;
        ballYdir = -2;
        playerX = 310;

        applyLevel(createLevel(lvl));
        setState(new PlayingState());
        repaint();
    }

    // ===================== State pattern =====================

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void nextLevel() {
        level++;
        startLevel(level);
    }

    public void restartGame() {
        scoreBoard.reset();
        level = 1;
        startLevel(level);
    }

    public boolean hasNextLevel() {
        return level < LevelDirector.LEVEL_COUNT;
    }

    public boolean isLevelCleared() {
        return totalBricks <= 0;
    }

    public boolean isBallLost() {
        return ballposY > 570;
    }

    public int getScore() {
        return scoreBoard.getScore();
    }

    public boolean saveHighScore() {
        return scoreBoard.saveHighScore();
    }

    // ===================== Observer pattern =====================

    @Override
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyBrickHit() {
        for (GameObserver observer : observers) {
            observer.onBrickHit();
        }
    }

    // ===================== Game logic =====================

    public void movePaddle(int dx) {
        playerX += dx;
        if (playerX < 10) {
            playerX = 10;
        }
        if (playerX > 590) {
            playerX = 590;
        }
    }

    // Moves the ball one step and checks all collisions
    public void moveBall() {
        if (new Rectangle(ballposX, ballposY, 20, 20)
                .intersects(new Rectangle(playerX, 550, 30, 8))) {

            ballYdir = -ballYdir;
            ballXdir = -2;

        } else if (new Rectangle(ballposX, ballposY, 20, 20)
                .intersects(new Rectangle(playerX + 70, 550, 30, 8))) {

            ballYdir = -ballYdir;
            ballXdir = ballXdir + 1;

        } else if (new Rectangle(ballposX, ballposY, 20, 20)
                .intersects(new Rectangle(playerX + 30, 550, 40, 8))) {

            ballYdir = -ballYdir;
        }

        // check map collision with the ball
        A:
        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[0].length; j++) {

                if (!map.map[i][j].isDestroyed()) {
                    int brickX = j * map.brickWidth + 80;
                    int brickY = i * map.brickHeight + 50;

                    Rectangle brickRect = new Rectangle(
                            brickX, brickY, map.brickWidth, map.brickHeight);

                    Rectangle ballRect = new Rectangle(
                            ballposX, ballposY, 20, 20);

                    if (ballRect.intersects(brickRect)) {

                        // A strong brick survives until its last hit
                        if (map.hitBrick(i, j)) {
                            totalBricks--;
                        }

                        // Observer: score and sound react to the hit
                        notifyBrickHit();

                        // when the ball hits right or left of brick
                        if (ballposX + 19 <= brickRect.x
                                || ballposX + 1 >= brickRect.x + brickRect.width) {

                            ballXdir = -ballXdir;

                        } else {
                            // when the ball hits top or bottom of brick
                            ballYdir = -ballYdir;
                        }

                        break A;
                    }
                }
            }
        }

        ballposX += ballXdir;
        ballposY += ballYdir;

        if (ballposX < 0) {
            ballXdir = -ballXdir;
        }

        if (ballposY < 0) {
            ballYdir = -ballYdir;
        }

        if (ballposX > 670) {
            ballXdir = -ballXdir;
        }
    }

    // ===================== Drawing =====================

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // background
        g.setColor(Color.black);
        g.fillRect(1, 25, 692, 592);

        // drawing map
        map.draw((Graphics2D) g);

        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.PLAIN, 15));
        g.drawString("Press [ESCAPE] to exit", 290, 640);

        // the paddle
        g.setColor(Color.orange);
        g.fillRect(playerX, 550, 100, 8);

        // the ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY, 20, 20);

        // level and score
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 18));
        g.drawString("Level " + level, 320, 18);
        g.drawString("Score: " + getScore(), 560, 18);

        // State: draws its own messages
        currentState.draw(this, g);
    }

    // ===================== Input =====================

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            facade.backToMenu();
        } else if (key == KeyEvent.VK_RIGHT) {
            currentState.movePaddle(this, 30);
        } else if (key == KeyEvent.VK_LEFT) {
            currentState.movePaddle(this, -30);
        } else {
            currentState.handleKey(this, key);
        }
    }

    public void mouseMoved(MouseEvent m) {
        if (m.getX() > playerX) {
            currentState.movePaddle(this, 30);
        } else if (m.getX() < playerX) {
            currentState.movePaddle(this, -30);
        }
    }

    public void actionPerformed(ActionEvent e) {
        currentState.update(this);
        repaint();
    }

    // Stops the game loop (called by the Facade when the game is closed)
    public void stop() {
        timer.stop();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
}
