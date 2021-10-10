package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import data.game.Game;
import data.game.GameRecord;
import data.game.HangmanGame;

public class Storage {
    private static File save;
    private static String path;

    public Storage(String filePath) {
        path = filePath;
    }

    public static GameRecord load() {
        ArrayList<Game> games = new ArrayList<Game>();
        try {
            File record = new File("data");
            if (!record.exists()) {
                record.mkdir();
            }

            save = new File(path);
            if (!save.exists()) {
                save.createNewFile();
            }

            Scanner s = new Scanner(save);

            while (s.hasNext()) {
                String reading = s.nextLine();
                if (reading.equals("Hang man")) {
                    games.add(new HangmanGame());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new GameRecord(games);
    }

    public void writeToFile(GameRecord games) throws IOException {

        try {
            FileWriter fw = new FileWriter(save);
            for (Game game : games.games) {
                if (game.getName().equals("Hang Man")) {
                    fw.write("Hang Man\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
