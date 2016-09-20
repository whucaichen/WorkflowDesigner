package com.applet.flows.ui;

import javax.swing.JLabel;
import javax.swing.JToolBar;

public class InfoBar extends JToolBar {

	private static final long serialVersionUID = -2671054344893887335L;

	private JLabel label = null;
	
	public InfoBar(FlowPanel frame) {
		this.label = new JLabel();
//		this.setFloatable(false);
		this.setSize(frame.getWidth(),40);
		this.setLocation(0,100);
		this.add(label);
		this.setText("状态信息");
	}
	
	public void setText(String text){
		this.label.setText(text);
	}
}
