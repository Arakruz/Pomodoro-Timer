package ui.components.buttons;

import ui.Editor;
import ui.components.tools.EditTool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButton extends Button {
    public EditButton(Editor editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Edit Session" button and invokes addToParent()
    @Override
    protected void createButton() {
        button = new JButton("Edit");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new EditButton.EditToolHandler());
    }

    private class EditToolHandler implements ActionListener {
        // EFFECTS: creates an EditTool to process userInput and change the selected sessionList
        @Override
        public void actionPerformed(ActionEvent e) {
            new EditTool(editor, parent);
        }
    }

}
