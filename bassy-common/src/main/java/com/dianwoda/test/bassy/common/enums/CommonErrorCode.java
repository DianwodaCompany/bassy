package com.dianwoda.test.bassy.common.enums;

/**
 * @author zcp
 * 2019/12/20 下午9:17
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public enum CommonErrorCode {
    GENERIC_ERROR("00000", "系统异常"),
    ILLEGAL_ARGUMENT("01000", "参数非法"),
    FILE_SAVE_FAIL("01022", "文件保存失败!"),
    RPC_CALL_ERROR("03000", "外部系统调用异常"),
    RIGHT_NOT_ENOUGH("04000", "权限不足"),
    DB_OPERATE_ERROR("05000", "数据库操作失败"),
    RESULT_IS_NULL("05005", "返回值为空"),
    DATA_ERROR("02000", "数据错误"),
    PARAM_IS_NULL("08001", "参数值为空"),
    SEND_SMS_FAIL("11001", "发送消息失败"),
    PARAM_MAP_ISNULL("05001", "参数为NULL");

    private String code;
    private String message;

    private CommonErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
