package persistence;

import model.FocusSession;
import model.SessionsList;
import model.exceptions.SmallerThanOneException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
 Represents a reader that reads SessionsList from JSON data stored in file
 Citation: code taken and modified from JsonSerializationDemo
      URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads SessionList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SessionsList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSessionsList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws  IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //  EFFECTS: parses sessionsList from JSON object and returns it
    private SessionsList parseSessionsList(JSONObject jsonObject) {
        SessionsList sl = new SessionsList();
        addSessions(sl, jsonObject);
        return sl;
    }

    // MODIFIES: sl
    // EFFECTS: parses FocusSessions from JSON object and adds them to SessionsList
    private void addSessions(SessionsList sl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("focusSessions");
        for (Object json : jsonArray) {
            JSONObject nextSession = (JSONObject) json;
            addSession(sl, nextSession);
        }
    }

    // MODIFIES: sl
    // EFFECTS: parses focusSession from JSON object and adds it to SessionsList
    private void addSession(SessionsList sl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int focus = jsonObject.getInt("focus");
        int shortBreak = jsonObject.getInt("shortBreak");
        int rest = jsonObject.getInt("rest");
        FocusSession session = null;
        try {
            session = new FocusSession(name, focus, shortBreak, rest);
        } catch (SmallerThanOneException e) {
            e.printStackTrace();
        }
        sl.addSession(session);
    }
}
