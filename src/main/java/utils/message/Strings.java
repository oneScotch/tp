package utils.message;

/**
 * String constants used in the program.
 */
public class Strings {
    private Strings() {
        // Disallow instantiation
    }

    // Application name and version
    public static final String APP_NAME_LONG = "A text-based code game for children ages 6 and up(Code Hunt)";
    public static final String APP_NAME_SHORT = "Code Hunt";
    public static final String APP_VERSION = "1.0";

    // Prompt
    public static final String MAIN_PROMPT = String.format("%s > ", APP_NAME_SHORT);
    public static final String HELP_MESSAGE = "Type \"help\" to see a list of available commands.";
    public static final String SUBCOMMAND_HELP_MESSAGE = "Please provide a subcommand.";

    // Main menu messages
    private static final String logo = "____          _        _   _             _  \n"
            + " / ___|___   __| | ___  | | | |_   _ _ __ | |_ \n"
            + "| |   / _ \\ / _` |/ _ \\ | |_| | | | | '_ \\| __|\n"
            + "| |__| (_) | (_| |  __/ |  _  | |_| | | | | |_\n"
            + " \\____\\___/ \\__,_|\\___| |_| |_|\\__,_|_| |_|\\__|\n";
    public static final String MAIN_WELCOME_MESSAGE = logo + String.format("Welcome to %s.Are you ready to play?\n", APP_NAME_LONG) + HELP_MESSAGE;
    public static final String MAIN_ABOUT_MESSAGE = String.format("%s (Version %s)\n", APP_NAME_LONG, APP_VERSION)
            + "Developed by Wang Chuhan, Jiang Yuxin, Xue Yushan, Wang Ruisi, Peng Wenxuan";
    public static final String MAIN_EXIT_MESSAGE = String.format("Thank you playing %s ! Have a nice day~~", APP_NAME_SHORT);


    // Error messages
    public static final String ERR_UNKNOWN_COMMAND = "Unknown command.\n" + HELP_MESSAGE;
    public static final String ERR_UNEXPECTED_INPUT = "Unexpected input.\n" + HELP_MESSAGE;
    public static final String ERR_EMPTY_STRING = "Please provide an input.";
    public static final String ERR_UNKNOWN_INPUT = "Please enter one of the options provided.";
    public static final String ERR_INPUT_MISMATCH_INT_EXPECTED = "Please enter an integer.";
    public static final String ERR_INPUT_MISMATCH_DOUBLE_EXPECTED = "Please enter a number.";
    public static final String ERR_BOOLEAN_INPUT = "Please enter \"y\" for yes, \"n\" for no.";
    public static final String ERR_DATE_INPUT = "Please enter a valid date.";
    public static final String ERR_TIME_INPUT = "Please enter a valid time.";

}
