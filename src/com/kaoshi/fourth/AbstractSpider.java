package com.kaoshi.fourth;

import java.util.Map;


public abstract class AbstractSpider implements  Spider{

	public abstract Map begin(String url);
	
	
	
	public void start() {
		
	}
	
	
	
	public abstract Map parseHTML(String response);
	/**
	 * 数据持久化
	 * @return
	 */
	public abstract boolean dataPersistence(Map result);
	
	
	
}
