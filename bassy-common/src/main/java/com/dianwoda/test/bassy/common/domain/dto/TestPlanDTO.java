package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by zcp on 2018/5/22.
 * Time always， fat thin man all miss.
 */
@Data
public class TestPlanDTO {

    private Integer programId;

    List<ProgramTaskDTO> programTasks;

}
