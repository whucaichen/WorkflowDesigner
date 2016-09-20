package com.chance;

import com.chance.ui.FlowBar;
import com.chance.ui.FlowPanel;
import com.chance.ui.InfoBar;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 流程设计器入口程序
 * Created by Chance on 16/09/07.
 */
public class App extends JFrame implements ComponentListener {
    public static App app;

    public static void main(String[] args) throws IOException {
        initUIStyle();
        app = new App();
        app.setVisible(true);
    }

    public static FlowBar flowBar = new FlowBar();
    public static FlowPanel flowPanel = new FlowPanel();
    public static InfoBar infoBar = new InfoBar();

    public App() throws HeadlessException {
        setTitle(Attri.APP_TITLE);
//        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        ImageIcon imageIcon = new ImageIcon("res/zjft.png");
        setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(flowBar, BorderLayout.PAGE_START);
        add(flowPanel, BorderLayout.CENTER);
        add(infoBar, BorderLayout.PAGE_END);

        addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        infoBar.setWindowInfo(app.getWidth(), app.getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

    public static void initUIStyle() {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        Object key = null;
        Object value = null;
        while (keys.hasMoreElements()) {
            key = keys.nextElement();
            value = UIManager.get(key);
            //设置全局的背景色
//            if (key instanceof String) {
//                if (((String) key).endsWith(".background")) {
//                    UIManager.put(key, Color.white);
//                }
//            }
            //设置全局的字体
            if (value instanceof Font) {
                UIManager.put(key, new Font("微软雅黑", Font.BOLD, 13));
            }
        }
    }
}
