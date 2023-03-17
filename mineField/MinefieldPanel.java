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
        controlPanel.setLayout(new GridLayout(3,3));
        NW = new JButton("NW");
        JPanel nwP = new JPanel();
        nwP.add(NW);
        N = new JButton("N");
        JPanel nP = new JPanel();
        nP.add(N);
        NE = new JButton("NE");
        JPanel neP = new JPanel();
        neP.add(NE);
        W = new JButton("W");
        JPanel wP = new JPanel();
        wP.add(W);
        JPanel centerPanel = new JPanel();
        E = new JButton("E");
        JPanel eP = new JPanel();
        eP.add(E);
        SW = new JButton("SW");
        JPanel swP = new JPanel();
        swP.add(SW);
        S = new JButton("S");
        JPanel sP = new JPanel();
        sP.add(S);
        SE = new JButton("SE");
        JPanel seP = new JPanel();
        seP.add(SE);

        NW.addActionListener(this);
        N.addActionListener(this);
        NE.addActionListener(this);
        W.addActionListener(this);
        E.addActionListener(this);
        SW.addActionListener(this);
        S.addActionListener(this);
        SE.addActionListener(this);

        controlPanel.add(nwP);
        controlPanel.add(nP);
        controlPanel.add(neP);
        controlPanel.add(wP);
        controlPanel.add(centerPanel);
        controlPanel.add(eP);
        controlPanel.add(swP);
        controlPanel.add(sP);
        controlPanel.add(seP);

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
