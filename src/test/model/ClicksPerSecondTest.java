package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClicksPerSecondTest {
    @Test
    void testScoreCalculator() {
        ClicksPerSecond cps = new ClicksPerSecond(5);
        cps.setTime(5);
        cps.setInputs(100);
        assertEquals(20, cps.calculateScore());
        assertEquals(20, cps.getCps());
    }
}