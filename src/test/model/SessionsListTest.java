package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SessionsListTest {
    private SessionsList testSessionsList;

    @BeforeEach
    void setUp() {
        testSessionsList = new SessionsList();
    }

    @Test
    void testAddSession() {
        testSessionsList.addSession("test", 30, 5, 10);
        assertEquals(1, testSessionsList.getSessionSize());
        assertEquals("test", testSessionsList.getSession(0).getSessionName());
    }

    @Test
    void testAddSessionWithNonEmptyList() {
        testSessionsList.addSession("test", 30, 5, 10);
        testSessionsList.addSession("Daniel", 50, 10, 30);

        assertEquals(2, testSessionsList.getSessionSize());

        assertEquals("test", testSessionsList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionsList.getSession(1).getSessionName());

        assertEquals(10, testSessionsList.getSession(0).getSessionRest());
        assertEquals(30, testSessionsList.getSession(1).getSessionRest());
    }

    @Test
    void testRemoveSession() {
        FocusSession sessionToBeRemove;
        testSessionsList.addSession("test", 30, 5, 10);
        testSessionsList.addSession("Daniel", 50, 10, 30);

        assertEquals(2, testSessionsList.getSessionSize());
        assertEquals("test", testSessionsList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionsList.getSession(1).getSessionName());

        sessionToBeRemove = testSessionsList.getSession(0);
        testSessionsList.removeSession(sessionToBeRemove);

        assertEquals(1, testSessionsList.getSessionSize());
        assertEquals("Daniel", testSessionsList.getSession(0).getSessionName());
    }

    @Test
    void testGetSessionBasedOnName() {
        FocusSession sessionToFind;

        testSessionsList.addSession("test", 30, 5, 10);
        testSessionsList.addSession("Daniel", 50, 10, 30);

        assertEquals(2, testSessionsList.getSessionSize());
        assertEquals("test", testSessionsList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionsList.getSession(1).getSessionName());

        sessionToFind = testSessionsList.getSessionBasedOnName("Daniel");
        assertEquals("Daniel", sessionToFind.getSessionName());
    }

    @Test
    void testGetSessionBasedOnNameReturnsNull() {
        testSessionsList.addSession("test", 30, 5, 10);

        assertNull(testSessionsList.getSessionBasedOnName("Daniel"));
    }
}
