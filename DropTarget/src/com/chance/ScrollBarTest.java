package com.chance;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * JScrollPane优化示例
 *
 * @author SongFei
 * @date 2015年5月17日
 */
public class ScrollBarTest extends JFrame {

    private JPanel content;
    private JTree tree;
    private JScrollPane scrollPane;

    public ScrollBarTest() {
        setSize(300, 400);
        setTitle("course");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tree = new JTree(getRootNode());

        content = new JPanel();
        content.setLayout(new BorderLayout());

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(tree);
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        content.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(content);
    }

    /**
     * 创建root节点
     * @return
     */
    private DefaultMutableTreeNode getRootNode() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        root.add(new DefaultMutableTreeNode("node1"));
        root.add(new DefaultMutableTreeNode("node2"));
        root.add(new DefaultMutableTreeNode("node3"));
        root.add(new DefaultMutableTreeNode("node4"));
        root.add(new DefaultMutableTreeNode("node5"));
        root.add(new DefaultMutableTreeNode("node6"));
        root.add(new DefaultMutableTreeNode("node7"));
        root.add(new DefaultMutableTreeNode("node8"));
        root.add(new DefaultMutableTreeNode("node9"));
        root.add(new DefaultMutableTreeNode("node10"));
        root.add(new DefaultMutableTreeNode("node11"));
        root.add(new DefaultMutableTreeNode("node12"));
        root.add(new DefaultMutableTreeNode("node13"));
        root.add(new DefaultMutableTreeNode("node14"));
        root.add(new DefaultMutableTreeNode("node15"));
        root.add(new DefaultMutableTreeNode("node16"));
        root.add(new DefaultMutableTreeNode("node17"));
        root.add(new DefaultMutableTreeNode("node18"));
        root.add(new DefaultMutableTreeNode("node19"));
        root.add(new DefaultMutableTreeNode("node20"));
        return root;
    }

    /**
     * 获取图片
     * @param name 图片名称
     * @return
     */
    private ImageIcon produceImage(String name) {
        ImageIcon backImage = new ImageIcon(getClass().getClassLoader().getResource(name));
        return backImage;
    }

    public static void main(String[] args) {
        ScrollBarTest demo1 = new ScrollBarTest();
        demo1.setVisible(true);
        demo1.setLocationRelativeTo(null);
    }

}