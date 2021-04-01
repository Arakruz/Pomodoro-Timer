package model;

import model.exceptions.SmallerThanOneException;
import org.json.JSONObject;
import persistence.Writable;

// Represents a Focus Session having a duration for a Focus, break and rest timers and name of the session
public class FocusSession implements Writable {
    private String name;         // name of this session
    private int focus;           // Focus time in minutes
    private int shortBreak;      // break time in minutes
    private int rest;            // rest time in minutes

    // Represents the possible ints that can be changed on a focus session
    public enum PossibleInt {
        FOCUS, BREAK, REST
    }

    // EFFECTS: name of session is set as sessionName; sessionBreak, rest and focus are positive integers
    // and set to shortBreak, longBreak and focusTimer, respectively.
    public FocusSession(String sessionName, int focus, int sessionBreak, int rest) throws SmallerThanOneException {
        if (rest <= 0 || sessionBreak <= 0 || focus <= 0) {
            throw new SmallerThanOneException("Please input a number greater than zero for the timers");
        } else {
            this.name = sessionName;
            this.focus = focus;
            this.shortBreak = sessionBreak;
            this.rest = rest;
        }
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

    // EFFECTS: returns a string with the name, focus timer, break timer, rest timer
    @Override
    public String toString() {
        return name + " | " + "Focus " + tsHelper(focus) + "Break " + tsHelper(shortBreak) + "Rest " + tsHelper(rest);
    }

    // EFFECTS: creates a string from the given int and adds the structure needed to represent the FocusSession as a
    //          single string
    public String tsHelper(int i) {
        return Integer.toString(i) + " minutes | ";
    }

    // MODIFIES: This
    // EFFECTS: changes the value of shortBreak, rest or focus to time based on which timerToChange string wasm use as
    //          an input, "break", "rest", "focus" respectively
    public void intSetter(PossibleInt intToChange, int time) throws SmallerThanOneException {
        if (time <= 0) {
            throw new SmallerThanOneException("Please input a number greater than zero for the timers");
        } else {
            if (intToChange == PossibleInt.FOCUS) {
                this.focus = time;
            } else if (intToChange == PossibleInt.BREAK) {
                this.shortBreak = time;
            } else {
                this.rest = time;
            }
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