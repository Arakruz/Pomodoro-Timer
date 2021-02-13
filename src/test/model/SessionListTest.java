package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionListTest {
    private SessionList testSessionList;

    @BeforeEach
    void setUp() {
        testSessionList = new SessionList();
    }

    @Test
    void testAddSession() {
        testSessionList.addSession("test", 30, 5, 10);
        assertEquals(1, testSessionList.getSessionSize());
        assertEquals("test", testSessionList.getSession(0).getSessionName());
    }

    @Test
    void testAddSessionWithNonEmptyList() {
        testSessionList.addSession("test", 30, 5, 10);
        testSessionList.addSession("Daniel", 50, 10, 30);

        assertEquals(2, testSessionList.getSessionSize());

        assertEquals("test", testSessionList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionList.getSession(1).getSessionName());

        assertEquals(10, testSessionList.getSession(0).getLongBreak());
        assertEquals(30, testSessionList.getSession(1).getLongBreak());
    }

    @Test
    void testRemoveSession() {
        FocusSession sessionToBeRemove;
        testSessionList.addSession("test", 30, 5, 10);
        testSessionList.addSession("Daniel", 50, 10, 30);

        assertEquals(2, testSessionList.getSessionSize());
        assertEquals("test", testSessionList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionList.getSession(1).getSessionName());

        sessionToBeRemove = testSessionList.getSession(0);
        testSessionList.removeSession(sessionToBeRemove);

        assertEquals(1,testSessionList.getSessionSize());
        assertEquals("Daniel", testSessionList.getSession(0).getSessionName());
    }

    @Test
    void testGetSessionBasedOnName() {
        FocusSession sessionToFind;

        testSessionList.addSession("test", 30, 5, 10);
        testSessionList.addSession("Daniel", 50, 10, 30);

        assertEquals(2, testSessionList.getSessionSize());
        assertEquals("test", testSessionList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionList.getSession(1).getSessionName());

        sessionToFind = testSessionList.getSessionBasedOnName("Daniel");
        assertEquals("Daniel", sessionToFind.getSessionName());
    }
}
