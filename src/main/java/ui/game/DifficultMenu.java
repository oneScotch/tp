package ui.game;

import data.Player;
import data.game.GuessingNumGame;
import data.game.HangmanGame;
import data.game.QuizGame;
import data.game.TreasureHuntGame;
import ui.Menu;
import utils.StringParser;
import utils.message.Strings;

import java.util.Scanner;

/**
 * The difficult game mode menu of the CodeHunt game.
 */
public class DifficultMenu extends Menu {
    /**
     * The {@code Scanner} for the menu to consume input.
     */
    private final Scanner in;

    /**
     * The parser with the rest of the command to be consumed by the menu.
     */
    private final StringParser parser;

    /**
     * Creates a new instance of the difficult menu.
     */
    public DifficultMenu(Scanner in, StringParser parser) {
        this.in = in;
        this.parser = parser;
    }

    @Override
    public void enter() {
        int cardId;

        welcome();

        System.out.println(Strings.HANG_MAN_START);
        HangmanGame hangmanGame = new HangmanGame();
        cardId = hangmanGame.execute();
        Player.winCard(cardId);

        System.out.println("Well done! You have finished the first level!");

        System.out.println(Strings.QUIZ_START);
        QuizGame quizGame = new QuizGame("1");
        cardId = quizGame.execute();
        Player.winCard(cardId);

        System.out.println(Strings.GUESS_NUM_START);
        GuessingNumGame guessingNumGame = new GuessingNumGame();
        cardId = guessingNumGame.execute();
        Player.winCard(cardId);

        System.out.println(Strings.TREASURE_HUNT_START);
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        cardId = treasureHuntGame.execute();
        Player.winCard(cardId);

        System.out.println(Strings.FINISH_EASY_MODE);
    }

    /**
     * Displays the welcome message and the background.
     */
    private void welcome() {
        System.out.println(Strings.EASY_LOGO);
        System.out.println(Strings.EASY_GAME_WELCOME_MESSAGE);
        System.out.println();
    }
}
