package com.dianwoda.test.bassy.common.domain.dto.autotest;

import lombok.Data;

/**
 * 执行测试结果返回值
 */
@Data
public class DoTestCaseResultDTO {

    private Integer autoTestId;

    private String text;
}
