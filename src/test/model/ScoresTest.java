package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.FangInputTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoresTest {

    String exampleDate;
    ScoreHistory history;
    ClicksPerSecond cps;
    WordsPerMinute wpm;
    AimTest aim;

    @BeforeEach
    void init() {
        System.out.println(FangInputTest.startDate);
        exampleDate = "2050-10-31 13:15:49";
        cps = new ClicksPerSecond(5);
        cps.setDate("no good");
        cps.setDate("no good");
        cps.setDate(exampleDate);
        cps.setCps(18);
        cps.setRank("S");
        wpm = new WordsPerMinute();
        wpm.setDate(exampleDate);
        wpm.setWpm(100);
        wpm.setRank("A");
        wpm.setAccuracy(99.99);
        aim = new AimTest(100);
        aim.setDate(exampleDate);
        aim.setTimeSpent(40);
        aim.setBps(2.5);
        aim.setRank("S+");

        history = new ScoreHistory();
        history.saveScore(cps);
        history.saveScore(wpm);
        history.saveScore(aim);
    }

    @Test
    void testScoreList() {
        List<String> expected = new ArrayList<>();
        String aim = exampleDate + " | Gamemode: Aim | Buttons per second: 2.5 | Rank: S+";
        String wpm = exampleDate + " | Gamemode: Typing Speed | WPM: 100 | Accuracy: 99.99% | Rank: A";
        String cps = exampleDate + " | Gamemode: Click Speed | CPS: 18.0 | Rank: S";
        expected.add(aim);
        expected.add(wpm);
        expected.add(cps);

        assertEquals(expected, history.displayScores(3));
        assertEquals(expected, history.displayScores(4));

        List<String> testEvents = new ArrayList<>();
        EventLog el = EventLog.getInstance();
        for (Event e : el) {
            testEvents.add(e.getDescription());
        }

        assertTrue(testEvents.contains(aim));
        assertTrue(testEvents.contains(wpm));
        assertTrue(testEvents.contains(cps));
    }
}
