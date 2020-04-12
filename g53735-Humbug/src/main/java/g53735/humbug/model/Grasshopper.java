package g53735.humbug.model;

/**
 * Represent the grasshopper in the game.
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
        Position grasshopper = this.getPositionOnBoard();
        Position arrivalPos = grasshopper.next(direction);

        return moveOnJumping(board, arrivalPos, direction, animals);
    }

    /**
     * @return a String that represent the animal.
     */
    @Override
    public String toString() {
        return "Grasshopper";
    }
}
