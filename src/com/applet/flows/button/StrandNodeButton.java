package com.applet.flows.button;

import com.applet.flows.INodeButton;
import com.applet.flows.NodeButtonSupport;

import javax.swing.ImageIcon;

public class StrandNodeButton extends NodeButtonSupport {

    private static final long serialVersionUID = 2364235735483525990L;

    @Override
    public String getType() {
        return "NORMAL";
    }

    @Override
    protected void init() {
        this.setText("流程节点");
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(this.getClass().getResource("../img/node.gif"));
    }

    @Override
    public boolean link(INodeButton button) {
        if (this.getParents().size() < 1) {
            return true;
        }
        return false;
    }

    public boolean report(INodeButton button) {
        if (this.getChilds().size() < 1) {
            return true;
        }
        return false;
    }

    public boolean checkNode() throws Exception {
        if (this.getChilds().size() < 1 || this.getParents().size() < 1) {
            throw new Exception("[ID=" + this.getID() + "][" + this.getText() + "]节点异常");
        }
        return true;
    }
}
