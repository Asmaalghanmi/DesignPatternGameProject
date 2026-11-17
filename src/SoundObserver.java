/**
 * Observer pattern - Concrete Observer.
 * Plays the hit sound through the SoundPlayer (Adapter pattern).
 */
public class SoundObserver implements GameObserver {

    private final SoundPlayer hitSound;

    public SoundObserver(SoundPlayer hitSound) {
        this.hitSound = hitSound;
    }

    @Override
    public void onBrickHit() {
        hitSound.play();
    }
}
