package com.fy.cq.ssq.modules.data.web;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fy.cq.common.dto.SecondDataDto;
import com.fy.cq.common.utils.RdoUtils;
import com.fy.cq.common.vo.Result;
import com.fy.cq.ssq.modules.data.entity.MonthData;
import com.fy.cq.ssq.modules.data.service.MonthDataService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@RestController
@RequestMapping("/monthData")
@Slf4j
public class MonthDataController {

    @Autowired
    MonthDataService service;
    @ApiOperation(value = "通过条件分页查询月数据")
    @GetMapping
    public Result getList(@ModelAttribute SecondDataDto data){
        Result result = new Result();
        if(data!=null){
            if(data.getDataTime()==null||!data.getDataTime().matches("^\\d{4}-\\d{2}$")){
                RdoUtils.setResultError(result,RdoUtils.UN_FORMAT_DATE);
            }else{
                IPage<MonthData> dataPage = service.selectPageByDto(data);
                if(dataPage==null){
                    log.error("分查询结果为null 数据格式不正确");
                    RdoUtils.setResultError(result,RdoUtils.UN_FORMAT_DATE);
                }else{
                    RdoUtils.setResultByData(result,dataPage);
                }
            }
        }else {
            RdoUtils.setResultError(result,RdoUtils.NULL_OBJECT);
        }
        return result;
    }
}

