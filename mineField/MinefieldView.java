package mineField;

import mvc.*;

import java.awt.*;

public class MinefieldView extends View {

    private static final int PATCH_SIZE = 20;

    public MinefieldView(Minefield field) {
        super(field);
        this.setLayout(new GridLayout(field.FIELD_SIZE, field.FIELD_SIZE));
        this.setPreferredSize(new Dimension(field.FIELD_SIZE*PATCH_SIZE, field.FIELD_SIZE*PATCH_SIZE));
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Minefield field = (Minefield)model;
        Color oldColor = gc.getColor();

        // get field
        Patch[][] patches = field.getField();

        // draw current field
        for (int x = 0; x < field.FIELD_SIZE; x++) {
            for (int y = 0; y < field.FIELD_SIZE; y++) {
                Cell cell = new Cell(patches[x][y], x, y, PATCH_SIZE, field);
                cell.draw((Graphics2D)gc);
            }
        }
        gc.setColor(oldColor);
    }

    class Cell {
        private Patch patch;
        private int xc, yc, width;
        private Color fillColor = Color.GRAY;
        private Color outlineColor = Color.BLACK;
        private String text = "?";

        private final int TEXT_OFFSET = 1;

        public Cell(Patch patch, int x, int y, int width, Minefield field) {
            this.patch = patch;
            this.xc = x * width;
            this.yc = y * width;
            this.width = width;
            // determine text in cell
            if (patch.isRevealed()) {
                if (patch.hasMine()) {
                    fillColor = Color.RED;
                    text = "";
                } else {
                    fillColor = Color.LIGHT_GRAY;
                    text = "" + patch.getMinesAround();
                }
            }
            // determine outline color
            if (x == field.FIELD_SIZE - 1 && x == y) {
                outlineColor = Color.GREEN;
                if (field.getPlayerX() == x && field.getPlayerY() == y) {
                    fillColor = Color.GREEN;
                }
            }
            if (x == field.getPlayerX() && y == field.getPlayerY()) {
                outlineColor = Color.WHITE;
            }
        }

        public void draw(Graphics2D gc) {
            Color oldColor = gc.getColor();
            // draw square and outline
            gc.setColor(fillColor);
            gc.fillRect(xc, yc, width, width);
            gc.setColor(outlineColor);
            gc.drawRect(xc, yc, width, width);
            gc.setColor(Color.BLACK);
            gc.drawString(text, xc + TEXT_OFFSET, yc + width - TEXT_OFFSET);
            gc.setColor(oldColor);
        }

    }
}
