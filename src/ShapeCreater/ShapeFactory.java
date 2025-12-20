package ShapeCreater;

import Shapes.*;
public class ShapeFactory {

    public static Shape createShape(String type) {

        switch (type) {
            case "Shapes.Rectangle":
                return new Rectangle();
            case "Shapes.Square":
                return new Square();
            case "Shapes.Oval":
                return new Oval();
            case "Shapes.Line":
                return new Line();
            case "Shapes.Pencil":
                return new Pencil();
            case "Shapes.Eraser":
                return new Eraser();
            default:
                return null;
        }
    }
}