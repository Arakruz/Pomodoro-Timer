package ui.components.tools;

import model.FocusSession;
import ui.Editor;

import javax.swing.*;

public class EditTool extends Tool {
    public EditTool(Editor editor, JComponent parent) {
        super(editor, parent);
        makeSessionDialog();
        dialogProcessor();
    }

    @Override
    public void dialogProcessor() {
        option = JOptionPane.showConfirmDialog(
                parent,
                panel,
                "Session editor",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            name = nameField.getText();

            try {
                focus = Integer.parseUnsignedInt(focusField.getText());
                shortBreak = Integer.parseUnsignedInt(breakField.getText());
                rest = Integer.parseUnsignedInt(restField.getText());
                listModifier();
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "No Session selected");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Please input number for the timers");
            }
        }
    }

    @Override
    protected void listModifier() {
        FocusSession session = editor.getCurrentSession();
        session.nameSetter(name);
        session.intSetter("focus", focus);
        session.intSetter("shortBreak", shortBreak);
        session.intSetter("rest", rest);
        editor.updateListModel();
    }
}
