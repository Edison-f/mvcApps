package mineField;

import mvc.*;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
// TODO trim down imports if they're unnecessary, copied them from other code

public class MinefieldPanel extends AppPanel {

    public MinefieldPanel(AppFactory factory) {
        super(factory);
        String[] strings = new String[] {"NW", "N", "NE", "W", "E", "SW", "S", "SE"};
        for (String s :
                strings) {
            JButton temp = new JButton(s);
            temp.addActionListener(this);
            controlPanel.add(temp);
        }

        controlPanel.setBackground(Color.WHITE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        // TODO handle property changes
    }


    public static void main(String[] args) {
        AppFactory factory = new MinefieldFactory();
        AppPanel panel = new MinefieldPanel(factory);
        panel.display();
    }
}
