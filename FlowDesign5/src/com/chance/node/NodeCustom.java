package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;

/**
 * 删除已添加的节点
 * Created by Chance on 16/09/07.
 */
public class NodeCustom extends NodeBasic {

    @Override
    public String getType() {
        return Attri.Name_CUSTOM;
    }

    @Override
    public boolean link(INode button) {
        if(this.getParents().size() < 3){
            return true;
        }
        JOptionPane.showMessageDialog(null, "此节点设置只允许最多3个接入点","警告",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean report(INode button) {
        if(this.getChilds().size() < 3){
            return true;
        }
        JOptionPane.showMessageDialog(null, "此节点设置只允许最多3个连出点","警告",JOptionPane.ERROR_MESSAGE);
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
//        return new ImageIcon("res/custom-l.png");
//        URL url = this.getClass().getResource("../res/custom-l.png");
//        return new ImageIcon(url);
        return Attri.getImgRes("custom-l.png");
    }
}
