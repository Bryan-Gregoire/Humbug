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
        Position snail = getPositionOnBoard();
        Position nextPos = snail.next(direction);
        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }
        boolean free = true;
        for (Animal animal : animals) {
            if (nextPos == animal.getPositionOnBoard()) {
                free = false;
            }
        }
        if (!free) {
            return snail;
        }
        if (board.getSquareType(nextPos) == SquareType.STAR) {
            snail = null;
            board[nextPos.getRow()][nextPos.getColumn()].setType(SquareType.GRASS);
        }
        return snail = nextPos;
    }
}
