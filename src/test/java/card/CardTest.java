package card;

import data.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CardTest {
    @Test
    public void testOutput() {
        Assertions.assertEquals(new Card("loop"), "loop");
    }

    @Test
    public void testIsUsed() {
        Assertions.assertFalse(new Card("loop").checkIfIsUsed());
    }
}