package ui.components.buttons;

import ui.Editor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadButton extends Button {
    public LoadButton(Editor editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Load" button and invokes addToParent() on the parent passed to this method
    @Override
    protected void createButton() {
        button = new JButton("Load");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new LoadToolHandler());
    }

    private class LoadToolHandler implements ActionListener {
        // EFFECTS: loads the SessionsList from file
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.load();
        }
    }
}
