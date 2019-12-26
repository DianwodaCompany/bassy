package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class AssetLog implements Serializable {
    private Integer id;

    private Integer assetId;

    private Short status;

    private String borrower;

    private Date borrowTm;

    private Date returnTm;

    private String remark;

    private String modifier;

    private Date modifyTm;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower == null ? null : borrower.trim();
    }

    public Date getBorrowTm() {
        return borrowTm;
    }

    public void setBorrowTm(Date borrowTm) {
        this.borrowTm = borrowTm;
    }

    public Date getReturnTm() {
        return returnTm;
    }

    public void setReturnTm(Date returnTm) {
        this.returnTm = returnTm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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