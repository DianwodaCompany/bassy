package com.dianwoda.test.bassy.common.domain.dto.statistics;

import java.util.Date;

/**
 * @author zcp
 * 2019/12/22 下午5:28
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public class WorkReportDTO {

    private Long id;

    private Date workday;

    private String staffCode;

    private String staffName;

    private Integer taskId;

    private String taskCode;

    private String taskName;

    private Integer requireId;

    private String requireRelate;

    private Integer projectId;

    private String projectName;

    private Float todayHour;

    private Integer type;

    private Integer taskLogId;

    private Integer percent;

    private Byte isNormal;

    private String reasonDetail;

    private String taskExplain;

    private Date modifyTm;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getWorkday() {
        return workday;
    }

    public void setWorkday(Date workday) {
        this.workday = workday;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Float getTodayHour() {
        return todayHour;
    }

    public void setTodayHour(Float todayHour) {
        this.todayHour = todayHour;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTaskLogId() {
        return taskLogId;
    }

    public void setTaskLogId(Integer taskLogId) {
        this.taskLogId = taskLogId;
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

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }
}
