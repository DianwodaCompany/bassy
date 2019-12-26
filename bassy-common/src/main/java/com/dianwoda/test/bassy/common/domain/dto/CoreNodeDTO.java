package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by zcp on 2018/6/15.
 * Time always， fat thin man all miss.
 */
@Data
public class CoreNodeDTO {

    private String projectNode;

    private Date startTime;

    private Date endTime;

    private Boolean demandNeed;
}
