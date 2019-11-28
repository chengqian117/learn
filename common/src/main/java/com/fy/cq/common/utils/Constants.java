package com.fy.cq.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量类
 * @author admin
 *
 */
@Component
public class Constants {
	
	static{
		
		SERVICE_KEY=AppProperties.getValue("key.service");
		
		KAFKA_TOPIC_TRAIN=AppProperties.getValue("kafak.topic.train");
		KAFKA_TOPIC_SLAVE=AppProperties.getValue("kafak.topic.slave");
		KAFKA_BOOTSTRAP_SERVERS=AppProperties.getValue("kafak.service");
	}
	/**
	 * 接口对应的验证数据
	 */

	public static  String SERVICE_KEY;
	//错误提示信息
	public static final String ERRORMESAGE_NULL="参数不能为空";
	
	public static final String ERRORMESAGE_EXCEP="发生异常";
	
	public static final String ERRORMESAGE_ERROR_KEY="接口key验证失败";
	
	public static final String ERRORMESAGE_NO_LOCMTV="查询不到对应的列车";
	
	public static final String ERRORMESAGE_NO_SLAVE="查询不到对应的列车";
	
	public static final String ERROR_KAFKAPRO_EXCEP="初始化kafka生产者异常";
	
	public static final String ERROR_KAFKACON_EXCEP="初始化kafka消费者异常";
	//成功提示信息
	public static final String SUCCESSMESAGE_OK="操作成功";
	/**
	 * kafka 存储列车消息的topic 标识
	 */
	@Value("${kafak.topic.train}")
	public static  String KAFKA_TOPIC_TRAIN;
	/**
	 * kafka 存储定位从设备消息的topic 标识
	 */
	@Value("${kafak.topic.slave}")
	public static  String KAFKA_TOPIC_SLAVE;
	/**
	 * kafka 对应的地址信息
	 */
	@Value("${kafak.service}")
	public static  String KAFKA_BOOTSTRAP_SERVERS;
	
	//kafka 生产者生产数据时，使用的key
	public static final String KAFKA_KEY_TRAIN_INSERT="train_insert";
	public static final String KAFKA_KEY_TRAIN_UPDATE="train_update";
	
	public static final String KAFKA_KEY_SLAVE_INSERT="slave_insert";
	public static final String KAFKA_KEY_SLAVE_UPDATE="slave_update";
	
	public static final String PROJECT_ID="a9483a398edc4dd8873d64e6ff85ba49";
	
}
