package com.dianwoda.test.bassy.common.domain.dto.testcase;

import lombok.Data;

/**
 * @author zcp
 * @date 2019/8/16 11:52
 */
@Data
public class MergeTestCaseParamDTO {

    /**
     * 需求id
     */
    private Integer requireId;

    /**
     * 项目用例id, 单个用例合并使用
     */
    private Integer programCaseId;

    /**
     * 用例最后编辑人  工号
     */
    private String lastEditedBy;

    /**
     * 用例通过状态 0 未通过(包含 未执行 阻塞 存疑)， 1 已通过
     */
    private Boolean notPass;
}
