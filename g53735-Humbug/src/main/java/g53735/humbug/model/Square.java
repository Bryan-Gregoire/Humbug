package g53735.humbug.model;

/**
 * Square on the board. A square has a type grass or star and it’s all. A square
 * does not know where it is on the board.
 *
 * @author g53735
 */
public class Square {

    private SquareType type;
    private boolean nortWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;

    /**
     * Constructor of Square on board.
     *
     * @param type Square is grass or star
     */
    public Square(SquareType type) {
        this.type = type;
        this.nortWall = false;
        this.southWall = false;
        this.westWall = false;
        this.eastWall = false;
    }

    /**
     * North wall setter.
     * 
     * @param nortWall set a wall or no.
     */
    public void setNortWall(boolean nortWall) {
        this.nortWall = nortWall;
    }

    /**
     * South wall setter.
     * 
     * @param southWall set wall or no.
     */
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    /**
     * West wall setter.
     * 
     * @param westWall set wall or no.
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * East wall setter.
     * 
     * @param eastWall set wall or no.
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
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
        if(type == null){
            throw new IllegalArgumentException("Wrong type");
        }
        this.type = type;
    }
    
    /**
     * Tell if there is a wall in a given direction.
     * 
     * @param direction the given direction.
     * @return true if there is a wall else false.
     */
    public boolean hasWall(Direction direction) {
        switch(direction){
            case NORTH :
                return nortWall;
            case SOUTH :
                return southWall;
            case WEST :
                return westWall;
            case EAST : 
                return eastWall;
        }
        return false;
    }
}
