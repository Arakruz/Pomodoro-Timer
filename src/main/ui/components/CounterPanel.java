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

public class CounterPanel extends JPanel {
    public static final Font TIMER_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 100);
    public static final Font NAME_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 60);
    public static final double COLLUM_WEIGHT = 0.2;
    public static final double ROW_WEIGHT = 0.2;
    private GridBagConstraints gbc;
    private Editor editor;
    private FocusSession currentSession;
    private PossibleTimers currentTimer;
    private int cycles;
    private JLabel counterName;
    private JLabel counterTimer;
    private StartButton startButton;
    private StopButton stopButton;
    private int minutes;
    private int seconds;
    private int savedMinutes;
    private int savedSeconds;
    private Timer timer;
    private TimerController timerController;
    private Color background;
    private Color foreground;

    public enum TimerController {
        RUNNING, STOPPED, FINISHED, STOP
    }

    public enum PossibleTimers {
        FOCUS, BREAK, REST
    }

    public CounterPanel(Editor editor) {
        this.editor = editor;
        this.timerController = TimerController.FINISHED;
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        currentSessionUpdater();
        initializeFields();
        initializeCounterLabels();
        counterUpdater();
    }

    public void currentSessionUpdater() {
        this.currentSession = editor.getCurrentSession();
    }

    private void initializeFields() {
        cycles = 0;
        background = editor.getBackground();
        foreground = editor.getForeground();
        currentTimer = PossibleTimers.FOCUS;
        counterName = new JLabel();
        counterTimer = new JLabel();
        startButton = new StartButton(editor, this);
        stopButton = new StopButton(editor, this);
    }

    private void initializeCounterLabels() {
        counterUpdater();
        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(counterName,gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        this.add(counterTimer,gbc);
        counterName.setBackground(background);
        counterName.setForeground(foreground);
        counterName.setFont(NAME_FONT);
        counterTimer.setBackground(background);
        counterTimer.setForeground(foreground);
        counterTimer.setFont(TIMER_FONT);
    }

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
        counterTimer.setText(minutes + " : " + seconds);
    }

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

    public void counterRunner() {
        timer = new Timer(1000, new TimerHandler());
        timer.start();
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public PossibleTimers getCurrentTimer() {
        return currentTimer;
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


    public GridBagConstraints getGbc() {
        return gbc;
    }

    private class TimerHandler implements ActionListener {
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

        void timerHelper() {
            if (minutes == 0 && seconds == 0) {
                try {
                    playAudio();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Audio File Not Found");
                }
                JOptionPane.showMessageDialog(editor, "Timer Up", "Stopped", 0);
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

        void playAudio() throws IOException {
            if (currentTimer == PossibleTimers.FOCUS) {
                new AudioComponent(PossibleTimers.FOCUS);
            } else {
                new AudioComponent(PossibleTimers.BREAK);
            }
        }
    }
}
