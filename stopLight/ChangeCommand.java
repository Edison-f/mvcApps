package stopLight;

import mvc.*;

public class ChangeCommand extends Command {

    public ChangeCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() {
        Stoplight light = (Stoplight)model;
        light.change();
    }

}