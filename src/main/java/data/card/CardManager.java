package data.card;

import java.util.ArrayList;
import java.util.List;

public class CardManager {
    private List<Card> cards;
    private static final String MARK_NOT_USED = "[ ]";
    private static final String MARK_USED = "[x]";
    private static final int NUM_OF_CARDS_PER_GAME = 10;     // 10 may need to be changed later on

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

    /**
     * find card that is not collected within a certain range.
     * @return the index of the card in the ArrayList, if the cards in the range are all collected, return -1
     */
    public int getCardPosition(int startID) {
        int index = -1;
        int startIndex = startID - 1;
        for (int i = startIndex; i < startIndex + NUM_OF_CARDS_PER_GAME; i++) {
            if (!cards.get(i).checkIfCollected()) {
                index = i;
            }
        }
        return index;
    }


    /**
     * find the index of the card in the cardList by cardID.
     * @return -1 if the cardID is not found
     */
    public int findCard(int cardID) {
        int len = cards.size();
        int index = -1;
        for (int i = 0; i < len; i++) {
            if (cards.get(i).getCardID() == cardID) {
                index = i;
                break;
            }
        }
        return index;
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

    public void setAsCollected(int index) {
        Card cardToCollect = cards.get(index);
        cardToCollect.setAsCollected();

    }

    /**
     * enter this function means you are sure to win the card and there are enough cards
     * print the card message when you win the card.
     * @param cardsToTransfer  CardsCollectd
     * @param index  the index of the card in the ArrayList of the CardsToBeCollected
     */
    public void transferTo(CardManager cardsToTransfer, int index) {
        Card cardToCollect = cards.get(index).setAsCollected();
        System.out.println("Great, this is the card you win!");
        System.out.println(cardsToTransfer);
        cards.set(index, cardToCollect);   //set the card as "collected" in the cards list
        cardsToTransfer.add(cardToCollect);   //add the "collected" card to cardsToTransfer
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