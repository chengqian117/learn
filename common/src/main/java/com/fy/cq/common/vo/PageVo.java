package com.fy.cq.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * Copyright:   Copyright 2018 - 2018 chinasofti Tech. Co. Ltd. All Rights Reserved.
 * Date:        2018年3月8日 上午11:21:55
 * Author:      liubo 
 * Version:     JTWL_V1.D1.0.0.0
 * Description: 公共分页实体类
 */
public class PageVo<T> implements Serializable
{
    
    private static final long serialVersionUID = -8065871877230151987L;
    
    // 当前页
    private Integer currentPage = 1;
    
    // 每页显示的总条数
    private Integer pageSize = 10;
    
    // 总条数
    private Integer totalNum;
    
    // 是否有下一页
    private Integer isMore;
    
    // 总页数
    private Integer totalPage;
    
    // 开始索引
    private Integer startIndex;
    
    // 分页结果
    private List<T> rows;
    
    public PageVo(int currentPage, int pageSize)
    {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }
    
    public PageVo()
    {
        
    }
    
    public PageVo(Integer currentPage, Integer pageSize, Integer totalNum)
    {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.totalPage = (this.totalNum + this.pageSize - 1) / this.pageSize;
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        this.isMore = this.currentPage >= this.totalPage ? 0 : 1;
    }
    
    public Integer getCurrentPage()
    {
        return currentPage;
    }
    
    public void setCurrentPage(Integer currentPage)
    {
        this.currentPage = currentPage;
    }
    
    public Integer getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public Integer getTotalNum()
    {
        return totalNum;
    }
    
    public void setTotalNum(Integer totalNum)
    {
        this.totalNum = totalNum;
        this.totalPage = (this.totalNum + this.pageSize - 1) / this.pageSize;
    }
    
    public Integer getIsMore()
    {
        return isMore;
    }
    
    public void setIsMore(Integer isMore)
    {
        this.isMore = isMore;
    }
    
    public Integer getTotalPage()
    {
        return totalPage;
    }
    
    public void setTotalPage(Integer totalPage)
    {
        this.totalPage = totalPage;
    }
    
    public Integer getStartIndex()
    {
        return startIndex;
    }
    
    public void setStartIndex(Integer startIndex)
    {
        this.startIndex = startIndex;
    }
    
    public List<T> getRows()
    {
        return rows;
    }
    
    public void setItems(List<T> items)
    {
        this.rows = items;
    }
    
}