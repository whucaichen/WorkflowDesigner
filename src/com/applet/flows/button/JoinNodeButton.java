package com.applet.flows.button;

import com.applet.flows.INodeButton;
import com.applet.flows.NodeButtonSupport;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class JoinNodeButton extends NodeButtonSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 580723081129178147L;

	@Override
	public String getType() {
		// TODO 自动生成方法存根
		return "JOIN";
	}

	@Override
	protected void init() {
		this.setText("流程汇集");
	}
	

	@Override
	public ImageIcon getIcon() {
		// TODO 自动生成方法存根
		return new ImageIcon(this.getClass().getResource("../img/join.gif"));
	}

	@Override
	public boolean link(INodeButton button) {
		return true;
	}

	public boolean report(INodeButton button) {
		if(this.getChilds().size() < 1){
			return true;
		}
		JOptionPane.showMessageDialog(null, "汇集节点只能有一个出口","警告",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public boolean checkNode() throws Exception {
		if(this.getChilds().size() < 1 || this.getParents().size() < 1){
			throw new Exception("[ID=" + this.getID() + "][" + this.getText() + "]节点异常");
		}
		return true;
	}
}
