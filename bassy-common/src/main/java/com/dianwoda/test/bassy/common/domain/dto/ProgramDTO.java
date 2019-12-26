package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by zcp on 2018/5/24.
 * Time always， fat thin man all miss.
 */
@Data
public class ProgramDTO {
    private Integer id;

    private String programName;

    private String programType;

    private Integer programModule;

    private Integer processModule;

    private String persons;

    private String coreNodes;

    private List<ProgramRequireDTO> requires;

    private String remark;

    private String workId;

    private String pressingReason;

    private String isCheck;

    private Date startTime;

    private Date endTime;

    private String status;

    private String innerProjectType;

    private Float eachTaskWorkHour;

    private Integer dailyTaskNum;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

}
