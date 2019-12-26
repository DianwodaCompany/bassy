package com.dianwoda.test.bassy.common.enums;

import lombok.Getter;

/**
 * Created by gaoh on 2019/1/29.
 */
public enum AssetTypeEn {

    PHONE(10, "手机"),
    COMPUTER(20, "电脑"),
    FITTINGS(30, "配件"),
    BOOK(40, "图书");

    @Getter
    private int code;

    @Getter
    private String mean;


    AssetTypeEn(int code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    public static AssetTypeEn toEnum(int code) {
        AssetTypeEn[] var = values();
        for(int i=0; i<var.length; i++) {
            AssetTypeEn en = var[i];
            if(en.code == code) {
                return en;
            }
        }
        return null;
    }
}
