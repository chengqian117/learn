package com.fy.cq.ssq.modules.data.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.ssq.modules.data.entity.HourData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface HourDataService extends IService<HourData> {
    /**
     * @Description:根据用户和小时汇总分钟数据
     * @Param: userId 用户id
     * @Param: date 当前小时 2019-10-17 11
     * @return: int 1 表示汇总完成 0表示汇总中存在问题
     * @Author: cq
     * @Date: 2019/10/17
     */
    int sumMinuteDataByUserAndDay(String userId, String date);

    IPage<HourData> selectPageByDto(SecondDataDto data);

}
