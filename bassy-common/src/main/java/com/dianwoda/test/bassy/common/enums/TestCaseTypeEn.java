package com.dianwoda.test.bassy.common.enums;

import lombok.Getter;

/**
 * @author zcp
 * @date 2019/5/31 14:46
 */
public enum TestCaseTypeEn {

    功能用例(0, "功能用例"),
    接口用例(1, "接口用例");


    @Getter
    int code;
    @Getter
    String desc;

    TestCaseTypeEn(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TestCaseTypeEn toEnum(int code) {
        TestCaseTypeEn[] var = values();
        for (TestCaseTypeEn testCaseTypeEn : var) {
            if (testCaseTypeEn.code == code) {
                return testCaseTypeEn;
            }
        }
        return null;
    }

    public static TestCaseTypeEn toEnum(String desc) {
        TestCaseTypeEn[] var = values();
        for (TestCaseTypeEn testCaseTypeEn : var) {
            if (testCaseTypeEn.desc.equals(desc)) {
                return testCaseTypeEn;
            }
        }
        return null;
    }
}
