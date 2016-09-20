package com.chance.node;

import com.chance.inter.NodeBasic;
import com.chance.inter.INode;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.util.Collection;

/**
 * 普通流程节点，有且仅有一个入口一个出口
 * Created by Chance on 16/09/07.
 */
public class NodeNormal extends NodeBasic {
    @Override
    public String getType() {
        return Attri.NAME_NORMAL;
    }

    @Override
    public boolean link(INode button) {
        return false;
    }

    @Override
    public boolean report(INode button) {
        return false;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {

    }

    @Override
    public boolean checkNode() throws Exception {
        return false;
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon("res/node.gif");
    }

    @Override
    public String getText() {
        return Attri.NAME_NORMAL;
    }
}
