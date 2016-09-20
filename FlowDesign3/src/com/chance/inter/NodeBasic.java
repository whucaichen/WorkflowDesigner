package com.chance.inter;

import com.chance.App;
import com.chance.ui.FlowLine;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
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
        this.setSize(130, 50);
        this.setHorizontalAlignment(LEFT);
//        this.setPreferredSize(new Dimension(130, 50));
        this.dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY, this);
//        dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY, this, true);
        new MIAdapter(this);
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

    public INode getThis() {
        return this;
    }

    //拖拽DropTarget
    @Override
    public final void drop(DropTargetDropEvent dtde) {
        System.out.println("drop");
        DataFlavor ob = dtde.getCurrentDataFlavors()[0];
        INode db = null;
        try {
            db = (INode) dtde.getTransferable().getTransferData(ob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.addParent(db);
        db.addChild(this);
        FlowLine line = new FlowLine(new JButton[]{(JButton) db, this});
        line.setLocation(0, 0);
//        line.setSize(App.app.myPanel.getSize());
//        App.app.myPanel.add(line);
//        App.app.myPanel.repaint();
        Container nodeParent = this.getParent();
        line.setSize(nodeParent.getSize());
        nodeParent.add(line);
        App.app.infoBar.setActionInfo("“" + this.getText() + "”连线成功");
        nodeParent.repaint();
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
//        return new ImageIcon("res/node.png");
        return null;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
     public void setToolTipText(String str) {
//        super.setToolTipText(this.getText()+
//                "\n左键设置属性"+
//                "\n中间点击删除"+
//                "\n右键拖拉连线"+
//                "\n左键移动布局");
        super.setToolTipText("<html>" +
                this.getText()+ "<br>" +
                "1.左键拖动布局<br>" +
                "2.中键点击删除<br>" +
                "3.右键拖拉连线<br>" +
                "4.双击设置属性" +
                "</html>");
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
    public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }
}
