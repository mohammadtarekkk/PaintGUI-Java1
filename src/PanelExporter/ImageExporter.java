package PanelExporter;

import java.nio.file.Path;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import FilesManger.FileManger;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageExporter {

    public static void savePanelAsImage(JPanel panel) {
        Path savePath = FileManger.chooseOpenFile(panel);
        if (savePath == null) return;

        String pathStr = savePath.toString();
        if (!pathStr.toLowerCase().endsWith(".png")) {
            pathStr += ".png";
            savePath = new File(pathStr).toPath();
        }

        int width = panel.getWidth();
        int height = panel.getHeight(); 
        int skipTop = 50; 

        BufferedImage image = new BufferedImage(width, height - skipTop, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = image.createGraphics();
        g2.translate(0, -skipTop);
        panel.paint(g2);
        g2.dispose();

        try {
            ImageIO.write(image, "png", savePath.toFile());
            JOptionPane.showMessageDialog(panel, "Panel saved as image:\n" + savePath, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Failed to save image", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
