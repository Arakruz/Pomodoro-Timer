package ui.components.buttons;

import ui.Editor;
import ui.components.tools.AddTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButton extends Button {
    public AddButton(Editor editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Add" button and invokes addToParent()
    @Override
    protected void createButton() {
        button = new JButton("Add");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new AddToolHandler());
    }

    private class AddToolHandler implements ActionListener {
        // EFFECTS: creates an AddTool to process userInput and add a focusSession to the sessionList
        @Override
        public void actionPerformed(ActionEvent e) {
            new AddTool(editor, parent);
        }
    }


}
