package com.dianwoda.test.bassy.common.result;

import java.io.Serializable;

/**
 * @author zcp
 * 2019/12/20 下午8:41
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public class ResponseDTO<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4082846602141879024L;

    private boolean status = true;

    private String msg;

    private Exception exception;

    private String errorCode;

    private T data;

    public boolean isSuccess() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     *            the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the exception
     */
    public Exception getException() {
        return exception;
    }

    /**
     * @param exception
     *            the exception to set
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
