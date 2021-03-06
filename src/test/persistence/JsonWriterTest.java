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
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            SessionsList sl = new SessionsList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySessionsList() {
        try {
            SessionsList sl = new SessionsList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySessionsList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySessionsList.json");
            sl = reader.read();
            assertEquals(0, sl.getSessionSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSessionsList() {
        try {
            SessionsList sl = new SessionsList();
            sl.addSession(new FocusSession("a", 1, 2, 3));
            sl.addSession(new FocusSession("b", 2, 3, 4));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSessionsList.json");
            writer.open();
            writer.write(sl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSessionsList.json");
            sl = reader.read();
            List<FocusSession> sessions = sl.getSessions();
            assertEquals(2, sessions.size());
            checkSession("a", 1, 2, 3, sessions.get(0));
            checkSession("b", 2, 3, 4, sessions.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
