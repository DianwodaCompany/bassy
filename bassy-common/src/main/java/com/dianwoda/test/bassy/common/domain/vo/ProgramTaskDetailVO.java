package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zcp on 2018/6/22.
 * Time always， fat thin man all miss.
 */
@Data
public class ProgramTaskDetailVO implements Serializable {

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

    private Date actualStartTm;

    private Date actualEndTm;

    private Date closeTm;

    private Date pauseTm;

    private String tester;

    private String withTester;

    private Float expectHour;

    private String status;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

    private Float actualHour;

    private Float todayHour;

    private Integer percent;

    private Byte isNormal;

}
