package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class WorkHourTrendDTO implements Serializable {

    private String month;
    private Float shangjia;
    private Float qishou;
    private Float zhicheng;
    private Float cekai;

}
