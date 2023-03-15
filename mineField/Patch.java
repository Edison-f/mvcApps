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
        // TODO implement constructor
    }

    // TODO implement helper methods for init/revealing

}
