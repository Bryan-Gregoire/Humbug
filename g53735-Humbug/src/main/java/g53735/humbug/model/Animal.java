package g53735.humbug.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * Represent the animal on the game board.
 *
 * @author g53735
 */
@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = Bumbelbee.class),
    @Type(value = Grasshopper.class),
    @Type(value = Ladybird.class),
    @Type(value = Snail.class),
    @Type(value = Spider.class),
    @Type(value = Butterfly.class)})
public abstract class Animal {
    
    protected Position positionOnBoard;
    protected boolean onStar;

    /**
     * Constructor of Animal.
     *
     * @param positionOnBoard the position of the animal on the game board.
     */
    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        this.onStar = false;
    }
    
    
    /**
     * Constructor of Animal.
     * 
     */
    public Animal() {
    }

    /**
     * Get the position of the animal on the game board.
     *
     * @return the position.
     */
    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Tell if the animal is on a star box or not.
     *
     * @return True if the animal is on a star box, else false.
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * Set the position of the animal on the game board.
     *
     * @param positionOnBoard the position of the animal.
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * Set true or false.
     *
     * @param onStar if is on star or no.
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Move the animal, change the position of the animal.
     *
     * @param board board game.
     * @param direction the direction where we want to move.
     * @param animals the animal(s) we want to move.
     * @return the new position.
     */
    public abstract Position move(Board board, Direction direction,
            Animal... animals);
    
    protected Position moveOnJumping(Board board, Direction direction,
            Animal... animals) {
        Position arrivalPos = this.positionOnBoard;
        Position nextPos = arrivalPos.next(direction);
        if (!board.isInside(nextPos)) {
            this.setPositionOnBoard(null);
            return null;
        }
        
        boolean free = true;
        while (free) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(nextPos)
                        && !animal.onStar) {
                    free = false;
                }
            }
            if (free) {
                break;
            } else {
                arrivalPos = nextPos;
                nextPos = arrivalPos.next(direction);
                
                if (!board.isInside(nextPos)) {
                    this.setPositionOnBoard(null);
                    return null;
                }
            }
            free = true;
        }
        
        arrivalPos = nextPos;
        if (board.getSquareType(arrivalPos) == SquareType.STAR) {
            this.setOnStar(true);
            board.setSquareType(arrivalPos, SquareType.GRASS);
        }
        return arrivalPos;
    }

    /**
     * The animal flies up to its destination, go to the next case if there is
     * an animal on the square where the animal land. Change the position of the
     * animal.
     *
     * @param board the given board.
     * @param arrivalPos The given position where the animal should landed.
     * @param direction the given direction where to move.
     * @param animals the others animals on the game board.
     * @return the position where landed the animal.
     */
    protected Position moveOneFlying(Board board, Position arrivalPos,
            Direction direction, Animal... animals) {
        Position initialPos;
        if (!board.isInside(arrivalPos)) {
            this.setPositionOnBoard(null);
            return null;
        }
        
        boolean free = true;
        while (free) {
            for (Animal animal : animals) {
                if (animal.getPositionOnBoard().equals(arrivalPos)
                        && !animal.onStar) {
                    free = false;
                }
            }
            if (free) {
                break;
            } else {
                initialPos = arrivalPos;
                arrivalPos = initialPos.next(direction);
                if (!board.isInside(arrivalPos)) {
                    this.setPositionOnBoard(null);
                    return null;
                }
            }
            free = true;
        }
        
        initialPos = arrivalPos;
        if (board.getSquareType(initialPos) == SquareType.STAR) {
            this.setOnStar(true);
            board.setSquareType(initialPos, SquareType.GRASS);
        }
        return initialPos;
    }
}
