package com.dianwoda.test.bassy.common.domain.dto.testcase;

/**
 * @author zcp
 * 2019/12/22 下午5:02
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public class BaseCaseStepDTO {

    private Integer id;

    private Integer caseId;

    private Long stepId;

    private String desc;

    private String expectDb;

    private String expectUi;

    private String expectResponse;

    private String expectOther;

    private Byte executeStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getExpectDb() {
        return expectDb;
    }

    public void setExpectDb(String expectDb) {
        this.expectDb = expectDb == null ? null : expectDb.trim();
    }

    public String getExpectUi() {
        return expectUi;
    }

    public void setExpectUi(String expectUi) {
        this.expectUi = expectUi == null ? null : expectUi.trim();
    }

    public String getExpectResponse() {
        return expectResponse;
    }

    public void setExpectResponse(String expectResponse) {
        this.expectResponse = expectResponse == null ? null : expectResponse.trim();
    }

    public String getExpectOther() {
        return expectOther;
    }

    public void setExpectOther(String expectOther) {
        this.expectOther = expectOther == null ? null : expectOther.trim();
    }

    public Byte getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(Byte executeStatus) {
        this.executeStatus = executeStatus;
    }
}
