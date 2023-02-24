package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AimTestTest {
    @Test
    void testScoreCalculator() {
        AimTest aim = new AimTest(100);
        aim.setTimeSpent(40);
        assertEquals(2.5, aim.calculateScore());
        assertEquals(2.5, aim.getBps());
        assertEquals(40, aim.getTimeSpent());
        assertEquals(100, aim.getAmount());
    }
}