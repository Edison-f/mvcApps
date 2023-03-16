package mineField;

import mvc.*;

public class Minefield extends Model {

    public static int percentMined = 5; // default percentage of Patches with mines in them
    final int fieldSize;
    final int mineCount;
    Patch[][] field;
    int playerX;
    int playerY;

    public Minefield(int fieldSize) {
        this.fieldSize = fieldSize;
        field = new Patch[fieldSize][fieldSize];
        // initialize field patches
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = new Patch();
            }
        }

        // determine mine count
        double patchCount = fieldSize * fieldSize;
        double percentDouble = Math.floorDiv(percentMined, 100);
        mineCount = (int) Math.floor(patchCount * percentDouble);

        // populate field with mines, excluding top left and bottom right patches (0,0 and fieldSize,fieldSize)
        // TODO this might not be the right way to implement this. gonna ask the teacher before class
        int placedMines = 0;
        while (placedMines <= mineCount) {
            // use Utilities rng to choose random mines
            int x = Utilities.rng.nextInt(fieldSize);
            int y = Utilities.rng.nextInt(fieldSize);

            // if x and y are 0, it tried to place a mine on spawn patch
            boolean isOnStartingPatch = (x == 0 && x == y);
            // if x and y are equal to fieldSize - 1, it tried to place on the end patch
            boolean isOnEndingPatch = (x == fieldSize - 1 && x == y);
            // check both and place mine if neither are true
            if (!(isOnStartingPatch || isOnEndingPatch)) {
                field[x][y].placeMine();
                placedMines++;
            }
        }
        generateAdjacentMines();
        // init player location
        playerX = 0;
        playerY = 0;
    }

    // default constructor
    public Minefield() {
        this(20);
    }

    private void generateAdjacentMines() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j].setMinesAround(findAdjacentMines(i, j));
            }
        }
    }

    public int findAdjacentMines(int x, int y) {
        int result = 0;
        for (int i = x - 1; i < x + 1; i++) {
            for (int j = y - 1; j < y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                try {
                    if (field[i][j].hasMine()) {
                        result++;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return result;
    }

    // TODO implement getters/setters and other important methods
}
