package mineField;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class MinefieldFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Minefield();
    }

    @Override
    public View makeView(Model model) {
        return new MinefieldView(model);
    }

    // TODO implement MinefieldFactory methods below
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String[] getHelp() {
        return new String[0];
    }

    @Override
    public String about() {
        return null;
    }

    @Override
    public String[] getEditCommands() {
        return new String[0];
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        return null;
    }
}
