package ui;

import model.AimTest;
import model.ClicksPerSecond;
import model.InputTest;
import model.WordsPerMinute;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Displays results after a game ends
public class ResultMenu extends JFrame {

    private String rank;
    private InputTest test;
    private String finalRank;

    // Initializes a result menu with the specified input test, and a list of stats to display
    @SuppressWarnings("methodlength")
    public ResultMenu(InputTest test, List<String> stats, String finalRank) {
        super("Results");

        this.test = test;
        if (finalRank == null) {
            rank = test.calculateRank(test.calculateScore(), test.getScoreCutoffs());
        } else {
            rank = finalRank;
            // Used for null check
            this.finalRank = finalRank;
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel resultLabel = new JLabel("Results");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPane.add(resultLabel);

        JLabel rankLabel = new JLabel("RANK: " + rank);
        rankLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
        rankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(rankLabel);

        for (String s : stats) {
            JLabel statLabel = new JLabel(s);
            statLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPane.add(statLabel);
        }

        JButton doneButton = new JButton("Done");
        doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        doneButton.addActionListener(e -> onDone());
        contentPane.add(doneButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // EFFECTS: Sets actions on done button, allowing aggregate tests to be run. Displays a final
    //          aggregate result when finished with aggregate testing. Adds score to score history
    @SuppressWarnings("methodlength")
    private void onDone() {
        dispose();
        FangInputTest main = FangInputTest.getMain();
        if (finalRank == null) {
            main.getHistory().saveScore(test);
            main.saveScoreHistory();
        }
        if (main.isAggregate()) {
            main.getAggregateResults().add(rank);
            if (test instanceof ClicksPerSecond) {
                new WpmMenu();
            } else if (test instanceof WordsPerMinute) {
                new AimMenu();
            } else if (test instanceof AimTest) {
                // Display final results
                List<String> finalResults = new ArrayList<>();
                finalResults.add("This is your final result. Thanks for playing!");

                // Add dummy input test for final results
                InputTest dummy = new ClicksPerSecond(-1);
                String finalRank = InputTest.averageRank(main.getAggregateResults());
                new ResultMenu(dummy, finalResults, finalRank);

                // Remove aggregate option from main
                main.setAggregate(false);
                main.getAggregateResults().clear();
            }
            return;
        }
        new MainMenu();
    }
}
