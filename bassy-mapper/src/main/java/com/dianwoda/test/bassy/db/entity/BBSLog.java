package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class BBSLog implements Serializable {
    private Integer id;

    private String staffCode;

    private Integer bbsId;

    private Short operation;

    private Date insTm;

    private Date updTm;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode == null ? null : staffCode.trim();
    }

    public Integer getBbsId() {
        return bbsId;
    }

    public void setBbsId(Integer bbsId) {
        this.bbsId = bbsId;
    }

    public Short getOperation() {
        return operation;
    }

    public void setOperation(Short operation) {
        this.operation = operation;
    }

    public Date getInsTm() {
        return insTm;
    }

    public void setInsTm(Date insTm) {
        this.insTm = insTm;
    }

    public Date getUpdTm() {
        return updTm;
    }

    public void setUpdTm(Date updTm) {
        this.updTm = updTm;
    }
}