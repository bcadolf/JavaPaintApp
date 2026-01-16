package JavaPaintApp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

public class PaintApp extends JFrame {
    public PaintApp() {
        setTitle("Basic Paint App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawingPanel panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER);

        JToolBar menuBar = new JToolBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        // tools to draw
        JButton penTool = new JButton("Pen");
        JButton markerTool = new JButton("Marker");
        JButton eraserTool = new JButton("Eraser");

        // colors to pick
        JButton blkColor = new JButton("Black");
        JButton redColor = new JButton("Red");
        JButton grnColor = new JButton("Green");
        JButton allColor = new JButton("More Colors");

        // brush size
        JButton incBrush = new JButton("+");
        JLabel curSize = new JLabel("Brush Size: " + panel.getBrushSize());
        JButton decBrush = new JButton("-");

        // functional tools
        JButton clrAll = new JButton("Clear");
        JButton saveImg = new JButton("Save");
        JButton loadImg = new JButton("Load");



        // action listeners and variable setting
        penTool.addActionListener(e -> panel.setTool(0));
        markerTool.addActionListener(e -> panel.setTool(1));
        blkColor.addActionListener(e -> panel.setColor(Color.BLACK));
        redColor.addActionListener(e -> panel.setColor(Color.RED));
        grnColor.addActionListener(e -> panel.setColor(Color.GREEN));
        allColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(allColor, "Pick a Color", Color.BLACK);
            if (newColor != null) {
                panel.setColor(newColor);
            }
        });
        incBrush.addActionListener(e -> {
            panel.setBrushsize(panel.getBrushSize() + 1);
            curSize.setText("Brush Size: " + panel.getBrushSize());
        });
        decBrush.addActionListener(e -> { 
            panel.setBrushsize(Math.max(1, panel.getBrushSize() - 1));
            curSize.setText("Brush Size: " + panel.getBrushSize());
        });
        eraserTool.addActionListener(e -> panel.setTool(2));
        clrAll.addActionListener(e -> panel.clearDrawing());
        saveImg.addActionListener(e -> FileManger.saveFile(panel, this));
        loadImg.addActionListener(e -> FileManger.loadFile(panel, this));


        // add to toolbar
        menuBar.add(penTool);
        menuBar.add(markerTool);
        menuBar.add(eraserTool);
        menuBar.addSeparator();
        menuBar.add(blkColor);
        menuBar.add(redColor);
        menuBar.add(grnColor);
        menuBar.add(allColor);
        menuBar.addSeparator();
        menuBar.add(incBrush);
        menuBar.add(curSize);
        menuBar.add(decBrush);
        menuBar.addSeparator();
        menuBar.add(clrAll);
        menuBar.add(loadImg);
        menuBar.add(saveImg);


        add(menuBar, BorderLayout.NORTH);


        setVisible(true);
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new PaintApp()); }
}