package com.chance.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;

public class FlowLine extends JComponent implements MouseMotionListener, MouseListener {

    private static final long serialVersionUID = 5300690492748181305L;

    private int[] p1 = new int[]{0, 0};

    private int[] p2 = new int[]{0, 0};

    private JButton[] Link = null;

    public void mouseDragged(MouseEvent e) {
        this.p2[0] = e.getX();
        this.p2[1] = e.getY();
        this.repaint();
    }

    public void mouseMoved(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}

    public void mousePressed(MouseEvent e) {
        this.p1[0] = e.getX();
        this.p1[1] = e.getY();
    }

    public void mouseReleased(MouseEvent arg0) {}

    public FlowLine() {
        this.setBackground(Color.white);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public FlowLine(JButton[] link) {
        this.Link = link;
        this.setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.Link != null) {
            int[][] ps = this.getObjectCenter(this.Link[0], this.Link[1]);
            this.p1 = ps[0];
            this.p2 = ps[1];
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.black);
        this.drawArrowLine(p1, p2, g2);
    }

    public int[][] getObjectCenter(JButton start, JButton end) {
        int[] s = new int[]{start.getX(), start.getY()};
        int[] e = new int[]{end.getX(), end.getY()};
        if (end.getX() > start.getX() + start.getWidth() - 1) {
            e[0] = end.getX();
            e[1] = end.getY() + end.getHeight() / 2;
            s[0] = start.getX() + start.getWidth();
            s[1] = start.getY() + start.getHeight() / 2;
        } else {
            Rectangle sR = new Rectangle(start.getX(), 0, start.getWidth(), 100);
            Rectangle sE = new Rectangle(end.getX(), 0, end.getWidth(), 100);
            if (sR.intersects(sE) || sE.intersects(sR)) {
                e[0] = end.getX() + end.getWidth() / 2;
                s[0] = start.getX() + start.getWidth() / 2;
                if (start.getY() <= end.getY()) {
                    s[1] = start.getY() + start.getHeight();
                    e[1] = end.getY();
                } else {
                    s[1] = start.getY();
                    e[1] = end.getY() + end.getHeight();
                }
            } else {
                e[0] = end.getX() + end.getWidth();
                e[1] = end.getY() + end.getHeight() / 2;
                s[0] = start.getX();
                s[1] = start.getY() + start.getHeight() / 2;
            }
        }
        return new int[][]{s, e};
    }

    public int[] getObjectCenter(JButton jb) {
        return new int[]{jb.getX() + jb.getWidth() / 2, jb.getY() + (jb.getHeight()) / 2};
    }

    public void drawArrowLine(int[] p1, int[] p2, Graphics2D g2) {
        double arrowHeight = 5;
        double arrowWidth = 5;
        double awrad = Math.atan(arrowWidth / arrowHeight);
        double arrow_len = Math.sqrt(arrowHeight * arrowHeight + arrowWidth * arrowWidth);
        double[] arrXY_1 = rotateVec(p2[0] - p1[0], p2[1] - p1[1], awrad, true, arrow_len);
        double[] arrXY_2 = rotateVec(p2[0] - p1[0], p2[1] - p1[1], -awrad, true, arrow_len);
        g2.drawLine(p1[0], p1[1], p2[0], p2[1]);
//		g2.drawChars("Line".toCharArray(), p1[0], p1[1], p2[0], p2[1]);
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(p2[0], p2[1]);
        triangle.lineTo(p2[0] - new Double(arrXY_1[0]).intValue(), p2[1] - new Double(arrXY_1[1]).intValue());
        triangle.lineTo(p2[0] - new Double(arrXY_2[0]).intValue(), p2[1] - new Double(arrXY_2[1]).intValue());
        triangle.closePath();
        g2.fill(triangle);
    }

    /**
     * 矢量运算算法
     *
     * @param px      X分量
     * @param py      Y分量
     * @param ang     旋转角度
     * @param isChLen 是否改变长度
     * @param newLen  现场长度
     * @return
     */
    public static double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

}
