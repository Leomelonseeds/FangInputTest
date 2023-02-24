package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    // Shamlessly stealing more code from CPSC210/JsonSerializationDemo
    @Test
    void testInvalidWrite() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testInvalidRead() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testScoreHistorytoJson() {
        try {
            ScoreHistory history = getExampleHistory();
            JsonWriter writer = new JsonWriter("./data/jsonTest.json");
            writer.open();
            writer.write(history);
            writer.close();

            JsonReader reader = new JsonReader("./data/jsonTest.json");
            history = reader.read();
            List<InputTest> tests = history.getHistory();
            ClicksPerSecond cps = (ClicksPerSecond) tests.remove(0);
            WordsPerMinute wpm = (WordsPerMinute) tests.remove(0);
            AimTest aim = (AimTest) tests.remove(0);
            String expectedDate = "2022-10-31 13:15:49";

            assertEquals(18, cps.getCps());
            assertEquals("S", cps.getRank());
            assertEquals(5, cps.getTime());
            assertEquals(expectedDate, cps.getDate());

            assertEquals(100, wpm.getWpm());
            assertEquals("A", wpm.getRank());
            assertEquals(99.99, wpm.getAccuracy());
            assertEquals(expectedDate, cps.getDate());

            assertEquals(100, aim.getAmount());
            assertEquals("S+", aim.getRank());
            assertEquals(40, aim.getTimeSpent());
            assertEquals(2.5, aim.getBps());
            assertEquals(expectedDate, aim.getDate());
        } catch (IOException e) {
            fail("Something went wrong...");
        }
    }

    private ScoreHistory getExampleHistory() {
        String exampleDate = "2022-10-31 13:15:49";
        ClicksPerSecond cps = new ClicksPerSecond(5);
        cps.setDate(exampleDate);
        cps.setCps(18);
        cps.setRank("S");
        WordsPerMinute wpm = new WordsPerMinute();
        wpm.setDate(exampleDate);
        wpm.setWpm(100);
        wpm.setRank("A");
        wpm.setAccuracy(99.99);
        AimTest aim = new AimTest(100);
        aim.setDate(exampleDate);
        aim.setTimeSpent(40);
        aim.setRank("S+");
        aim.setBps(2.5);

        ScoreHistory history = new ScoreHistory();
        history.saveScore(cps);
        history.saveScore(wpm);
        history.saveScore(aim);
        return history;
    }
}
