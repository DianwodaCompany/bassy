package com.dianwoda.test.bassy.common.domain.dto.tool;

/**
 * @author zcp
 * @date 2019/3/8 14:35
 */
public class OrderDispatchCheckParamDTO {

    private String orderId;

    private Integer riderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getRiderId() {
        return riderId;
    }

    public void setRiderId(Integer riderId) {
        this.riderId = riderId;
    }
}
