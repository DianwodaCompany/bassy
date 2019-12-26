package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by gaoh on 2019/1/24.
 */
@Data
public class AssetParamDTO {

    private AssetDTO asset;

    private String remark;

    private String modifier;

    private Date returnTm;

}
