package com.dianwoda.test.bassy.common.exception;

import com.dianwoda.test.bassy.common.enums.CommonErrorCode;

/**
 * @author zcp
 * 2019/12/20 下午9:16
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_MESSAGE_PREFIX = "Error Code : ";
    private String message;
    private String errorCode;
    private String extraMsg;

    public BusinessException(CommonErrorCode errorCode) {
        this(errorCode.getCode(), "Error Code : " + errorCode.getCode() + "[" + errorCode.getMessage() + "]", (Throwable)null);
    }

    public BusinessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(String message, String errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getExtraMsg() {
        return this.extraMsg;
    }

    public void setExtraMsg(String extraMsg) {
        this.extraMsg = extraMsg;
    }
}
