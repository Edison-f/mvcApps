package mineField;
import mvc.*;

public class Minefield extends Model {

    Patch[][] field;
    int playerX;
    int playerY;

    final int fieldSize;
    public static int percentMined = 5; // default percentage of Patches with mines in them
    final int mineCount;

    public Minefield(int fieldSize) {
        this.fieldSize = fieldSize;
        field = new Patch[fieldSize][fieldSize];
        // initialize field patches
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Patch currentPatch = new Patch();

                // TODO determine if this is the correct rng method
                /*
                if (!((i == 0 || i == fieldSize - 1) && (i == j))) { // starting and ending tile check
                    int rng = Utilities.rng.nextInt(100) + 1; // nextInt returns 0 to bound - 1, rng = 1 to bound incl
                    if (percentMined >= rng) { // if rng is 1 to percentMined, place a mine
                        currentPatch.placeMine();
                    }
                }
                */
                field[i][j] = currentPatch;
            }
        }

        // random population method that gives an exact number of mines to place
        // TODO this might not be the right way to implement this. gonna ask the teacher during class
        // determine probabilistic mine count
        double patchCount = fieldSize * fieldSize;
        double percentDouble = Math.floorDiv(percentMined, 100);
        mineCount = (int)Math.floor(patchCount * percentDouble);

        // populate field with mines, excluding top left and bottom right patches (0,0 and fieldSize,fieldSize)
        int mineToPlace = mineCount;
        while (mineToPlace > 0) {
            // use Utilities rng to choose random mines
            int x = Utilities.rng.nextInt(fieldSize);
            int y = Utilities.rng.nextInt(fieldSize);

            // if x and y are 0, it tried to place a mine on the spawn patch
            // if x and y are fieldSize - 1, it tried to place a mine on the end patch
            boolean onInvalidPatch = ((x == 0 || x == fieldSize - 1) && x == y);
            // if the check is false, place the mine on the valid patch
            if (!onInvalidPatch) {
                field[x][y].placeMine();
                mineToPlace--;
            }
        }


        // init player location
        playerX = 0;
        playerY = 0;
    }

    // default constructor
    public Minefield() {
        this(20);
    }

    // TODO implement getters/setters and other important methods
}
