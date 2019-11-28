package com.fy.cq.ssq.modules.data.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.ssq.modules.data.entity.SecondData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
public interface SecondDataService extends IService<SecondData> {


    IPage<SecondData> selectPageByDto(SecondDataDto data);

}
