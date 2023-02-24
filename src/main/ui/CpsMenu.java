package ui;

import model.ClicksPerSecond;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

// Class to run the clicks per second game
public class CpsMenu extends JFrame {

    private ClicksPerSecond cps;

    @SuppressWarnings("methodlength")
    public CpsMenu() {
        super("Clicking Speed Test");

        int time = 5;
        cps = new ClicksPerSecond(time);
        long finishTime = System.currentTimeMillis() + time * 1000 + 4000;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel cpsLabel = new JLabel("Click Speed Test");
        cpsLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        cpsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(cpsLabel);

        JLabel infoLabel = new JLabel("Get ready...");
        infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(infoLabel);

        JPanel cpsPanel = new JPanel();
        contentPane.add(cpsPanel);
        cpsPanel.setLayout(new BorderLayout(0, 0));

        JLabel clickInfoLabel = new JLabel("Clicks: 0");
        clickInfoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(clickInfoLabel);

        JButton cpsButton = new JButton("CLICK ME");
        cpsButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
        cpsButton.addActionListener(e -> {
            if (System.currentTimeMillis() > finishTime) {
                endGame();
                return;
            }
            int currentInputs = cps.getInputs() + 1;
            cps.setInputs(currentInputs);
            clickInfoLabel.setText("Clicks: " + currentInputs);
        });

        setLocationRelativeTo(null);
        setVisible(true);

        FangInputTest.initiateCountdown(infoLabel, this);
        new Timer(4000, e -> {
            infoLabel.setText("Click the button as many times as possible!");
            cpsPanel.add(cpsButton, BorderLayout.CENTER);
            revalidate();
            repaint();
        }).start();
    }

    // EFFECTS: Ends the game and displays results
    public void endGame() {
        cps.calculateScore();
        java.util.List<String> results = new ArrayList<>();
        results.add("Total clicks: " + cps.getInputs());
        results.add("Clicks per second: " + cps.getCps());
        new ResultMenu(cps, results, null);
        dispose();
    }
}
