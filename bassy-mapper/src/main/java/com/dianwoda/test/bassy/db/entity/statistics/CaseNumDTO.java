package com.dianwoda.test.bassy.db.entity.statistics;


/**
 * @author zcp
 * @date 2019/2/14 15:13
 */
public class CaseNumDTO {
    /**
     * 测试项目
     */
    private String testType;

    /**
     * 用例数
     */
    private Integer num;

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
