package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class TaskAbnormalCountDTO implements Serializable {

    private Integer productCount;
    private Integer developCount;
    private Integer testCount;
    private Integer operationCount;
    private Integer dbaCount;
    private Integer otherCount;
    private List<TaskAbnormalCountDetailDTO> taskAbnormalCountDetailDTOList;
}
