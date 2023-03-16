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
        int minesToPlace = mineCount;
        while (minesToPlace > 0) {
            // use Utilities rng to choose random mines
            int x = Utilities.rng.nextInt(fieldSize);
            int y = Utilities.rng.nextInt(fieldSize);

            // if x and y are 0, it tried to place a mine on the spawn patch
            // if x and y are fieldSize - 1, it tried to place a mine on the end patch
            boolean onInvalidPatch = ((x == 0 || x == fieldSize - 1) && x == y);
            // if the check is false, place the mine on the valid patch
            if (!onInvalidPatch) {
                field[x][y].placeMine();
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

    /* Move the player in the given direction. Throws exceptions in the following scenarios:
     * - when the player moves off the grid
     * - when the player steps on a mine
     * - when the player reaches the goal
     * - when the player attempts to move after the game ends
     */

    // TODO remove heading input and take in an x and y change, get that from MoveCommand
    public void movePlayer(Heading heading) throws MinefieldException {
        // check if game is over
        if (isGameOver) {
            throw MinefieldException.create(MinefieldExceptionType.GAME_OVER);
        }

        // store values
        int newX = playerX;
        int newY = playerY;

        // move new pos in heading direction
        switch (heading) {
            case NORTH: {
                newY -= 1;
                break;
            }
            case SOUTH: {
                newY += 1;
                break;
            }
            case EAST: {
                newX += 1;
                break;
            }
            case WEST: {
                newX -= 1;
                break;
            }
            case NORTHWEST: {
                newX -= 1;
                newY -= 1;
                break;
            }
            case SOUTHWEST: {
                newX -= 1;
                newY += 1;
                break;
            }
            case NORTHEAST: {
                newX += 1;
                newY -= 1;
                break;
            }
            case SOUTHEAST: {
                newX += 1;
                newY += 1;
                break;
            }
        }
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
                throw MinefieldException.create(MinefieldExceptionType.STEPPED_ON_MINE);
            }
            // check if player won
            if (playerX == fieldSize - 1 && playerX == playerY) { // manual check for bottom right tile
                // player won
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
