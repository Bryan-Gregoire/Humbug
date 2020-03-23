package g53735.humbug.model;

/**
 *
 * @author g53735
 */
public class Spider extends Animal {

    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position spider = this.getPositionOnBoard();
        Position nextPos = spider.next(direction);
        boolean free = true;
        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }
        while (board.isInside(nextPos) && free) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(nextPos)) {
                    free = false;
                }
            }
            if (free) {
                spider = nextPos;
                nextPos = spider.next(direction);
                if (!board.isInside(nextPos)) {
                    this.setPositionOnBoard(null);
                    return null;
                }
            }
        }
        if (!free) {
            if (board.getSquareType(spider) == SquareType.STAR) {
                this.setOnStar(true);
                board.setSquareType(spider, SquareType.GRASS);
            }
            return spider;
        }
        return spider;
    }

    public String toString() {
        return "Spider";
    }
}
