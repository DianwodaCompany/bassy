package com.dianwoda.test.bassy.common.domain.dto.program;

/**
 * Created by zcp on 2018/8/29.
 * Time always， fat thin man all miss.
 * @author zcp
 */
public class ProgramTaskPercentDTO {

    private Integer requireId;
    private String requireRelate;
    private String taskStage;
    private Integer programId;
    private Integer taskId;
    private String taskName;
    private String taskStatus;
    private Integer taskPercent;
    private String tester;
    private String testerName;
    private String taskExplain;

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
        this.requireRelate = requireRelate;
    }

    public String getTaskStage() {
        return taskStage;
    }

    public void setTaskStage(String taskStage) {
        this.taskStage = taskStage;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskPercent() {
        return taskPercent;
    }

    public void setTaskPercent(Integer taskPercent) {
        this.taskPercent = taskPercent;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }

    public String getTesterName() {
        return testerName;
    }

    public void setTesterName(String testerName) {
        this.testerName = testerName;
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }
}
