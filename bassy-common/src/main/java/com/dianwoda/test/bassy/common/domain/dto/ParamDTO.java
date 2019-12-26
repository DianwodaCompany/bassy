package com.dianwoda.test.bassy.common.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/** 分页基础数据
 * @author lichengkai
 * 2018 - 05 - 16 : 15:17
 * Copyright(c) for dianwoda
 */
@AllArgsConstructor
@NoArgsConstructor
public class ParamDTO {
    // 当前页
    private Integer current;
    //todo 未来下线pageNum
    private Integer pageNum;
    private Integer pageSize;


    public int getCurrent() {
        return current == null?1:current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Integer getPageNum() {
        return pageNum ==null?1:pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null?10:pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
