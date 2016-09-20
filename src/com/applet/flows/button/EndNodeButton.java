package com.applet.flows.button;

import com.applet.flows.INodeButton;
import com.applet.flows.NodeButtonSupport;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * @author 熊浩华 - xionghh
 *
 * 2011-3-11:上午11:47:54-
 */
public class EndNodeButton extends NodeButtonSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -772727430806830798L;
	@Override
	public String getType() {
		return "END";
	}

	@Override
	protected void init() {
		this.setText("结束");
	}
	
	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(this.getClass().getResource("../img/end.gif"));
	}
	
	@Override
	public boolean link(INodeButton button) {
		if(this.getParents().size() < 1){
			return true;
		}
		JOptionPane.showMessageDialog(null, "[结束节点]只允许接收一个节点","警告",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public boolean report(INodeButton button) {
		JOptionPane.showMessageDialog(null, "[结束节点]不允许成为任何节点的输入节点","警告",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public boolean checkNode() throws Exception {
		if(this.getParents().size() < 1){
			throw new Exception("终点节点无任何输入");
		}
		return true;
	}

}
