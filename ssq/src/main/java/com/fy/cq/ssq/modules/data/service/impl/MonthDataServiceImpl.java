package com.fy.cq.ssq.modules.data.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.common.utils.RdoUtils;
import com.fy.cq.ssq.modules.data.dao.MonthDataMapper;
import com.fy.cq.ssq.modules.data.entity.DayData;
import com.fy.cq.ssq.modules.data.entity.MonthData;
import com.fy.cq.ssq.modules.data.service.DayDataService;
import com.fy.cq.ssq.modules.data.service.MonthDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@Service
@Slf4j
public class MonthDataServiceImpl extends ServiceImpl<MonthDataMapper, MonthData> implements MonthDataService {
    //汇总后 低一级时间单位的数据进行修改状态的Server
    @Autowired
    DayDataService lowService;
    //提示信息
    private final  String messageBlock="每月汇总天数据";

    public String getMessageBlock() {
        return messageBlock;
    }
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int sumDayDataByUserAndDay(String userId, String date) {
        //先查询当前条件下时候汇总
        QueryWrapper<MonthData> entityWrapper=new QueryWrapper<>();
        MonthData searchData=new MonthData();
        searchData.setUserId(userId);
        searchData.setMonth(date);
        entityWrapper.setEntity(searchData);
        List<MonthData> list=this.list(entityWrapper);
        //每查找到数据，开始汇总
        if(list.size()==0){
            log.info(messageBlock+"未汇总，开始汇总");
            int i=this.baseMapper.insertSumDayDataByUserAndDay(userId, date);
            if(i==1){
                //汇总完成后，标记低一级数据为 version=2
                log.info(messageBlock+"完成，将已汇总数据标记版本");
                DayData lowData = new DayData();
                lowData.setVersion("2");
                QueryWrapper<DayData> entityWrapper2=new QueryWrapper<>();
                //查询条件 按照索引顺序来
//                entityWrapper2.eq("user_id",userId);
//                entityWrapper2.between("day",date+"-01",date+"-31");
//                entityWrapper2.eq("version","1");

                QueryWrapper<DayData> entityWrapper3=new QueryWrapper<>();
                //查询条件 按照索引顺序来
                entityWrapper3.eq("user_id",userId);
                entityWrapper3.between("day",date+"-01",date+"-31");
                entityWrapper3.eq("version","1");
                entityWrapper3.last(" fro update");
                List<DayData> lowList = lowService.list(entityWrapper3);
                List<Integer> list1 = lowList.stream().map(a -> a.getId()).collect(Collectors.toList());
                entityWrapper2.in("id",list1);
                boolean update = lowService.update(lowData,entityWrapper2);
                if(update){
                    log.info(messageBlock+"成功 修改状态完成");
                    return 1;
                }else{
                    log.error(messageBlock+"成功 修改状态异常，回滚。注意排查问题");
                    throw new RuntimeException(messageBlock+"成功 修改状态异常");
                }
            }else{
                log.error(messageBlock+"出错，回滚。注意排查问题");
                throw new RuntimeException(messageBlock+"汇总出错");
            }
        }else if(list.size()==1){
            log.info(messageBlock+"已经汇总过");//，要汇总去实现覆盖汇总方法");
        }else{
            log.info(messageBlock+"汇总数据已重复，用户id："+userId+" 时间："+date+" 去数据库清理 ");
        }
        return 0;
    }

    @Override
    public IPage<MonthData> selectPageByDto(SecondDataDto data) {
        QueryWrapper<MonthData> entityWrapper=new QueryWrapper<>();
        Page<MonthData> page=new Page<>();
        //封装分页查询条件，或者使用默认值
        RdoUtils.setPageByDto(page,data);
        //封装条件 按照 索引顺序
        if(data.getUserId()!=null&& !StringUtils.isEmpty(data.getUserId())){
            entityWrapper.eq("user_id",data.getUserId());
        }
        //月
        if(data.getDataTime().matches("^\\d{4}-\\d{2}$")){
            entityWrapper.eq("month",data.getDataTime());
        }//年
        else if (data.getDataTime().matches("^\\d{4}$")){
            entityWrapper.between("month",data.getDataTime()+"-01",data.getDataTime()+"-12");
        }
        else{
            return null;
        }
        return  this.page(page,entityWrapper);
    }
}
