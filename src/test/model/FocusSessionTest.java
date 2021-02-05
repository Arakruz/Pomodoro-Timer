package model;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class FocusSessionTest {
    private FocusSession testSession;

    @BeforeEach
    void setUp() {
        testSession = new FocusSession("test",30,5,10);
    }

}