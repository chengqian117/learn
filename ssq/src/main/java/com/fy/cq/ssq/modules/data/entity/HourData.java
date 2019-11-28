package com.fy.cq.ssq.modules.data.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@TableName("user_hour_data")
@Data
@EqualsAndHashCode(callSuper = false)
public class HourData  extends BaseData{

    /**
     * 小时
     */
    private String hour;

}
