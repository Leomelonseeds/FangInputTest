package model;

import org.json.JSONObject;

// A clicks per second test that has configurable test time. Stores users total inputs and cps score.
public class ClicksPerSecond extends InputTest {

    private int time;
    private int inputs;
    private double cps;

    // REQUIRES: time > 0
    // EFFECTS: Prepares a clicks per second test with the given test time
    public ClicksPerSecond(int time) {
        super(new double[] {18, 14, 10, 8, 5}, "cps");
        this.time = time;
        this.inputs = 0;
        this.cps = 0;
    }

    // MODIFIES: this
    // EFFECTS: Calculates the users clicks per second
    @Override
    public double calculateScore() {
        cps = (double) inputs / time;
        return cps;
    }

    // EFFECTS: Returns a string representation of this test
    @Override
    public String toString() {
        return getDate() + " | Gamemode: Click Speed | CPS: " + cps + " | Rank: " + rank;
    }

    // EFFECTS: Returns a json representation of this test
    @Override
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("name", name);
        result.put("date", getDate());
        result.put("cps", cps);
        result.put("time", time);
        result.put("rank", rank);
        return result;
    }

    public void setInputs(int i) {
        inputs = i;
    }

    public void setTime(int t) {
        time = t;
    }

    public void setCps(double cps) {
        this.cps = cps;
    }

    public int getInputs() {
        return inputs;
    }

    public int getTime() {
        return time;
    }

    public double getCps() {
        return cps;
    }
}
