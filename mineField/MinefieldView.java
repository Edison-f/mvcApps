package mineField;

import mvc.*;
import java.awt.*;

public class MinefieldView extends View {
    public MinefieldView(Model model) {
        super(model);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        // TODO implement paintComponent
        Color oldColor = gc.getColor();
        Minefield field = (Minefield)model;
        PatchShape[][] patchShapes = field.generateShapes();
        for (PatchShape[] inner :
                patchShapes) {
            for (PatchShape shape :
                    inner) {
                shape.draw((Graphics2D) gc);
            }
        }
        gc.setColor(oldColor);
    }
}
