package g53735.humbug.model;

/**
 *
 * @author g53735
 */
public class Board {

    private Square[][] squares;

    protected Board(Square[][] square) {
        this.squares = square;
    }

    public static Board getInitialBoard() {
        Board board = new Board(new Square[][]{
            {new Square(SquareType.GRASS), new Square(SquareType.GRASS), null},
            {null, new Square(SquareType.GRASS), new Square(SquareType.GRASS)},
            {null, null, new Square(SquareType.STAR)}
        });
        return board;
    }

    public boolean isInside(Position pos) {
        boolean inside;
        if (pos == null) {
            throw new IllegalArgumentException("Position not on the board");
        }
        if (pos.getRow() >= this.getNbRow()
                || pos.getColumn() >= this.getNbColumn()
                || pos.getRow() < 0 || pos.getColumn() < 0) {
            inside = false;
        } else if (squares[pos.getRow()][pos.getColumn()] == null) {
            inside = false;
        } else {
            inside = true;
        }
        return inside;
    }

    public SquareType getSquareType(Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException("Position not on the board");
        }
        return this.squares[pos.getRow()][pos.getColumn()].getType();
    }
//    public SquareType getSquareType(Position pos) {
//        if (pos == null) {
//            throw new IllegalArgumentException("Position not on the board");
//        } else if (squares[pos.getRow()][pos.getColumn()] == null) {
//            throw new IllegalArgumentException("Position not on the board");
//        }
//        return squares[pos.getRow()][pos.getColumn()].getType();
//    }

    public int getNbRow() {
        return this.squares.length;
    }

    public int getNbColumn() {
        return this.squares[0].length;
    }
}
