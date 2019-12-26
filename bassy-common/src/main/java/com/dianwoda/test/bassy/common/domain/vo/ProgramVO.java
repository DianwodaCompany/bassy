package com.dianwoda.test.bassy.common.domain.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 19:27
 * Copyright(c) for dianwoda
 */
@Data
public class ProgramVO {
    private Integer id;

    private String programName;

    private String programType;

    private Integer programModule;

    private Integer processModule;

    private JSONObject persons;

    private Object coreNodes;

    private Object requires;

    private Integer requiresId;

    private String remark;

    private String workId;

    private String pressingReason;

    private String status;

    private String innerProjectType;

    private Float eachTaskWorkHour;

    private Integer dailyTaskNum;

    private Date startTime;

    private Date endTime;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

    private String isLoop;
}
