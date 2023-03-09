package mvc;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener{

    public Model model;

    public View(Model model) {
        this.model = model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
