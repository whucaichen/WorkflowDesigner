package com.chance.ui;

import com.chance.App;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DragSource;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 操作信息提示，位置信息提示、帮助
 * Created by Chance on 16/09/07.
 */
public class InfoBar extends JToolBar implements MouseListener {

    public App app;

    public JLabel action = new JLabel("Action：设计器准备完毕");
    public JLabel windowSize = new JLabel("Window：X=-1 Y=-1");
    public JLabel position = new JLabel("Position：X=-1 Y=-1");
    public JPanel control = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    public JButton save = new JButton("保存", new ImageIcon("res/save.png"));
    public JButton help = new JButton("帮助", new ImageIcon("res/help.png"));

    public InfoBar(App app) {
        this.app = app;
        setFloatable(false);//不可拖动
        setLayout(new GridLayout(1, 4));
//        action.setHorizontalAlignment(LEFT);
//        position.setHorizontalAlignment(CENTER);
        windowSize.setHorizontalAlignment(CENTER);

        save.setToolTipText("生成XML流程文件");
        help.setToolTipText("查看软件使用说明");
        save.setForeground(Color.blue);
        help.setForeground(Color.blue);
        save.addMouseListener(this);
        help.addMouseListener(this);
        control.add(save);
        control.add(help);

        add(action);
        add(windowSize);
        add(position);
        add(control);
    }

    public void setActionInfo(String msg) {
        action.setText("Action：" + msg);
    }

    public void setWindowInfo(int x, int y) {
        windowSize.setText("Window：W=" + x + " H=" + y);
    }

    public void setPositionInfo(double x, double y) {
        position.setText("Position：X=" + x + " Y=" + y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() == save) {
            setActionInfo("保存流程到XML");
        } else if (e.getComponent() == help) {
            setActionInfo("打开“帮助”");
            JOptionPane.showMessageDialog(this.getRootPane(), Attri.APP_HELP,
                    "使用说明", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.HAND_CURSOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.DEFAULT_CURSOR);
    }

    public void setCursor(int cursor){
        Component c = this.getRootPane().getGlassPane();
        c.setVisible(true);
        c.setCursor(new Cursor(cursor));
    }
}
