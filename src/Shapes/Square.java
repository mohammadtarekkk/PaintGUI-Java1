package Shapes;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Square extends Shape{

    public Square() {
        super();
    }
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());
        int width = Math.abs(getCurrentX() - getXStarting());
        int x = Math.min(getXStarting(), getCurrentX());
        int y = Math.min(getYStarting(), getCurrentY());
        if (isFilled()) {
            g.fillRect(x, y, width, width);
        } else {
            if(!isDotted())
                g.drawRect(x, y, width, width);
            else{
                Graphics2D g2 = (Graphics2D) g; 
                g2.setColor(getColor());
                float[] dashPattern = {5f, 5f}; 
                Stroke oldStroke = g2.getStroke(); 
                g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
                g2.drawRect(x, y, width, width);
                g2.setStroke(oldStroke);
            }
        }
    }
}
