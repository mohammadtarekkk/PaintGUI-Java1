package Shapes;
import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Shape {

    public Oval() {
        super();
    }
    public Oval(int xStarting, int yStarting,int currentX, int currentY , String style, Color color, boolean isFilled ) {
        super(xStarting,yStarting,currentX,currentY,style,color, isFilled);
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
            g.drawOval(x, y, width, height);
        }
    }
}
