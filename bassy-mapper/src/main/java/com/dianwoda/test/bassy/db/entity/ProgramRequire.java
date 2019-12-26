package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class ProgramRequire implements Serializable {
    private Integer id;

    private Integer requireId;

    private String requireName;

    private Integer programId;

    private Integer ztProjectId;

    private String ztProjectName;

    private Integer status;

    private Date createTm;

    private Date modifyTm;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequireId() {
        return requireId;
    }

    public void setRequireId(Integer requireId) {
        this.requireId = requireId;
    }

    public String getRequireName() {
        return requireName;
    }

    public void setRequireName(String requireName) {
        this.requireName = requireName == null ? null : requireName.trim();
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getZtProjectId() {
        return ztProjectId;
    }

    public void setZtProjectId(Integer ztProjectId) {
        this.ztProjectId = ztProjectId;
    }

    public String getZtProjectName() {
        return ztProjectName;
    }

    public void setZtProjectName(String ztProjectName) {
        this.ztProjectName = ztProjectName == null ? null : ztProjectName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }
}