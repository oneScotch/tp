package data.game;

import java.io.Serializable;
import java.util.Scanner;

public class TreasureHuntGame extends Game implements Serializable {
    private static final long serialVersionUID = -9135686500512288865L;
    public static final String name = "Treasure Hunt";
    public static final String GAME_RULES = "The purpose of the game is to control the treasure hunter's"
            + " to get the treasure. The position of the treasure is marked as '*' on the map, "
            + "you need to choose a proper sequence of movement to reach that position.";
    private static int[][] map;
    private static int startID = 1;   // the start ID

    public TreasureHuntGame() {
        this.map = new TreasureHuntMap().getMap();
    }

    /**
     * execute the game and return cardID collected.
     * @return cardID of the card to collect in the Player class; return 0 if lose
     */
    @Override
    public int execute(boolean isEasy) {
        displayGameDetails();
        displayMovements();
        displayMapDetails();
        displayMap();
        TreasureHuntGame g = new TreasureHuntGame();
        boolean isWin = g.play();    //changed
        return isWin ? startID : 0;    // return startID if win return 0 indicates lose
    }

    /**
     * gets the name of this game.
     * @return string the name of the game
     *
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * executes the main part of game which the user can play with.
     * @return boolean whether the user has won this game
     */
    public boolean play() {
        boolean isWin = false; // todo: add limited attempts to lose the game
        int x = 1;
        int y = 1;
        boolean find = false;
        Scanner in = new Scanner(System.in);
        while (!find) {
            String input = in.nextLine();
            if (!isValidCommand(input)) {
                continue;
            }
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '1') {
                    x = x + 1;
                } else if (input.charAt(i) == '2') {
                    x = x - 1;
                } else if (input.charAt(i) == '3') {
                    y = y - 1;
                } else if (input.charAt(i) == '4') {
                    y = y + 1;
                }
                if (canMove(x, y)) {
                    if (found(x, y)) {
                        find = true;
                        break;
                    }
                    move(x, y);
                } else {
                    printCannotMoveMessage();
                    displayMap();
                    resetMap();
                    x = 1;
                    y = 1;
                    break;
                }
            }
            displayMap();
        }
        // win
        printSuccessfulMessage();
        isWin = true;
        return isWin;
    }

    /**
     * checks whether the input command is valid.
     * @return boolean whether the input command is valid
     */
    public boolean isValidCommand(String input) {
        for (int i = 0;i < input.length(); i++) {
            if (input.charAt(i) < 49 || input.charAt(i) > 52) {
                return false;
            }
        }
        return true;
    }

    /**
     * parses the command entered by the user.
     * @return int array of the encoded command sequence
     */
    public int[] parseCommand(String input) {
        String commandString = input.replace(" ", "");
        int[] commandArray = new int[commandString.length()];
        for (int index = 0; index < commandString.length(); index++) {
            if (commandString.charAt(index) == '1') {
                commandArray[index] = 1;
            } else if (commandString.charAt(index) == '2') {
                commandArray[index] = 2;
            } else if (commandString.charAt(index) == '3') {
                commandArray[index] = 3;
            } else if (commandString.charAt(index) == '4') {
                commandArray[index] = 4;
            } else {
                return commandArray;
            }
        }
        return commandArray;
    }

    /**
     * determines whether the move is available.
     * @return boolean whether the move is available
     */
    public boolean canMove(int x, int y) {
        if (this.map[y][x] != 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * moves the treasure hunter in the map.
     */
    public void move(int x, int y) {
        this.map[y][x] = 3;
    }

    /**
     * determines whether the treature hunter have found the treasure.
     * @return boolean whether the treasure is found
     */
    public boolean found(int x, int y) {
        if (this.map[x][y] == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * resets the map.
     */
    public void resetMap() {
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[0].length; j++) {
                if (this.map[i][j] == 3) {
                    this.map[i][j] = 0;
                }
            }
        }
        System.out.println("Restarted.\n");
    }

    /**
     * prints the message that the input move is not available.
     */
    public void printCannotMoveMessage() {
        System.out.println("Sorry, you cannot move like that :(");
    }

    /**
     * prints the message that the treasure hunter have found the treasure.
     */
    public void printSuccessfulMessage() {
        System.out.println("Congratulations! You have found the treasure!");
    }

    /**
     * prints the rule of the treasure hunt game.
     */
    public void displayGameDetails() {
        System.out.println(GAME_RULES);
    }

    /**
     * prints the choices of movement and corresponding instruction numbers.
     */
    public void displayMovements() {
        System.out.println("Enter a sequence of movement(enter the index e.g. 12321):\n"
                + "1. moveRight()\n"
                + "2. moveLeft()\n"
                + "3. moveUp()\n"
                + "4. moveDown()\n");
    }

    /**
     * prints the map for the user.
     */
    public void displayMap() {
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[0].length; j++) {
                if (this.map[i][j] == 1) {
                    System.out.print("x");
                } else if (this.map[i][j] == 0) {
                    System.out.print(" ");
                } else if (this.map[i][j] == 2) {
                    System.out.print("O");
                } else if (this.map[i][j] == -1) {
                    System.out.print("*");
                } else {
                    System.out.print("·");
                }
            }
            System.out.println();
        }
    }

    /**
     * prints the explanation of the map.
     */
    public void displayMapDetails() {
        System.out.println("Introduction of the map:\n"
            + "x: walls. You cannot go across it.\n"
            + "O: start position of the treasure hunter.\n"
            + "*: treasure position. You need get to there!\n"
            + "·: noting the path you have covered.\n");
    }
}
