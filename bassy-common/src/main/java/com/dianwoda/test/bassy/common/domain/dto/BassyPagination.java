package com.dianwoda.test.bassy.common.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcp
 * @date 2019/3/13 10:57
 */
public class BassyPagination<T> implements Serializable {

    // 当前页
    private int current;
    // 当前页页码下标索引
    private int currentPageIndex;
    // 每页查询数量
    private int pageSize;
    // 总记录数
    private int total;
    // 总页数
    private int pageCount;
    // MySQL查询数据开始下标
    private int offset;
    // 查询数据结果列表
    private List<T> list;
    /**
     * 默认每页记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 默认当前页
     */
    public static final int DEFAULT_CURRENT= 1;

    /**
     * 构造函数
     */
    public BassyPagination() {
        init(null, null);
    }

    /**
     * 构造方法
     *
     * @param current
     *            当前页码
     */
    public BassyPagination(Integer current) {
        init(current, null);
    }

    /**
     * 构造方法
     *
     * @param current
     *            当前页码
     * @param pageSize
     *            每页查询数量
     */
    public BassyPagination(Integer current, Integer pageSize) {
        init(current, pageSize);
    }

    /**
     * 初始化分页器
     *
     * @param current
     * @param pageSize
     * @date 2015年8月26日
     */
    private void init(Integer current, Integer pageSize) {
        this.current = (current == null || current < 1) ? DEFAULT_CURRENT : current;
        this.pageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;
        this.currentPageIndex = getCurrent() - 1;
        this.offset = getCurrentPageIndex() * getPageSize();
        this.list = new ArrayList<T>();
    }

    /**
     * 设置总记录数，并计算总页数
     *
     * @param total
     * @date 2015年8月26日
     */
    public void setTotal(int total) {
        this.total = total;
        this.pageCount = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        if (pageCount != 0 && current > pageCount) {
            this.current = pageCount;
        }
        this.currentPageIndex = getCurrent() - 1;
        this.offset = getCurrentPageIndex() * getPageSize();
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current < 1 ? DEFAULT_CURRENT : current;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }

    public int getTotal() {
        return total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getOffset() {
        return offset;
    }
}
