package data.game;

import java.util.Scanner;

public class TreasureHuntGame extends Game {
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
     * execute the game and return cardID collected
     * @return cardID of the card to collect in the Player class; return 0 if lose
     */
    @Override
    public int execute() {
        displayGameDetails();
        displayMovements();
        displayMap();
        TreasureHuntGame g = new TreasureHuntGame();
        boolean isWin = g.play();
        return isWin ? startID: 0;    // return startID if win return 0 indicates lose
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean play() {
        boolean isWin = false; // todo: add limited attempts to lose the game
        int x = 1;
        int y = 1;
        boolean find = false;
        Scanner in = new Scanner(System.in);
        while (!find) {
            String input = in.nextLine();
            int[] command = parseCommand(input);
            for (int i = 0; i < command.length; i++) {
                if (command[i] == 1) {
                    x = x + 1;
                } else if (command[i] == 2) {
                    x = x - 1;
                } else if (command[i] == 3) {
                    y = y - 1;
                } else if (command[i] == 4) {
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

    public boolean canMove(int x, int y) {
        if (this.map[y][x] != 1) {
            return true;
        } else {
            return false;
        }
    }

    public void move(int x, int y) {
        this.map[y][x] = 3;
    }

    public boolean found(int x, int y) {
        if (this.map[x][y] == -1) {
            return true;
        } else {
            return false;
        }
    }

    public void printCannotMoveMessage() {
        System.out.println("Sorry, you cannot move like that :(");
    }

    public void printSuccessfulMessage() {
        System.out.println("Congratulaitons! You have found the treasure!");
    }

    public void displayGameDetails() {
        System.out.println(GAME_RULES);
    }

    public void displayMovements() {
        System.out.println("Enter a sequence of movement(enter the index e.g. 12321):\n"
                + "1. moveRight()\n"
                + "2. moveLeft()\n"
                + "3. moveUp()\n"
                + "4. moveDown()\n");
    }

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
}
