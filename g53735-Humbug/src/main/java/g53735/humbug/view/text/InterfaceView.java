package g53735.humbug.view.text;

import g53735.humbug.model.Board;
import g53735.humbug.model.Direction;
import g53735.humbug.model.Position;
import g53735.humbug.model.Animal;

/**
 * Interface that represents the methods that must be in the view.
 *
 * @author g53735
 */
public interface InterfaceView {

    /**
     * Display the game board.
     *
     * @param board the given board to display.
     * @param animals the animals on the board.
     */
    void displayBoard(Board board, Animal... animals);

    /**
     * Ask for a position.
     *
     * @return the given position.
     */
    Position askPosition();

    /**
     * Ask for a cardinal direction.
     *
     * @return the given direction.
     */
    Direction askDirection();

    /**
     * Display an error message.
     *
     * @param message the given message to display.
     */
    void displayError(String message);
}
