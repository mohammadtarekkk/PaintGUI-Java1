package ProgramManger;
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


    private JButton btnRectangle;
    private JButton btnSquare;
    private JButton btnOval;
    private JButton btnLine;
    private JButton btnPencil;
    private JButton btnEraser;
    private JButton btnColorPicker;

    private JCheckBox fillOption;
    private Color currentColor;



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

        fillOption = new JCheckBox("Fill");
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
    }

    private void initializeEvents() {

        btnRectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("rectangle")
                );
            }
        });

        btnSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("square")
                );
            }
        });

        btnOval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("oval")
                );
            }
        });

        btnLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("line")
                );
            }
        });

        btnPencil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("pencil")
                );
            }
        });

        btnEraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapeContext.setShape(
                    ShapeFactory.createShape("eraser")
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

                Shape shape = shapeContext.getShape();
                shape.setCurrentX(e.getX());
                shape.setCurrentY(e.getY());

                repaint();
            }
        });

    }
}
