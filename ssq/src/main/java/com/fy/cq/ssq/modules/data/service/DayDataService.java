package com.fy.cq.ssq.modules.data.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.ssq.modules.data.entity.DayData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface DayDataService extends IService<DayData> {
    /**
     * @Description: 根据用户和天汇总小时数据
     * @Param: userId 用户id
     * @Param: date 当前天 2019-10-17
     * @return: int 1 表示汇总完成 0表示汇总中存在问题
     * @Author: cq
     * @Date: 2019/10/17
     */
    int sumHourDataByUserAndDay(String userId, String date);

    IPage<DayData> selectPageByDto(SecondDataDto data);

}
