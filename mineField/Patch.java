package mineField;

import mvc.*; // may be an unnecessary import
import java.io.Serializable;

public class Patch implements Serializable {
    // Class that handles storing variables for the Minefield tiles

    private boolean isRevealed;
    private boolean hasMine; // change these names if something makes more sense
    private int minesAround;

    public Patch() {
        super();
        isRevealed = false;
        hasMine = false;
        minesAround = 0;
        // TODO implement constructor
    }

    public void placeMine() {
        hasMine = true;
    }

    public void reveal() {
        isRevealed = true;
        // TODO push an update in the Minefield model that calls this!
    }

    // TODO implement helper methods for init/revealing

}
