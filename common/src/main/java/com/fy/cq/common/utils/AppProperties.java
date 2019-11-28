package com.fy.cq.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class AppProperties {
	private static Properties pro;
	static{
		pro=getProper();
	}
	public static Properties getProper(){
		Properties properties = new Properties();
	    // 使用ClassLoader加载properties配置文件生成对应的输入流
	    InputStream in = AppProperties.class.getClassLoader().getResourceAsStream("app.properties");
	    // 使用properties对象加载输入流
	    try {
			properties.load(new InputStreamReader(in,"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    //获取key对应的value值
	    return properties;
	}
	public static String getValue(String key){
		if(pro!=null){
			return pro.getProperty(key);
		}else{
			pro=getProper();
			return pro.getProperty(key);
		}
	}
	private AppProperties (){
		super();
	}
	public static void main(String[] args) {
		String s=AppProperties.getValue("aaa");
		System.out.println(s);

	}
}
