package mineField;

import mvc.*;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class MinefieldPanel extends AppPanel {

    public MinefieldPanel(AppFactory factory) {
        super(factory);
        String[] strings = new String[] {"NW", "N", "NE", "W", "", "E", "SW", "S", "SE"};
        controlPanel.setLayout(new GridLayout(3,3));
        for (String s :
                strings) {
            JButton temp = new JButton(s);
            JPanel tempPanel = new JPanel();
            tempPanel.add(temp);
            temp.addActionListener(this);
            controlPanel.add(temp);
        }

        controlPanel.setBackground(Color.WHITE);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
        // TODO handle property changes and special exceptions?
    }


    public static void main(String[] args) {
        AppFactory factory = new MinefieldFactory();
        AppPanel panel = new MinefieldPanel(factory);
        panel.display();
    }
}
