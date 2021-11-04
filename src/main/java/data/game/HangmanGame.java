package data.game;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame extends Game implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    // Coding Terminologies
    public static final String[] EASYWORDS = {"python", "argument", "arrays", "loops", "java", "linux",
            "statement", "variable", "while"};

    public static final String[] HARDWORDS = {"c", "algorithm", "oop"};
    public static final String name = "Hangman";
    public static final Random RANDOM = new Random();
    public static final int MAX_TRIES = 5;
    public static final String LOGO = "\n"
            + "        .-------------------------------------------------------------------------------.\n"
            + "        |      _      _                                                                  |\n"
            + "        |     | |    | |   __ _    _ __      __ _        /\\/\\      __ _    _ __          |\n"
            + "        |     | |----| |  / _  |  |  _ \\    / _` |      /    \\    / _  |  |  _  \\        |\n"
            + "        |     | |----| | | (_| |  | | | |  | (_| |     / /\\/\\ |  | (_| |  | | | |        |\n"
            + "        |     |_|    |_|  \\_ _ |  |_| |_|   \\__, |     \\/    \\/   \\_ _ |  |_| |_|        |\n"
            + "        |                                    |___/                                       |\n"
            + "        .--------------------------------------------------------------------------------.\n";
    public static final String GAME_RULES = "The purpose of this game is to guess an coding terminology, "
            + "secretly chosen by the application\n\n"
            + "You have to guess one letter at a time and you can have " + MAX_TRIES + " wrong attempts\n\n"
            + "Enter a lower-case letter and don't forget to enter key after each guess\n\n"
            + "Let's play the game!\n\n";
    Scanner in = new Scanner(System.in);
    private String secretWord;
    private int length;
    private char[] guessWord;
    private int remainingTries;
    private static int startID = 31;
    private boolean difficultLevel;
    public HangmanGame() {
        this.difficultLevel = true;
        this.secretWord = chooseSecretWord();
        this.length = secretWord.length();
        this.remainingTries = MAX_TRIES;
        this.guessWord = new char[length];
    }

    @Override
    public int execute(boolean difficultLevel) {
        this.difficultLevel = difficultLevel;
        this.secretWord = chooseSecretWord();
        this.length = secretWord.length();
        this.remainingTries = MAX_TRIES;
        this.guessWord = new char[length];
        displayGameDetails();
        boolean isWin;
        if (difficultLevel) {
            HangmanGame hangmanGame = new HangmanGame();
            isWin = hangmanGame.playEasy();
        } else {
            HangmanGame hangmanGame = new HangmanGame();
            isWin = hangmanGame.playHard();
        }

        return isWin ? startID : 0;
    }

    @Override
    public String getName() {
        return "Hang Man";
    }

    public void displayGameDetails() {
        System.out.println(LOGO);
        System.out.println(GAME_RULES);
    }

    public boolean playEasy() {
        boolean isWin = false;
        replaceDashes();
        System.out.println("Your guess word is:");
        displayWord();

        while (this.remainingTries > 0) {
            System.out.println("\nEnter your guess letter:");
            char guessLetter = in.next().charAt(0);

            int guess = isGuessTrue(guessLetter);

            if (guess == 0) {
                remainingTries--;
                System.out.println("\nWhoops! that letter is not present in the word");
                displayMan(remainingTries);
            }
            if (guess == 1) {
                System.out.println("\nYay! You have found the letter");
            }
            if (guess == 2) {
                System.out.println("\nYou have already guessed this letter. Try something else!");
            }

            System.out.println("You can have " + this.remainingTries + " more wrong attempts");
            System.out.println("Your guess word is:");
            displayWord();

            if (secretWord.equals(new String(guessWord))) {
                System.out.println("\nCongratulation! You won.");
                isWin = true;   // changed
                break;
            }
        }
        if (!secretWord.equals(new String(guessWord))) {
            System.out.println("\nToo many Guesses! You have been hanged.");
            System.out.println("\nThe secret word was: " + this.secretWord);
        }
        return isWin;  // changed
    }

    public boolean playHard() {

        boolean isWin = false;
        replaceDashes();
        System.out.println("Your guess word is:");
        displayWord();

        while (this.remainingTries > 0) {
            System.out.println("\nEnter your guess letter:");
            char guessLetter = in.next().charAt(0);

            int guess = isGuessTrue(guessLetter);

            if (guess == 0) {
                remainingTries--;
                System.out.println("\nWhoops! that letter is not present in the word");
                displayMan(remainingTries);
            }
            if (guess == 1) {
                System.out.println("\nYay! You have found the letter");
            }
            if (guess == 2) {
                System.out.println("\nYou have already guessed this letter. Try something else!");
            }

            System.out.println("You can have " + this.remainingTries + " more wrong attempts");
            System.out.println("Your guess word is:");
            displayWord();

            if (secretWord.equals(new String(guessWord))) {
                System.out.println("\nCongratulation! You won.");
                isWin = true;   // changed
                break;
            }
        }
        if (!secretWord.equals(new String(guessWord))) {
            System.out.println("\nToo many Guesses! You have been hanged.");
            System.out.println("\nThe secret word was: " + this.secretWord);
        }
        return isWin;  // changed
    }

    // choose next word randomly
    public String chooseSecretWord() {
        if (difficultLevel) {
            return EASYWORDS[RANDOM.nextInt(EASYWORDS.length)];
        } else {
            return HARDWORDS[RANDOM.nextInt(HARDWORDS.length)];
        }

    }

    public void replaceDashes() {
        for (int i = 0; i < this.length; i++) {
            this.guessWord[i] = '-';
        }
    }

    public void displayWord() {
        for (int i = 0; i < this.length; i++) {
            System.out.print(this.guessWord[i]);
        }
        System.lineSeparator();
    }

    public int isGuessTrue(char letter) {
        int flag = 0;
        for (int i = 0; i < this.length; i++) {
            if (this.secretWord.charAt(i) == letter) {
                if (this.guessWord[i] == this.secretWord.charAt(i)) {
                    flag = 2;
                } else {
                    this.guessWord[i] = this.secretWord.charAt(i);
                    flag = 1;
                }
            }
        }
        return flag;
    }

    public void displayMan(int remainingGuess) {
        String[] bodyPart = {"", "", "", ""};

        if (remainingGuess == 0) {
            bodyPart[0] = "( )";
            bodyPart[1] = "/|\\";
            bodyPart[2] = "/|\\";
            bodyPart[3] = " |";
        }

        if (remainingGuess == 1) {
            bodyPart[0] = "( )";
            bodyPart[1] = "/|\\";
            bodyPart[2] = "/|\\";
        }

        if (remainingGuess == 2) {
            bodyPart[0] = "( )";
            bodyPart[1] = "/|\\";
        }

        if (remainingGuess == 3) {
            bodyPart[0] = "( )";
        }

        String hangMan = "--------------"
                + "\n  |       " + bodyPart[3]
                + "\n  |       " + bodyPart[3]
                + "\n  |       " + bodyPart[0]
                + "\n  |       " + bodyPart[1]
                + "\n  |       " + bodyPart[2]
                + "\n  |\n"
                + "\n  |\n"
                + "--------------\n";

        System.out.println(hangMan);
    }

    public boolean startNewGame() {
        System.out.println("\nWould you like to play again? [y/n]");
        char again = in.next().charAt(0);
        return again == 'y';
    }
}