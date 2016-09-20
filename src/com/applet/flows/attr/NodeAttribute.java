package com.applet.flows.attr;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.applet.flows.NodeButtonSupport;

public class NodeAttribute extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3772388122490667022L;
	
	private String text = "";
	
	private JTextField textField = null;
	
	public NodeAttribute(final NodeButtonSupport node){
		this.text = node.getText();
		this.textField = new JTextField();
		this.textField.setText(this.text);
		this.textField.setSize(350,40);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400,400);
		this.add(this.textField);
		this.setLayout(null);
		final NodeAttribute frame = this;
		this.textField.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10){
					node.setText(textField.getText());
					node.setToolTipText(textField.getText());
					node.repaint();
					frame.setVisible(false);
				}
			}

			public void keyReleased(KeyEvent arg0) {
				
			}

			public void keyTyped(KeyEvent arg0) {
				
			}
			
			
		});
	}

}
