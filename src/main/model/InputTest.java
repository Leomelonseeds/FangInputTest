package model;

import persistence.Writable;
import ui.FangInputTest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

// Represents any type of input test, with a method to calculate the score of the test.
// Stores the available ranks and score cutoffs for each rank for the test.
public abstract class InputTest implements Writable {

    public static final String[] ranks = {"S+", "S", "A", "B", "C", "D"};
    protected double[] scoreCutoffs;
    protected String rank;
    protected String name;
    private Date date;

    // REQUIRES: scoreCutoffs has exactly *ranks.length - 1* numbers in decreasing order
    // EFFECTS: Stores score cutoffs for later use, and a rank to access.
    public InputTest(double[] scoreCutoffs, String name) {
        this.scoreCutoffs = scoreCutoffs;
        this.rank = "D";
        this.name = name;
        this.date = new Date(System.currentTimeMillis());
    }

    // REQUIRES: cutoffs has exactly *ranks.length - 1* numbers in decreasing order
    // EFFECTS: Calculates a rank based off *ranks* with the given score and cutoffs.
    //          Specifically, if the given score is larger than the nth cutoff, *ranks[n]* will be given
    //          If the score is less than all listed cutoffs, then the last rank will be given
    public String calculateRank(double score, double[] cutoffs) {
        for (int i = 0; i < ranks.length - 1; i++) {
            if (score >= cutoffs[i]) {
                rank = ranks[i];
                return rank;
            }
        }
        rank = ranks[ranks.length - 1];
        return rank;
    }

    // REQUIRES: all strings in results must be contained in ranks
    // EFFECTS: gives an average rank given a list of results from different tests
    public static String averageRank(List<String> results) {
        int aggregate = 0;
        for (String s : results) {
            for (int i = 0; i < ranks.length; i++) {
                if (ranks[i].equals(s)) {
                    aggregate += i;
                    break;
                }
            }
        }
        int averageIndex = (int) Math.ceil((double) aggregate / results.size());
        return ranks[averageIndex];
    }

    public String getRank() {
        return rank;
    }

    // REQUIRES: rank must be contained in [ranks]
    public void setRank(String rank) {
        this.rank = rank;
    }

    public double[] getScoreCutoffs() {
        return scoreCutoffs;
    }

    // REQUIRES: date must be appropriately formatted
    public void setDate(String date) {
        try {
            this.date = FangInputTest.dateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println("Date not formatted correctly.");
        }
    }

    // EFFECTS: Returns the date as a simple date format
    public String getDate() {
        return FangInputTest.dateFormat.format(date);
    }

    public abstract double calculateScore();

    public abstract String toString();
}
