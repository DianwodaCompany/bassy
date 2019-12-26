package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by zcp on 2018/6/15.
 * Time always， fat thin man all miss.
 */
@Data
public class ProcessNodeDTO {

    private String processNodeCode;

    private String processNodeValue;

    private List<ProcessTaskDTO> task;
}
