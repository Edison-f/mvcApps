package mineField;
import mvc.*;

public class Minefield extends Model {

    Patch[][] field;
    int playerX;
    int playerY;

    public static int percentMined = 5; // default percentage of Patches with mines in them

    public Minefield(int fieldSize) {
        field = new Patch[fieldSize][fieldSize];
        playerX = 0;
        playerY = 0;
    }

    // default constructor
    public Minefield() {
        this(20);
    }

    // TODO implement getters/setters and other important methods
}
