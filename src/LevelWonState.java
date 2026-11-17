import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * State pattern - Concrete State.
 * All bricks of the level are destroyed.
 */
public class LevelWonState implements GameState {

    @Override
    public void update(Gameplay game) {
        // The ball stops after winning
    }

    @Override
    public void draw(Gameplay game, Graphics g) {
        g.setColor(Color.RED);
        g.setFont(new Font("serif", Font.BOLD, 30));
        g.drawString("You Won", 275, 300);
        g.drawString("Score:" + game.getScore(), 285, 330);

        g.setFont(new Font("serif", Font.BOLD, 20));
        if (game.hasNextLevel()) {
            g.drawString("Press (Enter) for Next Level", 210, 500);
        } else {
            g.drawString("You have beaten the game!", 215, 500);
        }
    }

    @Override
    public void handleKey(Gameplay game, int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER && game.hasNextLevel()) {
            game.nextLevel();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            game.restartGame();
        }
    }

    @Override
    public void movePaddle(Gameplay game, int dx) {
        // The paddle does not move after winning
    }
}
