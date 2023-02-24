package ui;

import model.EventLog;
import model.ScoreHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

// Runs the main UI and options for the input tests
// Steals portions of JSON r/w code from CPSC210/JsonSerializationDemo
public class FangInputTest {

    // Singleton input test instance
    private static FangInputTest main = null;

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final Date startDate = new Date(System.currentTimeMillis());
    private static final String JSON_STORE = "./data/scorehistory.json";

    private boolean aggregate;
    private List<String> aggregateResults;
    private ScoreHistory history;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Starts a new input test
    public FangInputTest() throws FileNotFoundException {
        main = this;
        aggregate = false;
        aggregateResults = new ArrayList<>();
        history = new ScoreHistory();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadScoreHistory();
        new MainMenu();

        // Print event log on shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            EventLog.getInstance().forEach(System.out::println);
        }));
    }

    // EFFECTS: Returns the singleton instance of the running program
    public static FangInputTest getMain() {
        return main;
    }

    // EFFECTS: Initiates a countdown with the specified label
    public static void initiateCountdown(JLabel label, JFrame frame) {
        for (int i = 1; i <= 3; i++) {
            int number = 4 - i;
            Timer t = new Timer(i * 1000, e -> {
                label.setText(number + "");
                frame.revalidate();
                frame.repaint();
            });
            t.setRepeats(false);
            t.start();
        }
    }

    // EFFECTS: Gets a random line from the texts.txt file for typing
    public String getRandomLine() {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get("./data/texts.txt"));
            Random random = new Random();
            return lines.get(random.nextInt(lines.size()));
        } catch (IOException e) {
            System.out.println("No available texts to choose from! Using default...");
            return "The quick brown fox jumps over the lazy dog";
        }
    }

    // EFFECTS: saves the score history to file
    public void saveScoreHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();
            System.out.println("Saved the score history to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads score history from file
    private void loadScoreHistory() {
        try {
            history = jsonReader.read();
            System.out.println("Loaded score history from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public ScoreHistory getHistory() {
        return history;
    }

    public boolean isAggregate() {
        return aggregate;
    }

    public void setAggregate(boolean a) {
        aggregate = a;
    }

    public List<String> getAggregateResults() {
        return aggregateResults;
    }
}
