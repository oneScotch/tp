package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CodeHuntTest {
    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    void checkEchoFunction() {
        assertEquals("Test Message", CodeHunt.echoMessage("Test Message"));
    }
}