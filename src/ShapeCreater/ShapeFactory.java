package ShapeCreater;

import Shapes.*;
public class ShapeFactory {

    public static Shape createShape(String type) {

        switch (type.toLowerCase()) {
            case "rectangle":
                return new Rectangle();
            case "square":
                return new Square();
            case "oval":
                return new Oval();
            case "line":
                return new Line();
            case "pencil":
                return new Pencil();
            case "eraser":
                return new Eraser();
            default:
                return null;
        }
    }
}