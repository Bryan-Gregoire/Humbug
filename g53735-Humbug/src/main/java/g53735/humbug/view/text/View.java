package g53735.humbug.view.text;

import g53735.humbug.model.Board;
import g53735.humbug.model.Position;
import g53735.humbug.model.SquareType;
import g53735.humbug.model.Direction;
import java.util.Scanner;

/**
 *
 * @author g53735
 */
public class View implements InterfaceView {

    @Override
    public void displayBoard(Board board) {
        String[][] display = new String[board.getNbRow()][board.getNbColumn()];
        for (int lg = 0; lg < display.length; lg++) {
            for (int col = 0; col < display[lg].length; col++) {
                Position pos = new Position(lg, col);
                if (board.isInside(pos)
                        && board.getSquareType(pos) == SquareType.STAR) {
                    display[lg][col] = "STAR";
                } else if (board.isInside(pos)
                        && board.getSquareType(pos) == SquareType.GRASS) {
                    display[lg][col] = "GRASS";
                } else {
                    display[lg][col] = "null";
                }
            }
        }
        for (int lg = 0; lg < display.length; lg++) {
            for (int col = 0; col < display[0].length; col++) {

                if (display[lg][col].equals("GRASS")) {
                    System.out.print("|   |");
                } else if (display[lg][col].equals("STAR")) {
                    System.out.print("| * |");
                } else {
                    System.out.print("     ");
                }
                if (col == display[0].length - 1) {
                    System.out.println("");
                }
            }
        }
    }

    /**
     * Displays an error message.
     *
     * @param message the error message.
     */
    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Ask for a position.
     *
     * @return the position.
     */
    public Position askPosition() {
        System.out.println("Enter a position: ");
        int a = enterInteger("Row");
        int b = enterInteger("Column");
        Position position = new Position(a, b);
        System.out.println("The given position : " + "(" + a + ", " + b + ")");
        return position;
    }

    /**
     * Ask ton enter a direction.
     *
     * @return a given direction.
     */
    @Override
    public Direction askDirection() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a direction(North,East,South,West): ");
        String dir = keyboard.nextLine().toUpperCase();
        while (!"NORTH".equals(dir) && !"EAST".equals(dir) && !"SOUTH".equals(dir)
                && !"WEST".equals(dir)) {
            keyboard.next();
            System.out.println("This is not a direction");
            System.out.println("Enter a direction: ");
        }
        switch (dir) {
            case "NORTH":
                Direction north = Direction.NORTH;
                return north;
            case "EAST":
                Direction east = Direction.EAST;
                return east;
            case "SOUTH":
                Direction south = Direction.SOUTH;
                return south;
            case "WEST":
                Direction west = Direction.WEST;
                return west;
        }
        return null;
    }

    /**
     * Ask a integer, while it is not a integer, ask again.
     *
     * @param message the given message to display.
     * @return the given integer.
     */
    private int enterInteger(String message) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(message);
        while (!keyboard.hasNextInt()) {
            keyboard.next();
            System.out.println("The number entered is not an integer");
            System.out.println(message);
        }
        return keyboard.nextInt();
    }

    public static void main(String[] args) {
        View view = new View();
        view.displayBoard(Board.getInitialBoard());
    }
}
