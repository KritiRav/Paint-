
import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
public class main {
    public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    JFrame frame = new JFrame("Demo");
    frame.setMinimumSize(new Dimension(400,400));
    frame.setMaximumSize(new Dimension(1200,800));
    frame.setLayout(new BorderLayout());  
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    //ColorChooserDemo.createAndShowGUI(frame);
    Lines2 window = new Lines2();
    ColorChooserDemo.createAndShowGUI(frame);
    widget w = new widget(window);
    w.createColorBar(frame);
    frame.getContentPane().add(window.content, BorderLayout.CENTER);
   //frame.getContentPane().add(window, BorderLayout.CENTER);
    frame.getContentPane().add(window.buttonsPanel, BorderLayout.SOUTH);
    frame.setVisible(true);
}
}   