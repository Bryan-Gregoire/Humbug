package g53735.humbug;

import g53735.humbug.controller.Controller;
import g53735.humbug.model.Game;
import g53735.humbug.view.text.View;

/**
 *
 * @author g53735
 */
public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new Game(), new View());
        controller.startGame();
    }
}
