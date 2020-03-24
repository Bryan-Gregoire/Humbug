package g53735.humbug.controller;

import g53735.humbug.model.Direction;
import g53735.humbug.model.Model;
import g53735.humbug.model.Position;
import g53735.humbug.view.text.View;

/**
 *
 * @author g53735
 */
public class Controller {

    private Model game;
    private View view;

    public Controller(Model game, View interfaceView) {
        this.game = game;
        this.view = interfaceView;
    }

    public void startGame() {
        game.startLevel(1);
        while (!game.levelIsOver()) {
            view.displayBoard(game.getBoard(), game.getAnimals());
            Position position = view.askPosition();
            Direction direction = view.askDirection();
            game.move(position, direction);
        }
    }
}
