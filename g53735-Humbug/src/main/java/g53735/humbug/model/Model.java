package g53735.humbug.model;

/**
 * Define methods that the class Game should implement.
 *
 * @author g53735
 */
public interface Model {

    /**
     * The board of the game.
     *
     * @return the board.
     */
    Board getBoard();

    /**
     * The animals of the game.
     *
     * @return the animals.
     */
    Animal[] getAnimals();

    /**
     * Get the remaining moves of a game.
     *
     * @return remaining moves.
     */
    int getRemainingMoves();

    /**
     * Start a given level.
     *
     * @param level the given level.
     */
    void startLevel(int level);

    /**
     * Get the status of a level.
     *
     * @return the status.
     */
    LevelStatus getLevelStatus();

    /**
     * Move an animal, change the position.
     *
     * @param position the position of an animal.
     * @param direction the direction we want the animal to move.
     */
    void move(Position position, Direction direction);
}
