
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.awt.Shape;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


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
    Lines2 window = new Lines2(frame); 
    widget w = new widget(window);
    ColorChooserDemo ccd = new ColorChooserDemo(window,w.getCurColor());
    ccd.createAndShowGUI(frame);
    w.createColorBar(frame);
    frame.getContentPane().add(window.content, BorderLayout.CENTER);
    mainMenuView menu = new mainMenuView(window);
    frame.setJMenuBar(menu.menuBar);
    frame.setVisible(true);
}
}   