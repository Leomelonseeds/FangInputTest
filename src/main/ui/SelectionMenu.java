package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Class to select a specific game to play
public class SelectionMenu extends JFrame {

    @SuppressWarnings("methodlength")
    public SelectionMenu() {
        super("Select A Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel selectLabel = new JLabel("Select a game");
        selectLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        selectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(selectLabel);

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new GridLayout(1, 0, 0, 0));

        JButton cpsButton = new JButton("Clicks Per Second");
        cpsButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        cpsButton.addActionListener(e -> {
            dispose();
            new CpsMenu();
        });
        panel.add(cpsButton);

        JButton wpmButton = new JButton("Words \r\nPer \r\nMinute");
        wpmButton.addActionListener(e -> {
            dispose();
            new WpmMenu();
        });

        wpmButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        panel.add(wpmButton);

        JButton aimButton = new JButton("Mouse Aim");
        aimButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
        aimButton.addActionListener(e -> {
            dispose();
            new AimMenu();
        });
        panel.add(aimButton);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });
        contentPane.add(backButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
