package data.game;

import java.util.Scanner;
import java.util.InputMismatchException;

public class GuessingNumGame extends Game {
    public static final String name = "GuessingNumber";
    public static final int MAX_NUM = 100;
    public static final int MAX_TRIES = 8;
    public static final String LOGO = "";
    public static final String GAME_RULES = "The purpose of the game is to guess the secrect number. "
            + "You have to guess a number between 0 to " + MAX_NUM + " in a maximum of "
            + MAX_TRIES +" attemps, if the guess is not correct, a tip will be given telling"
            + "whether the number you guess is smaller or larger than the secrect number.";
    private final int secrectNum;
    private int remainingTries;

    Scanner in = new Scanner(System.in);

    public GuessingNumGame() {
        this.secrectNum = generateSecrectNum() % MAX_NUM;
        this.remainingTries = MAX_TRIES;
    }

    @Override
    public void execute() {
        displayGameDetails();
        GuessingNumGame g = new GuessingNumGame();
        g.play();
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int generateSecrectNum() {
        final double randomNum = Math.random();
        return (int)(randomNum*100);
    }

    public void play() {
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
                if (input == this.secrectNum) {
                    printSuccessfulMessage();
                    return;
                } else {
                    printFailingMessage(input);
                }
            }
        }
        printEndingMessage();
    }

    public void displayGameDetails() {
        System.out.println(GAME_RULES);
    }

    public void printSuccessfulMessage() {
        System.out.println("Nice! You have guess the secrect number " + this.secrectNum
                + " in " + (MAX_TRIES - this.remainingTries) + " attemps.\n");
    }

    public void printFailingMessage(int guess) {
        if (guess > this.secrectNum) {
            System.out.println("This number is larger than the secrect number :(");
        } else {
            System.out.println("This number is smaller than the secrect number :(");
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
