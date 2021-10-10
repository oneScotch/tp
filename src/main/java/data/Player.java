package data;

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
    private final List<Card> cards;
    /**
     * The used cards list.
     */
    private final List<Card> cardsused;


    //TODO 暂时为了过test
    private Player(String name, List<Game> games, List<Card> cards, List<Card> cardsused) {
        this.name = name;
        this.games = games;
        this.cards = cards;
        this.cardsused = cardsused;

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
