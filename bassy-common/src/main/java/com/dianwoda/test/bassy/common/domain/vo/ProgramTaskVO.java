package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zcp on 2018/6/21.
 * Time always， fat thin man all miss.
 */
@Data
public class ProgramTaskVO {

    private Integer id;

    private Integer programId;

    private String programName;

    private String programProcess;

    private String programProcessName;

    private String requireId;

    //TODO 字段是否可以转换为requireName
    private String requireRelate;

    private String taskCode;

    private String taskName;

    private Date startTm;
    private Date endTm;
    private Float expectHour;
    private Date actualStartTm;
    private Date actualEndTm;
    private Float actualHour;
    private String excludeDate;
    private String includeDate;

    private String status;

    private Integer percent;

    private String tester;

    private String testerName;

    private Date modifyTm;

    private Integer ztTaskId;

    private Integer ztProjectId;
}
