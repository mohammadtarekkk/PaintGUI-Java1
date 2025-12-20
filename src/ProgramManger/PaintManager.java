package ProgramManger;
import FilesManger.FileManger;
import PanelExporter.ImageExporter;
import ShapeChanger.ShapeContext;
import ShapeCreater.ShapeFactory;
import Shapes.Shape;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.*;

public class PaintManager extends JPanel {

    private ShapeFactory shapeFactory;
    private ShapeContext shapeContext;
    private ArrayList<Shape> shapes;
    private ArrayList<Shape> redoStack;

    private JButton btnRectangle;
    private JButton btnSquare;
    private JButton btnOval;
    private JButton btnLine;
    private JButton btnPencil;
    private JButton btnEraser;
    private JButton btnColorPicker;
    private JButton btnClear;
    private JButton btnUndo;
    private JButton btnRedo;
    private JButton imageExporter;


    private JCheckBox fillOption;
    private JCheckBox dottedOption;

    private Color currentColor;

    private JButton btnSave;
    private JButton btnLoad;




    public PaintManager() {
        initializeVariables();
        initializeEvents();
    }

    public void runApp() {
        JFrame frame = new JFrame("Paint App");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s : shapes) {
            s.drawShape(g);
        }
        if (shapeContext.getShape() != null) {
            shapeContext.getShape().drawShape(g);
        }
    }

    private void initializeVariables() {
        shapeFactory = new ShapeFactory();
        shapeContext = new ShapeContext(null);
        shapes = new ArrayList<>();

        btnRectangle = new JButton("Rectangle");
        btnSquare = new JButton("Square");
        btnOval = new JButton("Oval");
        btnLine = new JButton("Line");
        btnPencil = new JButton("Pencil");
        btnEraser = new JButton("Eraser");
        btnColorPicker = new JButton("Color");
        redoStack = new ArrayList<>();
        fillOption = new JCheckBox("Fill");
        dottedOption = new JCheckBox("Dotted");

        btnClear = new JButton("Clear");
        btnUndo = new JButton("Undo");
        btnRedo = new JButton("Redo");
        imageExporter = new JButton("Export to image");


        btnSave = new JButton("Save");
        btnLoad = new JButton("Load Shape");
        currentColor = Color.BLACK;

        setBackground(Color.WHITE);

        this.add(btnRectangle);
        this.add(btnSquare);
        this.add(btnOval);
        this.add(btnLine);
        this.add(btnPencil);
        this.add(btnEraser);
        this.add(btnColorPicker);
        this.add(fillOption);
        this.add(dottedOption);
        this.add(btnClear);
        this.add(btnUndo);
        this.add(btnRedo);
        this.add(imageExporter);
        this.add(btnSave);
        this.add(btnLoad);
    }

    private void initializeEvents() {

        btnRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("Shapes.Rectangle")
                );
            }
        });

        btnSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("Shapes.Square")
                );
            }
        });

        btnOval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("Shapes.Oval")
                );
            }
        });

        btnLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("Shapes.Line")
                );
            }
        });

        btnPencil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("Shapes.Pencil")
                );
            }
        });

        btnEraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("Shapes.Eraser")
                );
            }
        });

        btnColorPicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(
                        PaintManager.this,
                        "Choose Color",
                        currentColor
                );
                if (selectedColor != null) {
                    currentColor = selectedColor;
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                shapeContext.setShape(null);
                repaint();
                redoStack.clear();
            }
        });
        btnUndo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!shapes.isEmpty()) {
                    redoStack.add(shapes.remove(shapes.size() - 1));
                    shapeContext.setShape(null);
                    repaint();
                }
            }
        });
        btnRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!redoStack.isEmpty()) {
                    shapes.add(redoStack.remove(redoStack.size() - 1));
                    shapeContext.setShape(null);
                    repaint();
                }
            }
        });

        btnSave.addActionListener( new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e){
                FileManger.savePanel(shapes , PaintManager.this);
            }
            
        });

        btnLoad.addActionListener( new ActionListener() {
            @Override
            public  void actionPerformed(ActionEvent e){
                shapes = FileManger.loadShapes(PaintManager.this);
                repaint();
            }
            
        });

        imageExporter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageExporter.savePanelAsImage(PaintManager.this);
            }
        });

        //Mouse events
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                
                if (shapeContext.getShape() == null)
                    return;

                Shape shape = shapeContext.getShape();

                shape.setXStarting(e.getX());
                shape.setYStarting(e.getY());
                shape.setCurrentX(e.getX());
                shape.setCurrentY(e.getY());
                shape.setColor(currentColor);
                shape.setFilled(fillOption.isSelected());
                shape.setDotted(dottedOption.isSelected());

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (shapeContext.getShape() == null)
                    return;

                Shape shape = shapeContext.getShape();

                shape.setCurrentX(e.getX());
                shape.setCurrentY(e.getY());

                shapes.add(shape);
                redoStack.clear();
                Shape newShape = shape.clone();
                shapeContext.setShape(newShape);

                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {

                if (shapeContext.getShape() == null)
                    return;

                shapes = shapeContext.getShape().makeDragAction(shapes, e.getX(), e.getY() , shapeContext.getShape());
                repaint();
            }
        });

    }
}