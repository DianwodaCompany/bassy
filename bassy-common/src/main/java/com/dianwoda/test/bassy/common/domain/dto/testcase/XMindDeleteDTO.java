package com.dianwoda.test.bassy.common.domain.dto.testcase;

import lombok.Data;

/**
 * @author zcp
 * @date 2019/7/3 17:59
 */
@Data
public class XMindDeleteDTO {

    private Integer caseId;

    private Integer stepId;

    private String verifyType;

    private String labelType;
}
