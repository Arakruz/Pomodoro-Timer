package ui;

import model.SessionsList;

import java.util.Scanner;

public class TimerApp {
    private Scanner input;
    private SessionsList sessionsList;

    // EFFECTS: Runt the application
    public TimerApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String userInput = null;

        initializeSessionList();

        while (keepGoing) {
            welcomeScreen();
            userInput = input.next();
            userInput = userInput.toLowerCase();

            if (userInput.equals("quit")) {
                keepGoing = false;
            } else {
                processWelcomeScreen(userInput);
            }
        }
        System.out.println("\nBye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the SessionList and the input
    private void initializeSessionList() {
        input = new Scanner(System.in);
        sessionsList = new SessionsList();
    }

    // EFFECTS: display first welcome message to users, showing current listed Focus Sessions to start, add, modify
    // delete. Max of 5 Sessions shown, with option to show next and previous ones (to be added)
    private void welcomeScreen() {
        // Stub. Add a view for up to 5 focus sessions with all it's details
        System.out.println("\nSelect from:");
        System.out.println("\tadd                   | to add a new Focus Session");
        System.out.println("\tselect                | to select a Focus Session");
        System.out.println("\tback                  | to see previous 5 Focus Sessions");
        System.out.println("\tnext                  | to see next 5 Focus Sessions");
        System.out.println("\tquit                  | to quit");
    }


    // EFFECTS: Display the possible interactions with the selected section
    private void selectionScreen() {
        System.out.println("\nSelect from:");
        System.out.println("\tdelete    | to delete the specified session");
        System.out.println("\tmodify    | to modify the specified session");
        System.out.println("\tstart     | to start the specified session");
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs on the welcome screen
    private void processWelcomeScreen(String userInput) {
        switch (userInput) {
            // input coming from the welcoming screen, either adding, selecting, going back, forward or quitting
            case "add":
                addSessionScreen();
                break;

            case "select":
                selectSession();
                break;

            // input coming from the welcoming screen, either adding, selecting, going back, forward or quitting
            case "back":
                previousSessions();
                break;

            case "next":
                nextSessions();
                break;

            default:
                System.out.println("Selection not valid");
        }
    }

    // MODIFIES: SessionList
    // EFFECTS: creates a new session in SessionList based on the users inputs
    private void addSessionScreen() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: selects a session in session list and process what the user wants to do with that session
    private void selectSession() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: show previous 5 sessions or an error message if none exist
    private void previousSessions() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: show next 5 sessions or an error message if none exist
    private void nextSessions() {
        //stub
    }

    // RESTRAIN: SessionList must have at least one item
    // MODIFIES: SessionList
    // EFFECTS: Removes a session from session list
    private void deleteSession() {
        //stub
    }

    // RESTRAIN: SessionList must have at least one item
    // MODIFIES: SessionList
    // EFFECTS: modifies a session from session list
    private void modifySession() {
        //stub
    }

    // RESTRAIN: SessionList must have at least one item
    // MODIFIES: SessionList
    // EFFECTS: starts a session from session list
    private void startSession() {
        //stub
    }


}
