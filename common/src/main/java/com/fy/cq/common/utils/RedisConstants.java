package com.fy.cq.common.utils;
/**
 * 常量类
 * @author admin
 *
 */
public interface RedisConstants {
	/**
	 * 列车对应的redis key前缀 + 列车id
	 * 列车以map形式存储在 redis
	 */
	public static final String REDIS_TRAIN_KEY="TRAIN:";
	/**
	 * 通过线路编号获取列车的链表key前缀 +线路id
	 * 以set形式存储
	 */
	public static final String REDIS_GET_TRAIN_KEY="TRAIN:LINE:";
	/**
	 * 限速区域对应的redis key
	 * 限速区域以map形式存储在 redis
	 */
	public static final String REDIS_LIMIT_ZONE_KEY="LIMIT_ZONE";
	/**
	 * 通过线路编号获取限速区域的链表key前缀 +线路id
	 * 以set形式存储
	 */
	public static final String REDIS_GET_LIMIT_ZONE_KEY="LIMIT_ZONE:LINE:";
	/**
	 * 定位从设备对应的redis key
	 * 定位从设备以map形式存储在 redis
	 */
	public static final String REDIS_SLAVE_KEY="SLAVE:";
	/**
	 * 通过线路编号获取定位从设备的链表key前缀 +线路id
	 * 以set形式存储
	 */
	public static final String REDIS_GET_SLAVE_KEY="SLAVE:LINE:";
	/**
	 * 通过线路编号获取车站的链表key前缀 +线路id
	 * 以List形式存储
	 */
	public static final String REDIS_GET_STATION_KEY="STATION:LINE:";
	/**
	 * 车站对应的redis key
	 * 车站以map形式存储在 redis
	 */
	public static final String REDIS_STATION_KEY="STATION:";
	/**
	 * 通过线路编号获取线段的链表key前缀 +线路id
	 * 以List形式存储
	 */
	public static final String REDIS_GET_SEGMENT_KEY="SEGMENT:LINE:";
	/**
	 * 线段对应的redis key
	 * 线段以map形式存储在 redis
	 */
	public static final String REDIS_SEGMENT_KEY="SEGMENT:";
	/**
	 * 机车超速预警对应的redis key
	 * 机车预警以map形式存储在 redis
	 */
	public static final String REDIS_WARNING_TRAIN_SPEED_KEY="WARNING:SPEED:TRAIN";
	/**
	 * 查询机车预警对应的redis key
	 * 机车预警以map形式存储在 redis
	 */
	public static final String REDIS_WARNING_TRAIN_KEY="WARNING:*:TRAIN*";
	/**
	 * 查询人员预警对应的redis key
	 * 人员预警以map形式存储在 redis
	 */
	public static final String REDIS_WARNING_PERSON_KEY="WARNING:*:PERSON*";
	/**
	 * 防区对应的redis key
	 * 防区以map形式存储在 redis
	 */
	public static final String REDIS_PROTECTAREA_KEY="PROTECTAREA:";
	/**
	 * 通过线路编号获取防区的链表key前缀 +线路id
	 * 以List形式存储
	 */
	
	public static final String REDIS_GET_PROTECTAREA_KEY="PROTECTAREA:LINE:";
	
	/**
	 *  通过线路编号获取防区的链表key前缀 +项目id
	 *  以SET形式存储
	 */
	public static final String REDIS_GET_LINE_KEY="LINE:PROJECT:";
	/**
	 * 线路对应的redis key
	 * 线路以map形式存储在 redis
	 */
	public static final String REDIS_LINE_KEY="LINE:";
}
