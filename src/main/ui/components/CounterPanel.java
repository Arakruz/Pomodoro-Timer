package ui.components;

import model.FocusSession;
import ui.Editor;
import ui.components.buttons.StartButton;
import ui.components.buttons.StopButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

// Represents the CounterPanel that allows the user to run a counter for their respective timers
public class CounterPanel extends JPanel {
    // Font, color, format and GridBagConstraints that define how the CounterPanel will look. Also the editor needed to
    // get some of this information
    private static final Font TIMER_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 100);
    private static final Font NAME_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 60);
    private final DecimalFormat formatter = new DecimalFormat("00");
    private final GridBagConstraints gbc;
    private final Editor editor;
    private Color background;
    private Color foreground;
    // JLabels that will be shown to the user with the counter and it's name
    private JLabel counterName;
    private JLabel counterTimer;
    // Necessary fields that are used to make the logic behind the counter work
    private FocusSession currentSession;
    private PossibleTimers currentTimer;
    private int cycles;
    private int minutes;
    private int seconds;
    private int savedMinutes;
    private int savedSeconds;
    private Timer timer;
    private TimerController timerController;
    private StartButton startButton;
    private StopButton stopButton;

    // All possible states for the counter
    public enum TimerController {
        RUNNING, STOPPED, FINISHED, STOP
    }

    // All possible times of a focus session
    public enum PossibleTimers {
        FOCUS, BREAK, REST
    }

    public CounterPanel(Editor editor) {
        this.editor = editor;
        this.timerController = TimerController.FINISHED;
        this.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        currentSessionUpdater();
        initializeFields();
        initializeCounterLabels();
        counterUpdater();
    }

    // MODIFIES: this
    // EFFECTS:  updates the current session
    public void currentSessionUpdater() {
        this.currentSession = editor.getCurrentSession();
    }

    // MODIFIES: this
    // EFFECTS:  initializes cycles as 0, the background and foreground as the one being used in the editor, the current
    //           timer as focus, the save/load buttons and labels
    private void initializeFields() {
        this.cycles = 0;
        this.background = editor.getBackground();
        this.foreground = editor.getForeground();
        this.currentTimer = PossibleTimers.FOCUS;
        this.counterName = new JLabel();
        this.counterTimer = new JLabel();
        this.startButton = new StartButton(editor, this);
        this.stopButton = new StopButton(editor, this);
    }

    // MODIFIES: this
    // EFFECTS:  initializes the counter labels that represent the name and the actual counter and set's how it will
    //           look
    private void initializeCounterLabels() {
        counterUpdater();
        this.gbc.gridx = 3;
        this.gbc.gridy = 0;
        this.add(counterName, gbc);
        this.gbc.gridx = 3;
        this.gbc.gridy = 3;
        this.add(counterTimer, gbc);
        this.counterName.setBackground(background);
        this.counterName.setForeground(foreground);
        this.counterName.setFont(NAME_FONT);
        this.counterTimer.setBackground(background);
        this.counterTimer.setForeground(foreground);
        this.counterTimer.setFont(TIMER_FONT);
    }

    // MODIFIES: this
    // EFFECTS:  update minute, seconds, counter name and timer based on the current state of timerController
    public void counterUpdater() {
        try {
            if (timerController.equals(TimerController.FINISHED)) {
                minutes = focusSessionIntGetter();
                counterName.setText(currentTimer.toString());
            } else if (timerController.equals(TimerController.STOPPED)) {
                minutes = focusSessionIntGetter();
                seconds = 0;
                counterName.setText(currentTimer.toString());
            }
        } catch (NullPointerException e) {
            minutes = 0;
        }
        counterName.setText(currentTimer.toString());
        counterTimer.setText(formatter.format(minutes) + " : " + formatter.format(seconds));
    }

    // MODIFIES: this
    // EFFECTS:  returns the appropriate int value from currentSession based on the timer running
    private int focusSessionIntGetter() throws NullPointerException {
        switch (currentTimer) {
            case BREAK:
                return currentSession.getSessionBreak();
            case REST:
                return currentSession.getSessionRest();
            default:
                return currentSession.getSessionFocus();
        }
    }

    // MODIFIES: this
    // EFFECTS:  update the next timer to run and the number of cycles that the counter has gone through. Cycles are
    //           based on how many focus timers have been taken
    public void currentTimerUpdater() {
        if (currentTimer == PossibleTimers.FOCUS) {
            if (cycles == 2) {
                cycles = 0;
                currentTimer = PossibleTimers.REST;
            } else {
                currentTimer = PossibleTimers.BREAK;
                cycles++;
            }
        } else {
            currentTimer = PossibleTimers.FOCUS;
        }
        counterUpdater();
    }

    // MODIFIES: this
    // EFFECTS:  runs the actual counter
    public void counterRunner() {
        timer = new Timer(1000, new TimerHandler());
        timer.start();
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public void setCurrentTimer(PossibleTimers currentTimer) {
        this.currentTimer = currentTimer;
    }

    public TimerController getTimerController() {
        return timerController;
    }

    public void setTimerController(TimerController timerController) {
        this.timerController = timerController;
    }

    // Handles the actionListener that enables the timer to keep running
    private class TimerHandler implements ActionListener {
        // MODIFIES: counterPanel
        // EFFECTS:  Controls what will happen based on the timerController, saving the current set of minutes/seconds,
        //           loading them from savedMinutes/savedSeconds or just run the timer
        @Override
        public void actionPerformed(ActionEvent e) {
            if (TimerController.STOPPED == timerController) {
                minutes = savedMinutes;
                seconds = savedSeconds;
            }

            if (TimerController.STOP == timerController) {
                savedMinutes = minutes;
                savedSeconds = seconds;
                timerController = TimerController.STOPPED;
                timer.stop();
            }

            if (TimerController.RUNNING == timerController) {
                timerHelper();
            }
        }

        // MODIFIES: counterPanel
        // EFFECTS:  runs the counter until it reaches 0 where it plays a sounds and gives a popup message
        void timerHelper() {
            if (minutes == 0 && seconds == 0) {
                try {
                    playAudio();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Audio File Not Found");
                }
                JOptionPane.showMessageDialog(editor,
                        "Timer Up",
                        "Stopped",
                        JOptionPane.INFORMATION_MESSAGE);
                timerController = TimerController.FINISHED;
                currentTimerUpdater();
                timer.stop();
                counterUpdater();
            } else if (seconds == 0) {
                seconds = 59;
                minutes--;
                counterUpdater();
            } else {
                seconds--;
                counterUpdater();
            }
        }

        // EFFECTS: creates a new AudioComponent to play the corresponding sound based on what timer finished running
        void playAudio() throws IOException {
            if (currentTimer == PossibleTimers.FOCUS) {
                new AudioComponent(PossibleTimers.FOCUS);
            } else {
                new AudioComponent(PossibleTimers.BREAK);
            }
        }
    }
}
