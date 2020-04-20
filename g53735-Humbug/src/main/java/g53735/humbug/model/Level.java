package g53735.humbug.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Provides a level to the game.
 *
 * @author g53735
 */
public class Level {

    private Board board;
    private Animal[] animals;
    private int nMoves;

    /**
     * The constructor of the level.
     *
     * @param board the board of the level.
     * @param animals the animals of the level.
     * @param nMoves the number of movement of the level.
     */
    private Level(Board board, Animal[] animals, int nMoves) {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * Constructor of the level.
     *
     */
    public Level() {
        this.board = null;
        this.animals = null;
        this.nMoves = 0;
    }

    /**
     * Get the board.
     *
     * @return the board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the animals.
     *
     * @return the animals.
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Number of movements allowed for a level.
     *
     * @return the number of time.
     */
    public int getnMoves() {
        return nMoves;
    }

    /**
     * The level of the game.
     *
     * @param nbLevel The number of the level.
     * @return the level.
     */
    public static Level getLevel(int nbLevel) {
        return readLevel(nbLevel);
    }

    /**
     * Read a json file that represents a level of the game.
     *
     * @param n the number of the level.
     * @return the level.
     */
    public static Level readLevel(int n) {
        try {
            var objectMapper = new ObjectMapper();
            var inputStream = Level.class.getResourceAsStream(
                    "/data/level-" + n + ".json");
            var level = objectMapper.readValue(inputStream, Level.class);
            return level;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
