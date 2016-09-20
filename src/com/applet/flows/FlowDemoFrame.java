package com.applet.flows;

import com.applet.flows.ui.FlowPanel;

import javax.swing.*;

/**
 * 主程序启动入口
 */
public class FlowDemoFrame extends JFrame {

    private static final long serialVersionUID = -690856732128936770L;

    public FlowDemoFrame() {
        this.setTitle("工作流设计器");
//        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        FlowPanel panel = new FlowPanel();
        panel.setLayout(null);
        panel.setLocation(0, 0);
        panel.setSize(this.getSize());
        panel.init();
        this.add(panel);
        panel.setText("Width:" + this.getWidth() + " Height:" + this.getHeight());
    }

    public static void main(String[] arg) {
        new FlowDemoFrame().setVisible(true);
    }
}
