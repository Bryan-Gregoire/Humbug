package g53735.humbug.controller;

import g53735.humbug.model.Direction;
import g53735.humbug.model.LevelStatus;
import g53735.humbug.model.Model;
import g53735.humbug.model.Position;
import g53735.humbug.view.text.InterfaceView;
import g53735.humbug.view.text.View;

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
        view.displayMessage("Level: " + nLevel);
        while (game.getLevelStatus() == LevelStatus.IN_PROGRESS
                && nLevel <= 100) {
            view.displayRemaining(game.getRemainingMoves());
            view.displayBoard(game.getBoard(), game.getAnimals());
            Position position = view.askPosition();
            position = validPosition(position);
            Direction direction = view.askDirection();
            try {
                game.move(position, direction);
            } catch (Exception e) {
            }
            nLevel = winOrLose(nLevel);
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
                        .equals(position) || game.getAnimals()[i].isOnStar()) {
            if (!game.getBoard().isInside(position)) {
                view.displayMessage("Position is not on the game board");
                position = view.askPosition();
            } else {
                if (i == game.getAnimals().length - 1) {
                    i = 0;
                    view.displayMessage("There is no animal in this position");
                    position = view.askPosition();
                } else {
                    i++;
                }
            }
        }
        return position;
    }

    /**
     * Check if check if the level is won or lost, if the level is won, we pass
     * to the next level but if it is the last level we display the board. If
     * the level is lost, we repeat the same level.
     *
     * @param nLevel the number of the level.
     * @return the number of the new or same level.
     */
    private int winOrLose(int nLevel) {
        if (game.getLevelStatus().equals(LevelStatus.WIN)) {
            view.displayBoard(game.getBoard(), game.getAnimals());
            view.displayMessage("Well done, you finished the level " + nLevel);
            System.out.println();
            nLevel++;
            if (nLevel < 101) {
                view.displayMessage("Level : " + nLevel);
                game.startLevel(nLevel);
            } else {
                view.displayBoard(game.getBoard(), game.getAnimals());
                view.displayMessage("Game over");
            }
        }
        if (game.getLevelStatus().equals(LevelStatus.FAIL)) {
            view.displayMessage("You lost, try again");
            view.displayMessage("");
            view.displayMessage("Level:" + nLevel);
            game.startLevel(nLevel);
        }
        return nLevel;
    }
}
