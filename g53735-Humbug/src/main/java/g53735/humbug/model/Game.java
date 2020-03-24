package g53735.humbug.model;

/**
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
        for (int i = 0; i < getAnimals().length; i++) {
            if (getAnimals()[i].move(getBoard(), direction, getAnimals())
                    == null) {
                throw new IllegalArgumentException("move not valid");
            }
        }

        int index = 0;
        Position nextPos = getAnimals()[index].move(getBoard(), direction,
                getAnimals());
        int i = 0;
        boolean free = true;
        while (i < getAnimals().length && free) {
            for (int j = 0; j < getAnimals().length; j++) {
                if (getAnimals()[i].getPositionOnBoard().getRow()
                        == nextPos.getRow()
                        && getAnimals()[i].getPositionOnBoard().getColumn()
                        == nextPos.getColumn()) {
                    free = false;
                }
            }
            if (free && index < getAnimals().length) {
                position = nextPos;
                nextPos = getAnimals()[index].move(getBoard(), direction,
                        getAnimals());
            } else {
                System.out.println("Déplacement pas permis");
            }
            index++;
            i++;
        }
        //        if (free) {
        //            position = getAnimals()[i].move(board, direction,
//        animals);
        //            nextPos = position.next(direction);
        //        }
//        else {
//            System.out.println("Déplacement pas permis");
//        }
    }
}
