package com.fy.cq.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * Copyright:   Copyright 2018 - 2018 chinasofti Tech. Co. Ltd. All Rights Reserved.
 * Date:        2018年3月8日 上午11:26:16
 * Author:      liubo 
 * Version:     JTWL_V1.D1.0.0.0
 * Description: 返回前端公共类
 */
@JsonInclude(Include.NON_NULL)
@Data
public class Result
{
    /**
     * 成功失败状态
     */
	private int status;
	/**
	 * 数据条数
	 */
	private long count;
    /**
     * 成功失败标识(停用)
     */
    private boolean flag;
    
    /**
     * 数据
     */
    private Map<String, Object> dataMap;
    /**
     * 数据
     */
    private Object data;
    /**
     * 错误码
     */
    private String errCode;
    
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 错误信息
     */
    private String succMsg;
    
    public String getSuccMsg() {
		return succMsg;
	}

	public void setSuccMsg(String succMsg) {
		this.succMsg = succMsg;
	}

	/**
     * 需要返回给前端的字段参数，只过滤出对象的属性
     */
    private List<String> resultParams;
    
    public boolean isFlag()
    {
        return flag;
    }
    
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
    
    public String getErrCode()
    {
        return errCode;
    }
    
    public void setErrCode(String errCode)
    {
        this.errCode = errCode;
    }
    
    public Map<String, Object> getDataMap()
    {
        return dataMap == null ? dataMap = new HashMap<String, Object>() : dataMap;
    }
    
   
    
    public String getErrMsg()
    {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }
    
    /**
     * 设置返回给前端的字段
     * @param resultParam
     */
    public void setResultParams(String resultParam)
    {
        this.resultParams = Arrays.asList(resultParam.split(","));
        dataMap = this.resultFilterMap(dataMap);
    }
    
    /**
     * 过滤Map类型的数据
     * @param map
     * @return
     */
    private Map resultFilterMap(Map map)
    {
        JSONObject newJson = new JSONObject();
        Iterator it = map.keySet().iterator();
        while (it.hasNext())
        {
            Object key = it.next();
            Object obj = map.get(key);
            //将对象转换成JSON
            Object returnJson = resultFilterObject(obj);
            newJson.put(key, returnJson);
        }
        return newJson;
    }
    
    /**
     * 过滤List类型的数据
     * @param list
     * @return
     */
    private JSONArray resultFilterList(List list)
    {
        JSONArray jsonArray = new JSONArray();
        for (Object obj : list)
        {
            Object returnJson = resultFilterObject(obj);
            jsonArray.add(returnJson);
        }
        return jsonArray;
    }
    
    /**
     * 过滤对象
     * @param obj
     * @return Object
     */
    private Object resultFilterObject(Object obj)
    {
        if (obj == null)
        {
            return "";
        }
        //如果对象是基本类型的包装类型(基本类型以Object传入会自动装箱成包装类型)、String、BigDecimal，则直接返回
        else if (obj instanceof Byte || obj instanceof Short
            || obj instanceof Integer || obj instanceof Long
            || obj instanceof Float || obj instanceof Double
            || obj instanceof Character || obj instanceof Boolean
            || obj instanceof String || obj instanceof BigDecimal)
        {
            return obj;
        }
        else if (obj instanceof PageVo)
        {
            PageVo page = (PageVo) obj;
            JSONObject pageJson = JSONObject.fromObject(page);
            JSONArray pageRows = this.resultFilterList((List) page.getRows());
            pageJson.put("rows", pageRows);
            return pageJson;
        }
        else if (obj instanceof List)
        {
            return this.resultFilterList((List) obj);
        }
        else if (obj instanceof Map)
        {
            return this.resultFilterMap((Map) obj);
        }
        else if (obj instanceof Date)
        {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) obj);
        }
        else if (obj.getClass() instanceof Class)
        {
            return this.resultFilter(obj);
        }
        return obj;
    }
    
    /**
     * 过滤类对象数据
     * @param clazzObj
     * @return
     */
    private JSONObject resultFilter(Object clazzObj)
    {
        Class<? extends Object> clazz = clazzObj.getClass();
        //类对象的所有属性，包含其父类的属性
        Map<String, Field> fields = getAllFields(clazz);
        JSONObject json = JSONObject.fromObject(clazzObj);
        JSONObject newJson = new JSONObject();
        for (String str : resultParams)
        {
            if (json.has(str))
            {
                Field field = fields.get(str);
                //如果传入的属性不属于该类及其父类，则不对其进行处理
                if (field == null)
                {
                    continue;
                }
                /*
                 * 由于传入的数据是JSON格式的，如果类的属性有除String以外的其他引用对象（如List，Map,类对象），
                 * 在调用resultFilterObject方法时不能根据实际进行处理，则在此处使用反射机制获取属性对应的真正结果，
                 * 再调用resultFilterObject方法，就可以正常处理
                 */
                String getMethodName =
                    "get" + str.substring(0, 1).toUpperCase()
                        + str.substring(1);
                try
                {
                    //根据方法名得到方法对象
                    Method getMethod = clazz.getMethod(getMethodName);
                    //执行方法
                    Object methodValue = getMethod.invoke(clazzObj);
                    Object returnJson = resultFilterObject(methodValue);
                    newJson.put(str, returnJson);
                }
                catch (NoSuchMethodException e)
                {
                    getMethodName = "get" + str;
                    try
                    {
                        //根据方法名得到方法对象
                        Method getMethod = clazz.getMethod(getMethodName);
                        //执行方法
                        Object methodValue = getMethod.invoke(clazzObj);
                        Object returnJson = resultFilterObject(methodValue);
                        newJson.put(str, returnJson);
                    }
                    catch (NoSuchMethodException | SecurityException
                        | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                catch (SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return newJson;
    }
    
    /**
     * 获取类的所有属性
     * @param clazz
     * @return
     */
    private Map<String, Field> getAllFields(Class<? extends Object> clazz)
    {
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        while (clazz != null)
        {
            for (Field field : clazz.getDeclaredFields())
            {
                fieldMap.put(field.getName(), field);
            }
            clazz = clazz.getSuperclass();
        }
        return fieldMap;
    }


}