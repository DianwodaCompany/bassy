package com.dianwoda.test.bassy.db.entity;

import java.io.Serializable;
import java.util.Date;

public class DingdingNotify implements Serializable {
    private Integer id;

    private String chatid;

    private Byte type;

    private String content;

    private Date insTm;

    private String msgId;

    private Byte status;

    private Date modifyTm;

    private String notifyTo;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid == null ? null : chatid.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getInsTm() {
        return insTm;
    }

    public void setInsTm(Date insTm) {
        this.insTm = insTm;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getModifyTm() {
        return modifyTm;
    }

    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    public String getNotifyTo() {
        return notifyTo;
    }

    public void setNotifyTo(String notifyTo) {
        this.notifyTo = notifyTo == null ? null : notifyTo.trim();
    }
}