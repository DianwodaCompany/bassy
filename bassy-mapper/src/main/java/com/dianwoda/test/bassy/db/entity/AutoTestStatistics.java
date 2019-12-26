package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class AutoTestStatistics implements Serializable {
    private Integer id;

    private Date date;

    private String executionTimes;

    private String caseNum;

    private String passingRate;

    private String reasonSort;

    private String methodSort;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExecutionTimes() {
        return executionTimes;
    }

    public void setExecutionTimes(String executionTimes) {
        this.executionTimes = executionTimes == null ? null : executionTimes.trim();
    }

    public String getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(String caseNum) {
        this.caseNum = caseNum == null ? null : caseNum.trim();
    }

    public String getPassingRate() {
        return passingRate;
    }

    public void setPassingRate(String passingRate) {
        this.passingRate = passingRate == null ? null : passingRate.trim();
    }

    public String getReasonSort() {
        return reasonSort;
    }

    public void setReasonSort(String reasonSort) {
        this.reasonSort = reasonSort == null ? null : reasonSort.trim();
    }

    public String getMethodSort() {
        return methodSort;
    }

    public void setMethodSort(String methodSort) {
        this.methodSort = methodSort == null ? null : methodSort.trim();
    }
}