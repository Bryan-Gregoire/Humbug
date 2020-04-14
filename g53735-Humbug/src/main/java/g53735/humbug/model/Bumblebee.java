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
        
        return moveOneFlying(board, arrivalPos, direction, animals);
    }

    /**
     * @return a? String that represent the animal.
     */
    @Override
    public String toString() {
        return "β";
    }
}
