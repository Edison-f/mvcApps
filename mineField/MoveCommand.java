package mineField;

import mvc.*;
import stopLight.Stoplight;

public class MoveCommand extends Command {
    Heading heading; // TODO find a way to use the heading here?
    public MoveCommand(Model model, Heading heading) {
        super(model);
        this.heading = heading;
    }

    public void execute() throws Exception {
        if (!(model instanceof Minefield)) {
            throw new Exception("Model must instantiate Minefield");
        }
        Minefield field = (Minefield)model;

        // TODO implement MoveCommand's execute, make it throw exceptions if it isn't allowed?
        switch(heading) {
            case NORTH: {
                // waiting to implement the model first and then we can work on moving?
            }
        }

    }
}
