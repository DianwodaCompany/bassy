package com.dianwoda.test.bassy.common.enums;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 15:59
 * Copyright(c) for dianwoda
 */
@AllArgsConstructor
public enum ProgramStatusEn {
    INIT("init", "待排期"),
    SCHEDULED("scheduled", "已排期"),
    PROCESSING("processing", "进行中"),
    PAUSE("pause", "已暂停"),
    END("end", "已结束"),
    CLOSE("close", "已关闭");

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

    public static Map<String, String> getAll() {
        Map<String, String> map = new HashMap<>();
        ProgramTypeEn[] programTypeEns = ProgramTypeEn.values();
        for (int i = 0; i < programTypeEns.length; i++) {
            map.put(programTypeEns[i].getCode(),programTypeEns[i].getName());
        }
        return map;
    }

    public static Boolean contains(String projectNode) {
        ProgramStatusEn[] programStatusEns = ProgramStatusEn.values();
        for (int i = 0; i < programStatusEns.length; i++) {
            if (programStatusEns[i].getCode().equals(projectNode)) {
                return true;
            }
        }
        return false;
    }
}
