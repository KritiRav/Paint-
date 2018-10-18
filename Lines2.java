import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;
import java.io.*;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import static javax.swing.ScrollPaneConstants.*;

  
public class Lines2 extends JPanel
{
    // Instance variables
    DrawingPanel imagepanel;
    JPanel content;
    JScrollPane scroller; 
    static String whichShape = new String("line");
    JPanel buttonsPanel;
    LineWrangler wrangler;
    public String changeShape (String shape) {
        whichShape = shape;
        return whichShape;
    }
    public LineWrangler getLineWrangler () {
        return wrangler;
    }   
     // constructor
    Lines2()
    {
        //... Create contant pane, layout components
        super(new BorderLayout());
        buttonsPanel = new JPanel();
        whichShape = null;
        JButton recButton = new JButton("Rect");
        JButton lineButton = new JButton("Line");
        JButton thickButton = new JButton("Thick");
        JButton colorBorderButton = new JButton("Color Border");
        JButton eraseButton = new JButton("Erase");
        content = new JPanel();
        content.setLayout(new BorderLayout());
        // Create JPanel canvas to hold the picture
        imagepanel = new DrawingPanel();
        wrangler = new LineWrangler(imagepanel);
        //wrangler.setShape(new String("line"));
        imagepanel.addMouseListener(wrangler);
        imagepanel.addMouseMotionListener(wrangler);
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wrangler.changeErase(!wrangler.getErase());
            }
        });
        thickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wrangler.setThickness(Double.valueOf(4));
                //wrangler.setShape(whichShape);
            }
        });
        colorBorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                wrangler.setBorderCol(Color.pink);
                wrangler.setFillCol(Color.GRAY);
            }
        });
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whichShape = changeShape(new String("line"));
                wrangler.setShape(whichShape);
                System.out.println(whichShape);
            }
        });
        recButton.addActionListener(new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent e) {
                whichShape = changeShape(new String("rect"));
                wrangler.setShape(whichShape);
            }
        }); buttonsPanel.add(recButton); buttonsPanel.add(eraseButton);
        buttonsPanel.add(lineButton); buttonsPanel.add(colorBorderButton);
        buttonsPanel.add(thickButton);
        
  
        // Create JScrollPane to hold the canvas containing the picture
        scroller = new JScrollPane(imagepanel);
       // JPanel nonScrol = new JPanel(imagepanel); 
        scroller.setPreferredSize(new Dimension(500,300));
        scroller.setViewportBorder(BorderFactory.createLineBorder(Color.black));
        // Add scroller pane to Panel
        content.add(scroller,"Center");
    }
    public void setViewFull() {
        scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
    }
    public void setViewFit() {
        scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_NEVER);
    }

    public class Line {
        public Line2D l; 
        public Color colorBorder;
        public double thickness; 
        public Boolean isSelected;

        Line(double x, double y, double x2, double y2, Color border, double thick, Boolean isSelected) {
            this.l = new Line2D.Double(new Point2D.Double(x,y),new Point2D.Double(x2,y2));
            colorBorder=border;
            thickness=thick;
            this.isSelected=isSelected;
            System.out.println("call line contructor");
        }

        public void setLine(double x, double y, double x2, double y2, Color border, double thick, Boolean isSelected) {
            this.l.setLine(new Point2D.Double(x,y),new Point2D.Double(x2,y2));
            colorBorder=border;
            thickness = thick;
            this.isSelected = isSelected;
        }
    }
   
    public class Rect {
        Rectangle2D.Double r;
        Color colorBorder;
        double thickness; 
        Color colorFill;
        Boolean isSelected;
    
        Rect(double x, double y, double w, double h, Color border, double thick, Color fill, Boolean isSelected) {
            this.r = new Rectangle2D.Double(x,y,w,h);
            colorBorder=border;
            thickness=thick;
            colorFill=fill;
            this.isSelected=isSelected;
        }
        public void setRect(double x, double y, double w, double h, Color border, double thick, Color fill, Boolean isSelected) {
            r.setRect(x, y, w, h);
            colorBorder=border;
            thickness=thick;
            colorFill = fill;
            this.isSelected = isSelected;
        }
    }
    public class Circle {
        Ellipse2D.Double c;
        Color colorBorder;
        double thickness; 
        Color colorFill;
        Boolean isSelected;
    
        Circle(double x, double y, double w, double h, Color border, double thick, Color fill, Boolean isSelected) {
            this.c = new Ellipse2D.Double(x,y,w,h); 
            colorBorder=border;
            thickness=thick;
            colorFill=fill;
            this.isSelected=isSelected;
        }
        public void setCircle(double x, double y, double w, double h, Color border, double thick, Color fill, Boolean isSelected) {
            this.c = new Ellipse2D.Double(x,y,w,h);
            colorBorder=border;
            thickness=thick;
            colorFill = fill;
            this.isSelected = isSelected;
        }
    }
    class DrawingPanel extends JPanel
    {
       // List lines;                      // j2se 1.4-
        // optional generic declaration for j2se 1.5+
        List<Line> lines = new ArrayList<Line>();
        List<Rect> rects = new ArrayList<Rect>();
        List <Circle> circles = new ArrayList<Circle>();
        
        public DrawingPanel()
        {
            //ines = new ArrayList();            // j2se 1.4-
            lines = new ArrayList<Line>();  // j2se 1.5+
            rects = new ArrayList<Rect>();
            circles = new ArrayList<Circle>();
            setBackground(Color.white);
            setPreferredSize(new Dimension(750,950));
        } 
  
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            
            for(int j = 0; j < lines.size(); j++) {
                g2.setColor(lines.get(j).colorBorder);
                if (lines.get(j).isSelected) {
                    g2.setStroke(new BasicStroke((float)7));
                } else {
                g2.setStroke(new BasicStroke((float)lines.get(j).thickness));
                } 
                g2.draw(lines.get(j).l);
            } for(int j = 0; j < rects.size(); j++) {
                g2.setColor(rects.get(j).colorBorder);
                if (rects.get(j).isSelected) {
                    g2.setStroke(new BasicStroke((float)7));
                } else {
                g2.setStroke(new BasicStroke((float)rects.get(j).thickness));
                } g2.draw((Rectangle2D)rects.get(j).r);
                g2.setColor(rects.get(j).colorFill);
                g2.fill(rects.get(j).r);
            } for(int j = 0; j < circles.size(); j++) {
                g2.setColor(circles.get(j).colorBorder);
                if (circles.get(j).isSelected) {
                    g2.setStroke(new BasicStroke((float)7));
                } else {
                g2.setStroke(new BasicStroke((float)circles.get(j).thickness));
                } g2.draw((Ellipse2D.Double)circles.get(j).c);
                g2.setColor(circles.get(j).colorFill);
                g2.fill(circles.get(j).c);
            }
        }
  
        public void addLine(Point start, Point end, Color borderCol, double thick, Boolean isSelected)
        {
            System.out.println("call addLine");
            lines.add(new Line(start.getX(), start.getY(), end.getX(), end.getY(), borderCol, thick, isSelected));
            repaint();
        }

        public void addRect(Point start, Point end, Color borderCol, Color borderFill, double thick, Boolean isSelected) {
            double width =0;
            double length = 0;
            double x=0;
            double y=0;
            if (start.getX()>end.getX()) {
                x = end.getX();
                width = start.getX()-end.getX();
                if (start.getY()>end.getY()) {
                    y=end.getY();
                    length = start.getY()-end.getY();
                } else {
                    y=start.getY();
                    length = end.getY()-start.getY();
                }
            } else if (start.getY()>end.getY()) {
                width = end.getX()-start.getX();
                length = start.getY()-end.getY();
                x=start.getX();
                y=end.getY();
            } else {
                width = end.getX()-start.getX();
                length = end.getY()-start.getY();
                x=start.getX();
                y=start.getY();
            }
            rects.add(new Rect(x, y, width, length, borderCol, thick, borderFill, isSelected));
            repaint();
        }
        public void addCircle(Point start, Point end, Color borderCol, double thick,Color borderFill, Boolean isSelected) {
            System.out.println("addCircle called");
            double minX = Math.min(start.getX(),end.getX());
            double minY = Math.min(start.getY(), end.getY());
            double maxY = Math.max(start.getY(),end.getY());
            double maxX = Math.max(start.getX(),end.getX());
            double width = maxX-minX;
            double length = maxY-minY;
            circles.add(new Circle(minX, minY, width, length, borderCol, thick, borderFill, isSelected));
            repaint();
        }
        public void moveCircle (Circle cir, Point p, Point end) {
            double distX = end.getX() - p.getX();
            double distY = end.getY() - p.getY();
            cir.setCircle(cir.c.getX()+distX, cir.c.getY()+distY, cir.c.getWidth(), cir.c.getHeight(), cir.colorBorder, cir.thickness, cir.colorFill, cir.isSelected);
            repaint(); 
        }
        public void moveRect(Rect rec, Point p, Point end) {
            double distX = end.getX() - p.getX();
            double distY = end.getY() - p.getY();
            rec.setRect(rec.r.getX()+distX, rec.r.getY()+distY, rec.r.getWidth(), rec.r.getHeight(), rec.colorBorder, rec.thickness, rec.colorFill, rec.isSelected);
            repaint();
        }

        public void moveLine(Line line, Point p, Point end)
        {
            double distX = end.getX() - p.getX();
            double distY = end.getY() - p.getY();
            line.setLine(line.l.getX1()+distX, line.l.getY1()+distY, line.l.getX2()+distX, line.l.getY2()+distY, line.colorBorder,line.thickness, line.isSelected);
            repaint();
        }
        public void removeCircle(Circle c) {
            circles.remove(c);
            repaint();
        }
        public void removeRect(Rect r) {
            rects.remove(r);
            repaint();
        }
        public void removeLine(Line line)
        {
            lines.remove(line);
            repaint();
        }
        public void eraseAll () {
            rects.clear();
            lines.clear();
            circles.clear();
            repaint();
        }
    }
}
  
class LineWrangler extends MouseInputAdapter
{

    Lines2.DrawingPanel drawingPanel;
    Point start;
    Lines2.Line selectedLine;
    Lines2.Rect selectedRectangle;
    Lines2.Circle selectedCircle;
    boolean dragging;
    final int MIN_DIST = 3;
    final int MIN_DRAG_DIST = 5;
    Point p;
    Point end;
    Boolean haveSelection=false;
    List<Lines2.Line> list;
    List<Lines2.Rect> rectList;  
    List<Lines2.Circle> circleList;
    static String shape; 
    Color colorBorder=Color.black;
    double thickness = 2; 
    Color colorFill = Color.white;
    Boolean isSelected = false;
    Boolean erase = false;
    public Boolean getErase() { return this.erase; }
    public void changeErase(Boolean e) {
        erase = e;
    }
    public String getShape() {
        String s = shape;
        return s;
    }
    public void setShape(String inputShape) {
        shape = inputShape; 
        System.out.println(shape);
    }
    public void setBorderCol(Color b) {
        colorBorder=b;
    }
    public void setThickness(double t) {
        thickness = t;
    }
    public void setFillCol(Color b) {
        colorFill=b;
    }
    public void setThickness(Double b) {
        thickness=b;
    }
    public void setisSelected (Boolean s) {
        isSelected=s;
    }
    public LineWrangler(Lines2.DrawingPanel dp)
    {
        drawingPanel = dp;
        dragging = false;
    }
  
    public void mousePressed(MouseEvent e)
    {
        p = e.getPoint();
        haveSelection = false;
        list = drawingPanel.lines;   // j2se 1.5+
        //List list = drawingPanel.lines;             // j2se 1.4-
        rectList = drawingPanel.rects;
        circleList = drawingPanel.circles;
        for (int j = 0; j < list.size(); j++) {
            if (erase && list.get(j).l.ptLineDist(p) < MIN_DIST) {
                drawingPanel.removeLine(list.get(j));
            } else if(!erase) {
                if (list.get(j).l.ptLineDist(p) < MIN_DIST) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).isSelected = false;
                }
                list.get(j).isSelected=true;
                }
            }
        }
        for (int j = 0; j < rectList.size(); j++) {
            if (erase && p.getX()>rectList.get(j).r.getX() && p.getX()<rectList.get(j).r.getX()+rectList.get(j).r.getWidth() && p.getY()>rectList.get(j).r.getY() && p.getY()<=rectList.get(j).r.getY()+rectList.get(j).r.getHeight()) {
                drawingPanel.removeRect(rectList.get(j));
            } else if (!erase) {
                if (rectList.get(j).r.contains(p.getX(),p.getY())) {
                for (int i = 0; i < rectList.size(); i++) {
                    rectList.get(i).isSelected = false;
                } 
                rectList.get(j).isSelected=true;
                }
            }
        }for (int j = 0; j < circleList.size(); j++) {
            if (erase && circleList.get(j).c.contains(p.getX(),p.getY())) {
                drawingPanel.removeCircle(circleList.get(j));
            } else if (!erase) {
                // check if selected, then unselect everything else and select this shape
                //if it falls within the bounds 
            }
        }
    }

    
    public void mouseReleased(MouseEvent e)
    {
        end = e.getPoint();
        if(dragging) {
            dragging = false;
            for(int j = 0; j < list.size(); j++)
            {
                Lines2.Line line = list.get(j);
                if(line.l.ptLineDist(p) < MIN_DIST)
                {
                    // we have selected a line
                    haveSelection = true;
                    // wait and see if the user drags selectedLine
                    start = p;
                    selectedLine = line;
                     if(!erase) {
                    drawingPanel.moveLine(line, p, end);
                    } 
                    // we're done here so let's move on
                    break;
                }
            }
            for(int j = 0; j < rectList.size(); j++) {
                Lines2.Rect rec = rectList.get(j);
                if(p.getX()>rec.r.getX() && p.getX()<rec.r.getX()+rec.r.getWidth() && p.getY()>rec.r.getY() && p.getY()<=rec.r.getY()+rec.r.getHeight()) {
                    
                    haveSelection = true; 
                    start=p;
                    selectedRectangle = rec;
                    if(!erase) {
                    drawingPanel.moveRect(rec, p, end);
                    }
                    break; 
                    }   
                }
                for(int j = 0; j < circleList.size(); j++) {
                    Lines2.Circle cir = circleList.get(j);
                    if(cir.c.contains(p.getX(),p.getY())) {
                        haveSelection = true; 
                        start=p;
                        selectedCircle = cir;
                        if(!erase) {
                        drawingPanel.moveCircle(cir, p, end);
                        }
                        break; 
                        }   
                    }
            // if p is not near a line, add a line at p
            if(!haveSelection) {
                System.out.println("value of shape is:"+shape+"that's it");
                if (getShape().equals("rect")&&!erase) {
                    drawingPanel.addRect(p, end,colorBorder,colorFill,thickness,isSelected);
                } else if (shape==null) {
                } else if (getShape().equals("line")&&!erase) {
                    drawingPanel.addLine(p,end,colorBorder,thickness,isSelected);
                } else if (getShape().equals("circle")&&!erase) {
                    drawingPanel.addCircle(p,end,colorBorder,thickness, colorFill, isSelected);
                }
            }
        }
    }
  
    public void mouseDragged(MouseEvent e)
    {
        //Point p = e.getPoint();
       // if(dragging)
       //     drawingPanel.moveLine(selectedLine, p);
            // we have a bite, set the hook
            dragging = true;
    }
}