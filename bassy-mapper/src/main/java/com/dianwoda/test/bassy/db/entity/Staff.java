package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;

public class Staff implements Serializable {
    private Integer id;

    private Byte departId;

    private String staffCode;

    private String staffName;

    private Byte status;

    private String dingdingUserid;

    private String dingdingChatid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getDepartId() {
        return departId;
    }

    public void setDepartId(Byte departId) {
        this.departId = departId;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDingdingUserid() {
        return dingdingUserid;
    }

    public void setDingdingUserid(String dingdingUserid) {
        this.dingdingUserid = dingdingUserid == null ? null : dingdingUserid.trim();
    }

    public String getDingdingChatid() {
        return dingdingChatid;
    }

    public void setDingdingChatid(String dingdingChatid) {
        this.dingdingChatid = dingdingChatid == null ? null : dingdingChatid.trim();
    }
}