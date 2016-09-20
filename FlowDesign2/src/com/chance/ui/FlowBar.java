package com.chance.ui;

import com.chance.App;
import com.chance.inter.IFlow;
import com.chance.inter.NodeBasic;
import com.chance.node.*;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Vector;

/**
 * 流程节点工具面板
 * Created by Chance on 16/09/07.
 */
public class FlowBar extends JToolBar {

    public App app;

    public int ID = 1;
    public Collection<IFlow> collection = new Vector<IFlow>();

    public FlowBar(App app) {
        this.app = app;
        setFloatable(false);//不可拖动

        add(new BarButton(new NodeStart(), Attri.NAME_START));
        add(new BarButton(new NodeStop(), Attri.NAME_STOP));
        add(new BarButton(new NodeNormal(), Attri.NAME_NORMAL));
        add(new BarButton(new NodeFork(), Attri.NAME_FORK));
        add(new BarButton(new NodeJoin(), Attri.NAME_JOIN));
        add(new BarButton(new NodeSuper(), Attri.NAME_SUPER));
        add(new BarButton(new NodeDelete(), Attri.NAME_DELETE));
    }

    /**
     * 工具面板上的流程节点项目
     * Created by Chance on 16/09/07.
     */
    class BarButton extends JButton implements MouseListener {

        public NodeBasic nodeBasic;
//        public Point p;

        public BarButton(NodeBasic nodeBasic, String nodeName) {
            this.nodeBasic = nodeBasic;
            this.setText(nodeName);
            this.setIcon(nodeBasic.getIcon());
            this.addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            app.infoBar.setOperation("创建节点:" + nodeBasic.getText());
            nodeBasic.init(ID++);
            collection.add(nodeBasic);

            Point p = e.getPoint();
            int px = p.x+ this.getX()- this.getWidth()/2;
            int py = p.y- this.getHeight()/2;
            nodeBasic.setBounds(px, py, 100, 40);
//            nodeBasic.setLocation(p);
//            nodeBasic.setSize(100, 40);
            app.myPanel.add(nodeBasic);
            app.myPanel.setLayout(null);
            app.myPanel.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

}
