package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * Created by LiuJJ on 2019/5/5.
 */
@Data
public class BBSLogVO {

    private Integer id;

    private String staffCode;

    private String staffName;

    private Integer bbsId;

    private Short operation;

    private Date insTm;

    private Date updTm;

    private String title;

    private Byte type;
}
