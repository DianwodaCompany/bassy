package com.dianwoda.test.bassy.common.enums;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zcp on 2018/6/15.
 * Time always， fat thin man all miss.
 */
@AllArgsConstructor
public enum TestTaskStatusEn {

    INIT("init", "未开始"),
    PROCESSING("processing", "进行中"),
    PAUSE("pause", "已暂停"),
    CLOSE("close", "已关闭"),
    END("end", "已完成");

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


    public static TestTaskStatusEn getByCode(String code) {
        TestTaskStatusEn[] testTaskStatusEns = TestTaskStatusEn.values();
        for (int i = 0; i < testTaskStatusEns.length; i++) {
            if (testTaskStatusEns[i].getCode().equals(code)) {
                return testTaskStatusEns[i];
            }
        }
        return null;
    }
}
