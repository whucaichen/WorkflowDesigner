package com.applet.flows;

import com.applet.flows.attr.NodeAttribute;
import com.applet.flows.ui.FlowLine;
import com.applet.flows.ui.FlowPanel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Vector;

public abstract class NodeButtonSupport extends JButton implements INodeButton {

    private Collection<INodeButton> childs = null;

    private Collection<INodeButton> parents = null;

    private int id = -1;

    protected DropTarget dropTarget = null;

    public NodeButtonSupport() {
        this.childs = new Vector<INodeButton>();
        this.parents = new Vector<INodeButton>();
        this.dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY, this, true);
        new MouseApdaterListener(this);
        this.setSize(100, 40);
        this.setLocation(0, 45);
    }

    public ImageIcon getIcon() {
        return null;
    }

    public final String[] asXML() {
        String xml = "";
        xml = "<Node id=\"" + this.getID() + "\" type=\"" + this.getType() + "\" name=\"" + this.getText() + "\" x=\"" + this.getX() + "\" y=\"" + this.getY() + "\" />";
        String to = "";
        for (INodeButton nb : this.getChilds()) {
            to += nb.getID() + ",";
        }
        String line = "";
        if (!to.trim().equals("")) {
            line = "<Line from=\"" + this.getID() + "\" to=\"" + to.substring(0, to.length() - 1) + "\" />";
        }
        return new String[]{xml, line};
    }

    public final void init(int id) {
        this.id = id;
        ImageIcon icon = this.getIcon();
        if (icon != null) {
            this.setIcon(icon);
        }
        this.init();
    }

    public INodeButton getThis() {
        return this;
    }

    /**
     * 判断当某个对象接入子对象后，是否会成为一个循环流程
     *
     * @param button 接入后的对象
     * @return true：是一个循环，false：不是循环
     */
    public boolean isPathed(INodeButton button) {
        for (INodeButton nb : this.getChilds()) {
            if (nb == button) {
                return true;
            }
            if (nb instanceof NodeButtonSupport) {
                return ((NodeButtonSupport) nb).isPathed(button);
            }
        }
        return false;
    }

    /**
     * 找到Frame对象
     *
     * @param jc
     * @return
     */
    public FlowPanel getFrame(Container jc) {
        if (jc == null) {
            return null;
        }
        if (jc instanceof FlowPanel) {
            return (FlowPanel) jc;
        }
        return this.getFrame(jc.getParent());
    }

    protected abstract void init();

    public int getID() {
        return this.id;
    }

    public abstract boolean link(INodeButton button);

    public void addParent(INodeButton nb) {
        this.parents.add(nb);
    }

    public void addChild(INodeButton nb) {
        this.childs.add(nb);
    }

    public void dragEnter(DropTargetDragEvent arg0) {
    }

    public void dragExit(DropTargetEvent arg0) {
    }

    public void dragOver(DropTargetDragEvent arg0) {
    }

    public final void drop(DropTargetDropEvent e) {
        System.out.println("drop");
        System.out.println(e);
        System.out.println(e.getCurrentDataFlavors()[0]);
        DataFlavor ob = e.getCurrentDataFlavors()[0];
        try {
            if (!e.getTransferable().isDataFlavorSupported(ob)) {
                JOptionPane.showMessageDialog(null, "应用程序不支持对象序列化\n[" + ob.toString() + "]", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            INodeButton db = (INodeButton) e.getTransferable().getTransferData(ob);
            if (db == null) {
                JOptionPane.showMessageDialog(null, "应用程序无法读取对象序列化\n[" + ob.toString() + "]", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (isPathed(db)) {
                return;
            }
            if (db.report(this) && this.link(db)) {
                this.addParent(db);
                db.addChild(this);
                FlowPanel frame = this.getFrame(this.getParent());
                if (frame == null) {
                    JOptionPane.showMessageDialog(null, "程序无法找到父容器", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    FlowLine line = new FlowLine(new JButton[]{(JButton) db, this});
                    line.setSize(frame.getSize());
                    line.setLocation(0, 0);
                    frame.add(line);
                    frame.setText("[" + this.getText() + "]连线成功");
                    frame.repaint();
                }
            }
        } catch (Exception e1) {
            this.getFrame(this.getParent()).setText(e1.getClass().getName());
            JOptionPane.showMessageDialog(null, e1.toString(), "错误", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

    @Override
    public void setToolTipText(String str) {
        str = "[ID=" + this.getID() + "]\n" + str;
        super.setToolTipText(str);
    }

    public void dropActionChanged(DropTargetDragEvent arg0) {
    }

    public final Collection<INodeButton> getChilds() {
        return this.childs;
    }

    public final Collection<INodeButton> getParents() {
        return this.parents;
    }

    /**
     * 获取节点类型
     *
     * @return 节点类型
     */
    public abstract String getType();

    public final class MouseApdaterListener extends MouseInputAdapter {

        private NodeButtonSupport support = null;

        private Point point = new Point(0, 0);

        public MouseApdaterListener(NodeButtonSupport support) {
            this.support = support;
            this.support.addMouseListener(this);
            this.support.addMouseMotionListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            new NodeAttribute(this.support).setVisible(true);
        }

        public void mousePressed(MouseEvent e) {
//            System.out.println("press");
            if (e.getButton() != 1) {
                JComponent com = (JComponent) e.getSource();
                TransferHandler handler = new TransferHandler("this");
                com.setTransferHandler(handler);
                handler.exportAsDrag(com, e, DnDConstants.ACTION_COPY);
            } else {
                this.point = SwingUtilities.convertPoint(this.support, e.getPoint(), this.support.getParent());
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
//            System.out.println("drag");
            if (e.getButton() != 1) {
                Point newPoint = SwingUtilities.convertPoint(this.support, e.getPoint(), this.support.getParent());
                Point btnPoint = new Point((this.support.getX() + (newPoint.x - this.point.x)), (this.support.getY() + (newPoint.y - this.point.y)));
                this.support.setLocation(btnPoint);
                point = newPoint;
                FlowPanel frame = this.support.getFrame(this.support.getParent());
                frame.repaint();
            }
        }
    }
}
