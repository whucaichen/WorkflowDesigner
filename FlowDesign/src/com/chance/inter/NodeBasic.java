package com.chance.inter;

import com.chance.App;
import com.chance.ui.FlowLine;
import com.chance.utils.Properties;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Vector;

/**
 * 基本流程节点抽象类
 * Created by Chance on 16/09/07.
 */
public abstract class NodeBasic extends JButton implements INode {

    public int id = -1;
    public Collection<INode> childs = new Vector<INode>();
    public Collection<INode> parents = new Vector<INode>();

    public DropTarget dropTarget;

    public NodeBasic() {
        this.setSize(100, 40);
//        setLocation(0, 50);
        new MouseApdaterListener(this);
        dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY, this, true);
    }

    //判断当某个对象接入子对象后，是否会成为一个循环流程
    public boolean isPathed(INode button) {
        for (INode nb : this.getChilds()) {
            if (nb == button) {
                return true;
            }
            if (nb instanceof NodeBasic) {
                return ((NodeBasic) nb).isPathed(button);
            }
        }
        return false;
    }

    @Override
    public final String[] asXML() {
        String xml = "";
        xml = "<Node id=\"" + this.getID() + "\" type=\"" + this.getType() + "\" name=\"" +
                this.getText() + "\" x=\"" + this.getX() + "\" y=\"" + this.getY() + "\" />";
        String to = "";
        for (INode nb : this.getChilds()) {
            to += nb.getID() + ",";
        }
        String line = "";
        if (!to.trim().equals("")) {
            line = "<Line from=\"" + this.getID() + "\" to=\"" + to.substring(0, to.length() - 1) + "\" />";
        }
        return new String[]{xml, line};
    }

    //拖拽DropTarget
    @Override
    public void drop(DropTargetDropEvent dtde) {
        DataFlavor ob = dtde.getCurrentDataFlavors()[0];
        INode db = null;
        try {
            db = (INode) dtde.getTransferable().getTransferData(ob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FlowLine line = new FlowLine(new JButton[]{(JButton) db, this});
        line.setSize(getRootPane().getSize());
        line.setLocation(0, 0);
        getRootPane().add(line);
        App.infoBar.setOperation("“" + this.getText()+ "”连线成功");
//        getRootPane().repaint();
    }

    public final class MouseApdaterListener extends MouseInputAdapter {

        private NodeBasic nodeBasic;

        private Point point = new Point(0, 0);

        public MouseApdaterListener(NodeBasic nb) {
            this.nodeBasic = nb;
            this.nodeBasic.addMouseListener(this);
            this.nodeBasic.addMouseMotionListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            new Properties().setVisible(true);
        }

        public void mousePressed(MouseEvent e) {
            if (e.getButton() != 1) {
                JComponent com = (JComponent) e.getSource();
                TransferHandler handler = new TransferHandler("this");
                com.setTransferHandler(handler);
                handler.exportAsDrag(com, e, DnDConstants.ACTION_COPY);
            } else {
                this.point = SwingUtilities.convertPoint(this.nodeBasic, e.getPoint(), this.nodeBasic.getParent());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (e.getButton() != 1) {
                Point newPoint = SwingUtilities.convertPoint(this.nodeBasic, e.getPoint(), this.nodeBasic.getParent());
                Point btnPoint = new Point((this.nodeBasic.getX() + (newPoint.x - this.point.x)), (this.nodeBasic.getY() + (newPoint.y - this.point.y)));
                this.nodeBasic.setLocation(btnPoint);
                point = newPoint;
//                FlowPanel frame = this.nodeBasic.getFrame(this.nodeBasic.getParent());
//                frame.repaint();
            }
        }
    }

    //获取节点类型，由具体实现类生成
    public abstract String getType();

    @Override
    public final void init(int id) {
        this.id = id;
        this.setIcon(getIcon());
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon("res/help.gif");
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void addChild(INode nb) {
        this.childs.add(nb);
    }

    @Override
    public final Collection<INode> getChilds() {
        return childs;
    }

    @Override
    public void addParent(INode nb) {
        this.parents.add(nb);
    }

    @Override
    public final Collection<INode> getParents() {
        return parents;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {}

    @Override
    public void dragExit(DropTargetEvent dte) {}

    @Override
    public void dragOver(DropTargetDragEvent dtde) {}

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {}
}
