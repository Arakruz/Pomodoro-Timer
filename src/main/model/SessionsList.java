package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a session list storing all the sessions
public class SessionsList implements Writable {
    private String name;
    private List<FocusSession> sessionsList;

    // EFFECTS: constructs a list of FocusSessions
    public SessionsList(String name) {
        this.name = name;
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

    // REQUIRES: sessionsList needs at least 1 item
    // MODIFIES: this
    // EFFECTS: removes the specified FocusSession from the SessionList
    public void removeSession(FocusSession sessionToRemove) {
        this.sessionsList.remove(sessionToRemove);
    }

    // MODIFY: this
    // EFFECTS: Constructs a new FocusSession with given parameters and adds it to the list
    public void addNewSession(String name, int focus, int shortBreak, int rest) {
        FocusSession genericSession;

        genericSession = new FocusSession(name, focus, shortBreak, rest);
        this.sessionsList.add(genericSession);
    }

    // MODIFY: this
    // EFFECTS: adds a given FocusSession to the list
    public void addSession(FocusSession session) {
        this.sessionsList.add(session);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
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