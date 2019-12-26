package com.dianwoda.test.bassy.common.enums;

import lombok.Getter;

/**
 * Created by gaoh on 2019/2/28.
 */
public enum DepartEn {

    TEST_SPIDER(661,"商家业务", 3, 263, "测试"),
    TEST_FLASH(462,"骑手业务", 8, 263, "测试"),
    TEST_HAWKEYE(463,"支撑业务", 8, 263, "测试"),
    TEST_BIGBANGTEST(461,"测试开发", 4, 263, "测试"),
    PM(279,"项目管理", 5, 279,"项目管理"),
    BASE_DBA(267,"数据运维", 3, 264, "基础平台"),
    BASE_IT(266,"基础运维", 10, 264, "基础平台"),
    UI_BUSINESS(269,"业务支撑", 11, 268, "大前端"),
    UI_MIDDLE(278,"中台支撑", 13, 268, "大前端"),
    UI_APP(270,"移动支撑", 9, 268, "大前端"),
    UI_Operate(272,"运营支撑", 7, 268, "大前端"),
    BUSINESSDEV_ORDER(281,"订单中心", 9, 280, "业务平台"),
    BUSINESSDEV_DISPATCH(328,"调度", 9, 280, "业务平台"),
    BUSINESSDEV_OPEN(283,"开放平台", 8, 280, "业务平台"),
    BUSINESSDEV_TOOLS(282,"工具平台", 12, 280, "业务平台"),
    BUSINESSDEV_FLASH(284,"骑手中心", 15, 280, "业务平台"),
    BUSINESSDEV_SPIDER(285,"商家中心", 9, 280, "业务平台"),
    INSTITUTE_ALGORITHM(327,"算法", 13, 321, "研究院"),
    BIGDATA_ENGINE(325,"算法工程", 4, 322, "研究院-大数据"),
    BIGDATA_BUSINESS(326,"业务发展", 5, 322, "研究院-大数据"),
    BIGDATA_PLATFORM(324,"应用平台", 6, 322, "研究院-大数据"),
    BIGDATA_WAREHOUSE(881,"数据仓库", 8, 322, "研究院-大数据"),
    PD_UI(864,"设计部", 9, 258, "产品中心"),
    PD_FLASH(761,"骑手产品部", 5, 260, "产品中心-产品策划"),
    PD_BUSINESS(764,"业务产品部", 5, 260, "产品中心-产品策划"),
    PD_STRATEGY(762,"策略产品部", 5, 260, "产品中心-产品策划"),
    PD_MIDDLE(763,"中台产品部", 8, 260, "产品中心-产品策划"),
    PD_OA(261,"流程信息", 4, 258, "产品中心"),
    PD_RISK(584,"风险控制", 5, 258, "产品中心");


    @Getter
    int id;
    @Getter
    String name;
    @Getter
    int number;
    @Getter
    int parentId;
    @Getter
    String ParentName;

    DepartEn(int id, String name, int number, int parentId, String ParentName) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.parentId = parentId;
        this.ParentName = ParentName;
    }


    public static DepartEn toEnum(int id) {
        DepartEn[] var = values();
        for(int i=0; i<var.length; i++) {
            DepartEn en = var[i];
            if(en.id == id) {
                return en;
            }
        }
        return null;
    }
}
