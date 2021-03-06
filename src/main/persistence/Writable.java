package persistence;

import  org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns a JSON object
    JSONObject toJson();
}
