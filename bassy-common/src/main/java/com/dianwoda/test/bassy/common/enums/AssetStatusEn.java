package com.dianwoda.test.bassy.common.enums;

import lombok.Getter;

/**
 * Created by gaoh on 2019/1/29.
 */
public enum  AssetStatusEn {

    UN_USE((short)10, "闲置"),
    IN_USE((short)20, "领用"),
    REPAIR((short)30, "报修"),
    ABANDON((short)40, "作废");

    @Getter
    private short code;

    @Getter
    private String mean;

    AssetStatusEn(short code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    public static AssetStatusEn toEnum(short code) {
        AssetStatusEn[] var = values();
        for(int i=0; i<var.length; i++) {
            AssetStatusEn en = var[i];
            if(en.code == code) {
                return en;
            }
        }
        return null;
    }
}
