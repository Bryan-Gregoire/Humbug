package g53735.humbug.model;

/**
 *
 * @author g53735
 */
public class Butterfly extends Animal {

    /**
     * The constructor of the butterfly.
     *
     * @param positionOnBoard the position of butterfly in the game board.
     */
    public Butterfly(Position positionOnBoard) {
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
        Position fly1 = bumblebee.next(direction);
        Position fly2 = fly1.next(direction);
        Position arrivalPos = fly2.next(direction);

        return moveOneFlying(board, arrivalPos, direction, animals);
    }

    /**
     * @return a String that represent the animal.
     */
    @Override
    public String toString() {
        return "B";
    }
}
