package com.fy.cq.ssq.modules.data.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fy.cq.ssq.modules.data.entity.MinuteData;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface MinuteDataMapper extends BaseMapper<MinuteData> {

    int insertSumSecondDataByUserAndDay(String userId, String date);

}
