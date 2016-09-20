package com.chance.utils;

import com.chance.App;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 流程节点属性设置
 * 节点ID、名称、脚本、输出通路
 * Created by Chance on 16/09/07.
 */
public class Properties extends JDialog {

    public JTextField jtf_name = new JTextField("NODE_NAME");
    public JTextField jtf_action = new JTextField("DO_ACTION");

    public JButton jbt_confirm = new JButton("确 定");
    public JButton jbt_concel = new JButton("取 消");

    public Properties() {
        setTitle("流程节点设置");
        setSize(200, 120);
        setResizable(false);
        setLocationRelativeTo(App.app);
        setModal(true);
        ImageIcon imageIcon = new ImageIcon("res/zjft.png");
        setIconImage(imageIcon.getImage());
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        JLabel jlb_name = new JLabel("节点名：");
        JLabel jlb_action = new JLabel("脚本名：");

        JPanel jpn_name = new JPanel();
        jpn_name.setLayout(new BoxLayout(jpn_name, BoxLayout.X_AXIS));
        jpn_name.add(jlb_name);
        jpn_name.add(jtf_name);

        JPanel jpn_action = new JPanel();
        jpn_action.setLayout(new BoxLayout(jpn_action, BoxLayout.X_AXIS));
        jpn_action.add(jlb_action);
        jpn_action.add(jtf_action);

        JPanel jpn_control = new JPanel();
        jpn_control.setLayout(new GridLayout(1, 2));
        jpn_control.add(jbt_confirm);
        jpn_control.add(jbt_concel);

        this.add(jpn_name);
        this.add(jpn_action);
        this.add(jpn_control);
    }
}
