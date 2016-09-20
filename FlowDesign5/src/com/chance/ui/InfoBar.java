package com.chance.ui;

import com.chance.App;
import com.chance.inter.IFlow;
import com.chance.utils.Attri;
import com.chance.utils.FlowExport;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

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
    public JButton save = new JButton("保存", Attri.getImgRes("save.png"));
    public JButton help = new JButton("帮助", Attri.getImgRes("help.png"));

    public InfoBar(App app) {
        this.app = app;
        setFloatable(false);//不可拖动
        setLayout(new GridLayout(1, 4));
//        action.setHorizontalAlignment(LEFT);
//        position.setHorizontalAlignment(CENTER);
        windowSize.setHorizontalAlignment(CENTER);

//        URL url = this.getClass().getResource("../res/save.png");
//        URL url2 = this.getClass().getResource("../res/help.png");
//        save.setIcon(new ImageIcon(url));
//        help.setIcon(new ImageIcon(url2));
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
            try {
                String xml = "";
                String node = "";
                String line = "";
                for (IFlow flow : App.app.myPanel.collection) {
                    flow.checkNode();
                    String[] xs = flow.asXML();
                    node += xs[0];
                    line += xs[1];
                }
                System.out.println(node);
                xml = "<NODES>" + node + "</NODES><LINES>" + line + "</LINES>";
//                xml = "<NODES>" + node + "</NODES>";
                xml = "<WORK_FLOW>" + xml + "</WORK_FLOW>";
                Document doc = new SAXReader().read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("utf-8");
                StringWriter sw = new StringWriter();
                XMLWriter writer = new XMLWriter(sw, format);
                writer.write(doc);
                FlowExport flowExport = new FlowExport();
                flowExport.setFlowInfo(sw.toString());
                flowExport.setVisible(true);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this.getRootPane(), e1.getLocalizedMessage(),
                        "生成XML发生错误", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getComponent() == help) {
            setActionInfo("打开“帮助”");
            JOptionPane.showMessageDialog(this.getRootPane(), Attri.APP_HELP,
                    "使用说明", JOptionPane.QUESTION_MESSAGE);
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

    public void setCursor(int cursor) {
        Component c = this.getRootPane().getGlassPane();
        c.setVisible(true);
        c.setCursor(new Cursor(cursor));
    }
}
