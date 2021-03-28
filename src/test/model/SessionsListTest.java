package model;

import model.exceptions.NoSessionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SessionsListTest {
    private SessionsList testSessionsList;
    private FocusSession testSession;
    private FocusSession danielSession;

    @BeforeEach
    void setUp() {
        testSessionsList = new SessionsList();
        testSession = new FocusSession("test", 30, 5, 10);
        danielSession = new FocusSession("Daniel", 50, 10, 30);
    }

    @Test
    void testAddSession() {
        testSessionsList.addSession(testSession);
        assertEquals(1, testSessionsList.getSessionSize());
        assertEquals("test", testSessionsList.getSession(0).getSessionName());
    }

    @Test
    void testAddSessionWithNonEmptyList() {
        testSessionsList.addSession(testSession);
        testSessionsList.addSession(danielSession);

        assertEquals(2, testSessionsList.getSessionSize());

        assertEquals("test", testSessionsList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionsList.getSession(1).getSessionName());

        assertEquals(10, testSessionsList.getSession(0).getSessionRest());
        assertEquals(30, testSessionsList.getSession(1).getSessionRest());
    }

    @Test
    void testAddNewSessionWithNonEmptyList() {
        testSessionsList.addSession(testSession);
        testSessionsList.addSession(danielSession);

        assertEquals(2, testSessionsList.getSessionSize());

        assertEquals("test", testSessionsList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionsList.getSession(1).getSessionName());

        assertEquals(10, testSessionsList.getSession(0).getSessionRest());
        assertEquals(30, testSessionsList.getSession(1).getSessionRest());
    }

    @Test
    void testRemoveSession() {
        FocusSession sessionToBeRemove;
        testSessionsList.addSession(testSession);
        testSessionsList.addSession(danielSession);

        assertEquals(2, testSessionsList.getSessionSize());
        assertEquals("test", testSessionsList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionsList.getSession(1).getSessionName());

        sessionToBeRemove = testSessionsList.getSession(0);
        try {
            testSessionsList.removeSession(sessionToBeRemove);
        } catch (NoSessionException exception) {
            exception.printStackTrace();
        }

        assertEquals(1, testSessionsList.getSessionSize());
        assertEquals("Daniel", testSessionsList.getSession(0).getSessionName());
    }

    @Test
    void testGetSessionBasedOnName() {
        FocusSession sessionToFind;

        testSessionsList.addSession(testSession);
        testSessionsList.addSession(danielSession);

        assertEquals(2, testSessionsList.getSessionSize());
        assertEquals("test", testSessionsList.getSession(0).getSessionName());
        assertEquals("Daniel", testSessionsList.getSession(1).getSessionName());

        sessionToFind = testSessionsList.getSessionBasedOnName("Daniel");
        assertEquals("Daniel", sessionToFind.getSessionName());
    }

    @Test
    void testGetSessionBasedOnNameReturnsNull() {
        testSessionsList.addSession(testSession);

        assertNull(testSessionsList.getSessionBasedOnName("Daniel"));
    }
}
