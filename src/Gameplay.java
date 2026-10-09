/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */

import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseMotionListener {

    private boolean play = false;
    private int score;

    private int hscore;
    private int totalBricks;
    private int level = 1;
    private boolean levelWon = false;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;


    public Gameplay() {
        addKeyListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        applyLevel(LevelDirector.forLevel(1));
        timer.start();
    }

    // Takes the map, brick count and speed from a Level built by the Builder
    private void applyLevel(Level lvl) {
        map = lvl.getMap();
        totalBricks = lvl.getTotalBricks();
        timer.setDelay(lvl.getDelay());
    }

    private void startLevel(int lvl) {
        play = true;
        levelWon = false;
        ballposX = 120;
        ballposY = 350;
        ballXdir = -1;
        ballYdir = -2;
        playerX = 310;
        applyLevel(LevelDirector.forLevel(lvl));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // background
        g.setColor(Color.black);
        g.fillRect(1, 25, 692, 592);

        // drawing map
        map.draw((Graphics2D) g);

        // score display 		
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 18));

        //g.drawString("Score:"+score, 550,15);
        // g.drawString("Highest Score:"+hscore, 20,15);
        g.setFont(new Font("serif", Font.PLAIN, 15));

        g.setColor(Color.black);
        g.drawString("Press [ESCAPE] to exit", 290, 640);

        // the paddle
        g.setColor(Color.orange);
        g.fillRect(playerX, 550, 100, 8);

        // the ball
        g.setColor(Color.red);
        g.fillOval(ballposX, ballposY, 20, 20);

        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD, 18));
        g.drawString("Level " + level, 320, 15);

        // winning the level/game
        if (totalBricks <= 0) {
            levelWon = true;
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won", 275, 300);
            g.drawString("Score:" + score, 285, 330);
//             if(score>hscore)
//             {
//                 hscore=score;
//                 g.drawString("New High Score!", 265,360);
//             }
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 20));
            if (level < 3) {
                g.drawString("Press (Enter) for Next Level", 210, 500);
            } else {
                g.drawString("You have beaten the game!", 215, 500);
            }
        }

        // losing the game
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));

            g.drawString("Game Over", 270, 300);
            g.drawString("Score:" + score, 285, 330);
            if (score > hscore) {
                hscore = score;
                g.drawString("New High Score!", 245, 360);
            }

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press (Space) to Restart", 255, 500);
        }

    }

    public void mouseMoved(MouseEvent m) {
        if (m.getX() > playerX && playerX <= 560) {
            moveRight();
        }
        if (m.getX() < playerX && playerX > 10) {
            moveLeft();
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 590) {
                playerX = 590;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play && levelWon && level < LevelDirector.LEVEL_COUNT) {
                level++;
                if (level <= LevelDirector.LEVEL_COUNT) {
                    startLevel(level);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!play) {
                score = 0;
                level = 1;
                startLevel(level);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            timer.stop();
            java.awt.Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
            Menu.getInstance().showMenu();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void moveRight() {
        play = true;
        playerX += 30;
    }

    public void moveLeft() {
        play = true;
        playerX -= 30;
    }

    public void actionPerformed(ActionEvent e) {
        if (play) {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8))) {
                ballYdir = -ballYdir;
                ballXdir = -2;
            } else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))) {
                ballYdir = -ballYdir;
                ballXdir = ballXdir + 1;
            } else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8))) {
                ballYdir = -ballYdir;
            }

            // check map collision with the ball		
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (!map.map[i][j].isDestroyed()) {
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) {
                            // a strong brick survives until its last hit
                            if (map.hitBrick(i, j)) {
                                totalBricks--;
                            }
                            score += 5;

                            // when the ball hits right or left of brick
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } // when the ball hits top or bottom of brick
                            else {
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

            repaint();
        }
    }

}