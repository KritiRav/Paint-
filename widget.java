//package components; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.*;

import javafx.util.converter.CurrencyStringConverter;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

public class widget {  
    static JToggleButton b;
    static JToggleButton  b2;
    static JToggleButton green;
    static JToggleButton  orange;
    static JToggleButton black;
    static JToggleButton  red;
    static JToggleButton rectButton;
    static JToggleButton circleButton;
    static JToggleButton  lineButton;
    static JToggleButton thick1;
    static JToggleButton thick2;
    static JToggleButton thick3; 
    static JToggleButton undock; 
    static JToggleButton selectedColor; 
    public static JButton curColor; 
    static Boolean isFill;
    private static Lines2 canvasLines2; 

    public widget(Lines2 canvas) {  
        canvasLines2 = canvas;
        isFill=false;
    }
    public JButton getCurColor() { return curColor; }
    public void unsetBorder(List<JToggleButton> TogList, JToggleButton rm) {
        List <JToggleButton> TList = new LinkedList<JToggleButton>();
        TList.addAll(TogList);
        TList.remove(rm);
        for (JToggleButton t : TList) {
            t.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    public void unsetAllBorder(List<JToggleButton> TogList) {
        List <JToggleButton> TList = new LinkedList<JToggleButton>();
        TList.addAll(TogList);
        for (JToggleButton t : TList) {
            t.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    public void setBorder(JToggleButton btn) {
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }
    public void createColorBar(JFrame f) {
    
    thick1 = new JToggleButton(new ImageIcon("line_thin.jpg"));
    thick2 = new JToggleButton(new ImageIcon("line_med.jpg"));
    thick3 = new JToggleButton(new ImageIcon("line_thick.png"));
    undock = new JToggleButton("undock");
    JToggleButton b = new JToggleButton(new ImageIcon("color-blue.jpg"));
    JToggleButton b2 = new JToggleButton(new ImageIcon("color-pink.jpg"));
    JToggleButton green = new JToggleButton(new ImageIcon("green.jpg"));
    JToggleButton black = new JToggleButton(new ImageIcon("black.jpg"));
    JToggleButton red = new JToggleButton(new ImageIcon("red.jpg"));
    curColor = new JButton("");
    JToggleButton orange = new JToggleButton(new ImageIcon("orange.png"));
    JToggleButton rectButton = new JToggleButton(new ImageIcon("rect.png"));
    JToggleButton lineButton = new JToggleButton(new ImageIcon("line.png"));
    JToggleButton circleButton = new JToggleButton(new ImageIcon("circle.png"));
    JToggleButton selectButton = new JToggleButton(new ImageIcon("mouse.jpg"));
    JToggleButton eraseButton = new JToggleButton(new ImageIcon("eraser.jpg"));
    JToggleButton paintFill = new JToggleButton("Select to fill Shapes");
    List<JToggleButton> tListCol = new LinkedList<JToggleButton>();
     tListCol.add(orange); tListCol.add(red); tListCol.add(black); tListCol.add(green);
     tListCol.add(b); tListCol.add(b2);
    List<JToggleButton> tListSp = new LinkedList<JToggleButton>();
    tListSp.add(rectButton); tListSp.add(lineButton); tListSp.add(circleButton);
    List<JToggleButton> tListThic = new LinkedList<JToggleButton>();
    tListThic.add(thick1); tListThic.add(thick2); tListThic.add(thick3);
    widget w = this;
    isFill = false; 
    unsetAllBorder(tListThic);
    unsetAllBorder(tListSp);
    unsetAllBorder(tListCol);
    curColor.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            curColor.setOpaque(true); 
            curColor.setBackground (canvasLines2.wrangler.colorBorder);
            curColor.setBorderPainted(false); 
        }        
    });
    paintFill.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            if (isFill) {
                paintFill.setBorder(BorderFactory.createEmptyBorder());
                canvasLines2.wrangler.colorFill = Color.WHITE;
            } else {
                setBorder(paintFill);
                canvasLines2.wrangler.colorFill = canvasLines2.wrangler.colorBorder;
            } isFill = !isFill;
            canvasLines2.wrangler.isFill = isFill;
        }        
    });
    undock.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){ 
            canvasLines2.unDock(w);
            }  
        }); 
    thick1.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            double d = 2;  
            canvasLines2.wrangler.setThickness(d);
            unsetBorder(tListThic,thick1);
             setBorder(thick1);
            }  
        }); 
    thick2.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            double d = 5;  
            canvasLines2.wrangler.setThickness(d);
            unsetBorder(tListThic,thick2);
                setBorder(thick2);
            }  
        }); 
    thick3.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            double d = 9;  
            canvasLines2.wrangler.setThickness(d);
            unsetBorder(tListThic,thick3);
                setBorder(thick3);
            }  
        }); 
    rectButton.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){  
            String shape = canvasLines2.changeShape("rect");
            canvasLines2.wrangler.setShape(shape);
            unsetBorder(tListSp,rectButton);
             setBorder(rectButton);
            }  
        }); 
    circleButton.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){  
            String shape = canvasLines2.changeShape("circle");
            canvasLines2.wrangler.setShape(shape);
            unsetBorder(tListSp,circleButton);
            setBorder(circleButton);
            }  
        }); 
    lineButton.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){  
            String shape = canvasLines2.changeShape("line");
            canvasLines2.wrangler.setShape(shape);
            unsetBorder(tListSp,lineButton);
            setBorder(lineButton);
            }  
        }); 
    b.addActionListener(new ActionListener(){ 
    public void actionPerformed(ActionEvent e){  
        canvasLines2.wrangler.setBorderCol(new Color(26, 140, 255));
        if (isFill) {
        canvasLines2.wrangler.setFillCol(new Color(26, 140, 255));
        } else {
            canvasLines2.wrangler.setFillCol(Color.WHITE);
        } 
        curColor.setOpaque(true); 
            curColor.setBackground (canvasLines2.wrangler.colorBorder);
            curColor.setBorderPainted(false); 
        unsetBorder(tListCol, b);
        setBorder(b);
        }  
    }); eraseButton.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent e){
        canvasLines2.wrangler.changeErase(!canvasLines2.wrangler.erase);
        canvasLines2.wrangler.changeSelected(false);
        if (canvasLines2.wrangler.erase) {
        setBorder(eraseButton);
        } else {
            eraseButton.setBorder(BorderFactory.createEmptyBorder());
        }
        selectButton.setBorder(BorderFactory.createEmptyBorder());
    }
    });
    selectButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){
            canvasLines2.wrangler.changeErase(false);
            canvasLines2.wrangler.changeSelected(!canvasLines2.wrangler.isSelected);
            if (canvasLines2.wrangler.isSelected) {
                setBorder(selectButton);
            } else {
                selectButton.setBorder(BorderFactory.createEmptyBorder());
                canvasLines2.wrangler.unselect();
                canvasLines2.wrangler.drawingPanel.repaint();
            }
                eraseButton.setBorder(BorderFactory.createEmptyBorder());
        }
        });
    black.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.BLACK);
                if (isFill) {
                    canvasLines2.wrangler.setFillCol(Color.BLACK);
                    } else {
                        canvasLines2.wrangler.setFillCol(Color.WHITE);
                    }
                curColor.setOpaque(true); 
                curColor.setBackground (canvasLines2.wrangler.colorBorder);
                curColor.setBorderPainted(false); 
                unsetBorder(tListCol, black);
                setBorder(black);
                }  
            });  
    orange.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(new Color(255, 153, 51));
                if (isFill) {
                    canvasLines2.wrangler.setFillCol(new Color(255, 153, 51));
                    } else {
                        canvasLines2.wrangler.setFillCol(Color.WHITE);
                    }
                curColor.setOpaque(true); 
                curColor.setBackground (canvasLines2.wrangler.colorBorder);
                curColor.setBorderPainted(false); 
                unsetBorder(tListCol, orange);
                setBorder(orange);
                }  
            });  
    green.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.GREEN);
                if (isFill) {
                    canvasLines2.wrangler.setFillCol(Color.GREEN);
                    } else {
                        canvasLines2.wrangler.setFillCol(Color.WHITE);
                    }
                curColor.setOpaque(true); 
                curColor.setBackground (canvasLines2.wrangler.colorBorder);
                curColor.setBorderPainted(false); 
                unsetBorder(tListCol, green);
                setBorder(green);
                }  
            });  
    red.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(new Color(204, 0, 0));
                if (isFill) {
                    canvasLines2.wrangler.setFillCol(new Color(204, 0, 0));
                    } else {
                        canvasLines2.wrangler.setFillCol(Color.WHITE);
                    }
                curColor.setOpaque(true); 
                curColor.setBackground (canvasLines2.wrangler.colorBorder);
                curColor.setBorderPainted(false); 
                unsetBorder(tListCol, red);
                setBorder(red);
                }  
            });  
    b2.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){ 
    canvasLines2.wrangler.setBorderCol(Color.pink);
    if (isFill) {
        canvasLines2.wrangler.setFillCol(Color.PINK);
        } else {
            canvasLines2.wrangler.setFillCol(Color.WHITE);
        }
    curColor.setOpaque(true); 
    curColor.setBackground (canvasLines2.wrangler.colorBorder);
    curColor.setBorderPainted(false); 
    unsetBorder(tListCol, b2);
    setBorder(b2);
    }  
    });  

   JPanel holdButtons = new JPanel();
   holdButtons.setLayout(new GridLayout(5,2));
   holdButtons.setPreferredSize(new Dimension(200,90));
   holdButtons.setMaximumSize(new Dimension(350,90));
   holdButtons.setMinimumSize(new Dimension(50,80));
   JPanel secondButtons = new JPanel(new GridLayout(1,3));
   curColor.setMinimumSize(new Dimension(700,80));
   curColor.setMaximumSize(new Dimension(700,80));
   secondButtons.setPreferredSize(new Dimension(200,25));
   secondButtons.setMaximumSize(new Dimension(400,50));
    holdButtons.add(b); 
    holdButtons.add(b2);
    holdButtons.add(orange);
    holdButtons.add(green);
    holdButtons.add(black);
    holdButtons.add(red); holdButtons.add(curColor);
    holdButtons.add(rectButton);
    holdButtons.add(lineButton); holdButtons.add(circleButton);
    holdButtons.add(eraseButton); holdButtons.add(selectButton);
    holdButtons.add(thick1);
    holdButtons.add(thick2);
    holdButtons.add(thick3);
    secondButtons.add(paintFill);
    f.getContentPane().add(holdButtons, BorderLayout.LINE_START);
    f.getContentPane().add(secondButtons, BorderLayout.SOUTH);
    // f.getContentPane().add(cent, BorderLayout.CENTER);
    f.setVisible(true); 
    }
}  