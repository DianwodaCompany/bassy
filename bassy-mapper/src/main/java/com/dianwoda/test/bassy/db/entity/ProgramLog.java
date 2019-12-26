package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class ProgramLog implements Serializable {
    private Integer id;

    private Integer programId;

    private String status;

    private Integer percent;

    private String programExplain;

    private Integer logType;

    private String bugInfo;

    private String taskInfo;

    private Byte isNormal;

    private String reasonTeam;

    private String reasonType;

    private String reasonLevel;

    private String reasonDetail;

    private String modifier;

    private Date modifyTm;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getProgramExplain() {
        return programExplain;
    }

    public void setProgramExplain(String programExplain) {
        this.programExplain = programExplain == null ? null : programExplain.trim();
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getBugInfo() {
        return bugInfo;
    }

    public void setBugInfo(String bugInfo) {
        this.bugInfo = bugInfo == null ? null : bugInfo.trim();
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo == null ? null : taskInfo.trim();
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
}