package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class AutoTestFailResult implements Serializable {
    private Integer id;

    private Integer autoTestId;

    private String testName;

    private String testType;

    private String failMethod;

    private Integer failReason;

    private String remark;

    private String testerCode;

    private String testerName;

    private Date updateTm;

    private Date createTm;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutoTestId() {
        return autoTestId;
    }

    public void setAutoTestId(Integer autoTestId) {
        this.autoTestId = autoTestId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType == null ? null : testType.trim();
    }

    public String getFailMethod() {
        return failMethod;
    }

    public void setFailMethod(String failMethod) {
        this.failMethod = failMethod == null ? null : failMethod.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTesterCode() {
        return testerCode;
    }

    public void setTesterCode(String testerCode) {
        this.testerCode = testerCode == null ? null : testerCode.trim();
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName == null ? null : testerName.trim();
    }

    public Date getUpdateTm() {
        return updateTm;
    }

    public void setUpdateTm(Date updateTm) {
        this.updateTm = updateTm;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }
}