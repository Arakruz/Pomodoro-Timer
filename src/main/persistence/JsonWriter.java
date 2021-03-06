package persistence;

import model.SessionsList;
import org.json.JSONObject;

import java.io.*;

/*
 Represents a writer that writes JSON representation of SessionList to file
 Citation: code taken and modified from JsonSerializationDemo
      URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write the JSON to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes Json representation of SessionsList to file
    public void write(SessionsList sessionsList) {
        JSONObject json = sessionsList.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
