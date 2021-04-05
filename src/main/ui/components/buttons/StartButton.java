package ui.components.buttons;

import ui.Editor;
import ui.components.CounterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button the user can interact with to start the counter
public class StartButton extends Button {
    CounterPanel counterPanel;
    GridBagConstraints gbc;

    public StartButton(Editor editor, CounterPanel parent) {
        super(editor, parent);
        counterPanel = parent;
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Start" button and invokes addToParent()
    @Override
    protected void createButton() {
        button = new JButton("Start");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent with correct gbc positioning
    @Override
    public void addToParent() {
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 5;
        parent.add(button, gbc);
    }

    // MODIFIES: this
    // constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new TimerButtonHandler());
    }

    private class TimerButtonHandler implements ActionListener {
        // MODIFIES: counterPanel
        // EFFECTS: makes the counter inside counterPanel to start running. If it's already running it does nothing
        @Override
        public void actionPerformed(ActionEvent e) {
            if (counterPanel.getTimerController() != CounterPanel.TimerController.RUNNING) {
                counterPanel.setTimerController(CounterPanel.TimerController.RUNNING);
                counterPanel.counterRunner();
            }
        }
    }
}
