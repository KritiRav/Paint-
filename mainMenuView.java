
import java.util.*;
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

import javax.swing.JMenuBar;
public class mainMenuView extends JMenuBar{
    private JMenuItem newFile;
    private JMenuItem ViewFull;
    private JMenuItem ViewFit; 
    private static Lines2 lines2Model;
    public JMenuBar menuBar; 
    public mainMenuView (Lines2 mod) {
        menuBar = new JMenuBar();
        lines2Model = mod;
    //Create the menu bar.
    JMenu fileBar = new JMenu("File");
    this.add(menuBar);
    newFile = new JMenuItem("New File");
    ViewFull = new JMenuItem("View Full");
    ViewFit = new JMenuItem("View Fit");
    fileBar.add(newFile);
    fileBar.add(ViewFull);
    fileBar.add(ViewFit);
    menuBar.add(fileBar);
    newFile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            lines2Model.imagepanel.eraseAll();
        }
    });
    ViewFit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            lines2Model.setViewFit();
        }
    });
    ViewFull.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            lines2Model.setViewFull();
        }
    });
    }
}