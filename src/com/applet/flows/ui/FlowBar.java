package com.applet.flows.ui;

import com.applet.flows.*;
import com.applet.flows.button.*;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Vector;

/**
 * 工具面板
 */
public class FlowBar extends JToolBar {

	private static final long serialVersionUID = 3594121944485761637L;
	
	private int ID = 1;
	
	private Collection<IFlow> collection = null;

	private FlowPanel jframe = null;
	
	private JLabel label = null;
	
	public FlowBar(FlowPanel frame) {
		this.collection = new Vector<IFlow>();
		this.setSize(frame.getWidth(),40);
		this.setFloatable(false);
		this.setLocation(0, 0);
		this.jframe = frame;
		this.add(new BarButton(StartNodeButton.class,"起始节点"));
		this.add(new BarButton(EndNodeButton.class,"结束节点"));
		this.add(new BarButton(StrandNodeButton.class,"标准节点"));
		this.add(new BarButton(ForkNodeButton.class,"分散节点"));
		this.add(new BarButton(JoinNodeButton.class,"会聚节点"));
		this.addSeparator();
		this.add(new WriterButton());
		this.add(new HelpButton());
		this.addSeparator();
		this.label = new JLabel();
		this.add(this.label,JToolBar.LEFT_ALIGNMENT);
//		this.add(new CloseButton());
		this.jframe.add(this);
		this.label.setText("设计器准备完毕");
	}
	
	public void setText(String str){
		this.label.setText(str);
		this.label.repaint();
	}
	
	public class HelpButton extends JButton implements MouseListener{

		public HelpButton() {
			this.setText("帮助");
			this.setIcon(new ImageIcon(NodeButtonSupport.class.getResource("./img/help.gif")));
			this.addMouseListener(this);
		}
		
		public void mouseClicked(MouseEvent arg0) {
			JOptionPane.showMessageDialog(null,"1) 点击工具类按钮添加节点\n2) 左键拖动节点进行布局\n3) 右键拖动一个节点至另一节点进行连线\n4) 点击保存按钮生成XML" ,"帮助",JOptionPane.PLAIN_MESSAGE);
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	public class WriterButton extends JButton implements MouseListener{
		private static final long serialVersionUID = -14905681660248362L;

		public WriterButton(){
			this.setText("保存");
			this.setIcon(new ImageIcon(NodeButtonSupport.class.getResource("./img/save.gif")));
			this.addMouseListener(this);
		}
		
		public void mouseClicked(MouseEvent e) {
			try {
				String xml = "";
				String node = "";
				String line = "";
				for(IFlow flow : collection){
					flow.checkNode();
					String[] xs = flow.asXML();
					node += xs[0];
					line += xs[1];
				}
				xml = "<NODE>" + node + "</NODE><LINE>" + line + "</LINE>";
				xml = "<FYBY_WORK_FLOW>" + xml + "</FYBY_WORK_FLOW>";
				Document doc = new SAXReader().read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("utf-8");
				StringWriter sw = new StringWriter();
				XMLWriter writer = new XMLWriter(sw,format);
				writer.write(doc);
				JOptionPane.showMessageDialog(null,sw.toString() ,"生成XML",JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,e1.getLocalizedMessage() ,"生成XML发生错误",JOptionPane.ERROR_MESSAGE);
			}
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	public class CloseButton extends JButton implements MouseListener{

		public CloseButton() {
			this.setText("关闭");
			this.setIcon(new ImageIcon(NodeButtonSupport.class.getResource("close.gif")));
			this.addMouseListener(this);
		}
		
		public void mouseClicked(MouseEvent e) {
			jframe = null;
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	public class BarButton extends JButton implements MouseListener{
		private static final long serialVersionUID = -8810893399231635650L;
		private Class clazz = null;
		
		public BarButton(Class clazz,String text){
			this.clazz = clazz;
			this.setText(text);
			this.addMouseListener(this);
			try {
				Object ob = this.clazz.newInstance();
				if(ob instanceof NodeButtonSupport){
					Icon icon = ((NodeButtonSupport)ob).getIcon();
					if(icon != null){
						this.setIcon(icon);
					}
				}
				ob = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void mouseClicked(MouseEvent e) {
			try {
				Object ob = this.clazz.newInstance();
				if(ob instanceof INodeButton){
					INodeButton nb = (INodeButton)ob;
					nb.init(ID++);
					jframe.add((JButton)nb);
					jframe.setText("创建[" + ((JButton)nb).getText() + "]");
					collection.add(nb);
					jframe.repaint();
				}else{
					System.out.println("INodeButton Only");
				}
			}catch( Exception e1) {
				e1.printStackTrace();
			}
		}

		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
}
