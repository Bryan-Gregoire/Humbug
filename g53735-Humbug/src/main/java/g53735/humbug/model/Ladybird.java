package g53735.humbug.model;

/**
 * Represent the ladybird in the game.
 *
 * @author g53735
 */
public class Ladybird extends Animal {

    /**
     * Constructor of Ladybird.
     *
     * @param positionOnBoard the position of Ladybird on the game board.
     */
    public Ladybird(Position positionOnBoard) {
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
        Position ladyBird = this.getPositionOnBoard();
        Position nextPos = ladyBird.next(direction);

        if (board.getSquare(ladyBird).hasWall(direction)) {
            return ladyBird;
        }

        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }
        if (board.getSquare(nextPos).hasWall(direction.opposite())) {
            return ladyBird;
        }

        boolean free = true;
        int move = 0;
        while (free && move < 2) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(nextPos)
                        && !animal.onStar) {
                    free = false;
                }
            }
            if (!free) {
                break;
            }
            if (free) {
                ladyBird = nextPos;
                if (move < 1) {
                    nextPos = ladyBird.next(direction);
                    if (board.getSquare(ladyBird).hasWall(direction)) {
                        free = false;
                        break;
                    }
                    if (!board.isInside(nextPos)) {
                        this.setPositionOnBoard(null);
                        return null;
                    }
                    if (board.getSquare(nextPos).
                            hasWall(direction.opposite())) {
                        free = false;
                    }
                }
            }
            move++;
        }

        if (board.getSquareType(ladyBird) == SquareType.STAR) {
            this.setOnStar(true);
            board.setSquareType(ladyBird, SquareType.GRASS);
        }
        return ladyBird;
    }

    /**
     * @return A String that represent the animal.
     */
    @Override
    public String toString() {
        return "L";
    }
}
