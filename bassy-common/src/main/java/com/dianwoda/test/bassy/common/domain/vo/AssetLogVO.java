package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by gaoh on 2019/1/31.
 */
@Data
public class AssetLogVO {

    private Long id;

    private Integer assetId;

    private Integer status;

    private String statusMean;

    private String borrower;

    private String borrowerName;

    private Date borrowTm;

    private Date returnTm;

    private String remark;

    private String modifier;

    private String modifierName;

    private Date modifyTm;
}
