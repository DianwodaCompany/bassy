package com.dianwoda.test.bassy.common.domain.dto.testcase;

import lombok.Data;

/**
 * @author zcp
 * @date 2019/5/22 9:52
 */
@Data
public class ProductModuleParamDTO {

    private String keyWords;

    /**
     * 0 新增 1 删除 2 修改
     */
    private Integer operate;

    /**
     * 0 产品 1 产品模块
     */
    private Integer target;

    private Integer productId;

    private String productName;

    private Integer moduleId;

    private String path;

    private String moduleName;

    private String defender;

    private boolean allLayer;

}
