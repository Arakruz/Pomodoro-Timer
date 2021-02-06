package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FocusSessionTest {
    private FocusSession testSession;
    private FocusSession expectedTestSession;

    @BeforeEach
    void setUp() {
        testSession = new FocusSession("test",30,5,10);
    }

    @Test
    void intSetterFocusTest(){
        // sets up the expected session value with all values the same as testSession but sessionFocus
        expectedTestSession = new FocusSession("test", 45,5, 10);

        // checks if the intSetter is properly working with the focus part of the function
        assertEquals(expectedTestSession, testSession.intSetter("focus", 45, testSession));
    }

    @Test
    void intSetterShortTest(){
        // sets up the expected session value with all values the same as testSession but sessionShort
        expectedTestSession = new FocusSession("test", 30,10, 10);

        // checks if the intSetter is properly working with the short part of the function
        assertEquals(expectedTestSession, testSession.intSetter("short", 10, testSession));
    }

    @Test
    void intSetterLongTest(){
        // sets up the expected session value with all values the same as testSession but sessionLong
        expectedTestSession = new FocusSession("test", 30,5, 20);

        // checks if the intSetter is properly working with the long part of the function
        assertEquals(expectedTestSession, testSession.intSetter("long", 20, testSession));
    }

    @Test
    void nameSetterTest(){
        // sets up the expected session value with all values the same as testSession but sessionLong
        expectedTestSession = new FocusSession("worked!", 30,5, 10);

        // checks if the intSetter is properly working with the long part of the function
        assertEquals(expectedTestSession, testSession.nameSetter("worked!", testSession));
    }


}