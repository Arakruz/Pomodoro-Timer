package ui.components.tools;

import ui.Editor;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;

public abstract class Tool {
    private static final int NAME_MAX_SIZE = 20;
    private static final int INT_MAX_SIZE = 2;

    protected JTextField nameField;
    protected JTextField focusField;
    protected JTextField breakField;
    protected JTextField restField;
    protected String name;
    protected int focus;
    protected int shortBreak;
    protected int rest;
    protected int option;
    protected JPanel panel;
    protected Editor editor;
    protected JComponent parent;

    public Tool(Editor editor, JComponent parent) {
        this.editor = editor;
        this.parent = parent;
        panel = new JPanel();
        panel.setLayout(new GridLayout(0,4,2,2));
        nameField = new JTextField(NAME_MAX_SIZE);
        focusField = new JTextField(INT_MAX_SIZE);
        breakField = new JTextField(INT_MAX_SIZE);
        restField = new JTextField(INT_MAX_SIZE);

        nameField.setDocument(new FieldLimit(NAME_MAX_SIZE));
        focusField.setDocument(new FieldLimit(INT_MAX_SIZE));
        breakField.setDocument(new FieldLimit(INT_MAX_SIZE));
        restField.setDocument(new FieldLimit(INT_MAX_SIZE));
    }

    protected void makeSessionDialog() {
        panel.add(new JLabel("Name of your focus session :"));
        panel.add(nameField);

        panel.add(new JLabel("Duration of your focus time (in minutes) :"));
        panel.add(focusField);

        panel.add(new JLabel("Duration of your brake (in minutes) :"));
        panel.add(breakField);

        panel.add(new JLabel("Duration of your rest (in minutes) :"));
        panel.add(restField);
    }

    protected abstract void dialogProcessor();

    protected abstract void listModifier();

    private static class FieldLimit extends PlainDocument {
        private final int limit;

        FieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String string, AttributeSet attributeSet) throws BadLocationException {
            if ((getLength() + string.length()) <= limit) {
                super.insertString(offset, string, attributeSet);
            }
        }
    }
}
