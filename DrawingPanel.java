package JavaPaintApp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;



public class DrawingPanel extends JPanel {
    private BufferedImage loadedImage;
    private ArrayList<Stroke> strokes = new ArrayList<>();
    private Stroke currStroke;
    private Color currentColor = Color.BLACK;
    private int toolType = 0;
    private int brushSize = 2;

    public DrawingPanel() {
        setBackground(Color.WHITE); 
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currStroke = new Stroke();
                strokes.add(currStroke);
                }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (currStroke == null) { currStroke = new Stroke();}
                
                if (e.getX() >= 0 && e.getY() >= 0) {
                    currStroke.add(new DrawingObject(
                        currentColor,
                        toolType,
                        e.getPoint(),
                        brushSize));
                }

                repaint();

            }
        });
    }

    public void setColor(Color c) {
        this.currentColor = c;
    }

    public void setTool(int tool) {
        this.toolType = tool;
    }

    public void setBrushsize(int size) {
        this.brushSize = size;
    }

    public int getBrushSize() { return brushSize; }

    public void clearDrawing() {
        strokes.clear();
        repaint();
    }

    public void setLoadedImage(BufferedImage img) {
        this.loadedImage = img;
        repaint();
    }

    public void saveDrawing(File file) throws IOException{
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        paint(g2);
        g2.dispose();
        ImageIO.write(img, "png", file);
    }

    public void loadFromFile(File file) throws IOException {
    loadedImage = ImageIO.read(file);
    strokes.clear();
    repaint();
}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (loadedImage != null) {
            g2.drawImage(loadedImage, 0, 0, null);
        }

        for(Stroke stroke : strokes) {
            ArrayList<DrawingObject> points = stroke.getPoints();

            for (int i = 0; i < points.size(); i++) {
                DrawingObject obj = points.get(i);
                g2.setColor(obj.getColor());
                Point p = obj.getPoint();
                int size = obj.getBrushSize();

                if (obj.getToolType() == 0) {
                    g2.fillOval(p.x, p.y, size, size);
                }
            
                if (obj.getToolType() == 1) {
                    g2.fillRect(p.x, p.y, size, size * 4);
                }

                if (obj.getToolType() == 2) {
                    g2.clearRect(p.x, p.y, size, size);
                }

                if (i > 0) {
                    Point prev = points.get(i - 1).getPoint();
                    Point curr = p;

                    int dx = curr.x - prev.x;
                    int dy = curr.y - prev.y;

                    int steps = Math.max(Math.abs(dx), Math.abs(dy));

                    for (int s = 0; s < steps; s++) {
                        int x = prev.x + (dx * s) / steps;
                        int y = prev.y + (dy * s) / steps;

                        if (obj.getToolType() == 0) {
                            g2.fillOval(x, y, size, size);
                        }

                        if (obj.getToolType() == 1) {
                            g2.fillRect(x, y, size, size * 4);
                        }

                        if (obj.getToolType() == 2) {
                            g2.clearRect(x, y, size, size);
                        }
                    }
                }
            } 
        }
    }
}
