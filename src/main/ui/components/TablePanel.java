package ui.components;

import model.FocusSession;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// Represents a TablePanel showing a sessionList based on the given tableModel, also allows the user to select a specific
// session
public class TablePanel extends JTable {
    CounterPanel counterPanel;

    public TablePanel(DefaultTableModel tableModel, CounterPanel counterPanel) {
        this.setModel(tableModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setBackground(Color.LIGHT_GRAY);
        this.getSelectionModel().addListSelectionListener(new ListHandler());
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
