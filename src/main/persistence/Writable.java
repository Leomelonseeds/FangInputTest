package persistence;

import org.json.JSONObject;

// Represents a class that can be written into a JSONObject
// Taken from CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as a JSONObject
    JSONObject toJson();
}
