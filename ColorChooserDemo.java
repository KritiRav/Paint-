/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

//package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

/* ColorChooserDemo.java requires no other files. */
public class ColorChooserDemo extends JPanel
                              implements ChangeListener {
    private Lines2 l; 
    protected JColorChooser tcc;
    public JButton ColorChoose; 
    private JButton curColor;
    private JPanel gridTop; 
    //private JButton color;

    public ColorChooserDemo(Lines2 l_,JButton curColor_) {
        super(new BorderLayout());
        //Set up color chooser for setting text color
        l = l_;
        gridTop = new JPanel(new GridLayout(1,1));
        curColor = curColor_;
        tcc = new JColorChooser();
        tcc.getSelectionModel().addChangeListener(this);
        tcc.setBorder(BorderFactory.createTitledBorder(
                                             "Choose Text Color"));
        //add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }

    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        l.wrangler.setBorderCol(newColor); 
        //curColor.setBackground(newColor);
        //banner.setForeground(newColor);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public void createAndShowGUI(JFrame frame) {
        //Create and set up the window.
        

        //Create and set up the content pane.
        ColorChoose = new JButton("Choose Color");
        JComponent newContentPane = new ColorChooserDemo(l,curColor);
        ColorChoose.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
            int result = JOptionPane.showConfirmDialog(null, newContentPane, "Popup pick color", JOptionPane.PLAIN_MESSAGE);
            ColorChoose.setOpaque(true);
            ColorChoose.setBackground(l.wrangler.colorBorder);
            ColorChoose.setBorderPainted(false);
            }  
        });  
        newContentPane.setOpaque(true); //content panes must be opaque
        gridTop.add(ColorChoose);
        frame.getContentPane().add(gridTop, BorderLayout.PAGE_START);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}