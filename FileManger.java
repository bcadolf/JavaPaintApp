package JavaPaintApp;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FileManger {
    public static void saveFile(DrawingPanel panel, JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            try {
                panel.saveDrawing(fileChooser.getSelectedFile());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Error saving File");
            }
        }
    }

    public static void loadFile(DrawingPanel panel, JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            try {
                panel.loadFromFile(fileChooser.getSelectedFile());
            } catch (IOException ex) { 
                JOptionPane.showMessageDialog(parent, "Error opening file"); 
            }
        }
    }
}
