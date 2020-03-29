package g53735.humbug.model;

/**
 * Position on the board. A position has a row and column that form a position.
 *
 * @author g53735
 *
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Constructor of a position on the board.
     *
     * @param row the given row of a position
     * @param column the given column of a position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get the value of a row.
     *
     * @return the value of row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Get the value of a column.
     *
     * @return the value of column
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.row;
        hash = 97 * hash + this.column;
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false
     * otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

    /**
     * Get the position next to the direction pass in parameter.
     *
     * @param direction given direction
     * @return the position next to the given direction.
     */
    public Position next(Direction direction) {
        return new Position(row + direction.getDeltaRow(),
                column + direction.getDeltaColumn());
    }
}
