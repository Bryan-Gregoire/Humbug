package g53735.humbug.model;

/**
 * Represent the spider in the game.
 *
 * @author g53735
 */
public class Spider extends Animal {

    /**
     * Constructor of spider.
     *
     * @param positionOnBoard the position of spider in the game board.
     */
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Constructor of spider.
     * 
     */
    public Spider() {
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
        Position spider = this.getPositionOnBoard();
        Position nextPos = spider.next(direction);

        if (board.getSquare(spider).hasWall(direction)) {
            return spider;
        }

        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }

        if (board.getSquare(nextPos).hasWall(direction.opposite())) {
            return spider;
        }

        boolean free = true;
        while (board.isInside(nextPos) && free) {
            free = nextPosFree(nextPos, animals);
            if (free) {
                spider = nextPos;
                nextPos = spider.next(direction);
                if (board.getSquare(spider).hasWall(direction)) {
                    break;
                }
                if (!board.isInside(nextPos)) {
                    this.setPositionOnBoard(null);
                    return null;
                }
                if (board.getSquare(nextPos).hasWall(direction.opposite())) {
                    free = false;
                }
            }
        }
        animalOnStar(board, spider);
        
        return spider;
    }

    /**
     * @return a String that represent the animal.
     */
    @Override
    public String toString() {
        return "SP";
    }
}
