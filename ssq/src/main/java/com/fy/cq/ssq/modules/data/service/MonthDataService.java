package com.fy.cq.ssq.modules.data.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.ssq.modules.data.entity.MonthData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface MonthDataService extends IService<MonthData> {
    /**
     * @Description: 根据用户和月汇总天数据
     * @Param: userId 用户id
     * @Param: date 当前月 2019-10
     * @return: int 1 表示汇总完成 0表示汇总中存在问题
     * @Author: cq
     * @Date: 2019/10/17
     */
    int sumDayDataByUserAndDay(String userId, String date);

    IPage<MonthData> selectPageByDto(SecondDataDto data);

}
