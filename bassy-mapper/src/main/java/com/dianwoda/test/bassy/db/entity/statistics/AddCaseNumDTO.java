package com.dianwoda.test.bassy.db.entity.statistics;

/**
 * 近一周增加用例数
 * @author zcp
 * @date 2019/3/6 20:18
 */
public class AddCaseNumDTO {

    /**
     * 今日用例总数
     */
    private Integer todayNum;

    /**
     * 一周前用例总数
     */
    private Integer beforeNum;

    /**
     * 新增加用例数
     */
    private Integer addNum;

    public Integer getTodayNum() {
        return todayNum;
    }

    public void setTodayNum(Integer todayNum) {
        this.todayNum = todayNum;
    }

    public Integer getBeforeNum() {
        return beforeNum;
    }

    public void setBeforeNum(Integer beforeNum) {
        this.beforeNum = beforeNum;
    }

    public Integer getAddNum() {
        return addNum;
    }

    public void setAddNum(Integer addNum) {
        this.addNum = addNum;
    }
}
