package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class ProgramTaskLog implements Serializable {
    private Integer id;

    private Integer programId;

    private Integer taskId;

    private Integer requireId;

    private Date startTm;

    private Date endTm;

    private String taskStatus;

    private String tester;

    private String withTester;

    private Float expectHour;

    private Float actualHour;

    private Float todayHour;

    private Integer percent;

    private Byte isNormal;

    private String reasonTeam;

    private String reasonType;

    private String reasonLevel;

    private String reasonDetail;

    private String taskExplain;

    private Integer autoTestId;

    private String autoTestResult;

    private String document;

    private String modifier;

    private Date modifyTm;

    private Integer ztTaskId;

    private Byte abnormalType;

    private String abnormalOwner;

    private String valid;

    private Short abnormalDepart;

    private Integer duplicateAbnormal;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getRequireId() {
        return requireId;
    }

    public void setRequireId(Integer requireId) {
        this.requireId = requireId;
    }

    public Date getStartTm() {
        return startTm;
    }

    public void setStartTm(Date startTm) {
        this.startTm = startTm;
    }

    public Date getEndTm() {
        return endTm;
    }

    public void setEndTm(Date endTm) {
        this.endTm = endTm;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester == null ? null : tester.trim();
    }

    public String getWithTester() {
        return withTester;
    }

    public void setWithTester(String withTester) {
        this.withTester = withTester == null ? null : withTester.trim();
    }

    public Float getExpectHour() {
        return expectHour;
    }

    public void setExpectHour(Float expectHour) {
        this.expectHour = expectHour;
    }

    public Float getActualHour() {
        return actualHour;
    }

    public void setActualHour(Float actualHour) {
        this.actualHour = actualHour;
    }

    public Float getTodayHour() {
        return todayHour;
    }

    public void setTodayHour(Float todayHour) {
        this.todayHour = todayHour;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Byte getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(Byte isNormal) {
        this.isNormal = isNormal;
    }

    public String getReasonTeam() {
        return reasonTeam;
    }

    public void setReasonTeam(String reasonTeam) {
        this.reasonTeam = reasonTeam == null ? null : reasonTeam.trim();
    }

    public String getReasonType() {
        return reasonType;
    }

    public void setReasonType(String reasonType) {
        this.reasonType = reasonType == null ? null : reasonType.trim();
    }

    public String getReasonLevel() {
        return reasonLevel;
    }

    public void setReasonLevel(String reasonLevel) {
        this.reasonLevel = reasonLevel == null ? null : reasonLevel.trim();
    }

    public String getReasonDetail() {
        return reasonDetail;
    }

    public void setReasonDetail(String reasonDetail) {
        this.reasonDetail = reasonDetail == null ? null : reasonDetail.trim();
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain == null ? null : taskExplain.trim();
    }

    public Integer getAutoTestId() {
        return autoTestId;
    }

    public void setAutoTestId(Integer autoTestId) {
        this.autoTestId = autoTestId;
    }

    public String getAutoTestResult() {
        return autoTestResult;
    }

    public void setAutoTestResult(String autoTestResult) {
        this.autoTestResult = autoTestResult == null ? null : autoTestResult.trim();
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document == null ? null : document.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    public Integer getZtTaskId() {
        return ztTaskId;
    }

    public void setZtTaskId(Integer ztTaskId) {
        this.ztTaskId = ztTaskId;
    }

    public Byte getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(Byte abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getAbnormalOwner() {
        return abnormalOwner;
    }

    public void setAbnormalOwner(String abnormalOwner) {
        this.abnormalOwner = abnormalOwner == null ? null : abnormalOwner.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public Short getAbnormalDepart() {
        return abnormalDepart;
    }

    public void setAbnormalDepart(Short abnormalDepart) {
        this.abnormalDepart = abnormalDepart;
    }

    public Integer getDuplicateAbnormal() {
        return duplicateAbnormal;
    }

    public void setDuplicateAbnormal(Integer duplicateAbnormal) {
        this.duplicateAbnormal = duplicateAbnormal;
    }
}