package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class ProcessModule implements Serializable {
    private Integer id;

    private String moduleName;

    private String description;

    private String programModule;

    private String processNode;

    private String readOnly;

    private String status;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

    public ProgramModule getProgramModuleEntity() {
        return programModuleEntity;
    }

    public void setProgramModuleEntity(ProgramModule programModuleEntity) {
        this.programModuleEntity = programModuleEntity;
    }

    private ProgramModule programModuleEntity;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName == null ? null : moduleName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProgramModule() {
        return programModule;
    }

    public void setProgramModule(String programModule) {
        this.programModule = programModule == null ? null : programModule.trim();
    }

    public String getProcessNode() {
        return processNode;
    }

    public void setProcessNode(String processNode) {
        this.processNode = processNode == null ? null : processNode.trim();
    }

    public String getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(String readOnly) {
        this.readOnly = readOnly == null ? null : readOnly.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Date getmodifyTm() {
        return modifyTm;
    }

    public void setmodifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}