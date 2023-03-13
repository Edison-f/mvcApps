package mvc;

import java.awt.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener{

    public Model model;

    public View(Model model) {
        // init default model
        this.model = model;
        // set default looks
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // start listening to current model
        this.model.addPropertyChangeListener(this);
    }

    public void updateModel(Model model) { // called by AppPanel when updating the model, might be bad design -Kyle
        // stop listening to old model
        this.model.removePropertyChangeListener(this);
        // update model
        this.model = model;
        model.addPropertyChangeListener(this);

        // refresh
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.repaint();
    }
}
