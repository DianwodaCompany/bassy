package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by gaoh on 2019/1/30.
 */
@Data
public class AssetVO {
    private Integer id;

    private Byte type;

    private String typeMean;

    private String brand;

    private String model;

    private String version;

    private String size;

    private Short amount;

    private Short inUse;

    private String borrower;

    private String borrowerName;

    private Date borrowTm;

    private Short status;

    private String statusMean;

    private String imei;

    private String assetNumber;

}
