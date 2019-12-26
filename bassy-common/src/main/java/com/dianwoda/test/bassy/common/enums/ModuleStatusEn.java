package com.dianwoda.test.bassy.common.enums;

import lombok.AllArgsConstructor;

/** ProgramDocumentDTO module enum
 * @author lichengkai
 * 2018 - 05 - 02 : 10:08
 * Copyright(c) for dianwoda
 */
@AllArgsConstructor
public enum ModuleStatusEn {

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
