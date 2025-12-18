package ShapeChanger;
import Shapes.*;
import java.awt.Graphics;

public class ShapeContext {

    private Shape shape;

    public ShapeContext(Shape shape) {
        this.shape = shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape(){
        return shape;
    }
    public void drawShape(Graphics g) {
        if (shape != null) {
            shape.drawShape(g);
        }
    }
}