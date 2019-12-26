package com.dianwoda.test.bassy.common.enums;

/**
 * Created by zcp on 2018/7/13.
 * Time always， fat thin man all miss.
 */
public enum  AutoTestExecuteStatusEn {

    未开始(1, "NOT_BEGIN"),
    进行中(5, "EXECUTING"),
    异常结束(9, "ABNORMAL"),
    已结束(10, "END");

    /**
     * @param code
     * @param mean
     */
    AutoTestExecuteStatusEn(int code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    private int code;
    private String mean;

    public int getCode() {
        return code;
    }

    public String getMean() {
        return mean;
    }
}
