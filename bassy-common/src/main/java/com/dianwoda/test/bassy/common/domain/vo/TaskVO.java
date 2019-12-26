package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zcp on 2018/5/17.
 * Time always， fat thin man all miss.
 */
@Data
public class TaskVO {

    private Integer id;

    private String processCode;

    private String processValue;

    private Object tasks;

    private String sort;

    private String status;

    private Date createTm;

    private Date modifyTm;

    private String creator;

    private String modifier;

}
