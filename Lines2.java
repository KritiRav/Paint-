import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;
import java.io.*;
import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.io.IOException; 
import java.nio.file.*; 
import static javax.swing.ScrollPaneConstants.*;

// this class acts as the model
public class Lines2 extends JPanel
{
    // Instance variables
    JFrame f;
    JFrame undocked;
    DrawingPanel imagepanel;
    JPanel content;
    JScrollPane scroller; 
    static String whichShape = new String("line");
    LineWrangler wrangler;
    Boolean isUndocked; 
    public String changeShape (String shape) {
        whichShape = shape;
        return whichShape;
    }
    public LineWrangler getLineWrangler () {
        return wrangler;
    }   
     // constructor
    Lines2(JFrame frame)
    {
        //... Create contant pane, layout components
        super(new BorderLayout());
        f = frame;
        whichShape = null;
        content = new JPanel();
        content.setLayout(new BorderLayout());
        // Create JPanel canvas to hold the picture
        imagepanel = new DrawingPanel();
        wrangler = new LineWrangler(imagepanel);
        //wrangler.setShape(new String("line"));
        imagepanel.addMouseListener(wrangler);
        imagepanel.addMouseMotionListener(wrangler);
        // Create JScrollPane to hold the canvas containing the picture
        scroller = new JScrollPane(imagepanel);
       // JPanel nonScrol = new JPanel(imagepanel); 
        scroller.setPreferredSize(new Dimension(500,300));
        scroller.setViewportBorder(BorderFactory.createLineBorder(Color.black));
        // Add scroller pane to Panel
        content.add(scroller,"Center");
        isUndocked = false;
    }
    public void unDock (widget w) {
        if (isUndocked) {
            
        } else {
            
        }
        isUndocked = !isUndocked;
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
            this.setPreferredSize(new Dimension(750,950));
            this.setMinimumSize(new Dimension(600,450));
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
                    g2.setStroke(new BasicStroke((float)12));
                } else {
                g2.setStroke(new BasicStroke((float)lines.get(j).thickness));
                } 
                g2.draw(lines.get(j).l);
            } for(int j = 0; j < rects.size(); j++) {
                g2.setColor(rects.get(j).colorBorder);
                if (rects.get(j).isSelected) {
                    g2.setStroke(new BasicStroke((float)12));
                } else {
                g2.setStroke(new BasicStroke((float)rects.get(j).thickness));
                } g2.draw((Rectangle2D)rects.get(j).r);
                g2.setColor(rects.get(j).colorFill);
                g2.fill(rects.get(j).r);
            } for(int j = 0; j < circles.size(); j++) {
                g2.setColor(circles.get(j).colorBorder);
                if (circles.get(j).isSelected) {
                    g2.setStroke(new BasicStroke((float)12));
                } else {
                g2.setStroke(new BasicStroke((float)circles.get(j).thickness));
                } g2.draw((Ellipse2D.Double)circles.get(j).c);
                g2.setColor(circles.get(j).colorFill);
                g2.fill(circles.get(j).c);
            }
        }
  
        public void addLine(Point start, Point end, Color borderCol, double thick, Boolean isSelected)
        {
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
        public void saveFile() {
            try {
                String filename= "savedFile.txt";
                FileWriter fw = new FileWriter(filename); //the true will append the new data
                for (int i=0;i<rects.size();++i) {
                    String x = Integer.toString((int)rects.get(i).r.getX());
                    String y = Integer.toString((int)rects.get(i).r.getY());
                    String w = Integer.toString((int)rects.get(i).r.getWidth());
                    String h = Integer.toString((int)rects.get(i).r.getHeight());
                    String colRborder = Integer.toString(rects.get(i).colorBorder.getRed());
                    String colGborder = Integer.toString(rects.get(i).colorBorder.getGreen());
                    String colBborder = Integer.toString(rects.get(i).colorBorder.getBlue());
                    String colRfill = Integer.toString(rects.get(i).colorFill.getRed());
                    String colGfill = Integer.toString(rects.get(i).colorFill.getGreen());
                    String colBfill = Integer.toString(rects.get(i).colorFill.getBlue());
                    String isSel=Integer.toString(0);
                    if (rects.get(i).isSelected) {
                        isSel=Integer.toString(1);
                    }
                    String s=" ";
                    fw.append(x+s+y+s+w+s+h+s+colRborder+s+colGborder+s+colBborder+s+colRfill+s+colBfill+s+colGfill+s+Integer.toString((int)rects.get(i).thickness)+s+isSel+"\n");
                }
                fw.append("~\n"); 
                for (int i=0;i<circles.size();++i) {
                    String x = Integer.toString((int)circles.get(i).c.getX());
                    String y = Integer.toString((int)circles.get(i).c.getY());
                    String w = Integer.toString((int)circles.get(i).c.getWidth());
                    String h = Integer.toString((int)circles.get(i).c.getHeight());
                    String colRborder = Integer.toString(circles.get(i).colorBorder.getRed());
                    String colGborder = Integer.toString(circles.get(i).colorBorder.getGreen());
                    String colBborder = Integer.toString(circles.get(i).colorBorder.getBlue());
                    String colRfill = Integer.toString(circles.get(i).colorFill.getRed());
                    String colGfill = Integer.toString(circles.get(i).colorFill.getGreen());
                    String colBfill = Integer.toString(circles.get(i).colorFill.getBlue());
                    String isSel=Integer.toString(0);
                    if (circles.get(i).isSelected) {
                        isSel=Integer.toString(1);
                    }
                    String s=" ";
                    fw.append(x+s+y+s+w+s+h+s+colRborder+s+colGborder+s+colBborder+s+colRfill+s+colBfill+s+colGfill+s+Integer.toString((int)circles.get(i).thickness)+s+isSel+"\n");
                } fw.append("~\n");
                for (int i=0;i<lines.size();++i) { 
                    String x = Integer.toString((int)lines.get(i).l.getX1());
                    String y = Integer.toString((int)lines.get(i).l.getY1());
                    String w = Integer.toString((int)lines.get(i).l.getX2());
                    String h = Integer.toString((int)lines.get(i).l.getY2());
                    String colRborder = Integer.toString(lines.get(i).colorBorder.getRed());
                    String colGborder = Integer.toString(lines.get(i).colorBorder.getGreen());
                    String colBborder = Integer.toString(lines.get(i).colorBorder.getBlue());
                    String isSel=Integer.toString(0);
                    if (lines.get(i).isSelected) {
                        isSel=Integer.toString(1);
                    }
                    String s=" ";
                    fw.append(x+s+y+s+w+s+h+s+colRborder+s+colGborder+s+colBborder+s+Integer.toString((int)lines.get(i).thickness)+s+isSel+"\n");
                } fw.append("~\n");
                fw.close();
            }
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }   

        }
        public void openFile() {
            eraseAll();
            try (BufferedReader br = new BufferedReader(new FileReader("savedFile.txt"))) {
                String line;
                while ((line = br.readLine()) != null && !line.contains("~")) {
                   // process the line.
                   String[] parts = line.split(" ");
                   Boolean isSelect = false;
                   if (parts[11]=="1") {
                       isSelect=true;
                   }
                   Rect rectangle = new Rect(Double.valueOf(parts[0]),Double.valueOf(parts[1]),Double.valueOf(parts[2]),Double.valueOf(parts[3]),new Color(Integer.valueOf(parts[4]),Integer.valueOf(parts[5]),Integer.valueOf(parts[6])),Double.valueOf(parts[10]),new Color(Integer.valueOf(parts[7]),Integer.valueOf(parts[8]),Integer.valueOf(parts[9])),isSelect);
                   rects.add(rectangle);
                } while ((line = br.readLine()) != null && !line.contains("~")) {
                    // process the line.
                    String[] parts = line.split(" ");
                    Boolean isSelect = false;
                    if (parts[11]=="1") {
                        isSelect=true;
                    }
                    Circle circle = new Circle(Double.valueOf(parts[0]),Double.valueOf(parts[1]),Double.valueOf(parts[2]),Double.valueOf(parts[3]),new Color(Integer.valueOf(parts[4]),Integer.valueOf(parts[5]),Integer.valueOf(parts[6])),Double.valueOf(parts[10]),new Color(Integer.valueOf(parts[7]),Integer.valueOf(parts[8]),Integer.valueOf(parts[9])),isSelect);
                    circles.add(circle);
                 } while ((line = br.readLine()) != null && !line.contains("~")) {
                    // process the line.
                    String[] parts = line.split(" ");
                    Boolean isSelect = false;
                    if (parts[8]=="1") {
                        isSelect=true;
                    }
                    Line l = new Line(Double.valueOf(parts[0]),Double.valueOf(parts[1]),Double.valueOf(parts[2]),Double.valueOf(parts[3]),new Color(Integer.valueOf(parts[4]),Integer.valueOf(parts[5]),Integer.valueOf(parts[6])),Double.valueOf(parts[7]),isSelect);
                    lines.add(l);
                 } 
            } catch(IOException e) {}
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
    final int MIN_DIST = 2;
    final int MIN_DRAG_DIST = 5;
    Point p;
    Point end;
    Boolean haveSelection=false;
    List<Lines2.Line> list;
    List<Lines2.Rect> rectList;  
    List<Lines2.Circle> circleList;
    static String shape; 
    Color colorBorder=Color.black;
    double thickness = -1; 
    Color colorFill = Color.white;
    Boolean isFill = false; 
    Boolean isSelected = false;
    Boolean erase = false;
    public Boolean getErase() { return this.erase; }
    public void changeSelected(Boolean s) { isSelected = s; }
    public void unselect() {
        for (int i=0; i<list.size();++i) {
            list.get(i).isSelected = false;
        } for (int i=0; i<rectList.size();++i) {
            rectList.get(i).isSelected = false;
        } for (int i=0; i<circleList.size();++i) {
            circleList.get(i).isSelected = false;
        }
    }
    public void changeErase(Boolean e) {
        erase = e;
    }
    public String getShape() {
        String s = shape;
        return s;
    }
    public void setShape(String inputShape) {
        shape = inputShape; 
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
                if (isSelected) {
                list.get(j).isSelected=true;
                list.get(j).colorBorder = colorBorder;
                list.get(j).thickness = thickness;
                }
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
                if (isSelected) {
                rectList.get(j).isSelected=true;
                rectList.get(j).colorBorder = colorBorder;
                rectList.get(j).thickness = thickness;
                rectList.get(j).colorFill = colorFill;
                } if (isFill) {
                    rectList.get(j).colorFill = colorFill;
                }
                }
            } 
        }for (int j = 0; j < circleList.size(); j++) {
            if (erase && circleList.get(j).c.contains(p.getX(),p.getY())) {
                drawingPanel.removeCircle(circleList.get(j));
            } else if (!erase) {
                // check if selected, then unselect everything else and select this shape
                //if it falls within the bounds
                if (circleList.get(j).isSelected==true&&!circleList.get(j).c.contains(p.getX(),p.getY())) {
                    //unselect
                    circleList.get(j).isSelected=false;
                }
                else if (circleList.get(j).c.contains(p.getX(),p.getY())&&isSelected) {
                    circleList.get(j).isSelected=true;
                    circleList.get(j).setCircle(circleList.get(j).c.getX(),circleList.get(j).c.getY(),circleList.get(j).c.getWidth(),circleList.get(j).c.getHeight(),colorBorder,thickness, colorFill,true);
                } if (circleList.get(j).c.contains(p.getX(),p.getY())) {
                    if (isFill) {
                        circleList.get(j).colorFill = colorFill;
                       
                    }
                }
            }  
        }  drawingPanel.repaint();
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
                if(rec.r.contains(p.getX(),p.getY())) {
                    
                    haveSelection = true; 
                    start=p;
                    selectedRectangle = rec;
                    if (isSelected) {
                        rec.setRect(rec.r.getX(), rec.r.getY(), rec.r.getWidth(), rec.r.getHeight(), colorBorder, thickness, colorFill, true);
                    }
                    if(!erase) {
                        if (isFill) { rectList.get(j).colorFill = colorFill; }
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
                        if (isSelected) {
                            cir.isSelected =true;
                            cir.setCircle(cir.c.getX(), cir.c.getY(), cir.c.getWidth(), cir.c.getHeight(), colorBorder, thickness, colorFill, true);
                        }
                        if(!erase) {
                            if (isFill) { circleList.get(j).colorFill = colorFill; }
                        drawingPanel.moveCircle(cir, p, end);
                        }
                        break; 
                        }   
                    }
            // if p is not near a line, add a line at p
            if(!haveSelection && !isSelected) {
                if (thickness == -1) {}
                else if (getShape().equals("rect")&&!erase) {
                    drawingPanel.addRect(p, end,colorBorder,colorFill,thickness,isSelected);
                } else if (shape==null) {
                }  
                else if (getShape().equals("line")&&!erase) {
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