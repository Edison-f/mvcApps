package mineField;
import mvc.*;

public class Minefield extends Model {

    Patch[][] field;
    int playerX;
    int playerY;

    boolean isGameOver;

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

                // rng method that just randomly places mines as they are generated, number of mines is random
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
        // determine probabilistic mine count
        double patchCount = fieldSize * fieldSize;
        double percentDouble = (double)percentMined / 100.0;
        mineCount = (int)Math.floor(patchCount * percentDouble); // TODO code might be faulty
        System.out.println("debug math: " + patchCount + "," + percentDouble + "," + mineCount);

        // populate field with mines, excluding top left and bottom right patches (0,0 and fieldSize,fieldSize)
        // TODO potential issue: code can loop forever if there are more mines than patches available to place
        // could happen if mineCount > fieldSize^2 - 2 (2 safe tiles)
        int minesToPlace = mineCount;
        while (minesToPlace > 0) {
            // use Utilities rng to choose random mines
            int x = Utilities.rng.nextInt(fieldSize);
            int y = Utilities.rng.nextInt(fieldSize);
            Patch currentPatch = field[x][y];
            // check if the current patch doesn't have a mine on it, and that it isn't the start/end safe patches
            if (!(currentPatch.hasMine() || isSafePatch(x,y))) {
                currentPatch.placeMine();
                System.out.println("placing mine at " + x + "," + y);
                minesToPlace--;
                // TODO increment mine count of surrounding patches?
            }
        }


        // init player location & gamestate
        playerX = 0;
        playerY = 0;
        isGameOver = false;
    }

    // default constructor
    public Minefield() {
        this(20);
    }

    // helper method for determining if the coordinates given are inbounds
    private boolean isInBounds(int x, int y) {
        return (0 <= x && x < fieldSize) && (0 <= y && y < fieldSize);
    }

    private boolean isSafePatch(int x, int y) {
        return (x == 0 || x == fieldSize - 1) && (x == y);
    }

    /* Move the player in the given direction. Throws exceptions in the following scenarios:
     * - when the player moves off the grid
     * - when the player steps on a mine
     * - when the player reaches the goal
     * - when the player attempts to move after the game ends
     */
    public void movePlayer(int xChange, int yChange) throws MinefieldException {
        // check if game is over
        if (isGameOver) {
            // don't let the player move
            throw MinefieldException.create(MinefieldExceptionType.GAME_OVER);
        }

        // store new values
        int newX = playerX + xChange;
        int newY = playerY + yChange;

        // TODO remove debug print
        System.out.println("moving from " + playerX + "," + playerY + " to " + newX + "," + newY);
        // check if in bounds
        if (isInBounds(newX, newY)) {
            // movement is in bounds, so do it
            playerX = newX;
            playerY = newY;
            // get stepped on patch
            Patch steppedPatch = field[playerX][playerY];
            steppedPatch.reveal(); // reveal it
            this.firePropertyChange("PlayerMovedSuccessfully", null, null);
            // check if player moved onto a mine
            if (steppedPatch.hasMine()) {
                // player lost
                this.isGameOver = true;
                throw MinefieldException.create(MinefieldExceptionType.STEPPED_ON_MINE);
            }
            // check if player won
            if (playerX == fieldSize - 1 && playerX == playerY) { // manual check for bottom right tile
                // player won
                this.isGameOver = true;
                throw MinefieldException.create(MinefieldExceptionType.WON);
            }
        } else {
            // move is not allowed out of bounds
            this.firePropertyChange("PlayerMovedUnsuccessful", null, null); // TODO consider, may be unnecessary
            throw MinefieldException.create(MinefieldExceptionType.MOVED_OUT_OF_BOUNDS);
        }
    }



    // TODO implement getters/setters and other important methods
}
