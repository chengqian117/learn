package com.fy.cq.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询秒数据的查询参数")
public class SecondDataDto {

    @ApiModelProperty(value = "页数大小",example = "默认10")
    private Integer pageSize;
    @ApiModelProperty(value = "当前页数",example = "默认1")
    private Integer pageCurrent;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "数据时间 yyyy-MM-dd HH:mm:ss",example = "2019-10-21 9:00")
    private String dataTime;

}
