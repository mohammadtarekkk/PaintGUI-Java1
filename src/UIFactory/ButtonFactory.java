package UIFactory;

import javax.swing.*;
import java.awt.*;

public class ButtonFactory {

    public static JButton createIconButton(String iconPath, String tooltip, int size) {
        ImageIcon originalIcon = new ImageIcon(ButtonFactory.class.getResource(iconPath));
        Image scaledImage = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        
        JButton button = new JButton(new ImageIcon(scaledImage));
        button.setToolTipText(tooltip);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        
        return button;
    }
    
    public static JToggleButton createToggleButton(String iconOffPath, String iconOnPath, String tooltip, int size) {
        ImageIcon originalOff = new ImageIcon(ButtonFactory.class.getResource(iconOffPath));
        Image scaledOff = originalOff.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        
        ImageIcon originalOn = new ImageIcon(ButtonFactory.class.getResource(iconOnPath));
        Image scaledOn = originalOn.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        
        JToggleButton button = new JToggleButton(new ImageIcon(scaledOff));
        button.setSelectedIcon(new ImageIcon(scaledOn));
        button.setToolTipText(tooltip);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        
        return button;
}
}