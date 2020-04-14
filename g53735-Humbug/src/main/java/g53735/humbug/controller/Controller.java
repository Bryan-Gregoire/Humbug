package g53735.humbug.controller;

import g53735.humbug.model.Direction;
import g53735.humbug.model.Game;
import g53735.humbug.model.LevelStatus;
import g53735.humbug.model.Model;
import g53735.humbug.model.Position;
import g53735.humbug.view.text.InterfaceView;
import g53735.humbug.view.text.View;
import g53735.humbug.model.Level;

/**
 * The controller represents the dynamics of the game and the update of the view
 * as you go along.
 *
 * @author g53735
 */
public class Controller {

    private final Model game;
    private final InterfaceView view;

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
     * @param nLevel the number of the level.
     */
    public void startGame(int nLevel) {
        game.startLevel(nLevel);
        while (game.getLevelStatus() != LevelStatus.NOT_STARTED) {
            view.displayBoard(Level.getLevel(nLevel).getBoard(),
                    Level.getLevel(nLevel).getAnimals());
            Position position = view.askPosition();
            position = validPosition(position);
            Direction direction = view.askDirection();
            try {
                game.move(position, direction);
            } catch (Exception e) {
                System.out.println("");
                view.displayError("You lost :( ");
                break;
            }
        }
        if (game.getLevelStatus() == LevelStatus.WIN) {
            nLevel++;
            startGame(nLevel);
        }
    }

    /**
     * Check if the given position is valid.
     *
     * @param position the given position.
     */
    private Position validPosition(Position position) {
        int i = 0;
        while (!game.getBoard().isInside(position)
                || !game.getAnimals()[i].getPositionOnBoard()
                        .equals(position)) {
            if (!game.getBoard().isInside(position)) {
                System.out.println("Position is not on the game board");
                position = view.askPosition();
            } else {
                if (i == game.getAnimals().length - 1) {
                    System.out.println("There is no animal in this"
                            + " position.");
                    position = view.askPosition();
                } else {
                    i++;
                }
            }
        }
        return position;
    }
}
