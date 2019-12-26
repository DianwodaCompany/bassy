package com.dianwoda.test.bassy.common.domain.dto.program;

import lombok.Data;

/**
 * @author zcp
 * @date 2019/3/28 19:50
 */
@Data
public class ProgramBugInfoDTO {

    /**
     * 未修复bug数
     */
    private Integer notResolvedTotalBug;

    /**
     * 24小时未修复bug数
     */
    private Integer notResolvedTotalBugMoreThanOneDay;

    /**
     * 今日新增bug数
     */
    private Integer todayNewTotalBug;

    /**
     * 今日关闭bug数
     */
    private Integer todayCloseTotalBug;

    /**
     * 24小时没有回归bug数
     */
    private Integer resolvedTotalBugMoreThanOneDay;

    /**
     * 总bug数
     */
    private Integer todayTotal;
}
