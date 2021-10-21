package data.game;

import java.util.ArrayList;

public class GameRecord {

    public static ArrayList<Game> games;

    public GameRecord() {
        this.games = new ArrayList<Game>();
    }

    public GameRecord(ArrayList<Game> games) {
        this.games = games;
    }

    public void addGame(Game game) {
        this.games.add(game);
    }

    public Game deleteGame(int index) {
        Game removedGame = this.games.get(index);
        this.games.remove(index);
        return removedGame;
    }
}
