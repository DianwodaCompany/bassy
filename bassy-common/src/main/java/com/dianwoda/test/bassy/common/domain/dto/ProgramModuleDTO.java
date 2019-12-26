package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author lichengkai
 * 2018 - 05 - 02 : 10:42
 * Copyright(c) for dianwoda
 */
@Data
public class ProgramModuleDTO {
    private Integer id;

    private String moduleName;

    private String description;

    private String parentCode;

    private String persons;

    private String coreNodes;

    private String requires;

    private String remark;

    private String readOnly;

    private String workId;

    private String pressingReason;

    private String isCheck;

    private String innerProjectType;

    private Integer eachTaskWorkHour;

    private Integer dailyTaskNum;

    private String isLoop;

    private String status;

    private String creator;

    private String modifier;

    private Date createTm;

    private Date modifyTm;

}
