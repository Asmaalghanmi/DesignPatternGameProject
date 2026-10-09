import java.awt.Font;
import javax.swing.*;

public class Menu extends JFrame {
    private static Menu instance;
    private final JButton playButton;

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
        playButton.addActionListener(e -> startGame());
        add(playButton);
    }

    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void showMenu() {
        setVisible(true);
        toFront();
    }

    private void startGame() {
        Gameplay gameplay = new Gameplay();
        JFrame gameWindow = new JFrame("Break The Bricks");
        gameWindow.setSize(700, 700);
        gameWindow.setResizable(false);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.add(gameplay);
        setVisible(false);
        gameWindow.setVisible(true);
        gameplay.requestFocusInWindow();
    }
}
