package g53735.humbug.model;

/**
 *
 * @author g53735
 */
public class Snail extends Animal {

    public Snail(Position positionOnBoard) {
        super(positionOnBoard);
    }

    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position snail = this.getPositionOnBoard();
        Position nextPos = snail.next(direction);

        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }

        boolean free = true;
        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().equals(nextPos)) {
                free = false;
            }
        }
        if (!free) {
            return snail;
        }

        if (board.getSquareType(nextPos) == SquareType.STAR) {
            snail = null;
            this.setOnStar(true);
            board.setSquareType(nextPos, SquareType.GRASS);
        }
        return snail = nextPos;
    }
}
