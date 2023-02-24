package ui;

import model.WordsPerMinute;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// Class to run the words per minute
public class WpmMenu extends JFrame implements KeyListener {

    private WordsPerMinute wpm;
    private JTextArea inputField;
    private long startTime;


    @SuppressWarnings("methodlength")
    public WpmMenu() {
        super("Typing Speed Test");

        wpm = new WordsPerMinute();
        wpm.setLine(FangInputTest.getMain().getRandomLine());
        startTime = System.currentTimeMillis() + 4000;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel wpmLabel = new JLabel("Typing Speed Test");
        wpmLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        wpmLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        contentPane.add(wpmLabel);

        JLabel infoLabel = new JLabel("Get ready...");
        infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(infoLabel);

        JLabel lineLabel = new JLabel("<html><p>" + wpm.getLine() + "</p></html>");
        lineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(lineLabel);

        inputField = new JTextArea();
        inputField.setWrapStyleWord(true);
        inputField.setLineWrap(true);
        inputField.setEnabled(false);
        inputField.addKeyListener(this);

        JScrollPane scroll = new JScrollPane(inputField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentPane.add(scroll);

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> endGame());
        contentPane.add(submitButton);

        setLocationRelativeTo(null);
        setVisible(true);

        FangInputTest.initiateCountdown(infoLabel, this);
        new Timer(4000, e -> {
            infoLabel.setText("Type the following text as fast as possible!");
            inputField.setEnabled(true);
            inputField.requestFocusInWindow();
            revalidate();
            repaint();
        }).start();
    }

    // EFFECTS: Ends the game and displays results. Restart if word count not equal.
    public void endGame() {
        dispose();
        wpm.setInput(inputField.getText());
        wpm.setTimeTaken((System.currentTimeMillis() - startTime) / (double) 1000);
        if (!wpm.equalWords()) {
            new WpmErrorMenu();
            return;
        }

        wpm.calculateScore();
        java.util.List<String> results = new ArrayList<>();
        results.add("Words per minute: " + Math.round(wpm.getWpm()));
        results.add("Accuracy: " + (double) Math.round(wpm.getAccuracy() * 100) / 100 + "%");
        results.add("Time taken: " + wpm.getTimeTaken() + " seconds");
        results.add("Words typed: " + wpm.getWordsTyped());
        results.add("Characters typed: " + wpm.getInput().length());
        new ResultMenu(wpm, results, null);
    }

    // EFFECTS: Ends the game when enter is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            e.consume();
            endGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
