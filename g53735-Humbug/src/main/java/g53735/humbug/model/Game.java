package g53735.humbug.model;

/**
 *
 * @author g53735
 */
public class Game implements Model {

    Board board;
    Animal[] animals;

    /**
     * Get the board.
     *
     * @return the board.
     */
    @Override
    public Board getBoard() {
        return this.board;
    }

    /**
     * Get the array of animals.
     *
     * @return the animals.
     */
    @Override
    public Animal[] getAnimals() {
        return this.animals;
    }

    /**
     * Initialize the game board and animals for a level.
     *
     * @param level the given level.
     */
    @Override
    public void startLevel(int level) {
        this.board = Board.getInitialBoard();
    }

    /**
     * say if the level is finished.
     *
     * @return true if the level is finished, else false.
     */
    @Override
    public boolean levelIsOver() {
        for (int lg = 0; lg < board.getNbRow(); lg++) {
            for (int col = 0; col < board.getNbColumn(); col++) {
                Position pos = new Position(lg, col);
//                if(board.getSquareType(pos) == SquareType.STAR && )
            }
        }
        return false;
    }

    /**
     * Move if allowed.
     *
     * @param position the given position.
     * @param direction the given direction.
     */
    @Override
    public void move(Position position, Direction direction) {
    }
}
