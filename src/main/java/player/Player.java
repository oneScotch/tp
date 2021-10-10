package player;

import card.CardManager;

public class Player {
    private CardManager cardsCollected = new CardManager();
    private CardManager cardsToBeCollected = new CardManager();

    public void winCard() {
        cardsToBeCollected.transferTo(cardsCollected);
    }

    public void buyTip(int cardID) {
        boolean canBeExchanged = cardsCollected.exchange(cardID);
        if (canBeExchanged) {
            System.out.println("Sure, you successfully use one card to get the tip!");
        } else {
            System.out.println("Ops, it seems that you have already used that card, please choose another one");
        }
    }
}
