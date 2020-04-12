package g53735.humbug.model;

/**
 * Provides a level to the game.
 *
 * @author g53735
 */
public class Level {

    private Board board;
    private Animal[] animals;
    private int nMoves;

    /**
     * The constructor of the level.
     *
     * @param board the board of the level.
     * @param animals the animals of the level.
     * @param nMoves the number of movement of the level.
     */
    private Level(Board board, Animal[] animals, int nMoves) {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * Get the board.
     *
     * @return the board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the animals.
     *
     * @return the animals.
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Number of movements allowed for a level.
     *
     * @return the number of time.
     */
    public int getnMoves() {
        return nMoves;
    }

    /**
     * The level of the game.
     *
     * @param nbLevel The number of the level.
     * @return the level.
     */
    public static Level getLevel(int nbLevel) {
        Level level = null;
        if (nbLevel == 1) {
            level.board = new Board(new Square[][]{
                {new Square(SquareType.GRASS), new Square(SquareType.GRASS),
                    null},
                {null, new Square(SquareType.GRASS),
                    new Square(SquareType.GRASS)},
                {null, null, new Square(SquareType.STAR)}
            });
            level.animals = new Animal[]{
                new Snail(new Position(0, 0)),};
            level.nMoves = 4;
            return level;
        }

        if (nbLevel == 2) {
            level.board = new Board(new Square[][]{
                {new Square(SquareType.GRASS), new Square(SquareType.GRASS),
                    null},
                {null, new Square(SquareType.STAR), null},
                {new Square(SquareType.STAR), new Square(SquareType.GRASS),
                    new Square(SquareType.STAR)},
                {null, new Square(SquareType.GRASS), null}
            });
            level.animals = new Animal[]{
                new Snail(new Position(0, 0)),
                new Snail(new Position(2, 1)),
                new Snail(new Position(3, 1))};
            level.nMoves = 5;

            return level;
        }
        if (nbLevel == 3) {
            level.board = new Board(new Square[][]{
                {new Square(SquareType.GRASS), new Square(SquareType.GRASS),
                    new Square(SquareType.GRASS)}, //Faut rajouter les murs.
                {new Square(SquareType.GRASS), null,
                    new Square(SquareType.GRASS)},
                {new Square(SquareType.GRASS), null,
                    new Square(SquareType.GRASS)},
                {new Square(SquareType.STAR), new Square(SquareType.GRASS),
                    new Square(SquareType.GRASS)} //Manque les murs.
            });
            level.animals = new Animal[]{
                new Spider(new Position(2, 0))};

            level.nMoves = 4;
        }
        return level;
    }
}
