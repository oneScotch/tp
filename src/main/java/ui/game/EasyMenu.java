package ui.game;

import data.game.GuessingNumGame;
import data.game.HangmanGame;
import ui.Menu;
import utils.StringParser;
import utils.message.Strings;

import java.util.Scanner;

/**
 * The easy game mode menu of the CodeHunt game.
 */
public class EasyMenu extends Menu {
    /**
     * The {@code Scanner} for the menu to consume input.
     */
    private final Scanner in;

    /**
     * The parser with the rest of the command to be consumed by the menu.
     */
    private final StringParser parser;

    /**
     * Creates a new instance of the easy menu.
     */
    public EasyMenu(Scanner in, StringParser parser) {
        this.in = in;
        this.parser = parser;
    }

    @Override
    public void enter() {
        welcome();

        System.out.println("Level 1: Hang Man");
        HangmanGame hangmanGame = new HangmanGame();
        hangmanGame.execute();

        System.out.println("Well done! You have finished the first level!");

        System.out.println("Level 2: Guess Number");
        GuessingNumGame guessingNumGame = new GuessingNumGame();
        guessingNumGame.execute();

        System.out.println("Well done! You have finished the second level!");
    }

    /**
     * Displays the welcome message and the background,
     */
    private void welcome() {
        System.out.println(Strings.EASY_LOGO);
        System.out.println(Strings.EASY_GAME_WELCOME_MESSAGE);
        System.out.println();
    }
}

