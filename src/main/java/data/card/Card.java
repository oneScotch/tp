package data.card;


import java.io.Serializable;

public class Card implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    private String content;
    private boolean isUsed;

    public Card(String content) {
        this.content = content;
        this.isUsed = false;
    }

    public String getContent() {
        return content;
    }

    public boolean checkIfIsUsed() {
        return isUsed;
    }

    public void setAsUsed() {
        isUsed = true;
    }

    @Override
    public String toString() {
        return getContent();
    }
}