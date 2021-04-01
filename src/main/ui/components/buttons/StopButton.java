package ui.components.buttons;

import ui.Editor;
import ui.components.CounterPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button the user can interact with to stop the counter
public class StopButton extends Button {
    CounterPanel counterPanel;
    GridBagConstraints gbc;

    public StopButton(Editor editor, CounterPanel parent) {
        super(editor, parent);
        counterPanel = parent;
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Stop" button and invokes addToParent()
    @Override
    protected void createButton() {
        button = new JButton("Stop");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the counterPanel with correct gbc positioning
    @Override
    public void addToParent() {
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        parent.add(button, gbc);
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new StopButtonHandler());
    }

    public class StopButtonHandler implements ActionListener {
        // MODIFIES: counterPanel
        // EFFECTS: makes the counter inside counterPanel to stop running. If it's already not running it does nothing
        @Override
        public void actionPerformed(ActionEvent e) {
            if (counterPanel.getTimerController() != CounterPanel.TimerController.FINISHED) {
                counterPanel.setTimerController(CounterPanel.TimerController.STOP);
            }
        }
    }
}
