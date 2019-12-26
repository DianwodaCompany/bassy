package com.dianwoda.test.bassy.common.domain.dto.autotest;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SuiteInfoDTO implements Serializable {

    /**
     * 套件名称
     */
    private String suiteName;

    /**
     * 测试类型
     */
    private String testType;

    /**
     *aa已选用例的key
     */
    private List  keys;
}
