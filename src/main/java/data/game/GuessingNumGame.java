package data.game;

import java.io.Serializable;
import java.util.Scanner;
import java.util.InputMismatchException;

public class GuessingNumGame extends Game implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    public static final String name = "GuessingNumber";
    public static final int MAX_NUM = 100;
    public static final String LOGO = " _____ _     _____ ____  ____  _  _      _____   "
            + "_      _     _      ____  _____ ____ \n"
            + "/  __// \\ /\\/  __// ___\\/ ___\\/ \\/ \\  /|/  __/  / "
            + "\\  /|/ \\ /\\/ \\__/|/  _ \\/  __//  __\\\n"
            + "| |  _| | |||  \\  |    \\|    \\| || |\\ ||| |  _  | |\\"
            + " ||| | ||| |\\/||| | //|  \\  |  \\/|\n"
            + "| |_//| \\_/||  /_ \\___ |\\___ || || | \\||| |_//  | | \\"
            + "||| \\_/|| |  ||| |_\\\\|  /_ |    /\n"
            + "\\____\\\\____/\\____\\\\____/\\____/\\_/\\_/  \\|\\____\\"
            + "  \\_/  \\|\\____/\\_/  \\|\\____/\\____\\\\_/\\_\\";
    public static final String GAME_RULES = "The purpose of the game is to guess the secret number. "
            + "You have to guess a number between 0 to " + (MAX_NUM - 1) + " in limited attempts, "
            + "if the guess is not correct, a tip will be given telling "
            + "whether the number you guess is smaller or larger than the secret number.";
    private final int maxTries;
    private final int secretNum;
    private int remainingTries;
    private static int startID = 11;

    Scanner in = new Scanner(System.in);

    /**
     * Constructs a guessing number game according to whether it is difficult.
     * @param boolean isDifficult identifies whether the game is at difficult level
     */
    public GuessingNumGame(boolean isDifficult) {
        this.secretNum = generateSecretNum() % MAX_NUM;
        if (!isDifficult) {
            this.maxTries = 20;
        } else {
            this.maxTries = 8;
        }
        this.remainingTries = maxTries;
    }

    /**
     * execute the game and return cardID collected.
     * @return cardID of the card to collect in the Player class; return 0 if lose
     */
    @Override
    public int execute(boolean isEasy) {
        displayGameDetails();
        GuessingNumGame g = new GuessingNumGame(0);
        boolean isWin = g.play();
        return isWin ? startID : 0;
    }

    /**
     * gets the name of this game.
     * @return string the name of the game
     *
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * gets a tip about the secret number.
     */
    public void getTip() {
        int lowerBound = 0;
        int upperBound = MAX_NUM - 1;
        if (secretNum > MAX_NUM / 5) {
            lowerBound = secretNum - MAX_NUM / 5;
        }
        if (secretNum < 4 * MAX_NUM / 5) {
            upperBound = secretNum + MAX_NUM / 5;
        }
        System.out.println("The secret number is between " + lowerBound + " and "
                + upperBound);
    }

    /**
     * randomly generated a number between 0 and max number minus 1 to be guessed.
     * @return the randomly generated integer
     */
    public int generateSecretNum() {
        final double randomNum = Math.random();
        return (int) (randomNum * 100);
    }

    /**
     * executes the main part of game which the user can play with.
     * @return boolean whether the user has won this game
     */
    public boolean play() {
        boolean isWin = false;
        while (this.remainingTries > 0) {
            Scanner in = new Scanner(System.in);
            printEnterMessage();
            int input = -1;
            boolean isCorrectInput = true;
            try {
                input = in.nextInt();
                assert input < MAX_NUM && input >= 0 : "Please enter a number between 0 to " + MAX_NUM + ":(\n";
            } catch (InputMismatchException i) {
                System.out.println("Sorry, please enter an integer between 0 to " + MAX_NUM + ":(\n");
                isCorrectInput = false;
            }

            if (isCorrectInput) {
                this.remainingTries--;
                if (input == this.secretNum) {
                    printSuccessfulMessage();
                    isWin = true;
                    return isWin;
                } else {
                    printFailingMessage(input);
                }
            }
        }
        printEndingMessage();
        return isWin;
    }

    /**
     * prints the message that the user have guessed the secret number.
     */
    public void printSuccessfulMessage() {
        System.out.println("Nice! You have guess the secret number " + this.secretNum
                + " in " + (maxTries - this.remainingTries) + " attempts.\n");
    }

    /**
     * prints the rule of the guessing number game.
     */
    public void displayGameDetails() {
        System.out.println(LOGO);
        System.out.println(GAME_RULES);
    }

    /**
     * prints the message that the guess is incorrect and displays the number of remaining tries.
     */
    public void printFailingMessage(int guess) {
        if (guess > this.secretNum) {
            System.out.println("This number is larger than the secret number :(");
        } else {
            System.out.println("This number is smaller than the secret number :(");
        }
        System.out.println("Come on! You still have " + this.remainingTries + " chances.\n");
    }

    /**
     * prints the message that the tries have been used up.
     */
    public void printEndingMessage() {
        System.out.println("Oops! You have used up the chances. Game over :(");
    }

    /**
     * prints the message telling the user to enter a number for guessing.
     */
    public void printEnterMessage() {
        System.out.println("Please guess a number: \n");
    }
}
