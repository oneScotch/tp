package card;

import data.card.Card;
import data.card.CardManager;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

class CardManagerTest {
    CardManager cards;

    @BeforeEach
    void setUp() {
         ArrayList<Card> cardList = new ArrayList<>();
         cards.add(new Card("Loops can execute a block of code as long as a specified condition is reached."));
         cards.add(new Card("Arrays are used to store multiple values in a single variable"));
         cards.add(new Card("Use the if statement to specify a block of Java code to be executed if a condition is true."));
         cards = new CardManager(cardList);
    }

    @Test
    public void testDeleteCard() {
        Assertions.assertEquals(cards.deleteCard(1), "Loops can execute a block of code as long as a specified condition is reached.");
    }

    @Test
    public void testExchange() {
        Assertions.assertTrue(cards.exchange(1));
    }
}
