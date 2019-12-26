package com.dianwoda.test.bassy.common.domain.vo.testcase;

import lombok.Data;

/**
 * Created by gaoh on 2019/8/14.
 */
@Data
public class ProCaseRequireInfoVO {

    private Integer requireId;

    private String requireName;

    /**
     * 总用例数
     */
    private Integer totalCase;

    /**
     * 未合基线用例数
     */
    private Integer unpushCase;

    /**
     * 执行率
     */
    private Float runPercent;

    /**
     * 通过率
     */
    private Float passPercent;
}
