package g53735.humbug.view.text;

import g53735.humbug.model.Board;
import g53735.humbug.model.Direction;
import g53735.humbug.model.Position;

/**
 *
 * @author g53735
 */
public interface InterfaceView {

    void displayBoard(Board board);

    Position askPosition();

    Direction askDirection();

    void displayError(String message);
}
