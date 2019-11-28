package com.fy.cq.ssq.modules.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.common.utils.RdoUtils;
import com.fy.cq.ssq.modules.data.dao.SecondDataMapper;
import com.fy.cq.ssq.modules.data.entity.SecondData;
import com.fy.cq.ssq.modules.data.service.SecondDataService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@Service
public class SecondDataServiceImpl extends ServiceImpl<SecondDataMapper, SecondData> implements SecondDataService {

    @Override
    public IPage<SecondData> selectPageByDto(SecondDataDto data) {
        QueryWrapper<SecondData> entityWrapper=new QueryWrapper<>();
        Page<SecondData> page=new Page<>();
        //封装分页查询条件，或者使用默认值
        RdoUtils.setPageByDto(page,data);
        //封装条件 按照 索引顺序
        if(data.getUserId()!=null&& !StringUtils.isEmpty(data.getUserId())){
            entityWrapper.eq("user_id",data.getUserId());
        }
        //秒
        if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$")){
            entityWrapper.eq("data_time",data.getDataTime());
        }//分
        else if (data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$")){
            entityWrapper.between("data_time",data.getDataTime()+":00",data.getDataTime()+":59");
        }//小时
        else if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}$")){
            entityWrapper.between("data_time",data.getDataTime()+":00:00",data.getDataTime()+":59:59");
        }
        //天
        else if(data.getDataTime().matches("^\\d{4}-\\d{2}-\\d{2}\\s?$")){
            entityWrapper.between("data_time",data.getDataTime()+" 00:00:00",data.getDataTime()+" 23:59:59");
        }
        else{
            return null;
        }
        return  this.page(page,entityWrapper);
    }
}
