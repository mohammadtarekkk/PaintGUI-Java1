package Shapes;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.RenderingHints;

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
    Graphics2D g2d = (Graphics2D) g;

    // This is for Smoothing
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    g2d.setColor(getColor());
    
    if (isDotted()) {
        float[] dash = { 2f, 0f, 2f };
        g2d.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10f, dash, 0f));
    } else {
        g2d.setStroke(new BasicStroke(getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }
    
    g2d.drawLine(getXStarting(), getYStarting(), getCurrentX(), getCurrentY());
}
}
