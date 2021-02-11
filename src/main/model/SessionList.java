package model;

import java.util.ArrayList;
import java.util.List;

// Represents a session list storing all the sessions
public class SessionList {
    private List<FocusSession> sessionsList;
    private FocusSession genericSession;

    // EFFECTS: constructs a list of FocusSessions
    public SessionList() {
        sessionsList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: returns the size of the sessions list
    public int getSessionSize() {
        return sessionsList.size();
    }

    public FocusSession getSession(int position) {
        return sessionsList.get(position);
    }

    // REQUIRES: sessionsList needs at least 1 item
    // MODIFIES: this
    // EFFECTS: removes the specified session from the session list
    public void removeSession(FocusSession session) {
        sessionsList.remove(session);
    }

    // MODIFY: this
    // EFFECTS: Constructs a new FocusSession with given parameters and adds it to the list
    public void addSession(String name, int focus, int shortBreak, int longBreak) {
        genericSession = new FocusSession(name, focus, shortBreak, longBreak);
        sessionsList.add(genericSession);
    }

    // MODIFIES: This
    // EFFECTS: returns the FocusSession object with given name on the list or null if no object exists with that name
    public FocusSession getSessionBasedOnName(String name) {
        for (FocusSession session : sessionsList) {
            if (session.getSessionName().equals(name)) {
                return session;
            }
        }
        return null;
    }
}