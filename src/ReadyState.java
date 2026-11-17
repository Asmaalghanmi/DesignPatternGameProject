import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * State pattern - Concrete State.
 * The game is waiting for the player to move the paddle before it starts.
 */
public class ReadyState implements GameState {

    @Override
    public void update(Gameplay game) {
        // The ball does not move until the player starts
    }

    @Override
    public void draw(Gameplay game, Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 20));
        g.drawString("Press LEFT / RIGHT to start", 230, 400);
    }

    @Override
    public void handleKey(Gameplay game, int keyCode) {
        // No other keys are used in this state
    }

    @Override
    public void movePaddle(Gameplay game, int dx) {
        game.movePaddle(dx);
        game.setState(new PlayingState());   // first move starts the game
    }
}
