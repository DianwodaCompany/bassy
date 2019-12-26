package com.dianwoda.test.bassy.common.enums;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Program type enum
 *
 * @author lichengkai
 * 2018 - 04 - 28 : 20:23
 * Copyright(c) for dianwoda
 */
@AllArgsConstructor
public enum ProgramTypeEn {
    PROGRAM("program", "立项项目"),
    INTERNAL("inner", "内部项目"),
    PRESSING("urgent", "紧急项目"),
    NORMAL("normal", "常规项目");

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

    public static Boolean contains(String typeCode) {
        ProgramTypeEn[] programTypeEns = ProgramTypeEn.values();
        for (int i = 0; i < programTypeEns.length; i++) {
            if (programTypeEns[i].getCode().equals(typeCode)) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, String> getAllProgramType() {
        Map<String, String> map = new HashMap<>();
        ProgramTypeEn[] programTypeEns = ProgramTypeEn.values();
        for (int i = 0; i < programTypeEns.length; i++) {
            map.put(programTypeEns[i].getCode(),programTypeEns[i].getName());
        }
        return map;
    }
}
