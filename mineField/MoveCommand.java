package mineField;

import mvc.*;

public class MoveCommand extends Command {

    Heading heading; // TODO find a way to use the heading here?
    public MoveCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        // TODO implement MoveCommand's execute, make it throw exceptions if it isn't allowed?
    }
}
