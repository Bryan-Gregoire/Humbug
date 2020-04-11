package g53735.humbug.model;

import static g53735.humbug.model.SquareType.GRASS;
import static g53735.humbug.model.SquareType.STAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author g53735
 */
public class BumblebeeTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), null, new Square(STAR), new Square(GRASS)}
        });
        animals = new Animal[]{
            new Bumblebee(new Position(0, 0)),
            new Bumblebee(new Position(1, 1))
        };
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_flyOverNull() {
        System.out.println("fly over null");
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = new Position(2, 0); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_flyOverNull_fall() {
        System.out.println("fly over null and fall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), null, null, new Square(STAR)}
        });
        animals[0].setPositionOnBoard(new Position(2, 0));
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = null; //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_flyOverAnimal() {
        System.out.println("fly over an animal");
        Bumblebee instance = (Bumblebee) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_flyOverAnimals_Fall() {
        System.out.println("fly over animals and fall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), null, new Square(STAR), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), null, new Square(GRASS)}
        });
        animals = new Animal[]{
            new Bumblebee(new Position(0, 0)),
            new Bumblebee(new Position(3, 0)),
            new Snail(new Position(2, 0))
        };
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = null; //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_flyOverAnimals_2() {
        System.out.println("fly over animals");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), null, new Square(STAR), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), null, new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null}
        });
        animals = new Animal[]{
            new Bumblebee(new Position(0, 0)),
            new Bumblebee(new Position(3, 0)),
            new Snail(new Position(2, 0))
        };
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = new Position(4, 0); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_notInside() {
        System.out.println("move outside the game board");
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = null; //.next(Direction.NORTH);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_flyOverWall() {
        System.out.println("move over a wall");
        Bumblebee instance = (Bumblebee) animals[0];
        board.getSquare(new Position(0, 0)).setEastWall(true);
        board.getSquare(new Position(0, 1)).setWestWall(true);
        board.getSquare(new Position(0, 1)).setEastWall(true);
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_Onstar() {
        System.out.println("fly on star");
        animals[0].setPositionOnBoard(new Position(0, 2));
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = new Position(2, 2); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_Onstar_2() {
        System.out.println("fly on animal and on star");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR), new Square(GRASS)}
        });
        animals[1].setPositionOnBoard(new Position(2, 2));
        animals[0].setPositionOnBoard(new Position(0, 2));
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = new Position(3, 2); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Bumblebee.
     */
    @Test
    public void testMove_Onstar_3() {
        System.out.println("fly over nul, On animal and on star");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), null, new Square(GRASS), new Square(STAR)},
            {new Square(GRASS), null, new Square(GRASS), new Square(GRASS)}
        });
        animals[1].setPositionOnBoard(new Position(2, 2));
        animals[0].setPositionOnBoard(new Position(2, 0));
        Bumblebee instance = (Bumblebee) animals[0];
        Position expResult = new Position(2, 3); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }
}
