package com.chance.ui;

import com.chance.App;
import com.chance.utils.Attri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 操作信息提示，位置信息提示、帮助
 * Created by Chance on 16/09/07.
 */
public class InfoBar extends JToolBar implements MouseListener {

    public App app;

    public JLabel operation = new JLabel("状态：设计器准备完毕");
    public JLabel windowSize = new JLabel("尺寸：X:-1 Y:-1");
    public JLabel position = new JLabel("位置：X:-1 Y:-1");
    public JPanel control = new JPanel();
    public JLabel save = new JLabel("保存");
    public JLabel help = new JLabel("帮助");

    public InfoBar(App app) {
        this.app = app;
        setFloatable(false);//不可拖动
        setLayout(new GridLayout(1, 4));
//        operation.setHorizontalAlignment(LEFT);
//        position.setHorizontalAlignment(CENTER);

        save.addMouseListener(this);
        help.addMouseListener(this);
        save.setForeground(Color.blue);
        help.setForeground(Color.blue);
        save.setIcon(new ImageIcon("res/save.gif"));
        help.setIcon(new ImageIcon("res/help.gif"));
        control.setLayout(new FlowLayout(FlowLayout.RIGHT));
        control.add(save);
        control.add(help);

        add(operation);
        add(windowSize);
        add(position);
        add(control);
    }

    public void setOperation(String msg) {
        operation.setText("状态：" + msg);
    }

    public void setWindowInfo(int x, int y) {
        windowSize.setText("尺寸：W:" + x + " H:" + y);
    }

    public void setPositionInfo(double x, double y) {
        position.setText("位置：X:" + x + " Y:" + y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent() == save) {
            setOperation("保存流程到XML");
        } else if (e.getComponent() == help) {
            setOperation("打开“帮助”");
            JOptionPane.showMessageDialog(this.getRootPane(), Attri.APP_HELP,
                    "使用说明", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getComponent() == save) {
            save.setForeground(Color.blue);
        } else if (e.getComponent() == help) {
            help.setForeground(Color.blue);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getComponent() == save) {
            save.setForeground(Color.cyan);
        } else if (e.getComponent() == help) {
            help.setForeground(Color.cyan);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getComponent() == save) {
            save.setForeground(Color.blue);
        } else if (e.getComponent() == help) {
            help.setForeground(Color.blue);
        }
    }
}
