/**
 * Observer pattern - Subject interface.
 * The class that announces game events (Gameplay) implements this.
 */
public interface GameSubject {
    void addObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
    void notifyBrickHit();
}
