package com.dianwoda.test.bassy.common.domain.dto.testcase;


/**
 * @author zcp
 * @date 2019/5/15 20:53
 */
public class BaseCaseLabelDTO {

    private Integer caseId;

    private Integer labelId;

    private String labelName;

    private String type;

    /**
     * 操作类型  1:新增，2:删除
     */
    private Integer operation;

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}
