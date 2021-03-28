package ui;

import model.FocusSession;
import model.SessionsList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.CounterPanel;
import ui.components.ListPanel;
import ui.components.buttons.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

public class Editor extends JFrame {
    public static final int WIDTH = 1050;
    public static final int HEIGHT = 700;
    public static final int MIN_WIDTH = 750;
    public static final int MIN_HEIGHT = 500;
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
    public static final String JSON_STORE = "./data/SessionsList.json";

    private SessionsList sessionsList;
    private JPanel emptyPanel;
    private JLabel timerArea;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private GridBagConstraints gbc;
    private JList<FocusSession> listPanel;
    private DefaultListModel<FocusSession> listModel;
    private CounterPanel counterPanel;
    private FocusSession currentSession;
    private JPanel buttonPanel;
    private GridBagLayout gbl;


    public Editor() {
        super("Pomodoro Timer");
        initializeFields();
        initializeGraphics();
        initializeSound();
    }

    private void initializeSound() {
        //TODO
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this TimerApp will operate, and populates the tools to be used
    //           to manipulate this app
    private void initializeGraphics() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(MIN_WIDTH,MIN_HEIGHT));
        gbl = new GridBagLayout();
        gbl.columnWidths = new int[] {COLLUM1_WIDTH, COLLUM2_WIDTH};
        gbl.columnWeights = new double[] {COLLUM1_WEIGHT, COLLUM2_WEIGHT}; //TODO fix weight
        gbl.rowWeights = new double[] {ROW1_WEIGHT, ROW2_WEIGHT}; //TODO fix weight
        gbl.rowHeights = new int[] {ROW1_HEIGHT, ROW2_HEIGHT};
        setLayout(gbl);
        gbc = new GridBagConstraints();
        createTools();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //TODO change the effects to follow proper alignment

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {
        counterPanel = initializeCounterPanel(); //has to go first
        buttonPanel = initializeButtonPanel(); //TODO just call the methods
        listPanel = initializeListPanel();
        emptyPanel = initializeEmptyPanel();

        AddButton addButton = new AddButton(this, buttonPanel);
        SaveButton saveButton = new SaveButton(this, buttonPanel);
        //LoadButton loadButton = new LoadButton(this,buttonArea);
        DeleteButton deleteButton = new DeleteButton(this, buttonPanel);
        EditButton editButton = new EditButton(this, buttonPanel);
    }

    private JPanel initializeEmptyPanel() {
        emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.lightGray);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(emptyPanel,gbc);
        return emptyPanel;
    }

    private CounterPanel initializeCounterPanel() {
        counterPanel = new CounterPanel(this);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(counterPanel,gbc);
        return counterPanel;
    }


    private JList<FocusSession> initializeListPanel() {
        listPanel = new ListPanel(listModel, counterPanel);
        listPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPanel.setBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setHorizontalScrollBar(scrollPane.createHorizontalScrollBar());
        scrollPane.setVerticalScrollBar(scrollPane.createVerticalScrollBar());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        return listPanel;
    }

    //TODO add comments
    private JPanel initializeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.lightGray);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(buttonPanel, gbc);
        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: sets activeTool, currentSession to null, and instantiates the sessionsList //TODO change the effects
    private void initializeFields() {
        this.sessionsList = new SessionsList();
        this.timerArea = new JLabel();
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.listModel = new DefaultListModel<>();
        this.listPanel = new JList<>();
        load();
    }


    // MODIFIES: this
    // EFFECTS: clears the ListModel and adds the items in the sessions list to update the listArea properly
    public void updateListModel() {
        List<FocusSession> sessionsJList = sessionsList.getSessions();
        listModel.clear();
        for (FocusSession f: sessionsJList) {
            listModel.addElement(f);
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

    public void setJsonReader(JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public void setJsonWriter(JsonWriter jsonWriter) {
        this.jsonWriter = jsonWriter;
    }

    public JList<FocusSession> getListPanel() {
        return this.listPanel;
    }

    public FocusSession getCurrentSession() {
        currentSession = this.listPanel.getSelectedValue();
        return currentSession;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }


    public void load() {
        try {
            sessionsList = jsonReader.read();
            updateListModel();
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Unable to read from file: " + JSON_STORE);
        }
    }
}
