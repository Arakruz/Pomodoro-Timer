package ui.components;

import model.FocusSession;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

// Represents a ListPanel showing a sessionList based on the given listModel, also allows the user to select a specific
// session
public class ListPanel extends JList<FocusSession> {
    CounterPanel counterPanel;

    public ListPanel(DefaultListModel<FocusSession> listModel, CounterPanel counterPanel) {
        this.setModel(listModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setBackground(Color.LIGHT_GRAY);
        this.addListSelectionListener(new ListHandler());
        this.counterPanel = counterPanel;
    }

    private class ListHandler implements ListSelectionListener {

        // MODIFIES: counterPanel
        // EFFECTS: updates counterPanel to reflect the new selected session selected by the user
        @Override
        public void valueChanged(ListSelectionEvent e) {
            counterPanel.setCycles(0);
            counterPanel.setCurrentTimer(CounterPanel.PossibleTimers.FOCUS);
            counterPanel.currentSessionUpdater();
            counterPanel.counterUpdater();
        }
    }
}
