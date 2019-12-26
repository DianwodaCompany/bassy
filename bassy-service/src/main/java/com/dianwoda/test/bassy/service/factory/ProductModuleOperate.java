package com.dianwoda.test.bassy.service.factory;


import com.dianwoda.test.bassy.common.domain.dto.testcase.ProductModuleParamDTO;

/**
 * @author zcp
 * @date 2019/8/14 11:28
 */
public interface ProductModuleOperate {

    void operateProduct(ProductModuleParamDTO paramDTO);

    void operateModule(ProductModuleParamDTO paramDTO);
}
