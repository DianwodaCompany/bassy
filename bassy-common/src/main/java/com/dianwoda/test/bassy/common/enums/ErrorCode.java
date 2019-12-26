package com.dianwoda.test.bassy.common.enums;

/**
 *error code
 */
public enum ErrorCode {

    /**
     * 通用失败码
     */
    FAIL(0, "请求失败"),

    /**
     *请求成功
     */
    SUCCESS(1, "成功"),

    /**
     * 系统内部异常
     */
    UNKNOWN_ERROR(100, "系统内部异常"),

    /**
     * 用例导入异常
     */
    CASE_IMPORT_ERROR(3000, "用例导入异常");

    private final int code;

    private final String msg;


    private ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
