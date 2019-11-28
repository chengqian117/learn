package com.fy.cq.ssq.modules.data.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.ssq.modules.data.entity.YearData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface YearDataService extends IService<YearData> {
    /**
     * 根据用户和年汇总月数据
     * @Description: 根据用户和年汇总月数据
     * @param  userId 用户id
     * @param  date 当前年 2019
     * @return  int 1 表示汇总完成 0表示汇总中存在问题
     * @author  cq
     *
     */
    int sumMonthDataByUserAndDay(String userId, String date);


    /**
     * @Description:  测试
     * @param data 数据
     * @return : com.baomidou.mybatisplus.plugins.Page<com.softi.subwayMap.modules.data.entity.YearData>
     * @author cq
     * @date 2019/10/28
    */
    IPage<YearData> selectPageByDto(SecondDataDto data);

}
