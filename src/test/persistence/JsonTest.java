package persistence;

import model.FocusSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 Citation: code taken and modified from JsonSerializationDemo
      URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
*/
public class JsonTest {
    protected void checkSession(String name, int f, int b, int r, FocusSession fs) {
        assertEquals(name, fs.getSessionName());
        assertEquals(f, fs.getSessionFocus());
        assertEquals(b, fs.getSessionBreak());
        assertEquals(r, fs.getSessionRest());
    }
}
