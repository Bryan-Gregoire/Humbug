package g53735.humbug.model;

/**
 * Brings together the elements necessary for the game to present a facade to
 * the view.
 *
 * @author g53735
 */
public class Game implements Model {

    private Board board;
    private Animal[] animals;
    private int remainingMoves;
    //private int currentLevel;

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
     * Get the remaining moves of a level.
     *
     * @return the remaining moves.
     */
    @Override
    public int getRemainingMoves() {
        return this.remainingMoves;
    }

    /**
     * The status of a level.
     *
     * @return the status.
     */
    @Override
    public LevelStatus getLevelStatus() {
        boolean win = true;
        for (Animal animal : animals) {
            if (animal.positionOnBoard == null) {
                return LevelStatus.FAIL;
            }
            if (this.remainingMoves == 0 && !animal.onStar) {
                return LevelStatus.FAIL;
            }
            if (!animal.onStar) {
                win = false;
            }
        }
        if (win == true) {
            return LevelStatus.WIN;
        }
        return LevelStatus.IN_PROGRESS;
    }

    /**
     * Initialize the game board and animals for a given level and the remaining
     * moves.
     *
     * @param level the given level.
     */
    @Override
    public void startLevel(int level) {
        this.board = Level.getLevel(level).getBoard();
        this.animals = Level.getLevel(level).getAnimals();
        this.remainingMoves = Level.getLevel(level).getnMoves();
    }

    /**
     * Move if allowed.
     *
     * @param position the given position.
     * @param direction the given direction.
     */
    @Override
    public void move(Position position, Direction direction) {
        if (position == null || direction == null) {
            throw new IllegalArgumentException("Not a good position"
                    + " or direction");
        }

        if (getLevelStatus() == LevelStatus.NOT_STARTED) {
            throw new IllegalStateException("Level not started");
        }
        Position init;
        Position movePos;

        for (Animal animal : getAnimals()) {
            if (position.equals(animal.getPositionOnBoard())
                    && !animal.onStar) {
                init = animal.getPositionOnBoard();
                movePos = animal.move(getBoard(), direction, getAnimals());

                if (movePos == null) {
                    throw new IllegalArgumentException("perdu");
                } else {
                    animal.setPositionOnBoard(movePos);
                }
                if (init != movePos) {
                    this.remainingMoves--;
                }
            }
        }
    }
}
