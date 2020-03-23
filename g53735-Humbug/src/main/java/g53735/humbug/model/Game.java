package g53735.humbug.model;

/**
 *
 * @author g53735
 */
public class Game implements Model {
    
    Board board ;
    
    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public Animal[] getAnimals() {
        return null;
    }

    @Override
    public void startLevel(int level) {
    }

    @Override
    public boolean levelIsOver() {
        return false;
    }

    @Override
    public void move(Position position, Direction direction) {
    }

}
