package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Shape implements Cloneable {
    protected int xStarting;
    protected int yStarting;
    protected int currentX;
    protected int currentY;

    protected String style;
    protected Color color;
    protected boolean isFilled;
    protected boolean isDotted;
    protected int strokeWidth;


    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); 
        }
    }
    public Shape() {
        super();
    }
    public Shape(int xStarting, int yStarting,int currentX, int currentY , String style, Color color, boolean isFilled , boolean isDotted, int strokeWidth) {
        this.xStarting = xStarting;
        this.yStarting = yStarting;
        this.currentX = currentX;
        this.currentY = currentY;
        this.style = style;
        this.color = color;
        this.isFilled = isFilled;
        this.isDotted = isDotted;
        this.strokeWidth = strokeWidth;
    }
    
    public int getXStarting() {
        return xStarting;
    }

    public int getYStarting() {
        return yStarting;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public String getStyle() {
        return style;
    }

    public Color getColor() {
        return color;
    }
    public int getStrokeWidth() {
        return strokeWidth;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public boolean isDotted(){
        return isDotted;
    }
    public void setXStarting(int xStarting) {
        this.xStarting = xStarting;
    }

    public void setYStarting(int yStarting) {
        this.yStarting = yStarting;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public void setDotted(boolean isDotted){
        this.isDotted = isDotted;
    }
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public ArrayList<Shape> makeDragAction(ArrayList<Shape> shapes, int currentX, int currentY, Shape currShape) {
        
        currShape.setCurrentX(currentX);
        currShape.setCurrentY(currentY);

        shapes.set(shapes.size() - 1, currShape);

        return shapes;
    }


    public abstract void drawShape(Graphics g);
}
