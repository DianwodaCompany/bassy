package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseCaseLabel implements Serializable {
    private Integer id;

    private Integer caseId;

    private Integer labelId;

    private String creator;

    private Date creatorTm;

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

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatorTm() {
        return creatorTm;
    }

    public void setCreatorTm(Date creatorTm) {
        this.creatorTm = creatorTm;
    }
}