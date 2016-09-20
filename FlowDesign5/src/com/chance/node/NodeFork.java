package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;

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
        if(this.getParents().size() < 1){
            return true;
        }
        JOptionPane.showMessageDialog(null, "〔流程分散〕只允许存在一个接入点","警告",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean report(INode button) {
        return true;
    }

    @Override
    public boolean checkNode() throws Exception {
        if(this.getChilds().size() < 1 || this.getParents().size() < 1){
            throw new Exception("[ID=" + this.getID() + "][" + this.getText() + "]节点异常");
        }
        return true;
    }

    @Override
    public Icon getIcon() {
//        return new ImageIcon("res/fork-l.png");
//        URL url = this.getClass().getResource("../res/fork-l.png");
//        return new ImageIcon(img);
        return Attri.getImgRes("fork-l.png");
    }
}
