package com.dianwoda.test.bassy.common.exception;


public class BaseRuntimeException extends RuntimeException {

    /**
     * 异常对应的编号，子类需要赋一个对应自己错误的唯一值
     */
    protected final String code;

    public BaseRuntimeException(String code) {
        this.code = code;
//        log.error(this.getClass().getName(), this);
    }

    public BaseRuntimeException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.code = errorCode;
//        log.error(errorMessage, this);
    }

    public BaseRuntimeException(String errorCode, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.code = errorCode;
//        log.error(errorMessage, this);
    }

    public BaseRuntimeException(String errorCode, Throwable cause) {
        super(cause);
        this.code = errorCode;
//        log.error(this.getClass().getName(), this);
    }

    public String getCode() {
        return code;
    }
}