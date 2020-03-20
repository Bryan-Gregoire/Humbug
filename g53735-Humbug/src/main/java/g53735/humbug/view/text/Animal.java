package g53735.humbug.view.text;

import g53735.humbug.model.Board;
import g53735.humbug.model.Direction;
import g53735.humbug.model.Position;

/**
 *
 * @author g53735
 */
public abstract class Animal {

    private Position positionOnBoard;
    private boolean onStar;

    /**
     * Constructor of animal.
     *
     * @param positionOnBoard
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
     * Move the animal.
     *
     * @param board board game.
     * @param drection the direction where we want to move.
     * @param animals the animal(s) we want to move.
     * @return the new position.
     */
    public abstract Position move(Board board, Direction drection,
            Animal... animals);
}
