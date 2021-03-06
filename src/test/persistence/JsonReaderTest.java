package persistence;

import model.FocusSession;
import model.SessionsList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


/*
 Citation: code taken and modified from JsonSerializationDemo
      URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SessionsList sl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySessionsList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySessionsList.json");
        try {
            SessionsList sl = reader.read();
            assertEquals(0, sl.getSessionSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSessionsList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSessionsList.json");
        try {
            SessionsList sl = reader.read();
            List<FocusSession> sessions = sl.getSessions();
            assertEquals(2, sessions.size());
            checkSession("a", 1, 2, 3, sessions.get(0));
            checkSession("b", 2, 3, 4 , sessions.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
