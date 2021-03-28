package ui.components.buttons;

import model.FocusSession;
import model.SessionsList;
import persistence.JsonReader;
import ui.Editor;
import javax.swing.*;
import java.awt.*;

public abstract class Button {

    protected JButton button;
    protected Editor editor;
    protected SessionsList sessionsList;
    protected FocusSession currentSession;
    JsonReader jsonReader;
    JComponent parent;


    public Button(Editor editor, JComponent parent) {
        this.editor = editor;
        this.sessionsList = editor.getSessionsList();
        this.jsonReader = editor.getJsonReader();
        this.parent = parent;
        createButton();
        addToParent();
        addListener();
    }

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(140,80));
        return button;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton();

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener();

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent() {
        parent.add(button);
    }
}
