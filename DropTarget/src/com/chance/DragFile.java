package com.chance;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.*;

/**
 * Created by Chance on 16/09/07.
 */
public class DragFile extends JFrame {

    JPanel panel;//要接受拖拽的面板

    public DragFile() {
        panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        getContentPane().add(panel, BorderLayout.CENTER);
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 200);
        setTitle("最简单的拖拽示例：拖拽文件到下面");
        drag();//启用拖拽
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");//设置皮肤
        new DragFile().setVisible(true);
        ;
    }

    public void drag()//定义的拖拽方法
    {
        //panel表示要接受拖拽的控件
        new DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde)//重写适配器的drop方法
            {
                try {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//如果拖入的文件格式受支持
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接收拖拽来的数据
                        List<File> list = (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                        String temp = "";
                        for (File file : list)
                            temp += file.getAbsolutePath() + ";\n";
                        JOptionPane.showMessageDialog(null, temp);
                        dtde.dropComplete(true);//指示拖拽操作已完成
                    } else {
                        dtde.rejectDrop();//否则拒绝拖拽来的数据
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
