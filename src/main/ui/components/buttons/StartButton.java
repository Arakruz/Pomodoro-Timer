package ui.components.buttons;

import ui.Editor;
import ui.components.CounterPanel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends Button {
    CounterPanel counterPanel;

    public StartButton(Editor editor, CounterPanel parent) {
        super(editor, parent);
        counterPanel = parent;
    }

    @Override
    protected void createButton() {
        button = new JButton("Start");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new TimerButtonHandler());
    }

    private class TimerButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            counterPanel.setTimerController(CounterPanel.TimerController.RUNNING);
            counterPanel.counterRunner();
        }
    }

}
