import java.awt.Graphics;

/**
 * State pattern - Concrete State.
 * The ball is moving and the player is playing.
 */
public class PlayingState implements GameState {

    @Override
    public void update(Gameplay game) {
        game.moveBall();

        if (game.isLevelCleared()) {
            game.setState(new LevelWonState());
        } else if (game.isBallLost()) {
            game.setState(new GameOverState(game.saveHighScore()));
        }
    }

    @Override
    public void draw(Gameplay game, Graphics g) {
        // Nothing extra to draw while playing
    }

    @Override
    public void handleKey(Gameplay game, int keyCode) {
        // No other keys are used while playing
    }

    @Override
    public void movePaddle(Gameplay game, int dx) {
        game.movePaddle(dx);
    }
}
