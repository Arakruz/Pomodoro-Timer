package ui;

import model.FocusSession;
import model.SessionsList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Timer application
public class TimerApp {
    private static final String JSON_STORE = "./data/SessionsList.json";
    private Scanner input;
    private SessionsList sessionsList;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: Run the timer application
    public TimerApp() {
        this.input = new Scanner(System.in);
        this.sessionsList = new SessionsList();
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs and allows quit to be called any time to close the application
    private void runApp() {
        boolean keepGoing = true;
        String userInput = null;
        this.input = new Scanner(System.in);

//        initializer();

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

//    // MODIFIES: this
//    // EFFECTS: initializes the SessionList and the input
//    private void initializer() {
//        this.input = new Scanner(System.in);
//        this.sessionsList = new SessionsList();
//    }

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
        System.out.println("\nInsert the name of the section to be selected or back to go to the previous screen");
        userInput = input.next();
        sessionSelected = sessionsList.getSessionBasedOnName(userInput);

        if (userInput.equals("back")) {
            chooseScreen("null", "welcome");

        } else if (sessionSelected == null) {
            // checks if user placed a valid session, if not go back to the beginning
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
        // input coming from the welcoming screen, either adding, selecting, quitting (handled by runApp)
        switch (userInput) {
            case "add":
                addSession();
                chooseScreen("null", "welcome");
                System.out.println("\tSection added!");
                break;

            case "select":
                chooseScreen("null", "selection");
                break;

            case "null": // For runApp to check if the user wants to quit and at the same time use that input on the
                // welcome screen it needs to pass it as a parameter for this function. When going back and
                // forward between screens this is not needed, so null is used. Can be used later too for other
                // functionalities.
                break;
            case "save":
                saveList();
                break;
            case "load":
                loadList();
                break;
            default:
                System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs on the selection screen
    private void processSelectionScreen(String userInput, FocusSession selectedSession) {
        switch (userInput) {
            // input coming from the welcoming screen, either adding, selecting or quitting (handled by runApp)
            case "delete":
                sessionsList.removeSession(selectedSession);
                System.out.println("\nSession deleted!");
                break;

            case "modify":
                modifySession(selectedSession);
                break;

            case "start":
                startSession(selectedSession);
                break;

            default:
                System.out.println("Selection not valid");
        }
    }

    // EFFECTS: display first welcome message to users, showing current listed FocusSessions to start, add, modify or
    // delete.
    private void welcomeScreen() {
        sessionOutput();
        System.out.println("\nSelect from:");
        System.out.println("\tadd                   | to add a new Session");
        System.out.println("\tselect                | to select a Session to start, modify or delete");
        System.out.println("\tsave                  | to save the current Session to file");
        System.out.println("\tload                  | to save the current Session from file");
        System.out.println("\tquit                  | can be called in any screen to quit the application");
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
        System.out.println("\tquit      | quits the application");
    }

    // MODIFIES: this
    // EFFECTS: creates the system output of all sessions on the list with their respective information for the user to
    // see
    private void sessionOutput() {
        int listSize = sessionsList.getSessionSize();
        FocusSession currentSession;
        String breakText = " seconds  Break: ";
        String restText = " seconds  Rest: ";

        if (listSize != 0) {
            int i = 0;
            while (i < listSize) {
                currentSession = sessionsList.getSession(i);
                String name = currentSession.getSessionName();
                int focus = currentSession.getSessionFocus();
                int shortBreak = currentSession.getSessionBreak();
                int rest = currentSession.getSessionRest();

                System.out.println("\nSession name: " + name);
                System.out.println("\tFocus time: " + focus + breakText + shortBreak + restText + rest + " seconds");
                i++;
            }
        } else {
            System.out.println("\n No session created yet");
        }
    }

    // REQUIRES: the second, third and forth inputs must be positive numbers
    // MODIFIES: SessionsList
    // EFFECTS: creates a new FocusSession in SessionsList based on the users inputs
    private void addSession() {
        String sessionInfoInput;
        String name;
        int focus;
        int shortBreak;
        int rest;

        // Grabs all the needed inputs from the user
        System.out.println("Select the session name");
        sessionInfoInput = input.next();
        name = sessionInfoInput;

        System.out.println("Select the duration of the focus time in seconds");
        sessionInfoInput = input.next();
        focus = Integer.parseInt(sessionInfoInput);

        System.out.println("Select the duration of the break time in seconds");
        sessionInfoInput = input.next();
        shortBreak = Integer.parseInt(sessionInfoInput);

        System.out.println("Select the duration of the rest time in seconds");
        sessionInfoInput = input.next();
        rest = Integer.parseInt(sessionInfoInput);

        // Constructs the new FocusSession and adds it to the list
        this.sessionsList.addSession(new FocusSession(name, focus, shortBreak, rest));
    }

    // MODIFIES: FocusSession
    // EFFECTS: modifies a FocusSession from sessionsList based on given input
    public void modifySession(FocusSession currentSession) {
        String userInput;
        String fieldToChange;

        System.out.println("\nSelect what you want to modify (name, focus, break or rest) or back");
        userInput = input.next();
        fieldToChange = userInput;
        processModification(fieldToChange, currentSession);
    }

    // REQUIRES: if changing a timer user must input a number
    // MODIFIES: FocusSession
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

    // MODIFIES: this
    // EFFECTS: starts a session from session list (currently only printing that it will added in the future)
    private void startSession(FocusSession currentSession) {
        System.out.println("Not yet implemented due to the functionalities of the terminal, ");
        System.out.println("to be added with the GUI. This will run the timer for the session");
    }

    // EFFECTS: saves the SessionsList to file
    private void saveList() {
        try {
            jsonWriter.open();
            jsonWriter.write(sessionsList);
            jsonWriter.close();
            System.out.println("Saved SessionsList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads SessionsList from file
    private void loadList() {
        try {
            sessionsList = jsonReader.read();
            System.out.println("Loaded SessionsList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
