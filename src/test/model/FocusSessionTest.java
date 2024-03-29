package model;

import model.exceptions.SmallerThanOneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FocusSessionTest {
    private FocusSession testSession;

    @BeforeEach
    void setUp() {
        try {
            testSession = new FocusSession("test", 30, 5, 10);
        } catch (SmallerThanOneException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConstructorException() {
        try {
            testSession = new FocusSession("test1", 0, 1, 1);
            fail();
        } catch (SmallerThanOneException e) {
        }
        try {
            testSession = new FocusSession("test2", 1, 0, 1);
            fail();
        } catch (SmallerThanOneException e) {
        }
        try {
            testSession = new FocusSession("test3", 1, 1, 0);
            fail();
        } catch (SmallerThanOneException e) {
        }
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
        try {
            testSession.intSetter(FocusSession.PossibleInt.FOCUS, 45);
        } catch (SmallerThanOneException e) {
            fail();
        }
        assertEquals(45, testSession.getSessionFocus());
    }

    @Test
    void intSetterFocusTestCatchException() {
        try {
            testSession.intSetter(FocusSession.PossibleInt.FOCUS, 0);
            fail();
        } catch (SmallerThanOneException e) {
        }
    }

    @Test
    void intSetterBreakTest() {
        // checks if the intSetter is properly working with the break part of the function
        try {
            testSession.intSetter(FocusSession.PossibleInt.BREAK, 10);
        } catch (SmallerThanOneException e) {
            fail();
        }
        assertEquals(10, testSession.getSessionBreak());
    }

    @Test
    void intSetterBreakTestCatchException() {
        // checks if the intSetter is properly working with the break part of the function
        try {
            testSession.intSetter(FocusSession.PossibleInt.BREAK, 0);
            fail();
        } catch (SmallerThanOneException e) {
        }
    }

    @Test
    void intSetterRestTest() {
        // checks if the intSetter is properly working with the rest part of the function
        try {
            testSession.intSetter(FocusSession.PossibleInt.REST, 20);
        } catch (SmallerThanOneException e) {
            fail();
        }
        assertEquals(20, testSession.getSessionRest());
    }

    @Test
    void intSetterRestTestCatchException() {
        // checks if the intSetter is properly working with the rest part of the function
        try {
            testSession.intSetter(FocusSession.PossibleInt.REST, 0);
            fail();
        } catch (SmallerThanOneException e) {
        }
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