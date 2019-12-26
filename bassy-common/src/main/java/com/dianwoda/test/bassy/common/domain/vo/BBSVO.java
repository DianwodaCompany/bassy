package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by LiuJJ on 2019/5/5.
 */
@Data
public class BBSVO {

    private Integer id;

    private Byte type;

    private String title;

    private String authorCode;

    private String authorName;

    private Date createTm;

    private Date modifyTm;

    private Short like;

    private String content;

    private String[] tag;

    private Object[] files;

    private Integer ctrbNum;

    private boolean likeStatus;

    private String week;

    private Integer studyTimes;

    private Integer studyPeopleNum;

    private Byte noteType;

    private List<Map<String,Object>> relateBbs;



}
