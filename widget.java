//package components; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.*;
import java.awt.BorderLayout;

public class widget {  
    static JToggleButton b;
    static JToggleButton  b2;
    static JToggleButton green;
    static JToggleButton  orange;
    static JToggleButton black;
    static JToggleButton  red;
    static JToggleButton rectButton;
    static JToggleButton  lineButton;
    private static Lines2 canvasLines2; 

    public widget(Lines2 canvas) {  
        canvasLines2 = canvas;
    }
    public void setColorBorder(JToggleButton btn) {
        // b.setBorder(BorderFactory.createEmptyBorder());
        // b2.setBorder(BorderFactory.createEmptyBorder());
        // black.setBorder(BorderFactory.createEmptyBorder());
        // green.setBorder(BorderFactory.createEmptyBorder());
        // orange.setBorder(BorderFactory.createEmptyBorder());
 // red.setBorder(BorderFactory.createEmptyBorder());
       // red.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(10)));
        btn.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5)));
    }
    // public void unsetBorder(List<JToggleButton> TogList, JToggleButton rm) {
    //     List <JToggleButton> TList = new ArrayList<JToggleButton>();
    //     TList.addAll(TogList).remove(rm);
    //     for (JToggleButton t : TList) {
    //         t.setBorder(BorderFactory.createEmptyBorder());
    //     }
    // }
    // public void setShapeBorder(JToggleButton btn) {
    //   //  rectButton.setBorder(BorderFactory.createLineBorder(Color.WHITE,0));
    // // lineButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
    //     btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    // }
    public void createColorBar(JFrame f) {
    
    JToggleButton b = new JToggleButton(new ImageIcon("color-blue.jpg"));
    JToggleButton b2 = new JToggleButton(new ImageIcon("color-pink.jpg"));
    JToggleButton green = new JToggleButton(new ImageIcon("green.jpg"));
    JToggleButton black = new JToggleButton(new ImageIcon("black.jpg"));
    JToggleButton red = new JToggleButton(new ImageIcon("red.jpg"));
    JToggleButton orange = new JToggleButton(new ImageIcon("orange.png"));
    JToggleButton rectButton = new JToggleButton(new ImageIcon("rect.png"));
    JToggleButton lineButton = new JToggleButton(new ImageIcon("line.png"));
    // JToggleButton [] cArr = new JToggleButton [] {b,b2,green,black,red,orange};
    // List <JToggleButton> tListCol = Arrays.asList(cArr);
    // JToggleButton [] sArr = new JToggleButton [] {rectButton,lineButton};
    // List <JToggleButton> tListShape = Arrays.asList(sArr);
    rectButton.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){  
            String shape = canvasLines2.changeShape("rect");
            canvasLines2.wrangler.setShape(shape);
            // unsetBorder(tListShape,rectButton);
            // setShapeBorder(rectButton);
            }  
        }); 
    lineButton.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){  
            String shape = canvasLines2.changeShape("line");
            canvasLines2.wrangler.setShape(shape);
           // setShapeBorder(lineButton);
            }  
        }); b.addActionListener(new ActionListener(){ 
    public void actionPerformed(ActionEvent e){  
        canvasLines2.wrangler.setBorderCol(Color.blue);
        }  
    });  
    black.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.BLACK);
                setColorBorder(black);
                }  
            });  
    orange.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.orange);
                setColorBorder(orange);
                }  
            });  
    green.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.GREEN);
                }  
            });  
    red.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){  
                canvasLines2.wrangler.setBorderCol(Color.RED);
                }  
            });  
    b2.addActionListener(new ActionListener(){  
public void actionPerformed(ActionEvent e){ 
    canvasLines2.wrangler.setBorderCol(Color.pink);
    }  
    });  

   JPanel holdButtons = new JPanel();
   holdButtons.setLayout(new GridLayout(3,2));
   holdButtons.setPreferredSize(new Dimension(200,400));
   holdButtons.setMaximumSize(new Dimension(300,500));
    holdButtons.add(b); 
    holdButtons.add(b2);
    holdButtons.add(orange);
    holdButtons.add(green);
    holdButtons.add(black);
    holdButtons.add(red); holdButtons.add(rectButton);
    holdButtons.add(lineButton);
//    JPanel newCenter = new JPanel();
//    newCenter.setLayout(new GridLayout(1,0));
//    JButton cent = new JButton("Button Center");
    f.getContentPane().add(holdButtons, BorderLayout.LINE_START);
    // f.getContentPane().add(cent, BorderLayout.CENTER);
    f.setVisible(true); 
    }
}  