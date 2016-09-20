package com.chance;

import javax.swing.*;
import java.awt.event.*;

public class MouseTest extends JFrame {

    public static void main(String[] args) {
        MouseTest t = new MouseTest();
    }

    public MouseTest() {
        setSize(300, 300);
        this.getContentPane().addMouseListener(new mouseProcassor());
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public class mouseProcassor extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            String outStr = "";
            if (e.getButton() == e.BUTTON1) {
                outStr = "左键";
            } else if (e.getButton() == e.BUTTON3) {
                outStr = "右键";
            } else {
                outStr = "中键";
            }
            if (e.getClickCount() == 2) {
                outStr = outStr + "双击";
            } else {
                outStr = outStr + "点击";
            }
            System.out.println(outStr);
        }
    }
}