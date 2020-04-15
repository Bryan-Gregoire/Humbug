package g53735.humbug.model;

/**
 * Represent the animal on the game board.
 *
 * @author g53735
 */
public abstract class Animal {

    protected Position positionOnBoard;
    protected boolean onStar;

    /**
     * Constructor of animal.
     *
     * @param positionOnBoard the position of the animal on the game board.
     */
    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        this.onStar = false;
    }

    /**
     * Get the position of the animal on the game board.
     *
     * @return the position.
     */
    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Tell if the animal is on a star box or not.
     *
     * @return True if the animal is on a star box, else false.
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * Set the position of the animal on the game board.
     *
     * @param positionOnBoard the position of the animal.
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * Set true or false.
     *
     * @param onStar if is on star or no.
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Move the animal, change the position of the animal.
     *
     * @param board board game.
     * @param direction the direction where we want to move.
     * @param animals the animal(s) we want to move.
     * @return the new position.
     */
    public abstract Position move(Board board, Direction direction,
            Animal... animals);

    protected Position moveOnJumping(Board board, Position arrivalPos,
            Direction direction, Animal... animals) {

        if (!board.isInside(arrivalPos)) {
            this.setPositionOnBoard(null);
            return null;
        }

        boolean free = true;
        while (free) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(arrivalPos) && !animal.onStar) {
                    free = false;
                }
            }
            if (free) {
                break;
            } else {
                this.positionOnBoard = arrivalPos;
                arrivalPos = this.positionOnBoard.next(direction);

                if (!board.isInside(arrivalPos)) {
                    this.setPositionOnBoard(null);
                    return null;
                }
            }
            free = true;
        }

        this.positionOnBoard = arrivalPos;
        if (board.getSquareType(this.positionOnBoard) == SquareType.STAR) {
            this.setOnStar(true);
            board.setSquareType(this.positionOnBoard, SquareType.GRASS);
        }
        return this.positionOnBoard;
    }

    /**
     * The animal flies up to its destination, go to the next case if there is
     * an animal on the square where the animal land. Change the position of the
     * animal.
     *
     * @param board the given board.
     * @param arrivalPos The given position where the animal should landed.
     * @param direction the given direction where to move.
     * @param animals the others animals on the game board.
     * @return the position where landed the animal.
     */
    protected Position moveOneFlying(Board board, Position arrivalPos,
            Direction direction, Animal... animals) {
        if (!board.isInside(arrivalPos)) {
            this.positionOnBoard = null;
            return null;
        }

        boolean free = true;
        while (free) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(arrivalPos) && !animal.onStar) {
                    free = false;
                }
            }
            if (free) {
                break;
            } else {
                this.positionOnBoard = arrivalPos;
                arrivalPos = this.positionOnBoard.next(direction);
                if (!board.isInside(arrivalPos)) {
                    this.positionOnBoard = null;
                    return null;
                }
            }
            free = true;
        }

        this.positionOnBoard = arrivalPos;
        if (board.getSquareType(this.positionOnBoard) == SquareType.STAR) {
            this.setOnStar(true);
            board.setSquareType(this.positionOnBoard, SquareType.GRASS);
        }
        return this.positionOnBoard;
    }
}
