package ui.components.buttons;

import ui.Editor;
import ui.components.CounterPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopButton extends Button {
    CounterPanel counterPanel;

    public StopButton(Editor editor, CounterPanel parent) {
        super(editor, parent);
        counterPanel = parent;
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Stop" button and invokes addToParent() on the parent passed to this method
    @Override
    protected void createButton() {
        button = new JButton("Stop");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new StopButtonHandler());
    }

    public class StopButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            counterPanel.setTimerController(CounterPanel.TimerController.STOP);
        }
    }
}
