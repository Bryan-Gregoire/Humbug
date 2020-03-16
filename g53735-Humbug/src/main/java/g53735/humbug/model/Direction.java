package g53735.humbug.model;

/**
 * Direction to indicate in which direction we want to move, using the four
 * cardinal points.
 *
 * @author g53735
 */
public enum Direction {
    NORTH(-1, 0),
    SOUTH(1, 0),
    EAST(0, 1),
    WEST(0, -1),;

    private int deltaRow;
    private int deltaColumn;

    /**
     * Constructor of a direction where we want to move.
     *
     * @param deltaRow the given row
     * @param deltaColumn the given column
     */
    private Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * Get the value of a row.
     *
     * @return the value of row.
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Get the value of a column.
     *
     * @return the value of column.
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

}
