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

    /**
     * Displays the game board.
     *
     * @param board the game board to display.
     * @param animals the animals on the game board.
     */
    @Override
    public void displayBoard(Board board, Animal... animals) {
        String[][] boardDisplay = new String[board.getNbRow()][board.getNbColumn()];
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
        for (int i = 0; i < animals.length; i++) {
            int row = animals[i].getPositionOnBoard().getRow();
            int col = animals[i].getPositionOnBoard().getColumn();
            boardDisplay[row][col] = animals[i].toString();
        }
        for (int lg = 0; lg < boardDisplay.length; lg++) {
            for (int col = 0; col < boardDisplay[0].length; col++) {
                if (boardDisplay[lg][col].equals("Snail")) {
                    System.out.print("\033[42m|  SNAIL  |\033[0m");
                } else if (boardDisplay[lg][col].equals("Spider")) {
                    System.out.print("\033[42m|  SPIDER |\033[0m");
                } else if (boardDisplay[lg][col].equals("GRASS")) {
                    System.out.print("\033[42m|         |\033[0m");
                } else if (boardDisplay[lg][col].equals("STAR")) {
                    System.out.print("\033[42m|    *    |\033[0m");
                } else {
                    System.out.print("           ");
                }
                if(col == boardDisplay[0].length - 1){
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
        System.err.println(message);
    }

    /**
     * Ask for a position.
     *
     * @return the position.
     */
    @Override
    public Position askPosition() {
        System.out.println("Enter a position");
        int a = enterInteger("Row : ");
        int b = enterInteger("Column : ");
        Position position = new Position(a, b);
        System.out.println("The given position : " + "(" + a + ", " + b + ")");
        return position;
    }

    /**
     * Ask to enter a direction.
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
        return cardinalDirection(dir);
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
            System.out.print(message);
        }
        return keyboard.nextInt();
    }
    
    private Direction cardinalDirection(String direction){
        switch(direction){
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
}
