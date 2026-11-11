import java.awt.Font;
import javax.swing.*;

public class Menu extends JFrame {
    private static Menu instance = null;
    private final JButton playButton;
    private Runnable playAction = () -> { };

    private Menu() {
        super("Breakout - Main Menu");
        setSize(700, 700);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("BREAKOUT", SwingConstants.CENTER);
        heading.setFont(new Font("Serif", Font.BOLD, 48));
        heading.setBounds(100, 140, 500, 70);
        add(heading);

        playButton = new JButton("PLAY BREAKOUT");
        playButton.setBounds(240, 310, 220, 45);
        playButton.addActionListener(e -> playAction.run());
        add(playButton);
    }

    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    // Set by the Facade: what happens when PLAY is pressed
    public void setPlayAction(Runnable playAction) {
        this.playAction = playAction;
    }

    public void showMenu() {
        setVisible(true);
        toFront();
    }
}
