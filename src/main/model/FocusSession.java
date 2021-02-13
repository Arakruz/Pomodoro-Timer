package model;

import javax.swing.*;

// Represents a Focus Session having a duration for a Short break, Long break, Focus, the number of cycles and name of
// the session
public class FocusSession {
    private String name;         // name of this seconds
    private int focusSession;    // Focus time in seconds
    private int shortBreak;      // Short break time in seconds
    private int longBreak;       // Long break time in seconds
    private int numberOfFocus;   // Keeps track of how many focus sessions have been done
    // Timer timer;                // he timer that controls the countdown

    // REQUIRES: sessionShort, sessionLong and sessionFocus have to be greater than 0
    // EFFECTS: name of session is set as sessionName; sessionShort, sessionLong and sessionFocus are positive integers
    // and set to shortBreak, longBreak and focusTimer, respectively.
    public FocusSession(String sessionName, int sessionFocus, int sessionShort, int sessionLong) {
        name = sessionName;
        focusSession = sessionFocus;
        shortBreak = sessionShort;
        longBreak = sessionLong;
        numberOfFocus = 0;
    }

    public int getShortBreak() {
        return shortBreak;
    }

    public int getLongBreak() {
        return longBreak;
    }

    public int getFocusSession() {
        return focusSession;
    }

    public String getSessionName() {
        return name;
    }

    // REQUIRES: timerToChange has to be either one of "short","long" or "focus". Int has to be greater than 0
    // MODIFIES: This
    // EFFECTS: changes the value of shortBreak, longBreak or focusTimer to time based on which timerToChange string was
    // use as an input, "break", "rest", "focus" respectively
    public void intSetter(String timerToChange, int time) {
        switch (timerToChange) {
            case "focus":
                this.focusSession = time;
                break;
            case "break":
                this.shortBreak = time;
                break;
            case "rest":
                this.longBreak = time;
                break;
        }
    }

    // MODIFIES: This
    // EFFECTS: changes the value of sessionName to name
    public void nameSetter(String newName) {
        this.name = newName;
    }


    // Need to look into how to make a timer properly
//    // MODIFIES: this
//    // EFFECTS: prints the timer on the console with what part of the session it's in (time currently in seconds)
//    public void runSession(String currentSection) {
//        switch (currentSection) {
//            case "focus":
//                runFocus();
//                break;
//            case "break":
//                for (int i = shortBreak; i > 0; i--) {
//                    System.out.println("\nShort Break " + i);
//                }
//                System.out.println("\nInput focus to start your focus session!");
//                break;
//            case "rest":
//                for (int i = longBreak; i > 0; i--) {
//                    System.out.println("\nLong Break " + i);
//                }
//                System.out.println("\nInput focus to start your focus session!");
//                break;
//            default:
//                System.out.println("\nInvalid input");
//        }
//    }
//
//    public void runFocus() {
//        if (numberOfFocus < 4) {
//            for (int i = focusSession; i > 0; i--) {
//                System.out.println("\nFocus Session " + i);
//            }
//            numberOfFocus++;
//            System.out.println("\nInput break to start your break!");
//        } else {
//            for (int i = focusSession; i > 0; i--) {
//                System.out.println("\nFocus Session " + i);
//            }
//            numberOfFocus = 0;
//            System.out.println("\nInput rest to start your long break!");
//        }
//    }
}
