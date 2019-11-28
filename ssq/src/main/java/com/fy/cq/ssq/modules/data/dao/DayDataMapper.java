package com.fy.cq.ssq.modules.data.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fy.cq.ssq.modules.data.entity.DayData;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface DayDataMapper extends BaseMapper<DayData> {
    int insertSumHourDataByUserAndDay(String userId, String date);

}
