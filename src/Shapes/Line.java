package Shapes;
import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

    public Line() {
        super();
    }
    public Line(int xStarting, int yStarting,int currentX, int currentY , String style, Color color ) {
        super(xStarting,yStarting,currentX,currentY,style,color);
    }

    
    @Override
    public void drawShape(Graphics g) {
    }
}
