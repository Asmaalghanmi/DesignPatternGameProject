/**
 * Observer pattern - Concrete Observer.
 * Keeps the score and the high score.
 */
public class ScoreObserver implements GameObserver {

    private static final int POINTS_PER_HIT = 5;

    private int score;
    private int highScore;

    @Override
    public void onBrickHit() {
        score += POINTS_PER_HIT;
    }

    public int getScore() {
        return score;
    }

    public void reset() {
        score = 0;
    }

    /** Saves the score if it is a new high score. Returns true if it was. */
    public boolean saveHighScore() {
        if (score > highScore) {
            highScore = score;
            return true;
        }
        return false;
    }
}
