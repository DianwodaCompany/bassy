package com.dianwoda.test.bassy.common.domain.dto.program;

import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;

import java.util.Date;

/**
 * @author zcp
 * 2019/12/20 下午8:50
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public class DailyReportParamDTO extends ParamDTO {

    private Integer projectId;

    private String projectName;

    private Date reportDate;

    private String projectStage;

    private String bugSummary;

    private String taskPercent;

    private String projectRisk;

    private String creator;

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
        this.projectName = projectName;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getProjectStage() {
        return projectStage;
    }

    public void setProjectStage(String projectStage) {
        this.projectStage = projectStage;
    }

    public String getBugSummary() {
        return bugSummary;
    }

    public void setBugSummary(String bugSummary) {
        this.bugSummary = bugSummary;
    }

    public String getTaskPercent() {
        return taskPercent;
    }

    public void setTaskPercent(String taskPercent) {
        this.taskPercent = taskPercent;
    }

    public String getProjectRisk() {
        return projectRisk;
    }

    public void setProjectRisk(String projectRisk) {
        this.projectRisk = projectRisk;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
