package com.dianwoda.test.bassy.common.enums;

/**
 * Created by zcp on 2018/4/28.
 * Time always， fat thin man all miss.
 */
public enum  ProjectNodeEn {
    FEASIBILITY_REVIEW((byte)0, "可行性评审","FEASIBILITY_REVIEW"),
    REQUIREMENT_REVIEW((byte)10, "需求评审","REQUIREMENT_REVIEW"),
    TECHNICAL_REVIEW((byte)20, "技术评审","TECHNICAL_REVIEW"),
    SYSTEM_DEVELOPMENT((byte)30, "系统开发","SYSTEM_DEVELOPMENT"),
    SYSTEM_COUPLET((byte)40, "系统联调","SYSTEM_COUPLET"),
    TESTCASE_REVIEW((byte)50, "测试用例评审","TESTCASE_REVIEW"),
    BRANCH_TEST((byte)60, "分支测试","BRANCH_TEST"),
    MASTER_TEST((byte)70, "主干测试","MASTER_TEST"),
    GREY_TEST((byte)80, "灰发测试","GREY_TEST"),
    ONLINE_TEST((byte)100, "线上测试","ONLINE_TEST");

    private byte code;
    private String desc;
    private String ename;

    private ProjectNodeEn(byte code, String desc,String ename) {
        this.code = code;
        this.desc = desc;
        this.ename = ename;
    }

    public static ProjectNodeEn toEnum(byte code) {
        ProjectNodeEn[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ProjectNodeEn en = var1[var3];
            if (en.code == code) {
                return en;
            }
        }

        return null;
    }

    public static Boolean contains(String projectNode) {
        ProjectNodeEn[] projectNodeEn = ProjectNodeEn.values();
        for (int i = 0; i < projectNodeEn.length; i++) {
            if (projectNodeEn[i].getEname().equals(projectNode)) {
                return true;
            }
        }
        return false;
    }

    public byte getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getEname(){ return this.ename; }
}
