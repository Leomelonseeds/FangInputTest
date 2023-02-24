package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTestTest {
    @Test
    void testAverageScore() {
        List<String> testResults = new ArrayList<>();
        testResults.add("A");
        testResults.add("B");
        assertEquals("B", InputTest.averageRank(testResults));

        testResults.add("C");
        assertEquals("B", InputTest.averageRank(testResults));
    }

    @Test
    void testRankCalculation() {
        InputTest cps = new ClicksPerSecond(5);
        double[] cutoffs = {50, 40, 30, 20, 10};
        assertEquals("S", cps.calculateRank(45, cutoffs));
        assertEquals("D", cps.calculateRank(5, cutoffs));

        assertEquals("D", cps.getRank());
    }
}
