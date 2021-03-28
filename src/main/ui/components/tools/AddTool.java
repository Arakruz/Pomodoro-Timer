package ui.components.tools;

import model.FocusSession;
import ui.Editor;
import ui.components.tools.Tool;

import javax.swing.*;

public class AddTool extends Tool {
    public AddTool(Editor editor,JComponent parent) {
        super(editor,parent);
        makeSessionDialog();
        dialogProcessor();
    }

    @Override
    public void dialogProcessor() {
        option = JOptionPane.showConfirmDialog(
                parent,
                panel,
                "Session creator",
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
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please input number for the timers");
            }
        }
    }

    @Override
    protected void listModifier() {
        FocusSession session = new FocusSession(name,focus,shortBreak,rest);
        editor.getSessionsList().addSession(session);
        editor.updateListModel();
    }

}
