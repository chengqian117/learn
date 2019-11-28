package com.fy.cq.common.utils;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.common.vo.Result;

public class RdoUtils {

    /* *
     * 查询条件为空
     */
    public static final String NULL_OBJECT="NULL_OBJECT";
    /**
     * 时间格式不正确
     */
    public static final String UN_FORMAT_DATE="UN_FORMAT_DATE";

    /**
     * @Description: 通过错误信息赋值result
     * @param result
     * @param s 错误信息
     */

    public static void setResultError(Result result, String s){
        switch (s){
            case NULL_OBJECT:
                result.setFlag(false);
                result.setStatus(0);
                result.setErrCode("1");
                result.setErrMsg("查询条件为空");
                break;
            case UN_FORMAT_DATE:
                result.setFlag(false);
                result.setStatus(0);
                result.setErrCode("2");
                result.setErrMsg("日期格式不正确");
                break;
            default:
                result.setFlag(false);
                result.setStatus(0);
        }
    }

    /**
     * @Description 将分页数据赋值给result
     * @param result vo
     * @param dataPage 分页数据
    */
    public static void setResultByData(Result result, IPage dataPage){
        result.setStatus(1);
        result.setCount(dataPage.getTotal());
        result.setData(dataPage.getRecords());
    }

    public static void setPageByDto(Page page, SecondDataDto dto){
        if (dto.getPageCurrent()==null||dto.getPageCurrent()<=0){
            page.setCurrent(1);
        }else {
            page.setCurrent(dto.getPageCurrent());
        }
        if (dto.getPageSize()==null||dto.getPageSize()<=0) {
            page.setSize(10);
        }else{
            page.setSize(dto.getPageSize());
        }
    }
}
