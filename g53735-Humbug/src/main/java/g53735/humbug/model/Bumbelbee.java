package g53735.humbug.model;

/**
 * Represent the bumbelbee in the game.
 *
 * @author g53735
 */
public class Bumbelbee extends Animal {

    /**
     * Constructor of bumbelbee.
     *
     * @param positionOnBoard the position of bumblebee on the game board.
     */
    public Bumbelbee(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Constructor of Bumbelbee.
     * 
     */
    public Bumbelbee() {
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
        Position nextPos = this.positionOnBoard.next(direction);
        Position arrivalPos = nextPos.next(direction);
        
        return moveOneFlying(board, arrivalPos, direction, animals);
    }

    /**
     * @return a String that represent the animal.
     */
    @Override
    public String toString() {
        return "BB";
    }
}
