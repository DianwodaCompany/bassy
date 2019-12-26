package com.dianwoda.test.bassy.common.enums;

/**
 * Created by zcp on 2018/4/28.
 * Time always， fat thin man all miss.
 */
public enum TestTaskEn {

    PARTAKE_REVIEW((byte)0, "参与评审","PARTAKE_REVIEW"),
    TEST_RESOURCE_EVALUATION((byte)2, "测试资源评估","TEST_RESOURCE_EVALUATION"),
    FEASIBILITY_DISCUSSION((byte)3, "可行性讨论","FEASIBILITY_DISCUSSION"),
    REQUIREMENT_REFINEMENT_ANALYSIS((byte)4, "需求细化分析&讨论","REQUIREMENT_REFINEMENT_ANALYSIS"),
    PRELIMINARY_FORMULATION_OF_TEST_PLAN((byte)5, "测试计划初步制定","PRELIMINARY_FORMULATION_OF_TEST_PLAN"),
    TECHNICAL_REQUIREMENT_REFINEMENT_ANALYSIS((byte)6, "技术文档细化分析&讨论","TECHNICAL_REQUIREMENT_REFINEMENT_ANALYSIS"),
    DETAILE_FORMULATION_OF_TEST_PLAN((byte)7, "测试计划详细制定","DETAILE_FORMULATION_OF_TEST_PLAN"),
    TEST_ANALYSIS_AND_COMPILATION((byte)8, "测试分析编写","TEST_ANALYSIS_AND_COMPILATION"),
    TEST_CASE_COMPILATION((byte)9, "测试用例编写","TEST_CASE_COMPILATION"),
    SMOKE_CASE_COMPILATION((byte)10, "冒烟用例编写","SMOKE_CASE_COMPILATION"),
    TES_ENVIRONMENT_PREPARATION((byte)11, "测试环境准备","TES_ENVIRONMENT_PREPARATION"),
    TES_DATA_PREPARATION((byte)12, "测试数据准备","TES_DATA_PREPARATION"),
    PERFORMANCE_TEST_SCHEME((byte)13, "性能测试方案","PERFORMANCE_TEST_SCHEME"),
    PERFORMANCE_TEST_PREPARATION((byte)14, "性能测试准备","PERFORMANCE_TEST_PREPARATION"),
    INTERFACE_CASE_COMPILATION((byte)15, "接口用例","INTERFACE_CASE_COMPILATION"),
    INTERFACE_CODE_COMPILATION((byte)16, "接口脚本","INTERFACE_CODE_COMPILATION"),
    OPERATIONAL_GUIDANCE((byte)17, "操作指导","OPERATIONAL_GUIDANCE"),
    SMOKE_TEST_CHECK((byte)18, "冒烟验收","SMOKE_TEST_CHECK"),
    BRANCH_TEST((byte)19, "分支测试","BRANCH_TEST"),
    INTERFACE_AUTO_TEST((byte)20, "接口自动化","INTERFACE_AUTO_TEST"),
    UI_AUTO_TEST((byte)21, "UI自动化","UI_AUTO_TEST"),
    UI_PERFORMANCE_TEST((byte)22, "前端性能测试","UI_PERFORMANCE_TEST"),
    INTERFACE_PERFORMANCE_TEST((byte)23, "接口性能测试","INTERFACE_PERFORMANCE_TEST"),
    MASTER_TEST((byte)24, "主干测试","MASTER_TEST"),
    QUALITY_REPORT((byte)25, "质量报告","QUALITY_REPORT"),
    COOPERATE_ACCEPTANCE((byte)26, "配合验收","COOPERATE_ACCEPTANCE"),
    GREY_TEST((byte)27, "灰发测试","GREY_TEST"),
    ONLINE_TEST((byte)28, "线上回归","ONLINE_TEST");

    private byte code;
    private String desc;
    private String ename;

    private TestTaskEn(byte code, String desc,String ename) {
        this.code = code;
        this.desc = desc;
        this.ename = ename;
    }

    public static TestTaskEn toEnum(byte code) {
        TestTaskEn[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TestTaskEn en = var1[var3];
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

    public String getEname(){ return this.ename; }
}
