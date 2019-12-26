package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class ProgramModule implements Serializable {
    private Integer id;

    private String moduleName;

    private String description;

    private String parentCode;

    private String persons;

    private String coreNodes;

    private String requires;

    private String remark;

    private String readOnly;

    private String workId;

    private String pressingReason;

    private String isCheck;

    private String status;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

    private String innerProjectType;

    private Integer eachTaskWorkHour;

    private Integer dailyTaskNum;

    private String isLoop;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
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

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly == null ? null : readOnly.trim();
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

    public Integer getEachTaskWorkHour() {
        return eachTaskWorkHour;
    }

    public void setEachTaskWorkHour(Integer eachTaskWorkHour) {
        this.eachTaskWorkHour = eachTaskWorkHour;
    }

    public Integer getDailyTaskNum() {
        return dailyTaskNum;
    }

    public void setDailyTaskNum(Integer dailyTaskNum) {
        this.dailyTaskNum = dailyTaskNum;
    }

    public String getIsLoop() {
        return isLoop;
    }

    public void setIsLoop(String isLoop) {
        this.isLoop = isLoop == null ? null : isLoop.trim();
    }
}