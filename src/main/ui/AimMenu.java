package ui;

import model.AimTest;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

// Class to run the aim test game
public class AimMenu extends JFrame {

    public static final int MIN_X = 0;
    public static final int MAX_X = 1200;
    public static final int MIN_Y = 0;
    public static final int MAX_Y = 550;

    private AimTest aim;
    private JButton b1;
    private JButton b2;
    private long startTime;
    private int currentAmount;

    @SuppressWarnings("methodlength")
    public AimMenu() {
        super("Mouse Aim Test");

        aim = new AimTest(30);
        currentAmount = 0;

        setMinimumSize(new Dimension(1280, 720));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel aimLabel = new JLabel("Aim Test");
        aimLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        aimLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        contentPane.add(aimLabel);

        JLabel infoLabel = new JLabel("Click the buttons as fast as possible!");
        infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(infoLabel);

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(null);

        b1 = new JButton("!");
        b1.setBounds(getRandomX(), getRandomY(), 50, 50);
        b1.addMouseListener(new MouseListener() {

            // EFFECTS: Process click on mouse press instead of click
            @Override
            public void mousePressed(MouseEvent e) {
                processClick(b1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        panel.add(b1);

        b2 = new JButton("!");
        b2.setBounds(getRandomX(), getRandomY(), 50, 50);
        b2.addMouseListener(new MouseListener() {

            // EFFECTS: Process click on mouse press instead of click
            @Override
            public void mousePressed(MouseEvent e) {
                processClick(b2);
            }

            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        panel.add(b2);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: Picks random number between MIN_X and MAX_X
    private int getRandomX() {
        Random random = new Random();
        return random.nextInt(MAX_X - MIN_X) + MIN_X;
    }

    // EFFECTS: Picks random number between MIN_Y and MAX_Y
    private int getRandomY() {
        Random random = new Random();
        return random.nextInt(MAX_Y - MIN_Y) + MIN_Y;
    }

    // EFFECTS: Spawns the button in a new location. Ends game if maximum amount reached.
    private void processClick(JButton aimButton) {
        if (currentAmount == 0) {
            startTime = System.currentTimeMillis();
        }
        currentAmount++;
        if (currentAmount >= aim.getAmount()) {
            endGame();
            return;
        }
        aimButton.setLocation(getRandomX(), getRandomY());
        validate();
        repaint();
    }

    // EFFECTS: Ends game, displaying results menu and saving score.
    private void endGame() {
        dispose();
        aim.setTimeSpent((System.currentTimeMillis() - startTime) / (double) 1000);

        aim.calculateScore();
        java.util.List<String> results = new ArrayList<>();
        results.add("Time taken: " + aim.getTimeSpent() + " seconds");
        results.add("Buttons pressed: " + aim.getAmount());
        results.add("Buttons per second: " + aim.getBps());
        new ResultMenu(aim, results, null);
    }
}
