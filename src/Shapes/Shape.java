package Shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Cloneable {
    protected int xStarting;
    protected int yStarting;
    protected int currentX;
    protected int currentY;

    protected String style;
    protected Color color;

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); 
        }
    }
    public Shape(int xStarting, int yStarting,int currentX, int currentY , String style, Color color ) {
        this.xStarting = xStarting;
        this.yStarting = yStarting;
        this.currentX = currentX;
        this.currentY = currentY;
        this.style = style;
        this.color = color;
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


    public abstract void drawShape(Graphics g);
}
