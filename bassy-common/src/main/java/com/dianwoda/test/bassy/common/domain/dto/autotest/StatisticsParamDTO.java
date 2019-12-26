package com.dianwoda.test.bassy.common.domain.dto.autotest;

import lombok.Data;

@Data
public class StatisticsParamDTO {
    /**
     * 统计周期
     * 1，周；2，月；3，季度。
     */
    private Integer cycle;
}
