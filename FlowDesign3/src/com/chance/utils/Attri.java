package com.chance.utils;

/**
 * 属性常量表
 * ???通过配置文件设置
 * Created by Chance on 16/09/07.
 */
public class Attri {

    public final static String APP_TITLE = "流程设计器";
    public final static String APP_HELP =
            "1) 点击或拖动工具栏按钮添加节点；\n" +
            "2) 左键拖动工作空间节点调整布局；\n" +
            "3) 右键拖动工作空间节点进行连线；\n" +
            "4) 左键双击工作空间节点设置属性；\n" +
            "5) 中间点击工作空间节点将其删除；\n" +
            "6) 点击保存按钮生成XML；";

    /**
     * 流程节点名
     */
    public final static String NAME_START = "开始节点";
    public final static String NAME_STOP = "结束节点";
    public final static String NAME_NORMAL = "普通节点";
    public final static String NAME_FORK = "发散节点";
    public final static String NAME_JOIN = "汇聚节点";
    public final static String NAME_SUPER = "超级节点";
    public final static String Name_CUSTOM = "扩展节点";

    public final static String NODE_ID = "";
    public final static String NODE_ACTION = "";
    public final static String NODE_NEXT = "";

    /**
     * 输出的XML属性字段
     */
    public final static String XML_NAME_START = "";
    public final static String XML_NAME_STOP = "";
    public final static String XML_NAME_NORMAL = "";
    public final static String XML_NAME_FORK = "";
    public final static String XML_NAME_JOIN = "";
    public final static String XML_NAME_SUPER = "";
    public final static String XML_NAME_CUSTOM = "";

    public final static String XML_NODE_ID = "";
    public final static String XML_NODE_ACTION = "";
    public final static String XML_NODE_NEXT = "";
}
