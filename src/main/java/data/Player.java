package data;


import data.game.Game;
import data.card.CardManager;
import data.card.Card;
import utils.Errors;
import utils.IO;
import utils.message.Strings;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    public static List<Integer> collectedCardIDs = new ArrayList<>();
    // store the card id of the card collected -- for storage use
    public static List<Integer> usedCardIDs = new ArrayList<>();
    // store the card id of the card used to buy tips -- for storage use
    public static final int COLLECTED_LENGTH = 10;
    public static final int USED_LENGTH = 5;

    /**
     * The name of the restaurant.
     */
    private static String name;

    /**
     * The list of games.
     */
    private static List<Game> games;

    /**
     * The used cards list.
     */
    private static CardManager cardsCollected;

    /**
     * The cards list.
     */
    private static CardManager cardsToBeCollected;


    /**
     * startID indicates which game's card is collected.
     *
     * @param startID the startID return by the "execute()" method of games, startID = 0 indicates loose
     * @return boolean: if win card successfully
     */
    public static boolean winCard(int startID) {
        if (startID == 0) {
            return false;   // if startID = 0 , actually should not enter this method, need to be solved in the menu
        }
        int index = cardsToBeCollected.getCardPosition(startID);
        boolean canBeCollected = index == -1 ? false : true;
        if (canBeCollected) {
            int cardID = cardsToBeCollected.transferTo(cardsCollected, index);
            collectedCardIDs.add(cardID);
            // print card
        } else {
            System.out.println("Oops! You have already collected all the cards for this game.");
        }

        return canBeCollected;   // return false means not enough cards to collect for this game
    }

    public static void buyTip(int cardID) {
        boolean canBeExchanged = cardsCollected.exchange(cardID);
        if (canBeExchanged) {
            // todo : tip-related method
            usedCardIDs.add(cardID);
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
        if (games == null) {
            System.out.println(Strings.NO_GAME_RECORD_MESSAGE);
        } else {
            for (Game game : games) {
                System.out.println(game.getName());
            }
        }
    }

    public static void showCollectedCards() {
        cardsCollected.listCards();
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
     * save the card information by cardID.
     *
     * @param path the file path to save the data
     */
    public static void saveCard(String path) {
        try {
            String collectedIDs = "Collected:";
            String usedIDs = "Used:";
            for (int i = 0; i < collectedCardIDs.size(); i++) {
                collectedIDs += collectedCardIDs.get(i);
                collectedIDs += " ";
            }
            for (int i = 0; i < usedCardIDs.size(); i++) {
                usedIDs += usedCardIDs.get(i);
                usedIDs += " ";
            }
            FileWriter fw = new FileWriter(path);
            fw.write(collectedIDs);
            fw.write(System.getProperty("line.separator"));
            fw.write(usedIDs);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadCard(String path) throws FileNotFoundException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        // Assume the first line stores the card message
        String collectedIDs = sc.nextLine();
        String usedIDs = sc.nextLine();
        String[] collectedIdArray = collectedIDs.substring(COLLECTED_LENGTH).split(" ");
        for (int i = 0; i < collectedIdArray.length; i++) {
            int collectedCardID = Integer.parseInt(collectedIdArray[i]);
            cardsToBeCollected.transferTo(cardsCollected, cardsToBeCollected.findCard(collectedCardID));
            collectedCardIDs.add(collectedCardID);
        }
        String[] usedIdArray = usedIDs.substring(USED_LENGTH).split(" ");

        for (int i = 0; i < usedIdArray.length; i++) {
            int usedCardID = Integer.parseInt(usedIdArray[i]);
            buyTip(usedCardID);
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

        */
        initCards();

        //init Cards(), CardsUsed()
    }

    /**
     * Initial cards for diff games, each with a unique cardID.
     * Initial cardManager for cards and used cards.
     */
    private static void initCards() {
        ArrayList<Card> cardsInit = new ArrayList<>();
        // Init cards for diff games
        // For game Treasure
        cardsInit.add(new Card("'Sequence' is the order that commands are executed by a computer", 1));
        cardsInit.add(new Card("'Sequence' is a set of logical steps carried out in order.", 2));
        cardsInit.add(new Card("Binary Search algorithm could only be implemented over a sorted array.", 3));
        cardsInit.add(new Card("Small unsorted arrays would take considerate time in sorting "
                + "and then searching the desired element. So, binary search is not preferred in such cases.", 4));
        cardsInit.add(new Card("Binary search has better time complexity and thus takes less compilation time", 5));
        cardsInit.add(new Card("In Java, everything is an Object. Java can be easily extended "
                + "since it is based on the Object model.", 6));
        cardsInit.add(new Card("'break' terminates the loop or switch statement and transfers execution "
                + "to the statement immediately following the loop or switch.", 7));
        cardsInit.add(new Card("'+' can be used to add values on either side of the operator.", 8));
        cardsInit.add(new Card("'+=' add AND assignment operator. It adds right operand to the left "
                + "operand and assign the result to left operand.", 9));
        cardsInit.add(new Card("'*=' multiply AND assignment operator. It multiplies right operand with the "
                + "left operand and assign the result to left operand.", 10));

        // For game GuessingNum
        cardsInit.add(new Card("Binary search is an efficient algorithm"
                + " for finding an item from a sorted list of items.", 11));
        cardsInit.add(new Card("It works by repeatedly dividing in half the portion of the list"
                + " that could contain the item, until you've narrowed down the possible locations to just one.", 12));
        cardsInit.add(new Card("Binary Search algorithm could only be implemented over a sorted array.", 13));
        cardsInit.add(new Card("Small unsorted arrays would take considerate time in sorting "
                + "and then searching the desired element. So, binary search is not preferred in such cases.", 14));
        cardsInit.add(new Card("Binary search has better time complexity and thus takes less compilation time", 15));
        cardsInit.add(new Card("Binary search is used to search element in a given "
                + "sorted array with more efficiency.", 16));
        cardsInit.add(new Card("'&& (logical and)' is Called Logical AND operator. If both the operands are non-zero,"
                + " then the condition becomes true.", 17));
        cardsInit.add(new Card("'=' is a simple assignment operator. Assigns values from right side operands "
                + "to left side operand.", 18));
        cardsInit.add(new Card("The array elements are accessed through the index. Array indices are 0-based; "
                + "that is, they start from 0 to arrayRefVar.length-1.", 19));
        cardsInit.add(new Card("When processing array elements, we often use either for loop or foreach "
                + "loop because all of the elements in "
                + "an array are of the same type and the size of the array is known.", 20));

        // For game QuizGame
        cardsInit.add(new Card("a variable is an abstract storage location paired "
                + "with an associated symbolic name", 21));
        cardsInit.add(new Card("An if else statement in programming is "
                + "a conditional statement that runs a different set of statements"
                + " depending on whether an expression is true or false", 22));
        cardsInit.add(new Card("Java provides a data structure, the array, which stores a fixed-size sequential "
                + "collection of elements of the same type.", 23));
        cardsInit.add(new Card("A constructor initializes an object when it is created. It has the same name as "
                + "its class and is syntactically similar to a method. However, constructors have "
                + "no explicit return type.", 24));
        cardsInit.add(new Card("Java defines several bitwise operators,"
                + " which can be applied to the integer types, "
                + "long, int, short, char, and byte.", 25));
        cardsInit.add(new Card("'modifier' defines the access type of the method and it is optional to use.", 26));
        cardsInit.add(new Card("You can use one if or else if statement inside another if or else "
                + "if statement(s)", 27));
        cardsInit.add(new Card("A switch statement allows a variable to be tested for equality against"
                + " a list of values.", 28));
        cardsInit.add(new Card("Short data type is a 16-bit signed two's complement integer", 29));
        cardsInit.add(new Card("Short data type can also be used to save memory as byte data type."
                + " A short is 2 times smaller than an integer", 30));

        // For game HangMan
        cardsInit.add(new Card("a loop is a sequence of instruction s that is continually "
                + "repeated until a certain condition is reached. ", 31));
        cardsInit.add(new Card("A for loop is a control flow statement for specifying iteration, "
                + "which allows code to be executed repeatedly.", 32));
        cardsInit.add(new Card("A \"While\" Loop is used to repeat a specific block of code an unknown"
                + " number of times, until a condition is met. ", 33));
        cardsInit.add(new Card("An array is a data structure, which can store a fixed-size collection of "
                + "elements of the same data type. ", 34));
        cardsInit.add(new Card("An algorithm is simply a set of steps used "
                + "to complete a specific task. ", 35));
        cardsInit.add(new Card("Array is the variable that stores elements of a similar data type. ", 36));
        cardsInit.add(new Card("Knowledge of how to use a certain condition, "
                + "such that a set of code should execute only if a certain condition is true. "
                + "In case of a false condition,"
                + " the program should exit and should not continue the code further.", 37));
        cardsInit.add(new Card("Class is like a template that contains state and behavior, "
                + "which corresponding to programming is field and method. "
                + "In Object-Oriented languages like Java, everything revolves around Class and Object.", 38));
        cardsInit.add(new Card("A function can take parameters and process them to get the desired output. ", 39));
        cardsInit.add(new Card("Abstraction, means ignoring the non-essential details of an object "
                + "and concentrating on its essential details.", 40));

        cardsCollected = new CardManager();
        cardsToBeCollected = new CardManager(cardsInit);

    }

}
