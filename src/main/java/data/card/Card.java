package data.card;

public class Card {
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