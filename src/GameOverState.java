import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * State pattern - Concrete State.
 * The ball fell below the paddle.
 */
public class GameOverState implements GameState {

    private final boolean newHighScore;

    public GameOverState(boolean newHighScore) {
        this.newHighScore = newHighScore;
    }

    @Override
    public void update(Gameplay game) {
        // The ball stops after losing
    }

    @Override
    public void draw(Gameplay game, Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("Game Over", 270, 300);
        g.drawString("Score:" + game.getScore(), 285, 330);

        if (newHighScore) {
            g.drawString("New High Score!", 245, 360);
        }

        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Press (Space) to Restart", 255, 500);
    }

    @Override
    public void handleKey(Gameplay game, int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            game.restartGame();
        }
    }

    @Override
    public void movePaddle(Gameplay game, int dx) {
        // The paddle does not move after losing
    }
}
