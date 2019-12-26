package com.dianwoda.test.bassy.common.enums;

import lombok.AllArgsConstructor;

/**
 * Created by zcp on 2018/5/17.
 * Time always， fat thin man all miss.
 */
@AllArgsConstructor
public enum ProcessTaskRelateEn {

    ENABLE("enable","生效"),
    UNABLE("unable","失效");

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
