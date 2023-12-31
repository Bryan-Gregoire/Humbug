package g53735.humbug.model;

/**
 * Represent the snail in the game.
 *
 * @author g53735
 */
public class Snail extends Animal {

    /**
     * Constructor of Snail.
     *
     * @param positionOnBoard the position of snail on the game board.
     */
    public Snail(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Constructor of Snail.
     *
     */
    public Snail() {
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
        Position snail = this.getPositionOnBoard();
        Position nextPos = snail.next(direction);

        if (board.getSquare(snail).hasWall(direction)) {
            return snail;
        }

        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }

        if (board.getSquare(nextPos).hasWall(direction.opposite())) {
            return snail;
        }

        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().equals(nextPos) && !animal.onStar) {
                return snail;
            }
        }
       
        snail = nextPos;
        animalOnStar(board, snail);
        
        return snail;
    }

    /**
     * @return a String that represent the animal.
     */
    @Override
    public String toString() {
        return "SL";
    }
}
