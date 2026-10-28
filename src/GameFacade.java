public class GameFacade {

    public GameFacade() {
        // Constructor
    }
    public void startGame() {
        System.out.println("--- Starting the Game via Facade ---");
        loadResources();
        playBackgroundMusic();
        resetScore();
        System.out.println("Game is ready to play!");
    }
    public void stopGame() {
        System.out.println("--- Stopping the Game ---");
        stopBackgroundMusic();
        saveProgress();
        System.out.println("Game Stopped.");
    }
    private void loadResources() {
        System.out.println("Loading game assets, sounds, and images...");
    }

    private void playBackgroundMusic() {
        System.out.println("Playing background music...");
    }
    private void stopBackgroundMusic() {
        System.out.println("Stopping background music...");
    }
    private void resetScore() {
        System.out.println("Resetting player score to 0...");
    }
    private void saveProgress() {
        System.out.println("Saving game state...");
    }
}