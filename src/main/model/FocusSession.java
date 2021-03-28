package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Focus Session having a duration for a Focus, break and rest timers and name of the session
public class FocusSession implements Writable {
    private String name;         // name of this session
    private int focus;           // Focus time in seconds
    private int shortBreak;      // break time in seconds
    private int rest;            // rest time in seconds

    // REQUIRES: sessionFocus, sessionBreak and sessionRest have to be >= 0
    // EFFECTS: name of session is set as sessionName; sessionBreak, sessionRest and sessionFocus are positive integers
    // and set to shortBreak, longBreak and focusTimer, respectively.
    public FocusSession(String sessionName, int sessionFocus, int sessionBreak, int sessionRest) {
        this.name = sessionName;
        this.focus = sessionFocus;
        this.shortBreak = sessionBreak;
        this.rest = sessionRest;
    }

    public int getSessionBreak() {
        return this.shortBreak;
    }

    public int getSessionRest() {
        return this.rest;
    }

    public int getSessionFocus() {
        return this.focus;
    }

    public String getSessionName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name + " | " + "Focus " + tsHelper(focus) + "Break " + tsHelper(shortBreak) + "Rest " + tsHelper(rest);
    }

    // EFFECTS; creates a string from the given int and adds the structure needed to represent the FocusSession as a
    //          single string
    public String tsHelper(int i) {
        return Integer.toString(i) + " minutes | ";
    }

    // REQUIRES: timerToChange has to be either one of "short","long" or "focus". Int has to be >= 0
    // MODIFIES: This
    // EFFECTS: changes the value of shortBreak, rest or focus to time based on which timerToChange string wasm use as
    //          an input, "break", "rest", "focus" respectively
    public void intSetter(String timerToChange, int time) {
        if (timerToChange.equals("focus")) {
            this.focus = time;
        } else if (timerToChange.equals("break")) {
            this.shortBreak = time;
        } else {
            this.rest = time;
        }
    }

    // MODIFIES: This
    // EFFECTS: changes the value of sessionName to name
    public void nameSetter(String newName) {
        this.name = newName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("focus", focus);
        json.put("shortBreak", shortBreak);
        json.put("rest", rest);
        return json;
    }
}