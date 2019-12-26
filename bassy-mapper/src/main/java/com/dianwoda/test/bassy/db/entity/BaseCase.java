package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseCase implements Serializable {
    private Integer id;

    private Integer product;

    private Integer module;

    private Integer require;

    private String title;

    private Byte pri;

    private String type;

    private String status;

    private String lastEditedBy;

    private Date lastEditedDate;

    private Byte version;

    private Integer parentCase;

    private Byte family;

    private Byte executeStatus;

    private Byte edited;

    private Byte pushed;

    private Byte deleted;

    private String precondition;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getModule() {
        return module;
    }

    public void setModule(Integer module) {
        this.module = module;
    }

    public Integer getRequire() {
        return require;
    }

    public void setRequire(Integer require) {
        this.require = require;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Byte getPri() {
        return pri;
    }

    public void setPri(Byte pri) {
        this.pri = pri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy == null ? null : lastEditedBy.trim();
    }

    public Date getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Date lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }

    public Integer getParentCase() {
        return parentCase;
    }

    public void setParentCase(Integer parentCase) {
        this.parentCase = parentCase;
    }

    public Byte getFamily() {
        return family;
    }

    public void setFamily(Byte family) {
        this.family = family;
    }

    public Byte getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(Byte executeStatus) {
        this.executeStatus = executeStatus;
    }

    public Byte getEdited() {
        return edited;
    }

    public void setEdited(Byte edited) {
        this.edited = edited;
    }

    public Byte getPushed() {
        return pushed;
    }

    public void setPushed(Byte pushed) {
        this.pushed = pushed;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getPrecondition() {
        return precondition;
    }

    public void setPrecondition(String precondition) {
        this.precondition = precondition == null ? null : precondition.trim();
    }
}