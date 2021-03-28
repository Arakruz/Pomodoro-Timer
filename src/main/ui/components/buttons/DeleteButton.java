package ui.components.buttons;

import model.SessionsList;
import model.exceptions.NoSessionException;
import ui.Editor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton extends Button {
    public DeleteButton(Editor editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Delete" button and invokes addToParent() on the parent passed to this method
    @Override
    protected void createButton() {
        button = new JButton("Delete");
        button = customizeButton(button);
        addToParent();
    }

    // MODIFIES: this
    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new DeleteToolHandler());
    }

    private class DeleteToolHandler implements ActionListener {
        // EFFECTS: deletes the selected SessionsList to file
        @Override
        public void actionPerformed(ActionEvent e) {

            currentSession = editor.getCurrentSession();
            SessionsList sessionsList = editor.getSessionsList();

            try {
                sessionsList.removeSession(currentSession);
                editor.setSessionsList(sessionsList);
                editor.updateListModel();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Session deleted!");
            } catch (NoSessionException exception) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),exception.getMessage());
            }
        }
    }
}
