package com.dianwoda.test.bassy.common.exception.error;

public enum BassyError implements IError {

    /**
     * 系统异常/数据访问异常
     */
    SYS_ERROR("000", "系统异常"),
    DATABSE_ERROR("001", "数据访问异常"),

    /**
     * Client Error 4xx
     */
    SYS_101("101", "无权限操作"),

    /**
     *
     */
    PARAM_ERROR("2000", "参数不合法"),

    TEST_CASE_VERSION_ERROR("3000", "测试用例版本号不合法"),


    REPORT_NO_EXIT("5001","报告不存在");

    private String code;
    private String message;

    BassyError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String toString() {
        return code + ":" + message;
    }

}
