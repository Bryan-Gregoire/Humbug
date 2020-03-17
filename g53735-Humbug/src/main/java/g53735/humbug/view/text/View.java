package g53735.humbug.view.text;

import g53735.humbug.model.Board;
import g53735.humbug.model.Position;
import g53735.humbug.model.Square;
import g53735.humbug.model.SquareType;
import g53735.humbug.model.Direction;
import java.util.Scanner;

/**
 *
 * @author g53735
 */
public class View {

    public void displayBoard(Board board) {
        String[][] display = new String[board.getNbRow()][board.getNbColumn()];
        for (int lg = 0; lg < display.length; lg++) {
            for (int col = 0; col < display[lg].length; col++) {
                Position pos = new Position(lg, col);
                if (!board.isInside(pos)) {
                    System.out.println("      ");
                    System.out.println("      ");
                    System.out.println("      ");
                } else if (board.isInside(pos)
                        && board.getSquareType(pos) == SquareType.GRASS) {
                    System.out.println("_______");
                    System.out.println("|     |");
                    System.out.println("|     |");
                    System.out.println("|     |");
                    System.out.println("_______");
                } else {
                    System.out.println("_______");
                    System.out.println("|     |");
                    System.out.println("|  *  |");
                    System.out.println("|     |");
                    System.out.println("_______");
                }
            }
        }
    }

    /**
     * Displays an error message.
     *
     * @param message the error message.
     */
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Ask for a position.
     *
     * @return the position.
     */
    public Position askPosition() {
        System.out.println("Enter your position");
        int a = enterInteger("Row: ");
        int b = enterInteger("Column: ");
        Position position = new Position(a, b);
        System.out.println("The given position : " + "(" + a + "," + b + ")");

        return position;
    }

//    public Direction askDirection() {
//        Scanner keyboard = new Scanner(System.in);
//        System.out.println("Enter a direction: ");
//        String dir = keyboard.nextLine().toUpperCase();
//        while (dir != "NORTH" && dir != "EAST" && dir != "SOUTH"
//                && dir != "WEST") {
//            keyboard.next();
//            System.out.println("This is not a direction");
//            System.out.println("Enter a direction: ");
//        }
//    }

    /**
     * Ask a integer, while it is not a integer, ask again.
     *
     * @param message the given message to display.
     * @return the given integer.
     */
    public int enterInteger(String message) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(message);
        while (!keyboard.hasNextInt()) {
            keyboard.next();
            System.out.println("The number entered is not an integer");
            System.out.println(message);
        }
        return keyboard.nextInt();
    }
}
