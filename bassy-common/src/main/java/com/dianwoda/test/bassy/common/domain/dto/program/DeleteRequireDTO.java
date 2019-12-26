package com.dianwoda.test.bassy.common.domain.dto.program;

import lombok.Data;

/**
 * @author zcp
 * @date 2019/3/25 14:18
 */
@Data
public class DeleteRequireDTO {

    /**
     * 项目id
     */
    private Integer programId;
    /**
     * 需求id
     */
    private Integer requireId;

    /**
     * 修改人
     */
    private String modifier;
}
