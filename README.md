# 🎨 PaintGUI - Java Paint Application

A feature-rich desktop paint application built with Java Swing, implementing various design patterns for clean and maintainable code.

## 📸 Features

| Feature | Description |
|---------|-------------|
| **Drawing Tools** | Rectangle, Square, Oval, Line, Pencil, Eraser |
| **Styling Options** | Fill, Dotted lines, Adjustable stroke width |
| **Color Picker** | Full color selection via JColorChooser |
| **Undo/Redo** | Complete undo/redo functionality |
| **Save/Load** | Save and load drawings as text files |
| **Export** | Export canvas as PNG image |
| **Add Image** | Insert background images |

---

## 🏗️ Project Structure

```
src/
├── App.java                    # Application entry point
├── ProgramManger/
│   └── PaintManager.java       # Main controller (Facade Pattern)
├── Shapes/
│   ├── Shape.java              # Abstract base class
│   ├── Rectangle.java
│   ├── Square.java
│   ├── Oval.java
│   ├── Line.java
│   ├── Pencil.java
│   └── Eraser.java
├── ShapeCreater/
│   └── ShapeFactory.java       # Factory Pattern
├── ShapeChanger/
│   └── ShapeContext.java       # Strategy Pattern
├── UIFactory/
│   └── ButtonFactory.java      # UI Factory for buttons
├── FilesManger/
│   └── FileManger.java         # Save/Load functionality
├── PanelExporter/
│   └── ImageExporter.java      # PNG export
└── resources/
    └── icons/                  # UI icons (30x30 PNG)
```

---

## 🎯 Design Patterns Used

### 1. Factory Pattern (`ShapeFactory`)
Creates shape objects based on type string:
```java
Shape shape = ShapeFactory.createShape("Shapes.Rectangle");
```

### 2. Strategy Pattern (`ShapeContext`)
Holds reference to current drawing tool and delegates drawing:
```java
shapeContext.setShape(new Rectangle());
shapeContext.getShape().drawShape(g);
```

### 3. Prototype Pattern (`Shape.clone()`)
Clones shapes for reuse after drawing:
```java
Shape newShape = shape.clone();
```

### 4. Facade Pattern (`PaintManager`)
Simplifies complex subsystems (UI, events, drawing, storage).

### 5. Factory Pattern for UI (`ButtonFactory`)
Creates styled buttons with icons:
```java
JButton btn = ButtonFactory.createIconButton("/icons/rect.png", "Rectangle", 30);
```

---

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher

### Steps
1. Clone the repository
2. Navigate to the `src` directory
3. Compile: `javac App.java`
4. Run: `java App`

---

## 🎨 Shape Properties

Each shape supports:
- **Color**: Any RGB color
- **Fill**: Solid or outline only
- **Dotted**: Dashed line style
- **Stroke Width**: 1-20 pixels

---

## 📁 File Format

Drawings are saved as text files with format:
```
ShapeClass,xStart,yStart,xEnd,yEnd,style,colorRGB,isFilled,isDotted
```

---

## 🛠️ Key Classes

### `Shape` (Abstract)
Base class with common properties:
- Coordinates (xStarting, yStarting, currentX, currentY)
- Color, isFilled, isDotted, strokeWidth
- Abstract `drawShape(Graphics g)` method
- `makeDragAction()` for real-time drawing

### `PaintManager`
Main application controller:
- Handles all UI components
- Manages mouse events for drawing
- Stores shapes in ArrayList
- Implements undo/redo with null markers

### `ButtonFactory`
Creates styled UI buttons:
- `createIconButton()` - Regular buttons with icons
- `createToggleButton()` - Toggle buttons with on/off icons

---

## 📝 License

This project was created as part of ITI Java 1 course.
