package ui.components.buttons;

import model.SessionsList;
import model.exceptions.NoSessionException;
import ui.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button the user can interact with to delete the selected session from session list
public class DeleteButton extends Button {
    SessionsList sessionsList;

    public DeleteButton(Editor editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Delete" button and invokes addToParent()
    @Override
    protected void createButton() {
        button = new JButton("Delete");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: editor
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new DeleteToolHandler());
    }

    private class DeleteToolHandler implements ActionListener {
        // EFFECTS: deletes the selected SessionsList to file
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionsList = editor.getSessionsList();

            try {
                sessionsList.removeSession(editor.getCurrentSession());
                editor.setSessionsList(sessionsList);
                editor.updateListModel();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Session deleted!");
            } catch (NoSessionException exception) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), exception.getMessage());
            }
        }
    }
}
