package com.dianwoda.test.bassy.service.factory;


import com.dianwoda.test.bassy.common.domain.dto.testcase.ProductModuleParamDTO;
import com.dianwoda.test.bassy.db.dao.ProductMapper;
import com.dianwoda.test.bassy.db.dao.ProductModuleMapper;
import com.dianwoda.test.bassy.db.entity.Product;
import com.dianwoda.test.bassy.db.entity.ProductModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcp
 * @date 2019/8/14 11:30
 */
@Component
public class UpdateProductModule implements ProductModuleOperate {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductModuleMapper productModuleMapper;

    @Override
    public void operateModule(ProductModuleParamDTO paramDTO) {
        ProductModule productModule = new ProductModule();
        productModule.setId(paramDTO.getModuleId());
        productModule.setName(paramDTO.getModuleName());
        productModule.setDefender(paramDTO.getDefender());
        productModuleMapper.updateByPrimaryKeySelective(productModule);
    }

    @Override
    public void operateProduct(ProductModuleParamDTO paramDTO) {
        Product product = new Product();
        product.setId(paramDTO.getProductId());
        product.setName(paramDTO.getProductName());
        product.setDefender(paramDTO.getDefender());
        productMapper.updateByPrimaryKeySelective(product);
    }
}
