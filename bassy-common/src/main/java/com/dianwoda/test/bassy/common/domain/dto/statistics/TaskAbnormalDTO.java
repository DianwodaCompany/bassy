package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class TaskAbnormalDTO implements Serializable {

    private Integer id;
    private Integer projectId;
    private String projectName;
    private Integer storyId;
    private String storyTitle;
    private Integer taskId;
    private String taskName;
    private String taskStatus;
    private String reasonTeamId;
    private String reasonTeamName;
    private String reasonTypeId;
    private String reasonTypeName;
    private String reasonLevel;
    private String reasonDetail;
    private Integer currentNormalState;
    private String testerCode;
    private String testerName;
    private Date modifyTime;
    private int abnormalType;
    private String abnormalOwnerCode;
    private String abnormalOwnerName;
    private Integer duplicateAbnormal;
    private String dumplicateDetail;

}
