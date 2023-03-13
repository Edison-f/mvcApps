package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

//                        if (model.getFileName() == null) {
//                            model.setFileName(Utilities.getFileName((String) null, false));
//                        }
//                        String fName = model.getFileName();
//                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
//                        os.writeObject(this.model);
//                        os.close();
                        break;
                    }

                    case "SaveAs": {
                        Utilities.save(model, true);

//                        model.setFileName(Utilities.getFileName((String) null, false));
//                        String fName = model.getFileName();
//                        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
//                        os.writeObject(this.model);
//                        os.close();
                        break;
                    }

                    case "Open": {

                        Model newModel = Utilities.open(model);

                        if (newModel !=null) {
                            this.model = newModel;
                            view.model = this.model;
                            model.addPropertyChangeListener(this);
                            model.changed();
                        }

//                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
//                            model.setFileName(Utilities.getFileName((String) null, false));
//                            String fName = model.getFileName();
//                            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
//                            model = (Model) is.readObject();
//                            view.model = model;
//                            is.close();
//                        }

                        break;

                    }

                    case "New": {
                        if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                            this.model = factory.makeModel();
                            view.model = this.model;
                            model.addPropertyChangeListener(this);
                            model.changed();
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
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public class ControlPanel extends JPanel{
        public ControlPanel() {
            super();
        }
    }

}
