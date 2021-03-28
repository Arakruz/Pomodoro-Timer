package ui.components;

import model.FocusSession;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ListPanel extends JList {
    CounterPanel counterPanel;
    public ListPanel(DefaultListModel<FocusSession> listModel, CounterPanel counterPanel) {
        this.setModel(listModel);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setBackground(Color.LIGHT_GRAY);
        this.addListSelectionListener(new ListHandler());
        this.counterPanel = counterPanel;
    }

    private class ListHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            counterPanel.setCycles(0);
            counterPanel.setCurrentTimer(CounterPanel.PossibleTimers.FOCUS);
            counterPanel.currentSessionUpdater();
            counterPanel.counterUpdater();
        }
    }
}
