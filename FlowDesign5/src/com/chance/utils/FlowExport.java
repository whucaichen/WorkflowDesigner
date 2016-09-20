package com.chance.utils;

import com.chance.App;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 生成XML流程文件
 * Created by Chance on 16/09/07.
 */
public class FlowExport extends JDialog {

    public JTextArea jta_flow = new JTextArea("流程输出");

    public JButton jbt_copy = new JButton("复制流程");
    public JButton jbt_close = new JButton("关闭对话");

    public FlowExport() {
        setTitle("流程节点设置");
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(App.app);
        setModal(true);
        ImageIcon imageIcon = new ImageIcon("res/zjft.png");
        setIconImage(imageIcon.getImage());
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        jta_flow.setEditable(false);
        jta_flow.setBackground(this.getBackground());
        JScrollPane jsp_flow = new JScrollPane(jta_flow);
        jsp_flow.setPreferredSize(new Dimension(600, 480));
        jsp_flow.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp_flow.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        jsp_flow.add(jta_flow);

        JPanel jpn_control = new JPanel();
        jpn_control.setLayout(new FlowLayout(FlowLayout.RIGHT));
        jbt_copy.setPreferredSize(new Dimension(90, 30));
        jbt_close.setPreferredSize(new Dimension(90, 30));
        jbt_copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlowExport.this.dispose();
                setSysClipboardText(getFlowInfo());
                JOptionPane.showMessageDialog(App.app, "已复制流程文件到剪切板", "信息提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jbt_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlowExport.this.dispose();
            }
        });
        jpn_control.add(jbt_copy);
        jpn_control.add(jbt_close);

        this.add(jsp_flow);
        this.add(jpn_control);
    }

    public void setFlowInfo(String flowInfo){
        this.jta_flow.setText(flowInfo);
    }

    public String getFlowInfo(){
        return this.jta_flow.getText();
    }

    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }
}
