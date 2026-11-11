import javax.swing.JFrame;

/**
 * Facade: a single, simple entry point over the game subsystems.
 *
 *  - Menu        : show / hide the main menu
 *  - Gameplay    : creates the game (levels via Builder, bricks via Factory)
 *  - JFrame      : builds the game window
 *  - SoundPlayer : click sound
 *
 * Outside code (Main, Menu, Gameplay) talks only to this class and does not
 * need to know how the subsystems are wired together.
 */
public class GameFacade {

    private final Menu menu;
    private final SoundPlayer clickSound;

    private JFrame gameWindow;
    private Gameplay gameplay;

    public GameFacade() {
        this.menu = Menu.getInstance();
        this.clickSound = new ClipSoundAdapter("sounds/click.wav");

        // The menu only knows "something should happen when PLAY is pressed".
        // The Facade decides what that is.
        menu.setPlayAction(() -> {
            playClick();
            startGame();
        });
    }

    /** Opens the application on the main menu. */
    public void showMenu() {
        menu.showMenu();
    }

    /** Plays the click sound. */
    public void playClick() {
        clickSound.play();
    }

    /** Builds the game window, creates the game, and hides the menu. */
    public void startGame() {
        closeGameWindow();

        gameplay = new Gameplay(this);

        gameWindow = new JFrame("Break The Bricks");
        gameWindow.setSize(700, 700);
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.add(gameplay);

        menu.setVisible(false);
        gameWindow.setVisible(true);
        gameplay.requestFocusInWindow();
    }

    /** Used by Escape: closes the game window and returns to the menu. */
    public void backToMenu() {
        closeGameWindow();
        menu.showMenu();
    }

    private void closeGameWindow() {
        if (gameplay != null) {
            gameplay.stop();
            gameplay = null;
        }
        if (gameWindow != null) {
            gameWindow.dispose();
            gameWindow = null;
        }
    }
}
