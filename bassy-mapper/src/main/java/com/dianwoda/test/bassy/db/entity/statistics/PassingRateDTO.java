package com.dianwoda.test.bassy.db.entity.statistics;


/**
 * @author zcp
 * @date 2019/2/14 15:14
 */
public class PassingRateDTO {
    /**
     * 测试项目
     */
    private String testType;

    /**
     * 执行日期
     */
    private String day;

    /**
     * 通过率
     */
    private Double rate;

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
