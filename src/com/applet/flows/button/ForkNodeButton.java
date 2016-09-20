package com.applet.flows.button;

import com.applet.flows.INodeButton;
import com.applet.flows.NodeButtonSupport;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author 熊浩华 - xionghh
 *
 * 2011-3-11:上午11:48:04-
 */
public class ForkNodeButton extends NodeButtonSupport {

	@Override
	public String getType() {
		// TODO 自动生成方法存根
		return "FORK";
	}

	@Override
	protected void init() {
		this.setText("流程分散");
	}
	
	@Override
	public ImageIcon getIcon() {
		// TODO 自动生成方法存根
		return new ImageIcon(this.getClass().getResource("../img/fork.gif"));
	}

	@Override
	public boolean link(INodeButton button) {
		if(this.getParents().size() < 1){
			return true;
		}
		JOptionPane.showMessageDialog(null, "〔流程分散〕只允许存在一个接入点","警告",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public boolean report(INodeButton button) {
		return true;
	}

	public boolean checkNode() throws Exception {
		if(this.getChilds().size() < 1 || this.getParents().size() < 1){
			throw new Exception("[ID=" + this.getID() + "][" + this.getText() + "]节点异常");
		}
		return true;
	}

}
