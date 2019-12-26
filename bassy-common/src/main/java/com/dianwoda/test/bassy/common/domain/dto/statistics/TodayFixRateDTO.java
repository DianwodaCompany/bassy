package com.dianwoda.test.bassy.common.domain.dto.statistics;

import java.util.Date;

/**
 * @author zcp
 * @date 2019/3/4 22:06
 */
public class TodayFixRateDTO {

    /**
     * 排序序号
     */
    private Integer no;
    /**
     * 执行日期
     */
    private Date date;

    /**
     * 测试项目类型
     */
    private String testType;
    /**
     * 失败用例维护人工号
     */
    private String testerCode;
    /**
     * 失败用例维护人姓名
     */
    private String testerName;

    /**
     * 项目维护比率
     */
    private Double rate;
    /**
     * 失败用例数
     */
    private Integer fixNum;
    /**
     * 全部用例数
     */
    private Integer allFailNum;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTesterCode() {
        return testerCode;
    }

    public void setTesterCode(String testerCode) {
        this.testerCode = testerCode;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public Integer getFixNum() {
        return fixNum;
    }

    public void setFixNum(Integer fixNum) {
        this.fixNum = fixNum;
    }

    public Integer getAllFailNum() {
        return allFailNum;
    }

    public void setAllFailNum(Integer allFailNum) {
        this.allFailNum = allFailNum;
    }
}
