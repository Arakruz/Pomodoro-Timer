package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FocusSessionTest {
    private FocusSession testSession;

    @BeforeEach
    void setUp() {
        testSession = new FocusSession("test",30,5,10);
    }

    @Test
    void testConstructor() {
        assertEquals("test", testSession.getSessionName());
        assertEquals(30, testSession.getFocusTimer());
        assertEquals(5, testSession.getShortBreak());
        assertEquals(10, testSession.getLongBreak());
    }

    @Test
    void intSetterFocusTest(){
        // checks if the intSetter is properly working with the focus part of the function
        testSession.intSetter("focus", 45);
        assertEquals(45, testSession.getFocusTimer());
    }

    @Test
    void intSetterShortTest(){
        // checks if the intSetter is properly working with the short part of the function
        testSession.intSetter("short", 10);
        assertEquals(10, testSession.getShortBreak());
    }

    @Test
    void intSetterLongTest(){
        // checks if the intSetter is properly working with the long part of the function
        testSession.intSetter("long", 20);
        assertEquals(20, testSession.getLongBreak());
    }

    @Test
    void nameSetterTest(){
        // checks if the intSetter is properly working with the long part of the function
        testSession.nameSetter("worked!");
        assertEquals("worked!", testSession.getSessionName());
    }


}