package ui.game;

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
