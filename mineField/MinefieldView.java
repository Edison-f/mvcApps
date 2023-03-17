package mineField;

import mvc.*;

import javax.swing.*;
import java.awt.*;

public class MinefieldView extends View {
    private Cell cells[][];
    private int fieldSize;
    public MinefieldView(Minefield field) {
        super(field);
        fieldSize = field.fieldSize;
        cells = new Cell[fieldSize][fieldSize];
        this.setLayout(new GridLayout(fieldSize, fieldSize));

        for(int col = 0; col < fieldSize; col++) {
            for(int row = 0; row < fieldSize; row++) {
                cells[row][col] = new Cell();
                cells[row][col].setText("?");
                cells[row][col].patch = field.getPatch(row,col);
                cells[row][col].setBackground(Color.LIGHT_GRAY);
                cells[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(cells[row][col].patch.isGoal()) {
                    cells[row][col].setBackground(Color.WHITE);
                    cells[row][col].setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                this.add(cells[row][col]);
            }
        }

        this.setPreferredSize(new Dimension(fieldSize*40, fieldSize*40));
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Minefield field = (Minefield)model;
        Color oldColor = gc.getColor();

        for (Cell[] row : cells) {
            for (Cell c : row) {
                gc.fillRect(c.patch.getX()*40, c.patch.getY()*40, 40, 40);
                if (c.patch.isRevealed()) { c.reveal(); }
            }
        }

        cells[field.getPlayerX()][field.getPlayerY()].reveal();

        gc.setColor(oldColor);
    }

    class Cell extends JPanel {
        private JLabel label;
        public Patch patch;

        public Cell() {
            setPreferredSize(new Dimension(40, 40));
            label = new JLabel("?");
            add(label);
        }

        public void reveal() {
            setText("");
            setEnabled(false); // disables cell panel
            patch.reveal();
            if (patch.hasMine()) {
                setBackground(Color.RED);
                setBorder(BorderFactory.createLineBorder(Color.WHITE));
            } else if (patch.isGoal()) {
                setBackground(Color.WHITE);
                setBorder(BorderFactory.createLineBorder(Color.GREEN));
            } else {
                setBackground(Color.WHITE);
                setBorder(BorderFactory.createLineBorder(Color.WHITE));
                setText("" + patch.getMinesAround());
            }
        }
        public void setText(String text) {
            label.setText(text);
        }
    }
}
