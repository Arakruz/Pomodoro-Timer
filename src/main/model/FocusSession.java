package model;

// Represents a Focus Session having a duration for a Focus, break and rest timers and name of the session
public class FocusSession {
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

    // REQUIRES: timerToChange has to be either one of "short","long" or "focus". Int has to be >= 0
    // MODIFIES: This
    // EFFECTS: changes the value of shortBreak, rest or focus to time based on which timerToChange string wasm use as
    // an input, "break", "rest", "focus" respectively
    public void intSetter(String timerToChange, int time) {
        switch (timerToChange) {
            case "focus":
                this.focus = time;
                break;
            case "break":
                this.shortBreak = time;
                break;
            case "rest":
                this.rest = time;
                break;
        }
    }

    // MODIFIES: This
    // EFFECTS: changes the value of sessionName to name
    public void nameSetter(String newName) {
        this.name = newName;
    }
}