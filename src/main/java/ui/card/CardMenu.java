package ui.card;

import data.Player;
import ui.Menu;
import ui.game.DifficultMenu;
import ui.game.EasyMenu;
import ui.game.GameMenu;
import ui.main.GameMainCommandType;
import ui.main.GameMainMenu;
import utils.Errors;
import utils.StringParser;
import utils.message.Strings;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The card menu of the CodeHunt System.
 */
public class CardMenu extends Menu {
    /**
     * The {@code Scanner} for the menu to consume input.
     */
    private final Scanner in;

    /**
     * The parser with the rest of the command to be consumed by the menu.
     */
    private final StringParser parser;

    /**
     * Creates a new instance of the card menu.
     */
    public CardMenu(Scanner in, StringParser parser) {
        this.in = in;
        this.parser = parser;
    }

    @Override
    public void enter() {
        welcome();
        try {
            if (!parser.hasMoreTokens()) {
                System.out.println(Strings.SUBCOMMAND_HELP_MESSAGE);
                help();
                return;
            }
            CardCommandType commandType = CardCommandType.getCommandType(parser.nextToken());
            if (commandType == null) {
                Errors.print(parser.getString(), Strings.ERR_UNKNOWN_COMMAND);
                return;
            }
            if (parser.hasMoreTokens()) {
                Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                return;
            }
            switch (commandType) {
            case LIST: {
                System.out.println(Strings.LIST_CARD_MESSAGE);
                if (parser.hasMoreTokens()) {
                    Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                }
                listCards();
                break;

            }
            case DELETE: {
                if (parser.hasMoreTokens()) {
                    Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                }
                // TODO: add delete function
                break;
            }
            case FIND: {
                // TODO: add find function
                break;
            }
            case HELP: {
                if (parser.hasMoreTokens()) {
                    Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                }
                help();
                break;
            }
            case BACK: {
                GameMenu gameMenu = new GameMenu(in, parser);
                gameMenu.enter();
                break;
            }
            case EXIT: {
                if (parser.hasMoreTokens()) {
                    Errors.print(parser.getRemaining(), Strings.ERR_UNEXPECTED_INPUT);
                }
                exit(true);
                return;
            }
            default: {
                break;
            }
            }
        } catch (NoSuchElementException e) {
            exit(false);
        }
    }

    /**
     * Displays the welcome message.
     */
    private void welcome() {
        System.out.println(Strings.CARD_WELCOME_MESSAGE);
        System.out.println();
    }

    /**
     * Display holding cards.
     */
    private void listCards() {
        Player.showCollectedCards();
    }

    /**
     * Displays available commands and their corresponding details.
     */
    private void help() {
        for (CardCommandType commandType : CardCommandType.values()) {
            System.out.printf("%-12s%s\n", commandType.getCommand(), commandType.getInfo());
        }
        System.out.println();
    }

    /**
     * The handler for command "exit". Does all necessary cleanups before the exit.
     * Note that the exit does not happen here. It is done by the {@code return}
     * statement under the {@link #enter()} method.
     *
     * @param promptToSave whether to prompt to save the current state or not
     */
    public void exit(boolean promptToSave) {
        // TODO: Implement "prompt to save" functionality
        System.out.println(Strings.MAIN_EXIT_MESSAGE);
        System.out.println();
    }
}
