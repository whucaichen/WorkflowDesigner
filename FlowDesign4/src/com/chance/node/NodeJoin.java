package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;
import java.net.URL;

/**
 * 汇聚节点，多个入口一个出口
 * Created by Chance on 16/09/07.
 */
public class NodeJoin extends NodeBasic {

    @Override
    public String getType() {
        return Attri.NAME_JOIN;
    }

    @Override
    public boolean link(INode button) {
        return true;
    }

    @Override
    public boolean report(INode button) {
        if(this.getChilds().size() < 1){
            return true;
        }
        JOptionPane.showMessageDialog(null, "汇集节点只能有一个出口","警告",JOptionPane.ERROR_MESSAGE);
        return false;
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
//        return new ImageIcon("res/join-l.png");
//        URL url = this.getClass().getResource("../res/join-l.png");
//        return new ImageIcon(url);
        return Attri.getImgRes("join-l.png");
    }
}
