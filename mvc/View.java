package mvc;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class View extends JPanel implements PropertyChangeListener{

    public Model model;

    public View(Model model) {
        this.model = model;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName() == "New"
                || evt.getPropertyName() == "Open") {
            model.removePropertyChangeListener(this);
        }
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);

    }
}
