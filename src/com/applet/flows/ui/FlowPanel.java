package com.applet.flows.ui;

import javax.swing.JPanel;

import com.applet.flows.ui.FlowBar;

/**
 * 工具面板和工作面板
 */
public class FlowPanel extends JPanel {

    private static final long serialVersionUID = 5361892868757568929L;

    private FlowBar infoBar = null;

    public void init() {
        this.infoBar = new FlowBar(this);
    }

    public void setText(String text) {
        this.infoBar.setText(text);
    }
}
