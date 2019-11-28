package com.fy.cq.ssq.modules.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cq123
 * @since 2019-10-17
 */
@TableName("user_second_data")
@Data
public class SecondData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private String userId;
    /**
     * 数据时间
     */
    @TableField("data_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataTime;
    /**
     * 版本 1 是新数据 2 是统计过的数据
     */
    private String version;
    /**
     * 数据
     */
    @TableField("user_data")
    private BigDecimal userData;
    /**
     * 备注
     */
    private String remark;


    public SecondData(){};
    public SecondData(String userId, Date dataTime, BigDecimal userData,String version, String remark) {
        this.userId = userId;
        this.dataTime = dataTime;
        this.version = version;
        this.userData = userData;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SecondData{" +
        ", id=" + id +
        ", userId=" + userId +
        ", dataTime=" + dataTime +
        ", version=" + version +
        ", userData=" + userData +
        ", remark=" + remark +
        "}";
    }
}
