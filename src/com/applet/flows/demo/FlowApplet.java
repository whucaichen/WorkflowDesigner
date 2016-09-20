package com.applet.flows.demo;

import javax.swing.JApplet;

import com.applet.flows.ui.FlowPanel;

public class FlowApplet extends JApplet {

    private static final long serialVersionUID = -8857108586689425945L;

    public FlowApplet() {
        this.setLayout(null);
        FlowPanel panel = new FlowPanel();
        panel.setLayout(null);
        panel.setSize(1000, 500);
        panel.setLocation(0, 0);
        panel.init();
        this.add(panel);
    }
}
