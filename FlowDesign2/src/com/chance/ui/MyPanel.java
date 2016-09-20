package com.chance.ui;

import com.chance.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Chance on 16/09/09.
 */
public class MyPanel extends JPanel implements MouseMotionListener {
//public class MyPanel extends JPanel {

    public App app;
    public ImageIcon imageIcon = new ImageIcon("res/bg.png");

    public MyPanel(App app) {
        this.app = app;
//        setSize(1200, 900);
//        setLayout(null);
//        setOpaque(false);
//        setLayout(new FlowLayout(FlowLayout.LEFT));
        setPreferredSize(new Dimension(1800, 900));
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), null);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        app.infoBar.setPositionInfo(e.getPoint().getX(), e.getPoint().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        app.infoBar.setPositionInfo(e.getPoint().getX(), e.getPoint().getY());
    }
}
