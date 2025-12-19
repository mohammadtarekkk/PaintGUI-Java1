package Shapes;
import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape{

    public Square() {
        super();
    }
    public Square(int xStarting, int yStarting,int currentX, int currentY , String style, Color color, boolean isFilled ) {
        super(xStarting,yStarting,currentX,currentY,style,color, isFilled);
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
            g.drawRect(x, y, width, width);
        }
    }
}
