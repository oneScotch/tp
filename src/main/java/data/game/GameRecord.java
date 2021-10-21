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
}
