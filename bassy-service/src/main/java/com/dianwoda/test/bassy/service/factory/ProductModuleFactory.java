package com.dianwoda.test.bassy.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.dianwoda.test.bassy.common.constants.TestCaseConstant.*;


/**
 * @author zcp
 * @date 2019/8/14 11:27
 */
@Component
public class ProductModuleFactory {

    @Autowired
    private AddProductModule addProductModule;

    @Autowired
    private DeleteProductModule deleteProductModule;

    @Autowired
    private UpdateProductModule updateProductModule;

    public ProductModuleOperate getProductModuleOperate(Integer operate) {
        if (PRODUCT_MODULE_ADD.equals(operate)) {
            return addProductModule;
        } else if (PRODUCT_MODULE_DELETE.equals(operate)) {
            return deleteProductModule;
        } else if (PRODUCT_MODULE_UPDATE.equals(operate)) {
            return updateProductModule;
        }
        return null;
    }
}
