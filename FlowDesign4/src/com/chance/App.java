package com.chance;

import com.chance.ui.FlowBar;
import com.chance.ui.InfoBar;
import com.chance.ui.MyPanel;
import com.chance.ui.WorkPane;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.net.URL;
import java.util.Enumeration;

/**
 * 流程设计器入口程序
 * Created by Chance on 16/09/07.
 */
public class App extends JFrame implements ComponentListener {
    public static App app;

    public static void main(String[] args) throws Exception {
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
//        for (int i = 0; i < info.length; i++) {
//            System.out.println(info[i].getClassName());
//        }
        initUIStyle();
        app = new App();
        app.setVisible(true);
    }

    public FlowBar flowBar;
    public InfoBar infoBar;
    public WorkPane workPane;
    public MyPanel myPanel;

    public App() throws HeadlessException {
        setTitle(Attri.APP_TITLE);
        setSize(1000, 700);
//        setPreferredSize(new Dimension(1000, 700));
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
//        ImageIcon imageIcon = new ImageIcon("res/zjft.png");
//        setIconImage(imageIcon.getImage());
//        URL url = this.getClass().getResource("res/zjft.png");
//        setIconImage(new ImageIcon(url).getImage());
        setIconImage(Attri.getImgRes("zjft.png").getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        flowBar = new FlowBar(this);
        infoBar = new InfoBar(this);
        myPanel = new MyPanel(this);
        workPane = new WorkPane(myPanel, app);

        add(flowBar, BorderLayout.PAGE_START);
        add(workPane, BorderLayout.CENTER);
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
//            if (key instanceof String) {
//                if (((String) key).endsWith(".background")) {
//                    UIManager.put(key, Color.white);
//                }
//            }
            if (value instanceof Font) {
                UIManager.put(key, new Font("微软雅黑", Font.PLAIN, 14));
            }
        }
    }
}
