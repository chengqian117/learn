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
@TableName("user_year_data")
@Data
@EqualsAndHashCode(callSuper = false)
public class YearData extends BaseData {

    /**
     * 年
     */
    private String year;



}
