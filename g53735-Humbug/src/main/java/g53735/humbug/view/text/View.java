package g53735.humbug.view.text;

import g53735.humbug.model.Animal;
import g53735.humbug.model.Board;
import g53735.humbug.model.Position;
import g53735.humbug.model.SquareType;
import g53735.humbug.model.Direction;
import java.util.Scanner;

/**
 * This class represents the view of the game.
 *
 * @author g53735
 */
public class View implements InterfaceView {

    private Scanner keyboard;

    /**
     * Constructor of my Scanner.
     *
     * @param keyboard the scanner.
     */
    public View(Scanner keyboard) {
        this.keyboard = new Scanner(System.in);
    }

    /**
     * Displays the game board.
     *
     * @param board the game board to display.
     * @param animals the animals on the game board.
     */
    @Override
    public void displayBoard(Board board, Animal... animals) {
        String[][] boardDisplay
                = new String[board.getNbRow()][board.getNbColumn()];

        boardDisplay = boardString(board, boardDisplay);

        boardDisplay = animalOnBoard(boardDisplay, animals);

        displayGameBoard(boardDisplay, board);
    }

    /**
     * Displays an error message.
     *
     * @param message the error message.
     */
    @Override
    public void displayError(String message) {
        System.err.println(message);
    }

    /**
     * Ask for a position.
     *
     * @return the position.
     */
    @Override
    public Position askPosition() {
        System.out.println("Enter the position of the animal to move");
        int a = enterInteger("Enter the row you want to select: ");
        int b = enterInteger("Enter the Column you want to select: ");
        Position position = new Position(a, b);
        System.out.println("The given position : " + "(" + a + ", " + b + ")");
        return position;
    }

    /**
     * Ask to enter a cardinal direction.
     *
     * @return the given direction.
     */
    @Override
    public Direction askDirection() {
        keyboard.nextLine();
        System.out.println("Enter a cardinal direction(N(North), E(East),"
                + " S(South), W(West)): ");
        String dir = keyboard.nextLine().toUpperCase();
        while (!"N".equals(dir) && !"NORTH".equals(dir) && !"E".equals(dir)
                && !"EAST".equals(dir) && !"S".equals(dir)
                && !"SOUTH".equals(dir) && !"W".equals(dir)
                && !"WEST".equals(dir)) {
            System.out.println("This is not a direction");
            System.out.println("Enter a direction: ");
            dir = keyboard.nextLine().toUpperCase();
        }
        return cardinalDirection(dir);
    }

    /**
     * Display the remaining moves.
     */
    @Override
    public void displayRemaining(int remainingMoves) {
        System.out.println("You have " + remainingMoves + " movements left");
    }

    /**
     * Display the number of the current level.
     *
     * @param nbLevel number of the level.
     */
    @Override
    public void displayNumberLevel(int nbLevel) {
        System.out.println("Level: " + nbLevel);
    }

    /**
     * Ask a integer, while it is not a integer, ask again.
     *
     * @param message the given message to display.
     * @return the given integer.
     */
    private int enterInteger(String message) {
        System.out.println(message);
        while (!keyboard.hasNextInt()) {
            keyboard.next();
            System.out.println("not an integer, try again");
            System.out.print(message);
        }
        return keyboard.nextInt();
    }

    /**
     * If a given string corresponds to a cardinal direction.
     *
     * @param direction the given string.
     * @return the cardinal direction.
     */
    private Direction cardinalDirection(String direction) {
        switch (direction) {
            case "N":
            case "NORTH":
                return Direction.NORTH;
            case "E":
            case "EAST":
                return Direction.EAST;
            case "S":
            case "SOUTH":
                return Direction.SOUTH;
            case "W":
            case "WEST":
                return Direction.WEST;
        }
        return null;
    }

    /**
     * Array representing the type of square.
     *
     * @param board the given board.
     * @param boardDisplay the array of type of square.
     * @return the array of type of square.
     */
    private String[][] boardString(Board board, String[][] boardDisplay) {
        for (int lg = 0; lg < boardDisplay.length; lg++) {
            for (int col = 0; col < boardDisplay[lg].length; col++) {
                Position pos = new Position(lg, col);
                if (board.isInside(pos)
                        && board.getSquareType(pos) == SquareType.STAR) {
                    boardDisplay[lg][col] = "* ";
                } else if (board.isInside(pos)
                        && board.getSquareType(pos) == SquareType.GRASS) {
                    boardDisplay[lg][col] = "  ";
                } else {
                    boardDisplay[lg][col] = "null";
                }
            }
        }
        return boardDisplay;
    }

    /**
     * Place an animal on the game board and check if the animal is on star.
     *
     * @param boardDisplay the given array string.
     * @param animals the given animals.
     * @return the String array with the animals
     */
    private String[][] animalOnBoard(String[][] boardDisplay,
            Animal... animals) {
        for (int i = 0; i < animals.length; i++) {
            int row = animals[i].getPositionOnBoard().getRow();
            int col = animals[i].getPositionOnBoard().getColumn();
            boardDisplay[row][col] = animals[i].toString();
            if (animals[i].isOnStar()) {
                if (animals.length == 1) {
                    boardDisplay[row][col] = "  ";
                }
                for (int j = 0; j < animals.length; j++) {
                    if (i == j) {
                        j++;
                    }
                    if (j >= animals.length) {
                        break;
                    }
                    if (animals[i].getPositionOnBoard().
                            equals(animals[j].getPositionOnBoard())
                            && !animals[j].isOnStar()) {
                        boardDisplay[row][col] = animals[j].toString();
                        break;
                    } else {
                        boardDisplay[row][col] = "  ";

                    }
                }
            }
        }
        return boardDisplay;
    }

    /**
     * Display the game board.
     *
     * @param boardDisplay the given board to display.
     */
    private void displayGameBoard(String[][] boardDisplay, Board board) {
        int lineCount = boardDisplay.length * 5;
        int colCount = boardDisplay[0].length * 3;
        for (int line = 0; line < lineCount; line++) {
            for (int col = 0; col < colCount; col++) {
                int vBlocPos = line % 5;
                int hBlocPos = col % 3;

                String value = boardDisplay[line / 5][col / 3];
                Position wallPos = new Position(line / 5, col / 3);
                if ((vBlocPos != 4) || (line == lineCount - 1)) {
                    switch (value) {
                        case "  ":
                        case "* ":
                        case "SL":
                        case "SP":
                        case "LB":
                        case "GH":
                        case "BB":
                        case "BF":
                            if (vBlocPos == 0 || line == lineCount - 1) {
                                System.out.print("\033[42m-----\033[0m");
                            } else {
                                if (vBlocPos == 2 && hBlocPos == 1) {
                                    System.out.print("\033[42m  " + value
                                            + " \033[0m");
                                } else if ((vBlocPos == 1 && hBlocPos == 1
                                        && board.getSquare(wallPos).
                                                hasWall(Direction.NORTH))
                                        || (vBlocPos == 2 && hBlocPos == 2
                                        && board.getSquare(wallPos).
                                                hasWall(Direction.EAST))
                                        || (vBlocPos == 3 && hBlocPos == 1
                                        && board.getSquare(wallPos).
                                                hasWall(Direction.SOUTH))
                                        || (vBlocPos == 2 && hBlocPos == 0
                                        && board.getSquare(wallPos).
                                                hasWall(Direction.WEST))) {
                                    switch (hBlocPos) {
                                        case 0:
                                            System.out.print("\033[42m| "
                                                    + "\033[0m\033[41m "
                                                    + "\033[0m\033[42m  "
                                                    + "\033[0m");
                                            break;
                                        case 1:
                                            System.out.print("\033[42m  "
                                                    + "\033[0m\033[41m "
                                                    + "\033[0m\033[42m  "
                                                    + "\033[0m");
                                            break;
                                        case 2:
                                            System.out.print("\033[42m  "
                                                    + "\033[0m\033[41m "
                                                    + "\033[0m\033[42m "
                                                    + "|\033[0m");
                                            break;
                                    }
                                } else {
                                    switch (hBlocPos) {
                                        case 0:
                                            System.out.print("\033[42m|    "
                                                    + "\033[0m");
                                            break;
                                        case 1:
                                            System.out.print("\033[42m    "
                                                    + " \033[0m");
                                            break;
                                        case 2:
                                            System.out.print("\033[42m   "
                                                    + " |\033[0m");
                                            break;

                                    }
                                }
                            }
                            break;

                        default:
                            System.out.print("     ");
                            break;
                    }
                    if (col == colCount - 1) {
                        System.out.println("");
                    }

                }
            }
        }
    }
}
