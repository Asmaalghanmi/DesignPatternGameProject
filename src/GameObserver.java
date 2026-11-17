/**
 * Observer pattern - Observer interface.
 * Any class that wants to react when a brick is hit implements this.
 */
public interface GameObserver {
    void onBrickHit();
}
