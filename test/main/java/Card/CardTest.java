package main.java.Card;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    public void testMessageOutput() {
        assertEquals("4,7", new Card("for loop", 1).toString(),"for loop");
    }
}