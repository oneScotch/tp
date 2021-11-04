package ui.game;

import data.Player;
import data.game.GuessingNumGame;
import data.game.HangmanGame;
import data.game.QuizGame;
import data.game.TreasureHuntGame;
import ui.Menu;
import utils.Errors;
import utils.IO;
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

        startHangMan();

        startQuizGame();

        startGuessNum();

        startTreasureHunt();

        System.out.println(Strings.FINISH_EASY_MODE);

        // TODO: SAVE PLAYER
    }

    /**
     * Displays the welcome message and the background.
     */
    private void welcome() {
        System.out.println(Strings.EASY_LOGO);
        System.out.println(Strings._GAME_WELCOME_MESSAGE);
        System.out.println();
    }

    private void startHangMan() {
        int playStatus = 2;
        int cardId;

        while (playStatus == 2) {
            System.out.println(Strings.HANG_MAN_START);
            HangmanGame hangmanGame = new HangmanGame();
            cardId = hangmanGame.execute(true);
            Player.winCard(cardId);

            // TODO: STORE GAME RECORD

            System.out.print(hangmanGame.getName());
            playStatus = setPlayStatus();

            if (playStatus == 1) {
                return;
            } else if (playStatus == 3) {
                // TODO: EXIT TO GAME MENU
            }

        }
    }

    private void startQuizGame() {
        int playStatus = 2;
        int cardId;

        while (playStatus == 2) {
            System.out.println(Strings.QUIZ_START);
            QuizGame quizGame = new QuizGame("1");
            cardId = quizGame.execute(true);
            Player.winCard(cardId);

            // TODO: STORE GAME RECORD

            System.out.print(quizGame.getName());
            playStatus = setPlayStatus();

            if (playStatus == 1) {
                return;
            } else if (playStatus == 3) {
                // TODO: EXIT TO GAME MENU
            }

        }
    }

    private void startGuessNum() {
        int playStatus = 2;
        int cardId;

        while (playStatus == 2) {
            System.out.println(Strings.GUESS_NUM_START);
            GuessingNumGame guessingNumGame = new GuessingNumGame();
            cardId = guessingNumGame.execute(true);
            Player.winCard(cardId);

            // TODO: STORE GAME RECORD

            System.out.print(guessingNumGame.getName());
            playStatus = setPlayStatus();

            if (playStatus == 1) {
                return;
            } else if (playStatus == 3) {
                // TODO: EXIT TO GAME MENU
            }

        }
    }

    private void startTreasureHunt() {
        int playStatus = 2;
        int cardId;

        while (playStatus == 2) {
            System.out.println(Strings.TREASURE_HUNT_START);
            TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
            cardId = treasureHuntGame.execute(true);
            Player.winCard(cardId);

            // TODO: STORE GAME RECORD

            System.out.print(treasureHuntGame.getName());
            playStatus = setPlayStatus();

            if (playStatus == 1) {
                return;
            } else if (playStatus == 3) {
                // TODO: EXIT TO GAME MENU
            }

        }
    }

    private int setPlayStatus() {
        int playStatus;

        while (true) {
            playStatus = IO.readInt(in, Strings.GAME_END_SIGN);
            if (playStatus == 1 || playStatus == 2 || playStatus == 3) {
                break;
            }
            Errors.print(Integer.toString(playStatus), Strings.ERR_PLAY_INVALID_NUMBER);
        }

        return playStatus;
    }
}

