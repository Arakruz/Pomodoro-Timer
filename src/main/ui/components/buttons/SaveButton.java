package ui.components.buttons;

import model.SessionsList;
import persistence.JsonWriter;
import ui.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SaveButton extends Button {

    public SaveButton(Editor editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  creates a new "Save" button and invokes addToParent()
    @Override
    protected void createButton() {
        button = new JButton("Save");
        button = customizeButton(button);
        addToParent();
    }

    // EFFECTS:  constructs an ActionListener which is then added to this object's JButton
    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolHandler());
    }

    private class SaveToolHandler implements ActionListener {
        // EFFECTS: saves the SessionsList to file
        @Override
        public void actionPerformed(ActionEvent e) {
            JsonWriter jsonWriter = editor.getJsonWriter();
            String jsonPath = Editor.JSON_STORE;
            SessionsList sessionsList = editor.getSessionsList();

            try {
                jsonWriter.open();
                jsonWriter.write(sessionsList);
                jsonWriter.close();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Saved SessionsList to " + jsonPath);
            } catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Unable to write to file: " + jsonPath);
            }
        }
    }
}
