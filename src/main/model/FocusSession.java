package model;

// Represents a Focus Session having a duration for a Short break, Long break, Focus, the number of cycles and name of
// the session
@SuppressWarnings("checkstyle:RightCurly")
public class FocusSession {
    private int shortBreak; // Short break time in minutes
    private int longBreak;  // Long break time in minutes
    private int focusTimer;      //Focus time in minutes
    private String sessionName;    //name of this session

    // REQUIRES: sessionShort, sessionLong and sessionFocus have to be greater than 0
    // EFFECTS: name of session is set as sessionName; sessionShort, sessionLong and sessionFocus are positive integers
    // and set to shortBreak, longBreak and focusTimer, respectively.
    public FocusSession(String sessionName,int sessionFocus, int sessionShort,int sessionLong) {
        //stub
    }

    public int getShortBreak() {
        return shortBreak;
    }

    public int getLongBreak() {
        return longBreak;
    }

    public int getFocusTimer() {
        return focusTimer;
    }

    public String getSessionName() {
        return sessionName;
    }

    // REQUIRES: timerToChange has to be either one of "short","long" or "focus". Int has to be greater than 0
    // MODIFY: FocusSession
    // EFFECTS: changes the value of shortBreak, longBreak or focusTimer to time based on which timerToChange string was
    // use as an input, "short", "long", "focus" respectively
    public FocusSession intSetter(String timerToChange, int time, FocusSession currentSession) {
        return currentSession; //stub
    }

    // MODIFY: FocusSession
    // EFFECTS: changes the value of sessionName to name
    public FocusSession nameSetter(String name, FocusSession currentSession) {
        return currentSession; //stub
    }
}
