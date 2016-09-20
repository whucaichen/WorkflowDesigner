package com.chance.inter;

/**
 * 基本XML流程对象
 */
public interface IFlow {

	/**
	 * 返回该对象的XML分为{节点，连接线}
	 * @return
	 */
	public String[] asXML();
	
	/**
	 * 判断节点是否是合法
	 * @return 当节点存在异常时返回false并抛出节点异常信息
	 */
	public boolean checkNode() throws Exception;
	
}
