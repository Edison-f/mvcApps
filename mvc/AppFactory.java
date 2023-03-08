package mvc;

public interface AppFactory {
    Model makeModel();
    View makeView();
    String getTitle();
    String getHelp();
    String about();
    String getEditCommands();
    Command makeeditCommand(String name);
}
