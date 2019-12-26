package com.dianwoda.test.bassy.common.domain.dto.statistics;


/**
 * @author zcp
 * @date 2019/2/14 15:19
 */
public class ReasonSortDTO {

    /**
     * 测试项目
     */
    private String testType;

    /**
     * 失败次数
     */
    private Integer failReason;
    /**
     * 失败次数
     */
    private Integer num;

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Integer getFailReason() {
        return failReason;
    }

    public void setFailReason(Integer failReason) {
        this.failReason = failReason;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
