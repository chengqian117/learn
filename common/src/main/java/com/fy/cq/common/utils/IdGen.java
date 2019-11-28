/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.fy.cq.common.utils;

import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author liubo
 * @version 2018-02-27
 */
public class IdGen{

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
