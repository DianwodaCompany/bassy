package com.dianwoda.test.bassy.common.enums;

import lombok.AllArgsConstructor;

/**
 * Created by zcp on 2018/7/12.
 * Time always， fat thin man all miss.
 */
@AllArgsConstructor
public enum AutoTestExecuteTypeEn {

    预约执行("FIX_TIME","预约执行"),
    立即执行("IMMEDIATE","立即执行"),
    集成执行("INTEGRATE","集成执行");

    private String code;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
