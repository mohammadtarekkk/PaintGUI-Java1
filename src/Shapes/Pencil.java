package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Pencil extends Shape {

    public Pencil() {
        super();
    }
    public Pencil(int xStarting, int yStarting,int currentX, int currentY , String style, Color color, boolean isFilled , boolean isDotted , int strokeWidth) {
        super(xStarting,yStarting,currentX,currentY,style,color, isFilled , isDotted, strokeWidth);
    }

    @Override
    public ArrayList<Shape> makeDragAction(ArrayList<Shape> shapes, int currentX, int currentY, Shape currShape) {

        Pencil segment = new Pencil(
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
        g.setColor(getColor());
        ((Graphics2D) g).setStroke(new BasicStroke(getStrokeWidth()));
        if (isDotted()) {
            float[] dash = { 2f, 0f, 2f };
            ((Graphics2D) g).setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10f, dash, 0f));
        }
        g.drawLine(getXStarting(), getYStarting(), getCurrentX(), getCurrentY());
    }
}
