package ui;

import model.FocusSession;
import model.SessionList;
import java.util.Scanner;

// The object that runs the app, reacting to the user input, controlling the list of FocusSessions and printing the ui
public class TimerApp {
    private Scanner input;
    private SessionList sessionsList;

    // EFFECTS: Run the application
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
                chooseScreen(userInput, "welcome");
            }
        }
        System.out.println("\nBye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the SessionList and the input
    private void initializeSessionList() {
        input = new Scanner(System.in);
        sessionsList = new SessionList();
    }

    // MODIFIES: this
    // EFFECTS: sends the input to the correct processing method and screen. This is needed to avoid making multiple
    // runApp's while going back and forward between screens
    private void chooseScreen(String userInput, String screen) {
        switch (screen) {
            case "welcome":
                processWelcomeScreen(userInput);
                break;
            case "selection":
                selectSession();
                break;
        }

    }

    // MODIFIES: this
    // EFFECTS: selects a session in session list and sends it to processSelectionScreen to be processed
    private void selectSession() {
        String userInput;
        FocusSession sessionSelected;

        sessionOutput();
        System.out.println("\ninset the name of the section to be selected or back to go to the previous screen");
        userInput = input.next();
        sessionSelected = sessionsList.getSessionBasedOnName(userInput);

        if (userInput.equals("back")) {
            chooseScreen("null", "welcome");

            // checks if user placed a valid session, if go back to the beginning
        } else if (sessionSelected == null) {
            System.out.println("\nno valid session with given name");
            selectSession();
        } else {
            // Prints out the selectionScreen, get the desired function and pass it to the processSelectionScreen method
            selectionScreen(sessionSelected);
            userInput = input.next();
            processSelectionScreen(userInput, sessionSelected);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs on the welcome screen
    private void processWelcomeScreen(String userInput) {
        switch (userInput) {
            // input coming from the welcoming screen, either adding, selecting, going back, forward or quitting
            case "add":
                addSession();
                chooseScreen("null", "welcome");
                System.out.println("\tSection added!");
                break;

            case "select":
                chooseScreen("null", "selection");
                break;

            case "null": // Needed for chooseScreen method to work properly
                break;

            default:
                System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs on the selection screen
    private void processSelectionScreen(String userInput, FocusSession selectedSession) {
        switch (userInput) {
            // input coming from the welcoming screen, either adding, selecting, going back, forward or quitting
            case "delete":
                sessionsList.removeSession(selectedSession);
                System.out.println("\nSession deleted!");
                break;

            case "modify":
                modifySession(selectedSession);
                break;

            // input coming from the welcoming screen, either adding, selecting, going back, forward or quitting
            case "start":
                startSession(selectedSession);
                break;

            default:
                System.out.println("Selection not valid");
        }
    }

    // EFFECTS: display first welcome message to users, showing current listed Focus Sessions to start, add, modify
    // delete. Max of 5 Sessions shown, with option to show next and previous ones (to be added)
    private void welcomeScreen() {
        sessionOutput();
        System.out.println("\nSelect from:");
        System.out.println("\tadd                   | to add a new Focus Session");
        System.out.println("\tselect                | to select a Focus Session to start, modify or delete");
        System.out.println("\tquit                  | to quit");
    }

    // EFFECTS: Display the possible interactions with the selected section
    private void selectionScreen(FocusSession currentSession) {
        String name = currentSession.getSessionName();
        System.out.println("\nCurrent Session: " + name);
        System.out.println("\nSelect from:");
        System.out.println("\tdelete    | to delete the specified session");
        System.out.println("\tmodify    | to modify the specified session");
        System.out.println("\tstart     | to start the specified session");
        System.out.println("\tback      | goes back to the welcome screen");
    }

    // MODIFIES: this
    // EFFECTS: creates the system output of all sessions on the list with their respective information
    private void sessionOutput() {
        int listSize = sessionsList.getSessionSize();
        FocusSession currentSession;
        String shortText = " seconds  Short Break: ";
        String breakText = " seconds  Long Break: ";

        if (listSize != 0) {
            int i = 0;
            while (i < listSize) {
                currentSession = sessionsList.getSession(i);
                String name = currentSession.getSessionName();
                int focus = currentSession.getFocusSession();
                int shortBreak = currentSession.getShortBreak();
                int rest = currentSession.getLongBreak();

                System.out.println("\nSession name: " + name);
                System.out.println("\tFocus time: " + focus + shortText + shortBreak + breakText + rest + " seconds");
                i++;
            }
        } else {
            System.out.println("\n No sessions created yet");
        }
    }

    // REQUIRES: the second, third and forth inputs must be numbers
    // MODIFIES: this
    // EFFECTS: creates a new session in SessionList based on the users inputs
    private void addSession() {
        String sessionInfoInput;
        String name;
        int focus;
        int shortBreak;
        int longBreak;

        // Grabs all the needed inputs from the user
        System.out.println("Select the session name");
        sessionInfoInput = input.next();
        name = sessionInfoInput;

        System.out.println("Select the duration of the focus section in seconds");
        sessionInfoInput = input.next();
        focus = Integer.parseInt(sessionInfoInput);

        System.out.println("Select the duration of the short break in seconds");
        sessionInfoInput = input.next();
        shortBreak = Integer.parseInt(sessionInfoInput);

        System.out.println("Select the duration of the long break in seconds");
        sessionInfoInput = input.next();
        longBreak = Integer.parseInt(sessionInfoInput);

        // Constructs the new FocusSession and adds it to the list
        sessionsList.addSession(name, focus, shortBreak, longBreak);
    }

    // REQUIRES: SessionList must have at least one item
    // MODIFIES: SessionList
    // EFFECTS: modifies a session from session list
    public void modifySession(FocusSession currentSession) {
        String userInput;
        String fieldToChange;

        System.out.println("\nSelect what you want to modify or back (name, focus, break or rest)");
        userInput = input.next();
        fieldToChange = userInput;
        processModification(fieldToChange, currentSession);
    }

    // REQUIRES: if changing a timer user must input a number
    // MODIFIES: SessionList
    // EFFECTS: process what the user want to change and modifies it on the FocusSession
    private void processModification(String fieldToChange, FocusSession currentSession) {
        String userInput;
        int timeToChange;

        switch (fieldToChange) {
            case "focus":
            case "break":
            case "rest":
                System.out.println("\nSelect a time in seconds");
                userInput = input.next();
                timeToChange = Integer.parseInt(userInput);
                currentSession.intSetter(fieldToChange, timeToChange);
                break;

            case "name":
                System.out.println("\nSelect a new name for this session");
                currentSession.nameSetter(input.next());
                break;

            case "back":
                chooseScreen(null, "selection");
                break;

            default:
                System.out.println("\nInvalid input, please choose from name, focus, break, rest or back");
                modifySession(currentSession);
                break;
        }
    }

    // REQUIRES: SessionList must have at least one item
    // MODIFIES: this
    // EFFECTS: starts a session from session list
    private void startSession(FocusSession currentSession) {
        //TODO find a way to make a timer
//        boolean keepGoing = true;
//        String userInput = null;
//
//        while (keepGoing) {
//            userInput = input.next();
//            userInput = userInput.toLowerCase();
//
//            switch (userInput) {
//                case "quit":
//                    keepGoing = false;
//                    break;
//                case "focus":
//                    currentSession.runSession("focus");
//                    break;
//                case "break":
//                    currentSession.runSession("short");
//                    break;
//                case "rest":
//                    currentSession.runSession("long");
//                    break;
//            }
//
//        }
//        System.out.println("\nGood Job!");
    }
}
