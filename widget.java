//package components; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.*;
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
    private static Lines2 canvasLines2; 

    public widget(Lines2 canvas) {  
        canvasLines2 = canvas;
    }
    public void unsetBorder(List<JToggleButton> TogList, JToggleButton rm) {
        List <JToggleButton> TList = new LinkedList<JToggleButton>();
        TList.addAll(TogList);
        TList.remove(rm);
        for (JToggleButton t : TList) {
            t.setBorder(BorderFactory.createEmptyBorder());
        }
    }
    public void setBorder(JToggleButton btn) {
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }
    public void createColorBar(JFrame f) {
    
    thick1 = new JToggleButton("thin");
    thick2 = new JToggleButton("medium");
    thick3 = new JToggleButton("thick");
    JToggleButton b = new JToggleButton(new ImageIcon("color-blue.jpg"));
    JToggleButton b2 = new JToggleButton(new ImageIcon("color-pink.jpg"));
    JToggleButton green = new JToggleButton(new ImageIcon("green.jpg"));
    JToggleButton black = new JToggleButton(new ImageIcon("black.jpg"));
    JToggleButton red = new JToggleButton(new ImageIcon("red.jpg"));
    JToggleButton orange = new JToggleButton(new ImageIcon("orange.png"));
    JToggleButton rectButton = new JToggleButton(new ImageIcon("rect.png"));
    JToggleButton lineButton = new JToggleButton(new ImageIcon("line.png"));
    JToggleButton circleButton = new JToggleButton("circle");
    JToggleButton selectButton = new JToggleButton("Selector Tool");
    JToggleButton eraseButton = new JToggleButton("Erase Tool");
    List<JToggleButton> tListCol = new LinkedList<JToggleButton>();
     tListCol.add(orange); tListCol.add(red); tListCol.add(black); tListCol.add(green);
     tListCol.add(b); tListCol.add(b2);
    List<JToggleButton> tListSp = new LinkedList<JToggleButton>();
    tListSp.add(rectButton); tListSp.add(lineButton); tListSp.add(circleButton);
    List<JToggleButton> tListThic = new LinkedList<JToggleButton>();
    tListThic.add(thick1); tListThic.add(thick2); tListThic.add(thick3);
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
            double d = 7;  
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
        }); b.addActionListener(new ActionListener(){ 
    public void actionPerformed(ActionEvent e){  
        canvasLines2.wrangler.setBorderCol(Color.blue);
        unsetBorder(tListCol, b);
        setBorder(b);
        }  
    }); eraseButton.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent e){
        canvasLines2.wrangler.changeErase(true);
        setBorder(eraseButton);
        selectButton.setBorder(BorderFactory.createEmptyBorder());
    }
    });
    selectButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e){
            canvasLines2.wrangler.changeErase(false);
            setBorder(selectButton);
            eraseButton.setBorder(BorderFactory.createEmptyBorder());
        }
        });
    black.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.BLACK);
                unsetBorder(tListCol, black);
                setBorder(black);
                }  
            });  
    orange.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.orange);
                unsetBorder(tListCol, orange);
                setBorder(orange);
                }  
            });  
    green.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.GREEN);
                unsetBorder(tListCol, green);
                setBorder(green);
                }  
            });  
    red.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.RED);
                unsetBorder(tListCol, red);
                setBorder(red);
                }  
            });  
    b2.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){ 
    canvasLines2.wrangler.setBorderCol(Color.pink);
    unsetBorder(tListCol, b2);
    setBorder(b2);
    }  
    });  

   JPanel holdButtons = new JPanel();
   holdButtons.setLayout(new GridLayout(4,2));
   holdButtons.setPreferredSize(new Dimension(200,400));
   holdButtons.setMaximumSize(new Dimension(300,500));
    holdButtons.add(b); 
    holdButtons.add(b2);
    holdButtons.add(orange);
    holdButtons.add(green);
    holdButtons.add(black);
    holdButtons.add(red); holdButtons.add(rectButton);
    holdButtons.add(lineButton); holdButtons.add(circleButton);
    holdButtons.add(eraseButton); holdButtons.add(selectButton);
    holdButtons.add(thick1);
    holdButtons.add(thick2);
    holdButtons.add(thick3);
//    JPanel newCenter = new JPanel();
//    newCenter.setLayout(new GridLayout(1,0));
//    JButton cent = new JButton("Button Center");
    f.getContentPane().add(holdButtons, BorderLayout.LINE_START);
    // f.getContentPane().add(cent, BorderLayout.CENTER);
    f.setVisible(true); 
    }
}  