package g53735.humbug.view.text;

import g53735.humbug.model.Board;
import g53735.humbug.model.Direction;
import g53735.humbug.model.Position;
import g53735.humbug.model.Animal;

/**
 *
 * @author g53735
 */
public interface InterfaceView {

    void displayBoard(Board board,Animal... animals);

    Position askPosition();

    Direction askDirection();

    void displayError(String message);
}
