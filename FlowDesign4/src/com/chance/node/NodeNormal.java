package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;
import java.net.URL;

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
        if (this.getParents().size() < 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean report(INode button) {
        if (this.getChilds().size() < 1) {
            return true;
        }
        return false;
    }
    @Override
    public boolean checkNode() throws Exception {
        if (this.getChilds().size() < 1 || this.getParents().size() < 1) {
            throw new Exception("[ID=" + this.getID() + "][" + this.getText() + "]节点异常");
        }
        return true;
    }

    @Override
    public Icon getIcon() {
//        return new ImageIcon("res/node-l.png");
//        URL url = this.getClass().getResource("../res/node-l.png");
//        return new ImageIcon(url);
        return Attri.getImgRes("node-l.png");
    }
}
