package com.dianwoda.test.bassy.common.enums;

/**
 * Created by zcp on 2018/5/2.
 * Time always， fat thin man all miss.
 */
public enum  DictionaryEn {

    PROJECT_NODE((byte)0, "项目节点","PROJECT_NODE"),
    TEST_TASK((byte)1, "关联任务","TEST_TASK"),
    INNER_PROJECT_TYPE((byte)3, "内部项目类型","INNER_PROJECT_TYPE");

    private byte code;
    private String desc;
    private String ename;

    private DictionaryEn(byte code, String desc, String ename) {
        this.code = code;
        this.desc = desc;
        this.ename = ename;
    }

    public static DictionaryEn toEnum(byte code) {
        DictionaryEn[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            DictionaryEn en = var1[var3];
            if (en.code == code) {
                return en;
            }
        }

        return null;
    }

    public byte getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getEname() { return this.ename; }
}
