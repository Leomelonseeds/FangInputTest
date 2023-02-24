package persistence;

import model.AimTest;
import model.ClicksPerSecond;
import model.ScoreHistory;
import model.WordsPerMinute;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Class to read a score history from a JSON file
// Mostly uses code from CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads score history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ScoreHistory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseScoreHistory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses score history from JSON object and returns it
    private ScoreHistory parseScoreHistory(JSONObject jsonObject) {
        ScoreHistory history = new ScoreHistory();
        addScores(history, jsonObject);
        return history;
    }

    // MODIFIES: history
    // EFFECTS: parses scores from JSON object and adds them to score history
    private void addScores(ScoreHistory history, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("scores");
        for (Object json : jsonArray) {
            JSONObject nextScore = (JSONObject) json;
            addScore(history, nextScore);
        }
    }

    // MODIFIES: history
    // EFFECTS: parses score (an input test) from JSON object and adds it to score history list
    private void addScore(ScoreHistory history, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        switch (name) {
            case "cps":
                ClicksPerSecond cps = new ClicksPerSecond(jsonObject.getInt("time"));
                cps.setDate(jsonObject.getString("date"));
                cps.setRank(jsonObject.getString("rank"));
                cps.setCps(jsonObject.getDouble("cps"));
                history.saveScore(cps);
                break;
            case "wpm":
                WordsPerMinute wpm = new WordsPerMinute();
                wpm.setDate(jsonObject.getString("date"));
                wpm.setRank(jsonObject.getString("rank"));
                wpm.setWpm(jsonObject.getDouble("wpm"));
                wpm.setAccuracy(jsonObject.getDouble("accuracy"));
                history.saveScore(wpm);
                break;
            case "aim":
                saveNewAim(history, jsonObject);
        }
    }

    // EFFECTS: Saves a historical aim test game. Extra method so checkstyle doesn't complain.
    private void saveNewAim(ScoreHistory history, JSONObject jsonObject) {
        AimTest aim = new AimTest(jsonObject.getInt("amount"));
        aim.setDate(jsonObject.getString("date"));
        aim.setRank(jsonObject.getString("rank"));
        aim.setTimeSpent(jsonObject.getDouble("time"));
        aim.setBps(jsonObject.getDouble("bps"));
        history.saveScore(aim);
    }
}
