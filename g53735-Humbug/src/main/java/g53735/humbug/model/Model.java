package g53735.humbug.model;

/**
 * Define methods that the class Game should implement.
 *
 * @author g53735
 */
public interface Model {

    Board getBoard();

    Animal[] getAnimals();

    void startLevel(int level);

    boolean levelIsOver();

    void move(Position position, Direction direction);
}
