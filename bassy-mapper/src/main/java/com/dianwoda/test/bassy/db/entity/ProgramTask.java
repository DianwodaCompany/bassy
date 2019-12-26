package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class ProgramTask implements Serializable {
    private Integer id;

    private Integer programId;

    private String programProcess;

    private Integer requireId;

    private String requireRelate;

    private String taskName;

    private String taskCode;

    private Date startTm;

    private Date endTm;

    private Date actualStartTm;

    private Date actualEndTm;

    private Date closeTm;

    private Date pauseTm;

    private String tester;

    private String withTester;

    private Float expectHour;

    private String status;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

    private Integer ztTaskId;

    private Integer ztProjectId;

    private Integer percent;

    private Float actualHour;

    private String excludeDate;

    private String includeDate;

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

    public String getProgramProcess() {
        return programProcess;
    }

    public void setProgramProcess(String programProcess) {
        this.programProcess = programProcess == null ? null : programProcess.trim();
    }

    public Integer getRequireId() {
        return requireId;
    }

    public void setRequireId(Integer requireId) {
        this.requireId = requireId;
    }

    public String getRequireRelate() {
        return requireRelate;
    }

    public void setRequireRelate(String requireRelate) {
        this.requireRelate = requireRelate == null ? null : requireRelate.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
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

    public Date getActualStartTm() {
        return actualStartTm;
    }

    public void setActualStartTm(Date actualStartTm) {
        this.actualStartTm = actualStartTm;
    }

    public Date getActualEndTm() {
        return actualEndTm;
    }

    public void setActualEndTm(Date actualEndTm) {
        this.actualEndTm = actualEndTm;
    }

    public Date getCloseTm() {
        return closeTm;
    }

    public void setCloseTm(Date closeTm) {
        this.closeTm = closeTm;
    }

    public Date getPauseTm() {
        return pauseTm;
    }

    public void setPauseTm(Date pauseTm) {
        this.pauseTm = pauseTm;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Integer getZtTaskId() {
        return ztTaskId;
    }

    public void setZtTaskId(Integer ztTaskId) {
        this.ztTaskId = ztTaskId;
    }

    public Integer getZtProjectId() {
        return ztProjectId;
    }

    public void setZtProjectId(Integer ztProjectId) {
        this.ztProjectId = ztProjectId;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Float getActualHour() {
        return actualHour;
    }

    public void setActualHour(Float actualHour) {
        this.actualHour = actualHour;
    }

    public String getExcludeDate() {
        return excludeDate;
    }

    public void setExcludeDate(String excludeDate) {
        this.excludeDate = excludeDate == null ? null : excludeDate.trim();
    }

    public String getIncludeDate() {
        return includeDate;
    }

    public void setIncludeDate(String includeDate) {
        this.includeDate = includeDate == null ? null : includeDate.trim();
    }
}