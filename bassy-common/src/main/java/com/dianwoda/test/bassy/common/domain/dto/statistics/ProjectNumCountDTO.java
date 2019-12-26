package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class ProjectNumCountDTO implements Serializable {

    private Integer totalCount;
    private Integer processingCount;
    private Integer initCount;
    private Integer endCount;

}
