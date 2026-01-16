package JavaPaintApp;

import java.awt.Color;
import java.awt.Point;

public class DrawingObject {
    private Point point;
    private Color color;
    private int toolType;
    private int brushSize;
    

    public DrawingObject(Color color, int toolType, Point point, int brushSize) {
        this.point = point;
        this.color = color;
        this.toolType = toolType;
        this.brushSize = brushSize;
    }

    public Color getColor() {
        return color;
    }

    public int getToolType() {
        return toolType;
    }

    public Point getPoint() {
        return point;
    }

    public int getBrushSize() {
        return brushSize;
    }
}
