package com.dianwoda.test.bassy.common.enums;

import lombok.AllArgsConstructor;

/**
 * Created by zcp on 2018/7/12.
 * Time always， fat thin man all miss.
 */
@AllArgsConstructor
public enum AutoTestTypeEn {

    webUI测试("webUI-test","webUI测试"),
    商家接口测试("spider-test","商家接口测试"),
    openApi接口测试("openApi-test","openAPI接口测试"),
    charge接口测试("charge-test","charge接口测试"),
    dispatch接口测试("dispatch-test","dispatch接口测试"),
    骑手接口测试("flash-test","骑手接口测试"),
    工单测试("workorder-test","工单测试"),
    消息中心测试("notify-test","消息中心测试");

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
