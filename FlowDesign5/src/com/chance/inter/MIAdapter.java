package com.chance.inter;

import com.chance.App;
import com.chance.ui.FlowLine;
import com.chance.utils.Attri;
import com.chance.utils.Properties;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseEvent;

/**
 * Created by Chance on 16/09/11.
 */
public final class MIAdapter extends MouseInputAdapter {

    public NodeBasic nodeBasic;
    public Point point = new Point(0, 0);

    public MIAdapter(NodeBasic nb) {
        this.nodeBasic = nb;
        this.nodeBasic.addMouseListener(this);
        this.nodeBasic.addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if(e.getClickCount() == 2){
                new Properties(nodeBasic).setVisible(true);
            }
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            for(IFlow iFlow : App.app.myPanel.collection){
                if(((NodeBasic)iFlow).parents.contains(this.nodeBasic)){
                    ((NodeBasic)iFlow).parents.remove(this.nodeBasic);
                }
                if(((NodeBasic)iFlow).childs.contains(this.nodeBasic)){
                    ((NodeBasic)iFlow).childs.remove(this.nodeBasic);
                }
            }
            App.app.myPanel.collection.remove(this.nodeBasic);
            App.app.myPanel.ID--;

            for(FlowLine flowLine : nodeBasic.relate){
                App.app.myPanel.remove(flowLine);
            }
            App.app.myPanel.remove(this.nodeBasic);
            App.app.myPanel.repaint();
//            Container nodeParent = nodeBasic.getParent();
//            nodeParent.remove(this.nodeBasic);
//            nodeParent.repaint();
//            nodeParent = null;
        }
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            JComponent com = (JComponent) e.getComponent();
            TransferHandler handler = new TransferHandler("this");
            com.setTransferHandler(handler);
            handler.exportAsDrag(com, e, DnDConstants.ACTION_COPY);
            System.out.println("export");
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            Component c = this.nodeBasic.getRootPane().getGlassPane();
            c.setVisible(true);
//            c.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//                    new ImageIcon("res/delete.png").getImage(), new Point(0, 0), "delete"));
//            URL url = this.getClass().getResource("../res/delete.png");
//            c.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//                    new ImageIcon(url).getImage(), new Point(0, 0), "delete"));
            c.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                    Attri.getImgRes("delete.png").getImage(), new Point(0, 0), "delete"));
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            this.point = SwingUtilities.convertPoint(this.nodeBasic, e.getPoint(), this.nodeBasic.getParent());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getModifiersEx() == 1024) {//只有左键才能拖动
            Point newPoint = SwingUtilities.convertPoint(this.nodeBasic, e.getPoint(), this.nodeBasic.getParent());
            Point btnPoint = new Point((this.nodeBasic.getX() + (newPoint.x - this.point.x)),
                    (this.nodeBasic.getY() + (newPoint.y - this.point.y)));
//            Point btnPoint = new Point(newPoint.x- this.nodeBasic.getWidth()/2, newPoint.y- this.nodeBasic.getHeight()/2);
//            this.nodeBasic.setLocation(newPoint);
            this.nodeBasic.setLocation(btnPoint);
            point = newPoint;
            this.nodeBasic.getParent().repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.HAND_CURSOR);
        this.nodeBasic.setToolTipText("");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.DEFAULT_CURSOR);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON2) {
            setCursor(Cursor.DEFAULT_CURSOR);
        }
    }

    public void setCursor(int cursor) {
        Component c = this.nodeBasic.getRootPane().getGlassPane();
        c.setVisible(true);
        c.setCursor(new Cursor(cursor));
    }
}
