package ui;

import model.ScoreHistory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Class to view previously saved scores
public class ScoreMenu extends JFrame {

    @SuppressWarnings("methodlength")
    public ScoreMenu() {
        super("Score History");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel scoreLabel = new JLabel("Score History");
        scoreLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(scoreLabel, BorderLayout.NORTH);

        JButton backButton = new JButton("Back");
        backButton.setActionCommand("back");
        backButton.addActionListener(e -> {
            new MainMenu();
            dispose();
        });
        contentPane.add(backButton, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Get score history and add labels
        ScoreHistory history = FangInputTest.getMain().getHistory();
        for (String line : history.displayScores(1000)) {
            panel.add(new JLabel(line));
        }

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
