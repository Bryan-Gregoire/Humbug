package g53735.humbug.model;

import java.util.Iterator;

/**
 *
 * @author g53735
 */
public class Grasshopper extends Animal {

    /**
     * Constructor of grasshopper.
     *
     * @param positionOnBoard the position of grasshopper on the game board.
     */
    public Grasshopper(Position positionOnBoard) {
        super(positionOnBoard);
    }

    @Override
    public Position move(Board board, Direction drection, Animal... animals) {
        Position grasshopper = this.getPositionOnBoard();
        Position nextPos = grasshopper.next(drection);

        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }

        boolean free = true;
        while (free) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(nextPos)) {
                    free = false;
                }
            }
            if (free) {
                break;
            } else {
                grasshopper = nextPos;
                nextPos = grasshopper.next(drection);

                if (!board.isInside(nextPos)) {
                    this.setPositionOnBoard(null);
                    return null;
                }
            }
            free = true;
        }

        grasshopper = nextPos;
        if (board.getSquareType(grasshopper) == SquareType.STAR) {
            this.setOnStar(true);
            board.setSquareType(grasshopper, SquareType.GRASS);
        }
        return grasshopper;
    }

}
