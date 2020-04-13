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
    private int currentLevel;

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
        int inProgress = 0;
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
            if (this.remainingMoves > 0 && animal.positionOnBoard != null) {
                inProgress++;
            }
        }
        if (win == true) {
            return LevelStatus.WIN;
        }
        if (inProgress == animals.length - 1) {
            return LevelStatus.IN_PROGRESS;
        }
        return LevelStatus.NOT_STARTED;
    }

    /**
     * Initialize the game board and animals for a given level.
     *
     * @param level the given level.
     */
    @Override
    public void startLevel(int level) {
        if (level == 1) {
            this.board = Board.getInitialBoard();
            this.animals = new Animal[]{new Snail(new Position(0, 0))};
        }
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
        for (Animal animal : getAnimals()) {
            if (position.equals(animal.getPositionOnBoard())) {
                if (animal.move(getBoard(), direction, getAnimals()) == null) {
                    throw new IllegalArgumentException("perdu");
                } else {
                    animal.setPositionOnBoard(animal.move(getBoard(), direction,
                            getAnimals()));
                }
                this.remainingMoves--;
            }
        }
    }
}
