package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.FangInputTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Class to store the history of all scores
public class ScoreHistory implements Writable {

    List<InputTest> history;

    // EFFECTS: Initializes a new list to store the scores
    public ScoreHistory() {
        history = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a new score to the list, and records the event if it was after the time program was started.
    public void saveScore(InputTest test) {
        history.add(test);

        try {
            Date testDate = FangInputTest.dateFormat.parse(test.getDate());
            if (testDate.after(FangInputTest.startDate)) {
                Event e = new Event(test.toString());
                EventLog.getInstance().logEvent(e);
            }
        } catch (ParseException e) { /* Literally Impossible */ }
    }

    // REQUIRES: amount > 0
    // EFFECTS: Returns the last <amount> stored scores to the player
    public List<String> displayScores(int amount) {
        List<String> results = new ArrayList<>();
        for (int i = history.size() - 1; i >= 0; i--) {
            if (amount <= 0) {
                break;
            }
            results.add(history.get(i).toString());
            amount--;
        }
        return results;
    }

    public List<InputTest> getHistory() {
        return history;
    }

    // EFFECTS: Returns a JSON representation of the score history
    @Override
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        JSONArray scores = new JSONArray();

        for (InputTest test : history) {
            scores.put(test.toJson());
        }

        result.put("scores", scores);
        return result;
    }
}
