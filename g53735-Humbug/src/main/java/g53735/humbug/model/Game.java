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
     * say if the level is finished.
     *
     * @return true if the level is finished, else false.
     */
    @Override
    public boolean levelIsOver() {
        boolean[] allOnStar = new boolean[getAnimals().length];
        for (int i = 0; i < getAnimals().length; i++) {
            allOnStar[i] = getAnimals()[i].isOnStar();
        }
        for (int i = 0; i < allOnStar.length; i++) {
            if (allOnStar[i] == false) {
                return false;
            }
        }
        return true;
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

        int i = 0;
        Position nextPos = getAnimals()[0].move(getBoard(), direction,
                getAnimals());
        boolean free = true;
        int index = 1;
        while (i < getAnimals().length && free) {
            for (int j = 0; j < getAnimals().length; j++) {
                if (getAnimals()[i].getPositionOnBoard().getRow()
                        == nextPos.getRow()
                        && getAnimals()[i].getPositionOnBoard().getColumn()
                        == nextPos.getColumn()) {
                    free = false;
                }
                if (free) {
                    getAnimals()[i].setPositionOnBoard(nextPos);
                } else {
                    System.out.println("move not valid");
                }
                if (index < getAnimals().length) {
                    nextPos = getAnimals()[index].move(getBoard(), direction,
                            getAnimals());
                }
                index++;
            }
            i++;
        }
    }
}
