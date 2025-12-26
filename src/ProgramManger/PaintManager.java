package ProgramManger;
import UIFactory.ButtonFactory;
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
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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

    private JSlider strokeWidthSlider;
    private JToggleButton fillOption;
    private JToggleButton dottedOption;
    private BufferedImage backgroundImage;
    private JButton btnAddImage;


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
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 200, 200, 200, 200, null); 
        }
        for (Shape s : shapes) {
            if(s != null)
                s.drawShape(g);
        }

    }

    private void initializeVariables() {
        shapeFactory = new ShapeFactory();
        shapeContext = new ShapeContext(null);
        shapes = new ArrayList<>();

        btnRectangle = ButtonFactory.createIconButton("/resources/icons/rectangle.png", "Rectangle", 30);
        btnSquare = ButtonFactory.createIconButton("/resources/icons/square.png", "Square", 30);
        btnOval = ButtonFactory.createIconButton("/resources/icons/oval.png", "Oval", 30);
        btnLine = ButtonFactory.createIconButton("/resources/icons/line.png", "Line", 30);
        btnPencil = ButtonFactory.createIconButton("/resources/icons/pencil.png", "Pencil", 30);
        btnEraser = ButtonFactory.createIconButton("/resources/icons/eraser.png", "Eraser", 30);
        btnColorPicker = ButtonFactory.createIconButton("/resources/icons/color.png", "Color", 30);
        btnClear = ButtonFactory.createIconButton("/resources/icons/clear.png", "Clear", 30);
        btnUndo = ButtonFactory.createIconButton("/resources/icons/undo.png", "Undo", 30);
        btnRedo = ButtonFactory.createIconButton("/resources/icons/redo.png", "Redo", 30);
        imageExporter = ButtonFactory.createIconButton("/resources/icons/export.png", "Export to image", 30);
        btnSave = ButtonFactory.createIconButton("/resources/icons/save.png", "Save", 30);
        btnLoad = ButtonFactory.createIconButton("/resources/icons/load.png", "Load Shape", 30);
        fillOption = ButtonFactory.createToggleButton("/resources/icons/fill_off.png", "/resources/icons/fill.png", "Fill", 30);
        dottedOption = ButtonFactory.createToggleButton("/resources/icons/dotted_off.png", "/resources/icons/dotted.png", "Dotted", 30);
        
        btnAddImage = ButtonFactory.createIconButton("/resources/icons/add_image.png", "Add Image", 30);
        currentColor = Color.BLACK;
        redoStack = new ArrayList<>();
        

        strokeWidthSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, 1);

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
        this.add(strokeWidthSlider);
        this.add(btnAddImage);
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
                    int i = shapes.size()-1;
                    if(i > 0)
                        redoStack.add(null);
                    while (i >= 0) {
                        shapeContext.setShape(null);
                        if(shapes.get(i) == null ){
                            shapes.remove(i);
                            break;
                        }
                        redoStack.add(shapes.remove(i));
                        i = shapes.size()-1;

                    }

                    repaint();
                }
            }
        });
        btnRedo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!redoStack.isEmpty()) {

                    int i = redoStack.size()-1;
                    if(i> 0)
                        shapes.add(null);
                    while (i > 0) {
                        shapeContext.setShape(null);
                        if(redoStack.get(i) == null ){
                            
                            redoStack.remove(i);
                            break;
                        }
                        shapes.add(redoStack.remove(i));
                        i = redoStack.size()-1;

                    }

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

        btnAddImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(PaintManager.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        backgroundImage = ImageIO.read(chooser.getSelectedFile());
                        repaint();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
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
                shape.setStrokeWidth(strokeWidthSlider.getValue());
                shapes.add(null);
                shapes.add(shape);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (shapeContext.getShape() == null)
                    return;

                Shape shape = shapeContext.getShape();

                shape.setCurrentX(e.getX());
                shape.setCurrentY(e.getY());


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