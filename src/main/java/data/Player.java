package data;

import data.card.CardManager;
import data.game.Game;
import data.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A player.
 */
public class Player {
    /**
     * The name of the restaurant.
     */
    private final String name;

    /**
     * The list of games.
     */
    private final List<Game> games;

    /**
     * The cards list.
     */
    //private final List<Card> cards;
    /**
     * The used cards list.
     */
    //private final List<Card> cardsused;

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

    //TODO 暂时为了过test
    private Player(String name, List<Game> games, CardManager cardsCollected, CardManager cardsToBeCollected) {
        this.name = name;
        this.games = games;
        this.cardsCollected = cardsCollected;
        this.cardsToBeCollected = cardsToBeCollected;

    }

    public void addCard() {

    }

    public void deleteCard() {

    }

    public void getCard() {

    }

    public void initPlayer() {
        //initTables();
        //initCards();
    }

    public void loadPlayer() {

    }

    public void savePlayer() {
    }

}
