package com.dianwoda.test.bassy.db.entity.statistics;


/**
 * @author zcp
 * @date 2019/2/14 15:13
 */
public class MethodSortDTO {
    /**
     * 排序序号
     */
    private Integer no;
    /**
     * 测试项目
     */
    private String testType;
    /**
     * 测试方法
     */
    private String failMethod;

    /**
     * 失败原因
     */
    private Integer failReason;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用例维护人工号
     */
    private String testerCode;

    /**
     * 用例维护人名称
     */
    private String testerName;
    /**
     * 失败次数
     */
    private Integer num;


    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getFailMethod() {
        return failMethod;
    }

    public void setFailMethod(String failMethod) {
        this.failMethod = failMethod;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getFailReason() {
        return failReason;
    }

    public void setFailReason(Integer failReason) {
        this.failReason = failReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
