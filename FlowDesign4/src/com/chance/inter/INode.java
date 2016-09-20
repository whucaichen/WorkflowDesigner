package com.chance.inter;

import java.awt.dnd.DropTargetListener;
import java.util.Collection;

/**
 * 流程节点接口，用于处理流程的节点
 */
public interface INode extends DropTargetListener, IFlow {

    /**
     * 初始化节点对象
     */
    public void init(int id);

    /**
     * 通知对象你有某个对象拖入，是否允许他和你进行链接
     * @param button 发起链接的对象
     * @return 判断是否允许链接，返回true表示允许，false表示不允许操作
     */
    public boolean link(INode button);

    /**
     * 通知节点，你被拖动到某个节点上，需要你和他进行链接
     * @param button 需要链接的对象
     * @return 允许链接返回true，不允许链接返回false
     */
    public boolean report(INode button);

    /**
     * 获取对象ID
     */
    public int getID();

    /**
     * 添加一个父对象
     */
    public void addParent(INode nb);

    /**
     * 添加一个子对象
     */
    public void addChild(INode nb);

    /**
     * 获取所有子对象
     */
    public Collection<INode> getChilds();

    /**
     * 获取所有父对象
     */
    public Collection<INode> getParents();

}
