package g53735.humbug.model;

/**
 * Game board, made up of squares.
 *
 * @author g53735
 */
public class Board {

    private Square[][] squares;

    /**
     * Constructor of a game board.
     *
     * @param square array of squares.
     */
    protected Board(Square[][] square) {
        this.squares = square;
    }

    /**
     * Check the type of the square of a given position.
     *
     * @param pos the given position.
     * @return the type of the square, grass or star.
     */
    public SquareType getSquareType(Position pos) {
        if (!isInside(pos)) {
            throw new IllegalArgumentException("Position not on the board");
        }
        return this.squares[pos.getRow()][pos.getColumn()].getType();
    }

    /**
     * Gives the number of rows on the game board.
     *
     * @return the number of rows.
     */
    public int getNbRow() {
        return this.squares.length;
    }

    /**
     * Gives the number of columns on the game board.
     *
     * @return the number of columns.
     */
    public int getNbColumn() {
        return this.squares[0].length;
    }

    /**
     * Set a type for a given position.
     * 
     * @param pos the given position.
     * @param type the given type to set.
     */
    public void setSquareType(Position pos, SquareType type) {
        this.squares[pos.getRow()][pos.getColumn()].setType(type);
    }

    /**
     * Initializes a game board.
     *
     * @return the game board.
     */
    public static Board getInitialBoard() {
        Board board = new Board(new Square[][]{
            {new Square(SquareType.GRASS), new Square(SquareType.GRASS), null},
            {null, new Square(SquareType.GRASS), new Square(SquareType.GRASS)},
            {null, null, new Square(SquareType.STAR)}
        });
        return board;
    }

    /**
     * Check if a given position is on the game board.
     *
     * @param pos the given position.
     * @return true if pos is in the game board, else false.
     */
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
}
