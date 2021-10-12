package data.card;

import java.util.ArrayList;
import java.util.List;

public class CardManager {
    private List<Card> cards;
    private static final String MARK_NOT_USED = "[ ]";
    private static final String MARK_USED = "[x]";

    public CardManager(List<Card> cards) {
        this.cards = cards;
    }

    public CardManager() {
        this(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
        System.out.println("Great! You collect card: " + card.toString());
    }

    public void searchByKeyWord(String message) {
        int len = cards.size();
        int count = 0;
        for (int i = 0; i < len; i++) {
            Card currentCard = cards.get(i);
            if (currentCard.getContent().contains(message)) {
                System.out.println(currentCard);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Ops! It seems that you do not have any card containing the keyword");
        }
    }

    public void listCards() {
        int len = cards.size();
        for (int i = 0; i < len; i++) {
            Card card = cards.get(i);
            String label = card.checkIfIsUsed() ? MARK_USED : MARK_NOT_USED;
            int id = i + 1;
            System.out.println(id + ". " + cards.get(i) + " " + label);
        }
    }

    public Card deleteCard(int id) {
        try {
            boolean isValid = id > 0 && id <= cards.size();
            assert isValid : "Ops, it seems that you input an invalid card id, please try again!";
            System.out.println("Your card " + id + " : " + cards.get(id) + " has been removed!");
            return cards.remove(id - 1);
        } catch (AssertionError e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void transferTo(CardManager cardsToTransfer) {
        Card cardGet = cards.remove(0);
        cardsToTransfer.add(cardGet);
    }

    public boolean exchange(int cardID) {
        boolean canBeExchanged = true;
        Card cardSelected = cards.get(cardID - 1);
        if (cardSelected.checkIfIsUsed()) {
            canBeExchanged = false;
        } else {
            cardSelected.setAsUsed();
        }
        return canBeExchanged;
    }
}