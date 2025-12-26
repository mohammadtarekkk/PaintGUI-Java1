package Shapes;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Rectangle extends Shape {

    public Rectangle() {
        super();
    }
    
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());
        int width = Math.abs(getCurrentX() - getXStarting());
        int height = Math.abs(getCurrentY() - getYStarting());
        int x = Math.min(getXStarting(), getCurrentX());
        int y = Math.min(getYStarting(), getCurrentY());
        if (isFilled()) {
            g.fillRect(x, y, width, height);
        } else {
            ((Graphics2D) g).setStroke(new BasicStroke(getStrokeWidth()));
            if(!isDotted())
                g.drawRect(x, y, width, height);
            else{
                Graphics2D g2 = (Graphics2D) g; 
                g2.setColor(getColor());
                float[] dashPattern = {getStrokeWidth(), getStrokeWidth()}; 
                Stroke oldStroke = g2.getStroke(); 
                g2.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
                g2.drawRect(x, y, width, height);
                g2.setStroke(oldStroke);

            }
        }
    }
}
