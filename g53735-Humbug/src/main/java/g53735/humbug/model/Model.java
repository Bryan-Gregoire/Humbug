package g53735.humbug.model;

/**
 * Define methods that the class Game should implement.
 *
 * @author g53735
 */
public interface Model {

    Board getBoard();

    Animal[] getAnimals();

    int getRemainingMoves();

    void startLevel(int level);

    LevelStatus getLevelStatus();

    void move(Position position, Direction direction);
}
