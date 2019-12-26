package com.dianwoda.test.bassy.common.domain.dto.statistics;


import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;

/**
 * Created by gaoh on 2018/10/23.
 */
public class TaskAbnormalRequestDTO extends ParamDTO {

    private String sDate;
    private String eDate;
    private String story;

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getReasonTeam() {
        return reasonTeam;
    }

    public void setReasonTeam(String reasonTeam) {
        this.reasonTeam = reasonTeam;
    }

    public Byte getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(Byte abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getAbnormalOwner() {
        return abnormalOwner;
    }

    public void setAbnormalOwner(String abnormalOwner) {
        this.abnormalOwner = abnormalOwner;
    }

    private String reasonTeam;
    private Byte abnormalType;
    private String abnormalOwner;

}
