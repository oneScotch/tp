package data.game;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GuessingNumGame extends Game {
    public static final String name = "GuessingNumber";
    public static final int MAX_NUM = 100;
    public static final int MAX_TRIES = 8;
    public static final String LOGO = "";
    public static final String GAME_RULES = "The purpose of the game is to guess the secret number. "
            + "You have to guess a number between 0 to " + MAX_NUM + " in a maximum of "
            + MAX_TRIES + " attempts, if the guess is not correct, a tip will be given telling"
            + "whether the number you guess is smaller or larger than the secret number.";
    private final int secretNum;
    private int remainingTries;
    private static int startID = 11;

    Scanner in = new Scanner(System.in);

    public GuessingNumGame() {
        this.secretNum = generateSecretNum() % MAX_NUM;
        this.remainingTries = MAX_TRIES;
    }

    @Override
    public int execute() {
        displayGameDetails();
        GuessingNumGame g = new GuessingNumGame();
        boolean isWin = g.play();
        return isWin ? startID : 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int generateSecretNum() {
        final double randomNum = Math.random();
        return (int)(randomNum * 100);
    }

    public boolean play() {
        boolean isWin = false;
        while (this.remainingTries > 0) {
            Scanner in = new Scanner(System.in);
            printEnterMessage();
            int input = -1;
            boolean isCorrectInput = false;
            try {
                input = in.nextInt();
                if (input > MAX_NUM || input < 0) {
                    printInputOutOfRange();
                } else {
                    isCorrectInput = true;
                }
            } catch (InputMismatchException i) {
                System.out.println("Sorry, please enter an integer between 0 to " + MAX_NUM + ":(\n");
            }

            if (isCorrectInput) {
                this.remainingTries--;
                if (input == this.secretNum) {
                    printSuccessfulMessage();
                    isWin = true;
                } else {
                    printFailingMessage(input);
                }
            }
        }
        printEndingMessage();
        return isWin;
    }

    public void displayGameDetails() {
        System.out.println(GAME_RULES);
    }

    public void printSuccessfulMessage() {
        System.out.println("Nice! You have guess the secret number " + this.secretNum
                + " in " + (MAX_TRIES - this.remainingTries) + " attempts.\n");
    }

    public void printFailingMessage(int guess) {
        if (guess > this.secretNum) {
            System.out.println("This number is larger than the secret number :(");
        } else {
            System.out.println("This number is smaller than the secret number :(");
        }
        System.out.println("Come on! You still have " + this.remainingTries + " chances.\n");
    }

    public void printEndingMessage() {
        System.out.println("Oops! You have used up the chances. Game over :(");
    }

    public void printEnterMessage() {
        System.out.println("Please guess a number: \n");
    }

    public void printInputOutOfRange() {
        System.out.println("Please enter a number between 0 to " + MAX_NUM + ":(\n");
    }
}
