package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordsPerMinuteTest {

    WordsPerMinute wpm;

    @BeforeEach
    void setupTest() {
        wpm = new WordsPerMinute();
    }

    @Test
    void testWordsTyped() {
        wpm.setInput("Hello there");
        assertEquals(2, wpm.getWordsTyped());
    }

    @Test
    void testEqualWords() {
        wpm.setInput("a a a a");
        wpm.setLine("a b b a");
        assertTrue(wpm.equalWords());

        wpm.setLine("a a a");
        assertFalse(wpm.equalWords());
    }

    @Test
    void testScoreCalculation() {
        wpm.setLine("a b c d e f g h i jk");
        wpm.setInput("a b c d e f g h i jk");
        wpm.setTimeTaken(60);
        assertEquals(10, wpm.calculateScore());

        wpm.setInput("a b c d e f g h i jkl");
        assertEquals(9.5, wpm.calculateScore());
        assertEquals(95, wpm.getAccuracy());

        wpm.setInput("a b c d e f g h j k");
        assertEquals(8.5, wpm.calculateScore());

        assertEquals(10, wpm.getWpm());
    }
}
