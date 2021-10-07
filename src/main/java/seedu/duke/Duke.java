package seedu.duke;

import UI.GameMainMenu;
import message.Messages;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        boolean isExit = false;
        GameMainMenu ui = new GameMainMenu();

        ui.showWelcome();

        while(!isExit) {
            isExit = true;
        }

        ui.showExitMessage();
    }

    public String showByeMessage() {
        return Messages.MESSAGE_GOODBYE;
    }


}
