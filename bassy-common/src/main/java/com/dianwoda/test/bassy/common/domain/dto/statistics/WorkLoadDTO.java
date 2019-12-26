package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

/**
 * Created by gaoh on 2019/3/27.
 */
@Data
public class WorkLoadDTO {
    String staffCode;
    String staffName;
    int departId;
    String departName;
    float workLoad;
    int placing;
    float preWorkLoad;
    int prePlacing;
    float hisWorkLoad;
}
