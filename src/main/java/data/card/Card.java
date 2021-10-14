package data.card;


import utils.message.Strings;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    private String content;
    private boolean isUsed;
    private int cardID;
    private boolean isCollected;

    public Card(String content, int cardID) {
        this.content = content;
        this.cardID = cardID;
        this.isUsed = false;
        this.isCollected = false;
    }

    public Card(Card card) {
        this(card.getContent(), card.getCardID());
    }

    public int getCardID() {
        return cardID;
    }

    public String getContent() {
        return content;
    }

    public boolean checkIfIsUsed() {
        return isUsed;
    }

    public boolean checkIfCollected() {
        return isCollected;
    }

    public void setAsUsed() {
        isUsed = true;
    }

    public Card setAsCollected() {
        Card card = new Card(this);
        card.isCollected = true;
        return card;
    }

    @Override
    public String toString() {
        return Strings.CARD_DIVIDER_MESSAGE + getContent() + Strings.CARD_DIVIDER_MESSAGE;
    }
}