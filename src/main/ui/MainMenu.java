package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Main menu with 3 selectable play options
public class MainMenu extends JFrame {

    @SuppressWarnings("methodlength")
    public MainMenu() {
        super("Fang's Input Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 450, 300);


        ImageIcon img = new ImageIcon("./data/memes.jpg");
        JLabel contentPane = new JLabel(img);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel fitLabel = new JLabel("Fang's Input Test");
        fitLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        fitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(fitLabel);

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new GridLayout(1, 0, 0, 0));

        JButton selectivePlayButton = new JButton("Selective Play");
        selectivePlayButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        selectivePlayButton.setActionCommand("selectiveplay");
        selectivePlayButton.addActionListener(e -> {
            new SelectionMenu();
            dispose();
        });
        panel.add(selectivePlayButton);

        JButton playButton = new JButton("Play All");
        playButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        playButton.setActionCommand("play");
        playButton.addActionListener(e -> runAggregateTest());
        panel.add(playButton);

        JButton scoreButton = new JButton("View Scores");
        scoreButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scoreButton.setActionCommand("scores");
        scoreButton.addActionListener(e -> {
            new ScoreMenu();
            dispose();
        });
        panel.add(scoreButton);

        JLabel creditLabel = new JLabel("By Leomelonseeds");
        creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(creditLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: Runs Click speed, then words per minute, then aim training test.
    //          Extra result menu at the end to show total score.
    private void runAggregateTest() {
        dispose();
        FangInputTest.getMain().setAggregate(true);
        new CpsMenu();
    }
}
