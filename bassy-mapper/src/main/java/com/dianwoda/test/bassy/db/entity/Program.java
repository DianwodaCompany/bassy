package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Program implements Serializable {
    private Integer id;

    private String programName;

    private String programType;

    private Integer programModule;

    private Integer processModule;

    private String persons;

    private String coreNodes;

    //TODO 本次下线字段
    private String requires;

    private String remark;

    private String workId;

    private String pressingReason;

    private String isCheck;

    private Date startTime;

    private Date endTime;

    private String status;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

    private String innerProjectType;

    private Float eachTaskWorkHour;

    private Integer dailyTaskNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName == null ? null : programName.trim();
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType == null ? null : programType.trim();
    }

    public Integer getProgramModule() {
        return programModule;
    }

    public void setProgramModule(Integer programModule) {
        this.programModule = programModule;
    }

    public Integer getProcessModule() {
        return processModule;
    }

    public void setProcessModule(Integer processModule) {
        this.processModule = processModule;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons == null ? null : persons.trim();
    }

    public String getCoreNodes() {
        return coreNodes;
    }

    public void setCoreNodes(String coreNodes) {
        this.coreNodes = coreNodes == null ? null : coreNodes.trim();
    }

    public String getRequires() {
        return requires;
    }

    public void setRequires(String requires) {
        this.requires = requires == null ? null : requires.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public String getPressingReason() {
        return pressingReason;
    }

    public void setPressingReason(String pressingReason) {
        this.pressingReason = pressingReason == null ? null : pressingReason.trim();
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck == null ? null : isCheck.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public String getInnerProjectType() {
        return innerProjectType;
    }

    public void setInnerProjectType(String innerProjectType) {
        this.innerProjectType = innerProjectType == null ? null : innerProjectType.trim();
    }

    public Float getEachTaskWorkHour() {
        return eachTaskWorkHour;
    }

    public void setEachTaskWorkHour(Float eachTaskWorkHour) {
        this.eachTaskWorkHour = eachTaskWorkHour;
    }

    public Integer getDailyTaskNum() {
        return dailyTaskNum;
    }

    public void setDailyTaskNum(Integer dailyTaskNum) {
        this.dailyTaskNum = dailyTaskNum;
    }
}