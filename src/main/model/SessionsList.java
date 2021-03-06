package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a session list storing all the sessions
public class SessionsList implements Writable {
    private List<FocusSession> sessionsList;

    // EFFECTS: constructs a list of FocusSessions
    public SessionsList() {
        this.sessionsList = new ArrayList<>();
    }

    // EFFECTS: returns the size of the sessions list
    public int getSessionSize() {
        return this.sessionsList.size();
    }

    // EFFECTS: returns the FocusSession on given position
    public FocusSession getSession(int position) {
        return this.sessionsList.get(position);
    }

    // EFFECTS: returns the FocusSession object with given name on the list or null if no object exists with that name
    public FocusSession getSessionBasedOnName(String name) {
        for (FocusSession session : sessionsList) {
            if (session.getSessionName().equals(name)) {
                return session;
            }
        }
        return null;
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<FocusSession> getSessions() {
        return Collections.unmodifiableList(sessionsList);
    }

    // REQUIRES: sessionsList needs at least 1 item
    // MODIFIES: this
    // EFFECTS: removes the specified FocusSession from the SessionList
    public void removeSession(FocusSession sessionToRemove) {
        this.sessionsList.remove(sessionToRemove);
    }

    // MODIFY: this
    // EFFECTS: adds a given FocusSession to the list
    public void addSession(FocusSession session) {
        this.sessionsList.add(session);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("focusSessions", sessionToJson());
        return json;
    }

    // EFFECTS: returns sessions in this SessionsList as a JSON array
    private JSONArray sessionToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FocusSession fs : sessionsList) {
            jsonArray.put(fs.toJson());
        }

        return jsonArray;
    }
}