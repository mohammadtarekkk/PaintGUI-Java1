package Shapes;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Line extends Shape {

    public Line() {
        super();
    }
    
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());

        if(!isDotted())
            g.drawLine(getXStarting(), getYStarting(), getCurrentX(), getCurrentY());
        else{
            Graphics2D g2 = (Graphics2D) g; 
            g2.setColor(getColor());
            float[] dashPattern = {5f, 5f}; 
            Stroke oldStroke = g2.getStroke(); 
            g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
            g.drawLine(getXStarting(), getYStarting(), getCurrentX(), getCurrentY());
            g2.setStroke(oldStroke);
        }
    }
}
