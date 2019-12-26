package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by gaoh on 2019/6/28.
 */
@Data
public class RequireBoardVO {

    private Integer requireId;
    private String requireRelate;
    private Integer current; //当前进行到的节点
    private List<Map<String,Object>> nodes;

}
