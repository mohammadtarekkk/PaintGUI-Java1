package FilesManger;

import ShapeCreater.ShapeFactory;
import Shapes.Shape;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FileManger {

    public static void savePanel(ArrayList<Shape> shapes, JPanel parent) {
        if (shapes == null || shapes.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No shapes to save!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Path path = chooseSaveFile(parent);
        if (path == null) return;

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writeShapesToFile(shapes, writer);
            JOptionPane.showMessageDialog(parent, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, "Error saving file!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static ArrayList<Shape> loadShapes(JPanel parent) {

        Path path = chooseOpenFile(parent);
        if (path == null) return null;

        ArrayList<Shape> shapes = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Shape shape = parseShapeLine(line);
                if (shape != null) shapes.add(shape);
            }

            if (shapes.isEmpty())
                JOptionPane.showMessageDialog(parent, "No shapes loaded from file!", "Info", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parent, "Error loading file!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        return shapes;
    }

    public static Path chooseOpenFile(JPanel parent) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(parent, "Load operation cancelled", "Info", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return chooser.getSelectedFile().toPath();
    }
    
    private static Path chooseSaveFile(JPanel parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new java.io.File(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt"
        ));

        if (chooser.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(parent, "Save operation cancelled", "Info", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        Path path = chooser.getSelectedFile().toPath();
        if (path.toFile().exists() &&
            JOptionPane.showConfirmDialog(parent, "File already exists. Overwrite?", "Confirm Save", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(parent, "Save operation cancelled", "Info", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

        return path;
    }

    private static Shape parseShapeLine(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length < 9) return null;

            Shape shape = ShapeFactory.createShape(parts[0]);
            if (shape == null) return null;

            shape.setXStarting(Integer.parseInt(parts[1]));
            shape.setYStarting(Integer.parseInt(parts[2]));
            shape.setCurrentX(Integer.parseInt(parts[3]));
            shape.setCurrentY(Integer.parseInt(parts[4]));
            shape.setStyle(parts[5]);
            shape.setColor(new Color(Integer.parseInt(parts[6])));
            shape.setFilled(Boolean.parseBoolean(parts[7]));
            shape.setDotted(Boolean.parseBoolean(parts[8]));


            return shape;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static void writeShapesToFile(ArrayList<Shape> shapes, BufferedWriter writer) {
        try {
            for (Shape shape : shapes) {
                if (shape != null) {
                    writer.write(shapeToLine(shape) + "\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String shapeToLine(Shape shape) {
        return shape.getClass().getName() + "," +
               shape.getXStarting() + "," +
               shape.getYStarting() + "," +
               shape.getCurrentX() + "," +
               shape.getCurrentY() + "," +
               shape.getStyle() + "," +
               shape.getColor().getRGB() + "," +
               shape.isFilled()+ "," +
               shape.isDotted();
    }
}
