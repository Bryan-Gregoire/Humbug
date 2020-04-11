package g53735.humbug.model;

/**
 * Represent the bumblebee in the game.
 *
 * @author g53735
 */
public class Bumblebee extends Animal {

    /**
     * Constructor of bumblebee.
     *
     * @param positionOnBoard the position of bumblebee on the game board.
     */
    public Bumblebee(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Move the animal, change the position of the animal.
     *
     * @param board the given board.
     * @param direction the given direction where we want to move the animal.
     * @param animals the animals.
     * @return the new position of the animal.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position bumblebee = this.getPositionOnBoard();
        Position nextPos = bumblebee.next(direction);
        Position arrivalPos = nextPos.next(direction);
        if (!board.isInside(arrivalPos)) {
            this.setPositionOnBoard(null);
            return null;
        }

        boolean free = true;
        while (free) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(arrivalPos)) {
                    free = false;
                }
            }
            if (free) {
                break;
            } else {
                bumblebee = arrivalPos;
                arrivalPos = bumblebee.next(direction);
                if (!board.isInside(arrivalPos)) {
                    this.setPositionOnBoard(null);
                    return null;
                }
            }
            free = true;
        }
        bumblebee = arrivalPos;
        if (board.getSquareType(bumblebee) == SquareType.STAR) {
            this.setOnStar(true);
            board.setSquareType(bumblebee, SquareType.GRASS);
        }
        return bumblebee;
    }

    /**
     * @return a? String that represent the animal.
     */
    @Override
    public String toString() {
        return "Bumblebee";
    }
}
