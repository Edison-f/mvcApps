package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {

    ControlPanel controls;
    View view;
    Model model;
    AppFactory factory;

    public AppPanel(ControlPanel controls, View view, Model model, AppFactory factory) {
        this.controls = controls;
        this.view = view;
        this.model = model;
        this.factory = factory;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public class ControlPanel extends JPanel{

    }

}
