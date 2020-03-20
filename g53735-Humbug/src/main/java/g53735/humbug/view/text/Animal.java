package g53735.humbug.view.text;

import g53735.humbug.model.Position;

/**
 *
 * @author g53735
 */
public abstract class Animal {

    private Position positionOnBoard;
    private boolean onStar;

    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        this.onStar = false;
    }

    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    public boolean isOnStar() {
        return onStar;
    }

    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }
}
