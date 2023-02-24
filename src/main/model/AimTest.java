package model;

import org.json.JSONObject;

// Tests the user's mouse aim
public class AimTest extends InputTest {

    private int amount;
    private double timeSpent;
    private double bps;

    // EFFECTS: Initializes a new aim test instance with the specified required clicks
    public AimTest(int amount) {
        super(new double[] {2.5, 2, 1.5, 1, 0.5}, "aim");
        this.amount = amount;
        this.timeSpent = 0;
        this.bps = 0;
    }

    // REQUIRES: timeSpent must be already set using System.getCurrentTimeMillis()
    // EFFECTS: Calculates and returns score based on amount of buttons clicked per second
    @Override
    public double calculateScore() {
        bps = (double) Math.round((amount / timeSpent) * 100) / 100;
        return bps;
    }

    // EFFECTS: Returns a string representation of this test
    @Override
    public String toString() {
        return getDate() + " | Gamemode: Aim | Buttons per second: " + bps + " | Rank: " + rank;
    }

    @Override
    public JSONObject toJson() {
        JSONObject result = new JSONObject();
        result.put("name", name);
        result.put("date", getDate());
        result.put("amount", amount);
        result.put("time", timeSpent);
        result.put("bps", bps);
        result.put("rank", rank);
        return result;
    }

    public int getAmount() {
        return amount;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public double getBps() {
        return bps;
    }

    public void setBps(double bps) {
        this.bps = bps;
    }
}
