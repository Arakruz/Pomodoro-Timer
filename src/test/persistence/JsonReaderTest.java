package persistence;

import model.FocusSession;
import model.SessionsList;
import model.exceptions.SmallerThanOneException;
import org.junit.jupiter.api.BeforeEach;
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
        } catch (SmallerThanOneException wrongException) {
            fail("Wrong Exception");
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
        } catch (IOException | SmallerThanOneException e) {
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
            checkSession("b", 2, 3, 4, sessions.get(1));
        } catch (IOException | SmallerThanOneException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderCatchException() {
        JsonReader reader = new JsonReader("./data/testReaderException.json");
        try {
            SessionsList sl = reader.read();
            fail();
        } catch (Exception e) {
            //pass
        }
    }
}
