package com.applet.flows.button;

import com.applet.flows.INodeButton;
import com.applet.flows.NodeButtonSupport;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class StartNodeButton extends NodeButtonSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8953234109468766500L;
	
	
	@Override
	public String getType() {
		return "START";
	}

	@Override
	public boolean link(INodeButton button) {
		JOptionPane.showMessageDialog(null, "无法将〔流程起点〕设为接收方","警告",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	@Override
	protected void init() {
		this.setText("开始");
	}
	

	@Override
	public ImageIcon getIcon() {
		// TODO 自动生成方法存根
		return new ImageIcon(this.getClass().getResource("../img/start.gif"));
	}

	public boolean report(INodeButton button) {
		if(this.getChilds().size() < 1){
			return true;
		}
		JOptionPane.showMessageDialog(null, "〔流程起点〕只能有一个接收方","警告",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public boolean checkNode() throws Exception {
		if(this.getChilds().size() < 1){
			throw new Exception("起始节点不存在输入");
		}
		return true;
	}
}
