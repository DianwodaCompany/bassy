package com.dianwoda.test.bassy.common.domain.dto.program;

import lombok.Data;

/**
 * @author zcp
 * @date 2019/3/26 11:39
 */
@Data
public class TransferRequireDTO {

    /**
     * 需求id
     */
    private Integer requireId;

    /**
     * 目标项目id
     */
    private Integer targetProgramId;

    /**
     * 原项目id
     */
    private Integer sourcesProgramId;
}
