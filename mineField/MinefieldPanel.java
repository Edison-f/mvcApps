package mineField;

import mineField.*;
import mvc.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
// TODO trim down imports if they're unnecessary, copied them from other code

public class MinefieldPanel extends AppPanel {
    private JButton NW;
    private JButton N;
    private JButton NE;
    private JButton W;
    private JButton E;
    private JButton SW;
    private JButton S;
    private JButton SE;
    public MinefieldPanel(AppFactory factory) {
        super(factory);
        NW = new JButton("NW");
        N = new JButton("N");
        NE = new JButton("NE");
        W = new JButton("W");
        E = new JButton("E");
        SW = new JButton("SW");
        S = new JButton("S");
        SE = new JButton("SE");

        NW.addActionListener(this);
        N.addActionListener(this);
        NE.addActionListener(this);
        W.addActionListener(this);
        E.addActionListener(this);
        SW.addActionListener(this);
        S.addActionListener(this);
        SE.addActionListener(this);

        controlPanel.add(NW);
        controlPanel.add(N);
        controlPanel.add(NE);
        controlPanel.add(W);
        controlPanel.add(E);
        controlPanel.add(SW);
        controlPanel.add(S);
        controlPanel.add(SE);

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
