package data;

import data.card.CardManager;
import data.game.Game;
import data.card.Card;
import utils.Errors;
import utils.IO;
import utils.message.Strings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;

/**
 * A player.
 */
public class Player {
    public static final Path DATAPATH = Paths.get(System.getProperty("user.dir"), "data");
    public static String PLAYER_FILE_NAME;
    /**
     * The name of the restaurant.
     */
    private static String name;

    /**
     * The list of games.
     */
    private static List<Game> games;

    /**
     * The cards list.
     */
    private static CardManager cardsCollected = new CardManager();
    private static CardManager cardsToBeCollected = new CardManager();

    //private final List<Card> cards;
    /**
     * The used cards list.
     */
    //private final List<Card> cardsused;



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

    public static void showGameProgress() {
        for(Game game : games) {
            System.out.println(game.getName());
        }
    }

    public static void deleteCard() {

    }

    public static void getCard() {

    }


    public static void savePlayer() {

        if (!Files.exists(DATAPATH)) {
            System.out.println("Data folder not found!");
            File dir = new File(DATAPATH.toString());
            if (dir.mkdir()) {
                System.out.println("Directory " + DATAPATH + " created...");
            }
        }

        Object[] playerMember = {name, games, cardsCollected, cardsToBeCollected};

        Path saveFileName = Paths.get(DATAPATH.toString(), PLAYER_FILE_NAME);
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(saveFileName.toString());
            oos = new ObjectOutputStream(fos);
            oos.writeObject(playerMember);
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Load restaurant previous save state.
     */
    @SuppressWarnings("unchecked")
    public static void loadPlayer(String playerId) {
        PLAYER_FILE_NAME = playerId + ".dat";
        Object[] playerMember = null;
        Path saveData = Paths.get(DATAPATH.toString(), PLAYER_FILE_NAME);
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(saveData.toString());
            ois = new ObjectInputStream(fis);
            playerMember = (Object[]) ois.readObject();
            if (playerMember != null) {
                name = (String) playerMember[0];
                games = (ArrayList<Game>) playerMember[1];
                cardsCollected = (CardManager) playerMember[2];
                cardsToBeCollected = (CardManager) playerMember[3];
            }
            ois.close();
        } catch (IOException ex) {
            Errors.print(PLAYER_FILE_NAME + Strings.ERR_PLAYER_FILE_NOTFOUND_MESSAGE);
            initPlayer();
        } catch (ClassCastException | ClassNotFoundException ex) {
            System.out.println(String.format(Strings.ERR_PLAYER_FILE_CORRUPTED_MESSAGE, PLAYER_FILE_NAME));
            initPlayer();
        }

    }

    public static void loadPlayer() {
        initPlayer();
    }

    /**
     * Initialise player static members.
     */
    public static void initPlayer() {
        //TODO
        Scanner sc = new Scanner(System.in);
        name = IO.readString(sc, Strings.PLAYER_NAME_ENTER_PROMPT).trim();
        assert name == null : "Nothing is inputted!!";
        String playerId = name + Calendar.getInstance().hashCode();
        PLAYER_FILE_NAME = playerId + ".dat";
        System.out.println(String.format(Strings.PLAYER_ID_PROMPT, playerId));

        /*initGames();
        initCards();
        initCardsUsed();*/
    }

}
