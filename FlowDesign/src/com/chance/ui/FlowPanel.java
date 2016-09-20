package com.chance.ui;

import com.chance.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;

/**
 * 流程设计工作面板
 * ???改成JToolbar
 * Created by Chance on 16/09/07.
 */
public class FlowPanel extends JPanel implements MouseMotionListener{

    public ImageIcon imageIcon;

    public FlowPanel() {
//        setLayout(null);
        addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        App.infoBar.setPositionInfo(e.getPoint().getX(), e.getPoint().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        App.infoBar.setPositionInfo(e.getPoint().getX(), e.getPoint().getY());
    }

    //加背景之后的重影问题
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        URL url = this.getClass().getResource("../img/bg.png");
//        imageIcon = new ImageIcon(url);
//
//        if (imageIcon != null) {
//            float width = this.getWidth();
//            float height = this.getHeight();
//            int iconWidth = imageIcon.getIconWidth();
//            int iconHeight = imageIcon.getIconHeight();
//
//            Graphics2D g2d = (Graphics2D) g;
//            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g2d.scale(width / iconWidth, height / iconHeight);
//            g2d.drawImage(imageIcon.getImage(), 0, 0, null);
//        }
//    }


}
