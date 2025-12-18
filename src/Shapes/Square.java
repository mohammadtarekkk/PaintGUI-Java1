package Shapes;
import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape{

    public Square() {
        super();
    }
    public Square(int xStarting, int yStarting,int currentX, int currentY , String style, Color color ) {
        super(xStarting,yStarting,currentX,currentY,style,color);
    }

    
    @Override
    public void drawShape(Graphics g) {

    }
}
