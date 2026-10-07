/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {

    private static Menu instance;   // the only Menu

    JButton b1, b2;
    JPanel newPanel;
    JLabel heading, game1, game2, input;
    JFrame frame;

    private Menu() {
        frame = new JFrame();

        heading = new JLabel();
        game1 = new JLabel();
        game2 = new JLabel();
        heading.setFont(new Font("serif", Font.BOLD, 48));
        heading.setText("MINI GAMES");
        heading.setBounds(200, 100, 600, 40);
        game1.setFont(new Font("serif", Font.BOLD, 20));
        game1.setText("1. Break The Bricks");
        game1.setBounds(75, 275, 200, 30);
        game2.setFont(new Font("serif", Font.BOLD, 20));
        game2.setText("2. Tic Tac Toe");
        game2.setBounds(425, 275, 200, 30);

        b1 = new JButton("PLAY");
        b1.setBounds(75, 310, 200, 30);

        b2 = new JButton("PLAY");
        b2.setBounds(425, 310, 200, 30);

        frame.add(game1);
        frame.add(game2);
        frame.add(heading);
        frame.add(b1);
        frame.add(b2);
        frame.setSize(700, 700);
        frame.setLayout(null);

        b1.addActionListener(this);
        b2.addActionListener(this);
        frame.setTitle("Main Menu");
    }

    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void showMenu() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            JFrame obj = new JFrame();

            Gameplay gamePlay = new Gameplay();
            frame.setVisible(false);
            obj.setSize(700, 700);
            obj.setTitle("Break The Bricks");
            obj.setResizable(false);
            obj.setVisible(true);
            obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            obj.add(gamePlay);
            obj.setVisible(true);
            frame.setVisible(false);
        }

        if (ae.getSource() == b2) {
            TTT TicTacToe = new TTT();
            frame.setVisible(false);
        }
    }
}