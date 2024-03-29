package ui;

import model.FocusSession;
import model.SessionsList;
import model.exceptions.SmallerThanOneException;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.CounterPanel;
import ui.components.TablePanel;
import ui.components.buttons.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Represents the main frame of the program. Also generates all other GUI elements
public class Editor extends JFrame {
    // Preferred and minimum sizes of the JFrame
    public static final int WIDTH = 1135;
    public static final int HEIGHT = 700;
    public static final int MIN_WIDTH = 1000;
    public static final int MIN_HEIGHT = 630;
    // How big the rows and columns are and how they scale on the GridBagLayout when the JFrame changes size
    public static final double COLLUM1_PERCENT = 0.4;
    public static final double ROW1_PERCENT = 0.85;
    public static final int COLLUM1_WIDTH = (int) (WIDTH * COLLUM1_PERCENT);
    public static final int COLLUM2_WIDTH = WIDTH - COLLUM1_WIDTH;
    public static final double COLLUM1_WEIGHT = COLLUM1_PERCENT;
    public static final double COLLUM2_WEIGHT = 1 - COLLUM1_PERCENT;
    public static final int ROW1_HEIGHT = (int) (HEIGHT * ROW1_PERCENT);
    public static final int ROW2_HEIGHT = HEIGHT - ROW1_HEIGHT;
    public static final double ROW1_WEIGHT = ROW1_PERCENT;
    public static final double ROW2_WEIGHT = 1 - ROW1_PERCENT;
    // How the background and foreground of the whole program looks and the fonts for the buttons and the list
    public static final Color BACKGROUND = new Color(0x2F3137);
    public static final Color FOREGROUND = new Color(0xaaafbd);
    public static final Font BUTTON_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
    public static final Font TABLE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
    // The path of the Json file used for saving
    public static final String JSON_STORE = "./data/SessionsList.json";

    // These are all related to the actual GUI, they are the mane panels and all the objects that they need to run
    private JTable tablePanel;
    private CounterPanel counterPanel;
    private JPanel emptyPanel;
    private JPanel buttonPanel;
    private DefaultTableModel tableModel;
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    // The sessionList being used, the current selected session and the Json objects needed to save/load
    private SessionsList sessionsList;
    private FocusSession currentSession;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private AddButton addButton;
    private SaveButton saveButton;
    private DeleteButton deleteButton;
    private EditButton editButton;

    public Editor() {
        super("Pomodoro Timer");
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this Editor will operate, populates the tools to be used to manipulate
    //           this app and customize how the GridBagLayout will look
    private void initializeGraphics() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{COLLUM1_WIDTH, COLLUM2_WIDTH};
        gbl.columnWeights = new double[]{COLLUM1_WEIGHT, COLLUM2_WEIGHT};
        gbl.rowWeights = new double[]{ROW1_WEIGHT, ROW2_WEIGHT};
        gbl.rowHeights = new int[]{ROW1_HEIGHT, ROW2_HEIGHT};
        setLayout(gbl);
        gbc = new GridBagConstraints();
        createTools();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which initializes all JPanels
    private void createTools() {
        counterPanel = initializeCounterPanel(); // has to go first due to other panels dependencies
        buttonPanel = initializeButtonPanel();
        tablePanel = initializeTablePanel();
        emptyPanel = initializeEmptyPanel();
    }

    // MODIFIES: this
    // EFFECTS:  creates an empty panel to fill the JFrame with the correct color
    private JPanel initializeEmptyPanel() {
        emptyPanel = new JPanel();
        emptyPanel.setBackground(BACKGROUND);
        emptyPanel.setForeground(FOREGROUND);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(emptyPanel, gbc);
        return emptyPanel;
    }

    // MODIFIES: this
    // EFFECTS:  creates a counter panel with the counter, counter's name, stop and start buttons
    private CounterPanel initializeCounterPanel() {
        counterPanel = new CounterPanel(this);
        counterPanel.setBackground(BACKGROUND);
        counterPanel.setForeground(FOREGROUND);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(counterPanel, gbc);
        return counterPanel;
    }

    // MODIFIES: this
    // EFFECTS:  creates a Table panel inside a scroll panel that represents the sessionList and allow the user to select
    //           a session to interact with.
    private JTable initializeTablePanel() {
        tablePanel = new TablePanel(tableModel, counterPanel);
        tablePanel.setBackground(BACKGROUND);
        tablePanel.setForeground(FOREGROUND);
        tablePanel.setFont(TABLE_FONT);
        tablePanel.setSelectionBackground(new Color(0xFF4F6797, true));
        tablePanel.setPreferredScrollableViewportSize(tablePanel.getPreferredSize());
        tablePanel.setFillsViewportHeight(true);
        tablePanel.setDefaultEditor(Object.class,null);
        tablePanel.setRowHeight(50);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(tablePanel, gbc);
        return tablePanel;
    }

    // MODIFIES: this
    // EFFECTS:  create a button panel with all necessary JButtons (add,save,delete,edit)
    private JPanel initializeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BACKGROUND);
        buttonPanel.setForeground(FOREGROUND);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(buttonPanel, gbc);

        addButton = new AddButton(this, buttonPanel);
        editButton = new EditButton(this, buttonPanel);
        //new LoadButton(this, buttonPanel);
        deleteButton = new DeleteButton(this, buttonPanel);
        saveButton = new SaveButton(this, buttonPanel);
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets sessionList to be the list saved on the Json from JSON_STORE or a new empty list and initializes
    //          an empty JTable and JModel for the tablePanel
    private void initializeFields() {
        this.sessionsList = new SessionsList();
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Focus Time");
        tableModel.addColumn("Break Time");
        tableModel.addColumn("Rest Time");
        load();
    }


    // MODIFIES: this
    // EFFECTS: clears the TableModel and adds the items in the sessions list to update the tablePanel properly
    public void updateListModel() {
        List<FocusSession> sessionsJList = sessionsList.getSessions();
        tableModel.setRowCount(0);
        for (FocusSession f : sessionsJList) {
            Vector<String> values = new Vector<>(4);
            values.add(f.getSessionName());
            values.add(Integer.toString(f.getSessionFocus()));
            values.add(Integer.toString(f.getSessionBreak()));
            values.add(Integer.toString(f.getSessionRest()));
            tableModel.addRow(values);
        }
    }

    // MODIFIES: this
    // EFFECTS:  loads a session list from the Json in JSON_STORE and updates the tableModel to properly show the new
    //           table on the GUI
    public void load() {
        try {
            sessionsList = jsonReader.read();
            updateListModel();
        } catch (IOException | SmallerThanOneException exception) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Unable to read from file: " + JSON_STORE);
        }
    }

    public SessionsList getSessionsList() {
        return sessionsList;
    }

    public void setSessionsList(SessionsList sessionsList) {
        this.sessionsList = sessionsList;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public FocusSession getCurrentSession() {
        int index;

        try {
            index = this.tablePanel.getSelectedRow();
        } catch (NullPointerException e) {
            return sessionsList.getSession(0);
        }


        if (index == -1) {
            return sessionsList.getSession(0);
        }

        //return currentSession = this.listPanel.getSelectedValue();
        currentSession = sessionsList.getSession(index);
        return currentSession;
    }
}
