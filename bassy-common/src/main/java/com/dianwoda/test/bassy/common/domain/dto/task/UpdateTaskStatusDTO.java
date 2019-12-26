package com.dianwoda.test.bassy.common.domain.dto.task;

import lombok.Data;

/**
 * Created by zcp on 2018/8/7.
 * Time always， fat thin man all miss.
 */
@Data
public class UpdateTaskStatusDTO {

    private Integer taskId;

    private String status;

    private String modifier;
}
