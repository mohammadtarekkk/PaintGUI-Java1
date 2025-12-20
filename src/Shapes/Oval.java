package Shapes;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Oval extends Shape {

    public Oval() {
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
            g.fillOval(x, y, width, height);
        } else {
            ((Graphics2D) g).setStroke(new BasicStroke(getStrokeWidth()));

            if(!isDotted())
                g.drawOval(x, y, width, height);

            else{
                Graphics2D g2 = (Graphics2D) g; 
                g2.setColor(getColor());
                float[] dashPattern = {getStrokeWidth(), getStrokeWidth()}; 
                Stroke oldStroke = g2.getStroke(); 
                g2.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
                g.drawOval(x, y, width, height);
                g2.setStroke(oldStroke);
            }

        }
    }
}
