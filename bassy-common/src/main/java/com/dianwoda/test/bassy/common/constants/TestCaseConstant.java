package com.dianwoda.test.bassy.common.constants;


public class TestCaseConstant {

    //产品及模块
    public final static Integer TARGET_PRODUCT = 0;
    public final static Integer TARGET_PRODUCT_MODULE = 1;

    //产品状态
    public final static String PRODUCT_ENABLE = "0";
    public final static String PRODUCT_UNABLE = "1";

    //产品状态
    public final static Integer PRODUCT_ADD = 0;
    public final static Integer PRODUCT_DELETE = 1;
    public final static Integer PRODUCT_UPDATE = 2;

    //产品模块状态
    public final static String PRODUCT_MODULE_ENABLE = "0";
    public final static String PRODUCT_MODULE_UNABLE = "1";

    //产品模块状态
    public final static Integer PRODUCT_MODULE_ADD = 0;
    public final static Integer PRODUCT_MODULE_DELETE = 1;
    public final static Integer PRODUCT_MODULE_UPDATE = 2;

    //测试用例状态
    public final static String ENABLE = "1";
    public final static String UNABLE = "0";

    //测试用例标签类型
    public final static String BUSINESS_CASE = "1";
    public final static String API_CASE = "2";

    //测试用例标签类型
    public final static String SYSTEM_LABEL = "system";
    public final static String BASE_LABEL = "base";

    //测试用例类型
    public final static Byte BASE_CASE = 0;
    public final static Byte PROGRAM_CASE = 1;

    //xMind用例节点类型
    public final static String ROOT_NODE_ID = "rmind_root_node";
    public final static String ROOT_NODE_TEXT = "测试用例集合";
    public final static String CASE_STEP_CN = "步骤";
    public final static String MINDMAP_ID = "rmind_mindmap_wrapper";
    public final static String ROOT = "root";
    public final static String PRODUCT = "product";
    public final static String PRODUCT_MODULE = "product_module";
    public final static String CASE = "case";
    public final static String CASE_PRECONDITION = "precondition";
    public final static String CASE_PRI = "pri";
    public final static String CASE_STEP = "step";
    public final static String CASE_STEP_DESCRIBE = "step_describe";
    public final static String CASE_LABEL = "label";
    public final static String CASE_LABEL_CN = "标签";
    public final static String CASE_LABEL_SYSTEM = "system";
    public final static String CASE_LABEL_BASE = "base";
    public final static String EXCEPT_DB = "except_db";
    public final static String EXCEPT_UI = "except_ui";
    public final static String EXCEPT_RESPONSE = "except_response";
    public final static String EXCEPT_OTHER = "except_other";

    //测试用例执行状态
    public final static Byte CASE_UNEXECUTE = 0;
    public final static Byte CASE_EXECUTE_PASS = 1;
    public final static Byte CASE_EXECUTE_BLOCK = 2;
    public final static Byte CASE_EXECUTE_DOUBT = 3;

    //测试用例编辑状态
    public final static Byte CASE_EDITED= 1;
    public final static Byte CASE_UNEDITED = 0;

    //测试用例向基线用例合并状态
    public final static Byte CASE_PUSHED= 1;
    public final static Byte CASE_UNPUSHED = 0;
}
