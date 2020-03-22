package g53735.humbug.model;

/**
 * Square on the board. A square has a type grass or star and it’s all. A square
 * does not know where it is on the board.
 *
 * @author g53735
 */
public class Square {

    private SquareType type;

    /**
     * Constructor of Square on board.
     *
     * @param type Square is grass or star
     */
    public Square(SquareType type) {
        this.type = type;
    }

    /**
     * Simple getter of type.
     *
     * @return type of Square
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Set a type.
     *
     * @param type the given type.
     */
    public void setType(SquareType type) {
        this.type = type;
    }
}
