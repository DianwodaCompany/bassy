package com.dianwoda.test.bassy.service.factory;


import com.dianwoda.test.bassy.common.domain.dto.testcase.ProductModuleParamDTO;
import com.dianwoda.test.bassy.db.dao.ProductMapper;
import com.dianwoda.test.bassy.db.dao.ProductModuleMapper;
import com.dianwoda.test.bassy.db.entity.Product;
import com.dianwoda.test.bassy.db.entity.ProductModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.dianwoda.test.bassy.common.constants.TestCaseConstant.PRODUCT_ENABLE;
import static com.dianwoda.test.bassy.common.constants.TestCaseConstant.PRODUCT_MODULE_ENABLE;

/**
 * @author zcp
 * @date 2019/8/14 11:29
 */
@Component
public class AddProductModule implements ProductModuleOperate {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductModuleMapper productModuleMapper;

    @Override
    public void operateModule(ProductModuleParamDTO paramDTO) {
        ProductModule productModule = new ProductModule();
        productModule.setRoot(paramDTO.getProductId());
        productModule.setName(paramDTO.getModuleName());
        productModule.setPath(paramDTO.getPath());
        productModule.setDefender(paramDTO.getDefender());
        productModule.setDeleted(PRODUCT_MODULE_ENABLE);
        productModuleMapper.insert(productModule);
    }

    @Override
    public void operateProduct(ProductModuleParamDTO paramDTO) {
        Product product = new Product();
        product.setName(paramDTO.getProductName());
        product.setCode(paramDTO.getProductName());
        product.setDefender(paramDTO.getDefender());
        product.setCreateddate(new Date());
        product.setDeleted(PRODUCT_ENABLE);
        productMapper.insertSelective(product);
    }
}
