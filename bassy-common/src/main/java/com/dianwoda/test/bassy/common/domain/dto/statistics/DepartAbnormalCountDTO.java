package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

/**
 * Created by gaoh on 2019/2/26.
 */
@Data
public class DepartAbnormalCountDTO {

    private int departId;
    private String departName;
    private int departParentId;
    private String departParentName;
    private int totalScore;
    private float scorePerStaff;
    private String topReasonType;
    private String topReasonMean;
    private String topStaffCode;
    private String topStaffName;
}
