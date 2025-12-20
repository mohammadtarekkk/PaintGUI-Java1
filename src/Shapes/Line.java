package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Line extends Shape {

    public Line() {
        super();
    }
    public Line(int xStarting, int yStarting,int currentX, int currentY , String style, Color color, boolean isFilled , boolean isDotted , int strokeWidth) {
        super(xStarting,yStarting,currentX,currentY,style,color, isFilled , isDotted, strokeWidth);
    }   
    
    @Override
    public void drawShape(Graphics g) {
        g.setColor(getColor());
        ((Graphics2D) g).setStroke(new BasicStroke(getStrokeWidth()));

        if(!isDotted())
            g.drawLine(getXStarting(), getYStarting(), getCurrentX(), getCurrentY());
        else{
            Graphics2D g2 = (Graphics2D) g; 
            g2.setColor(getColor());
            float[] dashPattern = {5f, 5f}; 
            Stroke oldStroke = g2.getStroke(); 
            g2.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dashPattern, 0f));
            g.drawLine(getXStarting(), getYStarting(), getCurrentX(), getCurrentY());
            g2.setStroke(oldStroke);
        }
    }
}
