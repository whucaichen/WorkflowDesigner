package com.chance.ui;

import com.chance.App;
import com.chance.inter.NodeBasic;
import com.chance.node.*;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 流程节点工具面板
 * Created by Chance on 16/09/07.
 */
public class FlowBar extends JToolBar {

    public App app;

    public FlowBar(App app) {
        this.app = app;
        setFloatable(false);//不可拖动
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        add(new BarButton(NodeStart.class, Attri.NAME_START));
        add(new BarButton(NodeStop.class, Attri.NAME_STOP));
        add(new BarButton(NodeNormal.class, Attri.NAME_NORMAL));
        add(new BarButton(NodeFork.class, Attri.NAME_FORK));
        add(new BarButton(NodeJoin.class, Attri.NAME_JOIN));
        add(new BarButton(NodeSuper.class, Attri.NAME_SUPER));
        add(new BarButton(NodeCustom.class, Attri.Name_CUSTOM));
    }

    /**
     * 工具面板上的流程节点项目
     * Created by Chance on 16/09/07.
     */
    class BarButton extends JButton implements MouseListener {

        public Class clazz;

        public BarButton(Class clazz, String nodeName) {
            super();
            this.clazz = clazz;
            this.setText(nodeName);
            this.setIconTextGap(0);
            this.setHorizontalAlignment(LEFT);
            this.setPreferredSize(new Dimension(100, 35));
            this.addMouseListener(this);
            try {
                Object ob = this.clazz.newInstance();
                if (ob instanceof NodeBasic) {
                    Icon icon = ((NodeBasic) ob).getIcon();
                    this.setIcon(icon);
                }
                ob = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void setToolTipText(String str) {
            super.setToolTipText(this.getText());
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Component c = this.getRootPane().getGlassPane();
            c.setVisible(true);
//            c.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//                    new ImageIcon("res/add.png").getImage(), new Point(0, 0), "add"));
//            URL url = this.getClass().getResource("../res/add.png");
//            c.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//                    new ImageIcon(url).getImage(), new Point(0, 0), "add"));
            c.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    Attri.getImgRes("add.png").getImage(), new Point(0, 0), "add"));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setCursor(Cursor.DEFAULT_CURSOR);
            try {
                Object ob = this.clazz.newInstance();
                if (ob instanceof NodeBasic) {
                    NodeBasic nb = (NodeBasic) ob;
                    nb.init(App.app.myPanel.ID++);
                    Point p = e.getPoint();
                    int px = p.x + this.getX() - this.getWidth() / 2;
                    int py = p.y - this.getHeight() / 2;
                    nb.setLocation(px, py);
//                    nb.setBounds(px, py, 100, 40);
                    app.myPanel.add(nb);
                    app.infoBar.setActionInfo("创建节点:" + nb.getText());
                    App.app.myPanel.collection.add(nb);
                    app.myPanel.setLayout(null);
                    app.myPanel.repaint();
                } else {
                    System.out.println("NodeBasic Only");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            setCursor(Cursor.HAND_CURSOR);
            this.setToolTipText("");
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        public void setCursor(int cursor){
            Component c = app.getRootPane().getGlassPane();
            c.setVisible(true);
            c.setCursor(new Cursor(cursor));
        }
    }
}
