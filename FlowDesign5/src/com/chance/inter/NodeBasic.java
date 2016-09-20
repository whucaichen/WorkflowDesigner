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
    public String action = "执行脚本";
    public Collection<INode> childs = new Vector<INode>();
    public Collection<INode> parents = new Vector<INode>();
    public Collection<FlowLine> relate = new Vector<FlowLine>();

    public DropTarget dropTarget;

    public NodeBasic() {
        this.setSize(130, 50);
        this.setHorizontalAlignment(LEFT);
//        this.setPreferredSize(new Dimension(130, 50));
        this.dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY, this);
//        dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY, this, true);
        new MIAdapter(this);
    }

    @Override
    public final void init(int id) {
        this.id = id;
        this.setIcon(getIcon());
        this.setText(getType() + id);
        this.setActionInfo(getActionInfo() + id);
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public int getID() {
        return this.id;
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
        String to = "";
        for (INode nb : this.getChilds()) {
            to += nb.getID() + ",";
        }
//        xml = "<Node id=\"" + this.getID() + "\" type=\"" + this.getType() + "\" name=\"" + this.getText() +
//                "\" action=\"" + this.getActionInfo() + "\" next=\"" + to.substring(0, to.length() - 1) + "\" />";
        xml = "<Node id=\"" + this.getID() + "\" type=\"" + this.getType() + "\" name=\"" + this.getText() +
                "\" action=\"" + this.getActionInfo() + "\" />";
        String line = "";
        if (!to.trim().equals("")) {
            line = "<Line from=\"" + this.getID() + "\" to=\"" + to.substring(0, to.length() - 1) + "\" />";
        }
        return new String[]{xml, line};
//        return new String[]{xml};
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
//            System.out.println(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isPathed(db)) {
            return;
        }
        System.out.println(db.getParents().size() + " - " + this.getChilds().size());
        if (db.report(this) && this.link(db)) {
            System.out.println("link");
            this.addParent(db);
            db.addChild(this);
            FlowLine line = new FlowLine(new JButton[]{(JButton) db, this});
            line.setLocation(0, 0);
            line.setSize(App.app.myPanel.getSize());
            this.relate.add(line);
            ((NodeBasic)db).relate.add(line);
            App.app.myPanel.add(line);
            App.app.myPanel.repaint();
//            Container nodeParent = this.getParent();
//            line.setSize(nodeParent.getSize());
//            nodeParent.add(line);
//            nodeParent.repaint();
            App.app.infoBar.setActionInfo("“" + this.getText() + "”连线成功");
        }
    }

    //获取节点类型，由具体实现类生成
    public abstract String getType();

    public String getActionInfo() {
        return this.action;
    }

    public void setActionInfo(String action) {
        this.action = action;
    }

    @Override
    public void setToolTipText(String str) {
        super.setToolTipText("<html>" +
                this.getText() + "<br>" +
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
