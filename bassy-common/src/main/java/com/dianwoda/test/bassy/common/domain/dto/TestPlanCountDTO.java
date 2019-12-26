package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

/**
 * Created by Chen WenJie on 2019/1/4.
 */
@Data
public class TestPlanCountDTO {
    /**
     * 批量修改总条数
     */
    private int totalCount;
    /**
     * 批量修改成功条数
     */
    private int successCount;
    /**
     * 批量修改失败条数
     */
    private int failCount;

    /**
     * 项目状态更新结果
     */
    private boolean projectStatusUpdate;
}
