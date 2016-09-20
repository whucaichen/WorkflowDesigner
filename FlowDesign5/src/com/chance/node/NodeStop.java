package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;

/**
 * 结束节点，只有入口没有出口
 * Created by Chance on 16/09/07.
 */
public class NodeStop extends NodeBasic {

    @Override
    public String getType() {
        return Attri.NAME_STOP;
    }

    @Override
    public boolean link(INode button) {
        if(this.getParents().size() < 1){
            return true;
        }
        JOptionPane.showMessageDialog(null, "[结束节点]只允许接收一个节点","警告",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean report(INode button) {
        JOptionPane.showMessageDialog(null, "[结束节点]不允许成为任何节点的输入节点","警告",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean checkNode() throws Exception {
        if(this.getParents().size() < 1){
            throw new Exception("终点节点无任何输入");
        }
        return true;
    }

    @Override
    public Icon getIcon() {
//        return new ImageIcon("res/stop-l.png");
//        URL url = this.getClass().getResource("../res/stop-l.png");
//        return new ImageIcon(url);
        return Attri.getImgRes("stop-l.png");
    }
}
