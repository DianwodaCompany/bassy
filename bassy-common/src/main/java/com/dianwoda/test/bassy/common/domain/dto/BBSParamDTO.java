package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by LiuJJ on 2019/5/5.
 */
@Data
public class BBSParamDTO extends ParamDTO {

    private Integer id;

    private Byte type;

    private String title;

    private String authorCode;

    private String authorName;

    private String[] tag;

    private String content;

    private String sort;

    private String keyword;

    private String staffCode;

    private Byte origin;

    private String files;

    private boolean likeStatus;

    private String week;

    private Byte noteType;

    private List<Map<String,Object>> relateBbs;
}
