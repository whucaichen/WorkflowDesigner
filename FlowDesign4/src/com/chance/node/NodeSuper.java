package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;
import java.net.URL;

/**
 * 普通流程节点，多个入口多个出口
 * Created by Chance on 16/09/07.
 */
public class NodeSuper extends NodeBasic {

    @Override
    public String getType() {
        return Attri.NAME_SUPER;
    }

    @Override
    public boolean link(INode button) {
        return true;
    }

    @Override
    public boolean report(INode button) {
        return true;
    }

    @Override
    public boolean checkNode() throws Exception {
        if(this.getChilds().size() < 2 || this.getParents().size() < 2){
            throw new Exception("[ID=" + this.getID() + "][" + this.getText() + "]节点异常");
        }
        return true;
    }

    @Override
    public Icon getIcon() {
//        return new ImageIcon("res/super-l.png");
//        URL url = this.getClass().getResource("../res/super-l.png");
//        return new ImageIcon(url);
        return Attri.getImgRes("super-l.png");
    }
}
