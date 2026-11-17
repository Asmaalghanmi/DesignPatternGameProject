import java.awt.Graphics;

/**
 * State pattern - State interface.
 *
 * Each state of the game (Ready, Playing, LevelWon, GameOver) is a class
 * that implements this interface and decides how the game behaves
 * while it is in that state.
 */
public interface GameState {

    /** Called on every timer tick. */
    void update(Gameplay game);

    /** Draws what is special to this state (messages on screen). */
    void draw(Gameplay game, Graphics g);

    /** Handles keys other than the arrows and Escape. */
    void handleKey(Gameplay game, int keyCode);

    /** Handles paddle movement (arrows / mouse). */
    void movePaddle(Gameplay game, int dx);
}
