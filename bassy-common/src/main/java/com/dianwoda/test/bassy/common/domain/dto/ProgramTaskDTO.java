package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by zcp on 2018/5/22.
 * Time always， fat thin man all miss.
 */
@Data
public class ProgramTaskDTO {

    private Integer id;

    private Integer programId;

    private String programProcess;

    private Integer requireId;

    private String requireRelate;

    private Integer taskId;

    private String taskCode;

    private String taskName;

    private Date startTm;

    private Date endTm;

    private String tester;

    private String testerName;

    private String withTester;

    private String withTesterName;

    private Float expectHour;

    private String status;

    private String creator;

    private String modifier;

    private Byte isNormal;

    private String reasonTeam;

    private String reasonType;

    private String reasonLevel;

    private String reasonDetail;

    private Byte abnormalType;

    private String abnormalOwner;

    private Integer ztTaskId;

    private Integer ztProjectId;

    private String excludeDate;

    private String includeDate;

    @Override
    public Object clone() {
        ProgramTaskDTO programTaskDTO = null;
        try{
            programTaskDTO = (ProgramTaskDTO)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return programTaskDTO;
    }

}
