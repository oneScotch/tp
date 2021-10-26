package data.game;

import java.io.Serializable;
import java.util.ArrayList;

public class GameRecord implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;

    public static ArrayList<Game> games;

    public GameRecord() {
        this.games = new ArrayList<Game>();
    }

    public GameRecord(ArrayList<Game> games) {
        this.games = games;
    }

    /**
     * Prints the games completed in game record.
     */
    public void checkRecord() {
        if (this.games.size() == 0) {
            System.out.println("You have not completed any game yet, come on!\n");
        } else {
            System.out.println("Here is your recorded game progress: \n");
            int index = 1;
            for (Game game : this.games) {
                System.out.println(index++ + ". " + game.getName());
            }
        }
    }

    /**
     * Adds a game to the game record after completed.
     */
    public void addGame(Game game) {
        this.games.add(game);
    }

    /**
     * Deletes a game in the record according to a given index.
     * @param index The index of the game record to be deleted.
     * @return Game the game deleted.
     */
    public Game deleteGame(int index) throws IndexOutOfBoundsException {
        Game removedGame = this.games.get(index);
        this.games.remove(index);
        return removedGame;
    }
}
