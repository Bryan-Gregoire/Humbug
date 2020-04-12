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
public class ButterflyTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {new Square(GRASS), null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR), new Square(GRASS)}
        });
        animals = new Animal[]{
            new Butterfly(new Position(0, 0)),
            new Butterfly(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_notInside() {
        System.out.println("move outside the game board");
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = null; //.next(Direction.NORTH);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_flyOverNull() {
        System.out.println("fly over null");
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(3, 0); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_flyOverNull_2() {
        System.out.println("fly over null 2");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(GRASS)},
            {new Square(GRASS), null, new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(3, 0); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_flyOverNullAndFall_3() {
        System.out.println("fly over null and fall 3");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(GRASS)},
            {null, null, new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = null; //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_flyOverNullOnStar_4() {
        System.out.println("fly over null and fall 4");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(GRASS)},
            {new Square(STAR), null, new Square(GRASS)},
            {new Square(GRASS), null, null}
        });
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(3, 0); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_flyOverAnimals() {
        System.out.println("fly over animals");
        animals = new Animal[]{
            new Butterfly(new Position(0, 0)),
            new Snail(new Position(0, 1)),
            new Ladybird(new Position(0, 2))
        };
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(0, 3); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_LandOnAnimal() {
        System.out.println("fly and land on animal");
        animals = new Animal[]{
            new Butterfly(new Position(0, 0)),
            new Snail(new Position(1, 0)),
            new Ladybird(new Position(3, 0))
        };
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = new Position(4, 0); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_LandOnAnimalAndFall() {
        System.out.println("fly and land on animals and fall");
        animals = new Animal[]{
            new Butterfly(new Position(0, 0)),
            new Grasshopper(new Position(3, 0)),
            new Spider(new Position(4, 0))
        };
        Butterfly instance = (Butterfly) animals[0];
        Position expResult = null; //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_flyOverWall() {
        System.out.println("fly over walls");
        Butterfly instance = (Butterfly) animals[0];
        board.getSquare(new Position(0, 0)).setEastWall(true);
        board.getSquare(new Position(0, 1)).setWestWall(true);
        board.getSquare(new Position(0, 1)).setEastWall(true);
        board.getSquare(new Position(0, 2)).setWestWall(true);
        board.getSquare(new Position(0, 2)).setEastWall(true);
        board.getSquare(new Position(0, 3)).setWestWall(true);
        Position expResult = new Position(0, 3); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Butterfly.
     */
    @Test
    public void testMove_Onstar() {
        System.out.println("fly on star");
        Butterfly instance = (Butterfly) animals[1];
        Position expResult = new Position(4, 2); //.next(Direction.SOUTH);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }
}
