package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;

/**
 * 发散节点，一个入口多个出口
 * Created by Chance on 16/09/07.
 */
public class NodeFork extends NodeBasic {

    @Override
    public String getType() {
        return Attri.NAME_FORK;
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
    public boolean checkNode() throws Exception {
        return false;
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon("res/fork-l.png");
    }

    @Override
    public String getText() {
        return Attri.NAME_FORK;
    }
}
