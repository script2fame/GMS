package com.hungteshun.gms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 采用单例模式读取exam-config.properties配置文件
 * @author hungteshun
 *
 */
public class ExamConfigReader {

	private static ExamConfigReader instance = new ExamConfigReader();
	
	private Properties props = new Properties();
	
	//此构造方法只调用一次
	private ExamConfigReader() {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("gms-config.properties");
		try {
			//将文件输入流放到Properties类
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ExamConfigReader getInstance() {
		return instance;
	}
	
	/**
	 * 根据属性名称得到属性值
	 * @param key
	 * @return
	 */
	public String getPropertyValue(String key) {
		return props.getProperty(key);
	}
	
	public static void main(String[] args) {
		String driverName = ExamConfigReader.getInstance().getPropertyValue("dirver-name");
		String url = ExamConfigReader.getInstance().getPropertyValue("url");
		System.out.println(driverName);
		System.out.println(url);
	}
}
