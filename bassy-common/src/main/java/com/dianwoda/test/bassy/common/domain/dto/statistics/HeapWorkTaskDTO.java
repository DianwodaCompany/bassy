package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class HeapWorkTaskDTO implements Serializable {

    private Integer projectId;
    private String projectName;
    private Integer taskId;
    private String taskName;
    private String taskStatus;
    private String taskStatusName;
    private Integer percent;
    private Integer storyId;
    private String storyTitle;
    private Float hour;
    private String actualStartTm;
    private String actualEndTm;
    private Float actualHour;
    private String startTm;
    private String endTm;
    private Float expectHour;
    private String excludeDate;
    private String includeDate;
}
