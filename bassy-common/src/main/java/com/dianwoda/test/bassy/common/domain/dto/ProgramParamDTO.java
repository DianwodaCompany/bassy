package com.dianwoda.test.bassy.common.domain.dto;

/**
 * Created by zcp on 2018/6/5.
 * Time always， fat thin man all miss.
 */
public class ProgramParamDTO extends ParamDTO{

    private String status;

    private String name;

    private String creator;
    
    private String to;

    private String[] programType;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getProgramType() {
        return programType;
    }

    public void setProgramType(String[] programType) {
        this.programType = programType;
    }
}
