package g53735.humbug.model;

/**
 * Game board, made up of squares.
 *
 * @author g53735
 */
public class Board {

    private final Square[][] squares;

    /**
     * Constructor of a game board.
     *
     * @param square array of squares.
     */
    protected Board(Square[][] square) {
        this.squares = square;
    }

    /**
     * Constructor of a board.
     * 
     */
    public Board() {
        this.squares = null;
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
     * Get a square of a given position.
     *
     * @param pos the given position.
     * @return the square.
     */
    public Square getSquare(Position pos) {
        return this.squares[pos.getRow()][pos.getColumn()];
    }

    /**
     * Get a Square.
     * 
     * @return square.
     */
    public Square[][] getSquares() {
        return squares;
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
        if (type == null) {
            throw new IllegalArgumentException("Not a type");
        }
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
        if (pos == null) {
            throw new IllegalArgumentException("Position not on the board");
        }
        if (pos.getRow() >= this.getNbRow()
                || pos.getColumn() >= this.getNbColumn()
                || pos.getRow() < 0 || pos.getColumn() < 0) {
            return false;
        } else {
            return squares[pos.getRow()][pos.getColumn()] != null;
        }
    }
}
