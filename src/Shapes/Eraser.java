package Shapes;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.BasicStroke;


public class Eraser extends Shape {

    public Eraser() {
        super();
    }
    public Eraser(int xStarting, int yStarting,int currentX, int currentY , String style, Color color, boolean isFilled , boolean isDotted , int strokeWidth) {
        super(xStarting,yStarting,currentX,currentY,style,color, isFilled , isDotted, strokeWidth);
    }

    @Override
    public ArrayList<Shape> makeDragAction(ArrayList<Shape> shapes, int currentX, int currentY, Shape currShape) {

        Eraser segment = new Eraser(
            currShape.getXStarting(), currShape.getYStarting(), 
            currentX, currentY, 
            currShape.style, currShape.color, currShape.isFilled, currShape.isDotted, currShape.getStrokeWidth()
        );
        
        shapes.add(segment);
        currShape.setXStarting(currentX);
        currShape.setYStarting(currentY);
        currShape.setCurrentX(currentX);
        currShape.setCurrentY(currentY);
        return shapes;
    }

    @Override
    public void drawShape(Graphics g) {
        g.setColor(Color.WHITE);
        ((Graphics2D) g).setStroke(new BasicStroke(getStrokeWidth()));
        g.drawLine(getXStarting(), getYStarting(), getCurrentX(), getCurrentY());
    }
}
