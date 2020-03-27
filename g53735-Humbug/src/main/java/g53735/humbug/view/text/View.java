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
        String[][] boardDisplay = new String[board.getNbRow()]
                [board.getNbColumn()];
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
            if(animals[i].isOnStar()){
                boardDisplay[row][col] = "GRASS";
            }
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
        System.out.println("Enter the position of the animal to move");
        int a = enterInteger("Enter the row you want to select: ");
        int b = enterInteger("Enter the Column you want to select");
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
        System.out.println("Enter a direction(N(North), E(East), S(South),"
                + " W(West)): ");
        String dir = keyboard.nextLine().toUpperCase();
        while (!"N".equals(dir) && !"NORTH".equals(dir) && !"E".equals(dir)&& 
                !"EAST".equals(dir) && !"S".equals(dir) && !"SOUTH".equals(dir)
                && !"W".equals(dir) && !"WEST".equals(dir)) {
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
        Scanner keyboard = new Scanner(System.in);
        System.out.println(message);
        while (!keyboard.hasNextInt()) {
            keyboard.next();
            System.out.println("not an integer, try again");
            System.out.print(message);
        }
        return keyboard.nextInt();
    }
    
    /**
     * If a given string corresponds to a cardinal direction, 
     * we return this direction.
     * @param direction the given string
     * @return a cardinal direction.
     */
    private Direction cardinalDirection(String direction){
        switch(direction){
            case "N":
                Direction n = Direction.NORTH;
                return n;
            case "NORTH":
                Direction north = Direction.NORTH;
                return north;
            case "E":
                Direction e = Direction.EAST;
                return e;
            case "EAST":
                Direction east = Direction.EAST;
                return east;
            case "S":
                Direction s = Direction.SOUTH;
                return s;
            case "SOUTH":
                Direction south = Direction.SOUTH;
                return south;
            case "W":
                Direction w = Direction.WEST;
                return w;
            case "WEST":
                Direction west = Direction.WEST;
                return west;
        }
        return null;
    }
}
