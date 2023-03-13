package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {
    public ControlPanel controlPanel;
    public View view;
    public Model model;
    public AppFactory factory;

    public AppPanel(AppFactory factory) {

        this.factory = factory;
        this.controlPanel = new ControlPanel();
        this.model = factory.makeModel();
        this.view = factory.makeView(model);

        model.addPropertyChangeListener(this);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        view.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmmd = ae.getActionCommand();
        Command clicked = factory.makeEditCommand(model, cmmd, null);
        try {
            if (clicked!=null){
                clicked.execute();
            }
            else {
                switch (cmmd) {
                    case "Save": {
                        Utilities.save(model, false);
                        break;
                    }

                    case "SaveAs": {
                        Utilities.save(model, true);
                        break;
                    }

                    case "Open": {
                        Model newModel = Utilities.open(model);
                        if (newModel != null) {
                            this.updateModel(newModel);
                        }
                        break;
                    }

                    case "New": {
                        // TODO check that this works
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            this.updateModel(factory.makeModel());
                        }
                        break;
                    }

                    case "Quit": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!"))
                            System.exit(0);
                        break;
                    }

                    case "About": {
                        Utilities.inform(factory.about());
                        break;
                    }

                    case "Help": {
                        Utilities.inform(factory.getHelp());
                        break;
                    }

                    default: {
                        throw new Exception("Unrecognized command: " + cmmd);
                    }
                }
            }

        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }

    public void display() {
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);

        SafeFrame frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(500, 300); // TODO un-hardcode values?
        frame.setVisible(true);
        frame.setResizable(false);
    }

    // support function for switching the active model
    public void updateModel(Model model) {
        model.initSupport();
        // stop listening to old model
        this.model.removePropertyChangeListener(this);
        // update model
        this.model = model;
        this.model.addPropertyChangeListener(this);

        // make view update
        view.updateModel(model);
    }

    public class ControlPanel extends JPanel {
        public ControlPanel() {
            // make control panel pink
            this.setBackground(Color.PINK);


        }
    }

}
