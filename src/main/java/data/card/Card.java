package data.card;

import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    private String codeTip;
    private int cardType;

    public Card(String codeTip, int cardType) {
        this.codeTip = codeTip;
        this.cardType = cardType;
    }

    public String getCodeTip() {
        return codeTip;
    }

    public int getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return codeTip;
    }
}