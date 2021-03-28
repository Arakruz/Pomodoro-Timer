package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FocusSessionTest {
    private FocusSession testSession;

    @BeforeEach
    void setUp() {
        testSession = new FocusSession("test", 30, 5, 10);
    }

    @Test
    void testConstructor() {
        assertEquals("test", testSession.getSessionName());
        assertEquals(30, testSession.getSessionFocus());
        assertEquals(5, testSession.getSessionBreak());
        assertEquals(10, testSession.getSessionRest());
    }

    @Test
    void intSetterFocusTest() {
        // checks if the intSetter is properly working with the focus part of the function
        testSession.intSetter("focus", 45);
        assertEquals(45, testSession.getSessionFocus());
    }

    @Test
    void intSetterBreakTest() {
        // checks if the intSetter is properly working with the break part of the function
        testSession.intSetter("break", 10);
        assertEquals(10, testSession.getSessionBreak());
    }

    @Test
    void intSetterRestTest() {
        // checks if the intSetter is properly working with the rest part of the function
        testSession.intSetter("rest", 20);
        assertEquals(20, testSession.getSessionRest());
    }

    @Test
    void nameSetterTest() {
        // checks if the intSetter is properly working with the long part of the function
        testSession.nameSetter("worked!");
        assertEquals("worked!", testSession.getSessionName());
    }

    @Test
    void toStringTest() {
        // checks if the toString method is giving the correct output
        String expectedString = "test | Focus 30 minutes | Break 5 minutes | Rest 10 minutes | ";
        assertEquals(expectedString,testSession.toString());
    }

}