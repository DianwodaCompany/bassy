package com.dianwoda.test.bassy.common.domain.vo.testcase;

import lombok.Data;

import java.util.List;

/**
 * @author zcp
 * @date 2019/7/8 15:47
 */
@Data
public class ProductModuleTreeNodeVO {

    private String id;

    private Integer productId;

    private Integer moduleId;

    private String path;

    private String name;

    private String defender;

    private List<ProductModuleTreeNodeVO> children;
}
