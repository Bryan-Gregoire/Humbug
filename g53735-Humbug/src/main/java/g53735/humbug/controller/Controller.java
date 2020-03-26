package g53735.humbug.controller;

import g53735.humbug.model.Direction;
import g53735.humbug.model.Model;
import g53735.humbug.model.Position;
import g53735.humbug.view.text.View;

/**
 * The controller represents the dynamics of the game and the update of the view
 * as you go along.
 *
 * @author g53735
 */
public class Controller {

    private Model game;
    private View view;

    /**
     * Constructor of Controller
     *
     * @param game represents the model.
     * @param interfaceView represents the view.
     */
    public Controller(Model game, View interfaceView) {
        this.game = game;
        this.view = interfaceView;
    }

    /**
     * Start a game.
     *
     */
    public void startGame() {
        game.startLevel(1);
        while (!game.levelIsOver()) {
            view.displayBoard(game.getBoard(), game.getAnimals());
            Position position = view.askPosition();
            if (!game.getBoard().isInside(position)){
                System.out.println("Position out of the boundaries "
                        + "of the game board, try again");
                position = view.askPosition();
            }
            int i = 0;
            while (!game.getAnimals()[i].getPositionOnBoard().equals(position)){
                System.out.println("There is no animal in this position.");
                position = view.askPosition();
            }
            i++;
            Direction direction = view.askDirection();
            try {
                game.move(position, direction);
            } catch (Exception e) {
                view.displayError(e.getMessage());
            }
        }
        view.displayBoard(game.getBoard(), game.getAnimals());
        System.out.println("Well done, you won");
    }
}
