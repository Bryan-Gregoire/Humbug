package g53735.humbug.model;

import static g53735.humbug.model.SquareType.GRASS;
import static g53735.humbug.model.SquareType.STAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author g53735
 */
public class LadybirdTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),
            new Ladybird(new Position(1, 1))};
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_notInside() {
        System.out.println("move and fall");
        Ladybird instance = (Ladybird) animals[1];
        Position expResult = null; //.next(Direction.WEST);
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_Fall() {
        System.out.println("move and fall");
        Ladybird instance = (Ladybird) animals[1];
        Position expResult = null; //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_BlockByAnimal() {
        System.out.println("move and blocked by an animal");
        animals[1].setPositionOnBoard(new Position(0, 2));
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_NextFree() {
        System.out.println("move with an other animal on the game board");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals[1].setPositionOnBoard(new Position(0, 3));
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_passOnStar() {
        System.out.println("move on a star and fall");
        animals[1].setPositionOnBoard(new Position(1, 2));
        Ladybird instance = (Ladybird) animals[1];
        Position expResult = null; //.next(Direction.EAST);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertFalse(animals[1].isOnStar());
        assertFalse(board.getSquareType(new Position(2, 2)) == GRASS);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_onStar() {
        System.out.println("move on star");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(STAR),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(GRASS), null}
        });
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertTrue(board.getSquareType(new Position(0, 2)) == GRASS);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_onStar_2() {
        System.out.println("move blocked by an animal and on star");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(GRASS), null}
        });
        Ladybird instance = (Ladybird) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 2));
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertTrue(board.getSquareType(new Position(0, 1)) == GRASS);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_onWall() {
        System.out.println("move and blocked by a wall");
        Ladybird instance = (Ladybird) animals[0];
        board.getSquare(new Position(0, 0)).setEastWall(true);
        Position expResult = new Position(0, 0); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_onWall_2() {
        System.out.println("move and blocked by a wall");
        Ladybird instance = (Ladybird) animals[0];
        board.getSquare(new Position(0, 1)).setWestWall(true);
        Position expResult = new Position(0, 0); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_onWall_notFall() {
        System.out.println("move and blocked by a wall");
        Ladybird instance = (Ladybird) animals[0];
        board.getSquare(new Position(0, 0)).setNorthWall(true);
        Position expResult = new Position(0, 0); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_onWall_OnStar() {
        System.out.println("move and blocked by a wall and on star");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(GRASS), null}
        });
        Ladybird instance = (Ladybird) animals[0];
        board.getSquare(new Position(0, 1)).setEastWall(true);
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertTrue(board.getSquareType(new Position(0, 1)) == GRASS);
    }
}
