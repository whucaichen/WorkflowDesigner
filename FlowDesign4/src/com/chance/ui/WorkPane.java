package com.chance.ui;

import com.chance.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * 流程设计工作面板
 * ???添加其他内容（标尺，标签，Logo）
 * CardLayout
 * Created by Chance on 16/09/07.
 */
public class WorkPane extends JScrollPane implements MouseMotionListener {

    public App app;

    public WorkPane(Component view, App app) {
        super(view);
        this.app = app;

        //默认自动？
//        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        app.infoBar.setPositionInfo(e.getPoint().getX(), e.getPoint().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        app.infoBar.setPositionInfo(e.getPoint().getX(), e.getPoint().getY());
    }
}
