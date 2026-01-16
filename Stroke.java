package JavaPaintApp;

import java.util.ArrayList;

public class Stroke {
    private ArrayList<DrawingObject> points = new ArrayList<>();

    public void add(DrawingObject obj) {
        points.add(obj);
    }

    public ArrayList<DrawingObject> getPoints() {
        return points;
    }
}
