package UI;
import message.Messages;
public class GameMainMenu {
    public void showWelcome() {
        showToUser(Messages.MESSAGE_WELCOME,  Messages.MESSAGE_GREET);

    }

    public void showExitMessage() {
        showToUser(Messages.MESSAGE_GOODBYE);
    }

    private void showToUser(String... message) {
        for (String m : message) {
            String formatted = m.replace("\n", System.lineSeparator());
            System.out.println(formatted);
        }
    }
}
