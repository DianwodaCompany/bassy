package com.dianwoda.test.bassy.common.enums;

import lombok.Getter;

/**
 * Created by gaoh on 2018/10/22.
 */
public enum TeamEn {

    SPIDER(0,"商家组","SPIDER"),
    FLASH(1,"骑手组","FLASH"),
    HAWKEYE(2,"支撑组","HAWKEYE"),
    BIGBANGTEST(3,"测开组","BIGBANGTEST");


    @Getter
    int code;
    @Getter
    String desc;
    @Getter
    String ename;

    TeamEn(int code, String desc, String ename) {
        this.code = code;
        this.desc = desc;
        this.ename = ename;
    }


    public static TeamEn toEnum(int code) {
        TeamEn[] var = values();
        for(int i=0; i<var.length; i++) {
            TeamEn en = var[i];
            if(en.code == code) {
                return en;
            }
        }
        return null;
    }
}
