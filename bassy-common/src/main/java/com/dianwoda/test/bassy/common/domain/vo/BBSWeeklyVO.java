package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuJJ on 2019/11/25
 */
@Data
public class BBSWeeklyVO {

    private Integer id;

    private Byte type;

    private String title;

    private String authorCode;

    private String authorName;

    private Date createTm;

    private Date modifyTm;

    private Short like;

    private boolean likeStatus;

    private String week;

    private List<Map<String,Object>> bbsList;
}
