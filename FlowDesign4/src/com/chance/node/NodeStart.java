package com.chance.node;

import com.chance.inter.INode;
import com.chance.inter.NodeBasic;
import com.chance.utils.Attri;

import javax.swing.*;
import java.net.URL;

/**
 * 开始节点，只有出口没有入口
 * Created by Chance on 16/09/07.
 */
public class NodeStart extends NodeBasic {

    @Override
    public String getType() {
        return Attri.NAME_START;
    }

    @Override
    public boolean link(INode button) {
        JOptionPane.showMessageDialog(null, "无法将〔流程起点〕设为接收方","警告",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean report(INode button) {
        if(this.getChilds().size() < 1){
            return true;
        }
        JOptionPane.showMessageDialog(null, "〔流程起点〕只能有一个接收方","警告",JOptionPane.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean checkNode() throws Exception {
        if(this.getChilds().size() < 1){
            throw new Exception("起始节点不存在输入");
        }
        return true;
    }

    @Override
    public Icon getIcon() {
//        return new ImageIcon("res/start-l.png");
//        URL url = this.getClass().getResource("../res/start-l.png");
//        return new ImageIcon(url);
        return Attri.getImgRes("start-l.png");
    }
}
