package com.dianwoda.test.bassy.common.domain.vo.testcase;

import lombok.Data;

import java.util.List;

/**
 * @author zcp
 * @date 2019/6/4 15:20
 */
@Data
public class TestCaseImportVO {

    private List<Object[]> failCases;
}
