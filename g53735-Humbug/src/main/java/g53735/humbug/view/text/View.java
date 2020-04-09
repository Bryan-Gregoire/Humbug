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

        displayGameBoard(boardDisplay);
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
                    boardDisplay[lg][col] = "STAR";
                } else if (board.isInside(pos)
                        && board.getSquareType(pos) == SquareType.GRASS) {
                    boardDisplay[lg][col] = "GRASS";
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
        for (Animal animal : animals) {
            int row = animal.getPositionOnBoard().getRow();
            int col = animal.getPositionOnBoard().getColumn();
            boardDisplay[row][col] = animal.toString();
            if (animal.isOnStar()) {
                boardDisplay[row][col] = "GRASS";
            }
        }
        return boardDisplay;
    }

    /**
     * Display the game board.
     *
     * @param boardDisplay the given board to display.
     */
    private void displayGameBoard(String[][] boardDisplay) {
        int lineCount = boardDisplay.length * 3;
        int colCount = boardDisplay[0].length;
        for (int line = 0; line < lineCount; line++) {
            for (int col = 0; col < colCount; col++) {
                int blocPos = line % 3;
                String value = boardDisplay[line / 3][col];
                switch (value) {
                    case "Snail":
                        if (blocPos == 1) {
                            System.out.print("\033[42m|  SNAIL  |\033[0m");
                        } else {
                            System.out.print("\033[42m|         |\033[0m");
                        }
                        break;
                    case "Spider":
                        if (blocPos == 1) {
                            System.out.print("\033[42m|  SPIDER |\033[0m");
                        } else {
                            System.out.println("\033[42m|         |\033[0m");
                        }
                        break;
                    case "GRASS":
                        System.out.print("\033[42m|         |\033[0m");
                        break;
                    case "STAR":
                        if (blocPos == 1) {
                            System.out.print("\033[42m|    *    |\033[0m");
                        } else {
                            System.out.print("\033[42m|         |\033[0m");
                        }
                        break;
                    default:
                        System.out.print("           ");
                        break;
                }
                if (col == colCount - 1) {
                    System.out.println("");
                }
            }
        }
    }
}
