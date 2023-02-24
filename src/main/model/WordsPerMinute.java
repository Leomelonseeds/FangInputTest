package model;

import org.json.JSONObject;

// A words per minute test that stores various result stats
public class WordsPerMinute extends InputTest {

    private String line;
    private String input;
    private double timeTaken;
    private double wpm;
    private double accuracy;

    // EFFECTS: Initializes a words per minute test
    public WordsPerMinute() {
        super(new double[] {120, 100, 85, 60, 40}, "wpm");
        this.line = "";
        this.input = "";
        this.timeTaken = 0;
        this.wpm = 0;
        this.accuracy = 100;
    }

    // EFFECTS: Returns true of line and input have the same amount of words
    public boolean equalWords() {
        int lineCount = line.split(" ").length;
        return lineCount == getWordsTyped();
    }

    // EFFECTS: Returns the amount of words in the input text
    public int getWordsTyped() {
        return input.split(" ").length;
    }

    // MODIFIES: this
    // EFFECTS: Calculates the following:
    //          Words per minute by dividing word count by time taken in minutes
    //          Accuracy by comparing each word and subtracting a percentage for each non-similar character
    //          A total score by multiplying words per minute by accuracy
    @Override
    public double calculateScore() {
        double minutesTaken = timeTaken / 60;
        wpm = getWordsTyped() / minutesTaken;

        int mistakes = 0;
        double percentPenalty = (double) 100 / line.length();
        String[] lineWords = line.split(" ");
        String[] typedWords = input.split(" ");
        for (int i = 0; i < lineWords.length; i++) {
            String lineWord = lineWords[i];
            String typedWord = typedWords[i];
            mistakes += Math.abs(lineWord.length() - typedWord.length());
            int shortestWord = Math.min(lineWord.length(), typedWord.length());
            for (int j = 0; j < shortestWord; j++) {
                if (lineWord.charAt(j) != typedWord.charAt(j)) {
                    mistakes++;
                }
            }
        }
        accuracy = Math.max(100 - (mistakes * percentPenalty), 0);
        return wpm * (accuracy / 100);
    }

    // EFFECTS: Return a string representation of the results of this test
    @Override
    public String toString() {
        return getDate() + " | Gamemode: Typing Speed | WPM: " + (int) wpm + " | Accuracy: "
                + (double) Math.round(accuracy * 100) / 100 + "% | Rank: " + rank;
    }

    // EFFECTS: Return a json representation of this test
    @Override
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("name", name);
        result.put("date", getDate());
        result.put("wpm", wpm);
        result.put("accuracy", accuracy);
        result.put("rank", rank);
        return result;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setTimeTaken(double t) {
        timeTaken = t;
    }

    public void setWpm(double w) {
        wpm = w;
    }

    public void setAccuracy(double a) {
        this.accuracy = a;
    }

    public double getWpm() {
        return wpm;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public String getInput() {
        return input;
    }

    public String getLine() {
        return line;
    }
}
