package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

/**
 * Created by zcp on 2018/6/15.
 * Time always， fat thin man all miss.
 */
@Data
public class ProcessTaskDTO {

    private String taskCode;

    private String taskName;

    private Boolean taskNeed;
}
