package com.dianwoda.test.bassy.service.factory;


import com.dianwoda.test.bassy.common.domain.dto.testcase.ProductModuleParamDTO;
import com.dianwoda.test.bassy.common.exception.BassyException;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.db.dao.BaseCaseMapper;
import com.dianwoda.test.bassy.db.dao.ProductMapper;
import com.dianwoda.test.bassy.db.dao.ProductModuleMapper;
import com.dianwoda.test.bassy.db.entity.BaseCaseExample;
import com.dianwoda.test.bassy.db.entity.Product;
import com.dianwoda.test.bassy.db.entity.ProductModule;
import com.dianwoda.test.bassy.db.entity.ProductModuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.dianwoda.test.bassy.common.constants.TestCaseConstant.PRODUCT_MODULE_UNABLE;
import static com.dianwoda.test.bassy.common.constants.TestCaseConstant.PRODUCT_UNABLE;

/**
 * @author zcp
 * @date 2019/8/14 11:31
 */
@Component
public class DeleteProductModule implements ProductModuleOperate {

    @Autowired
    private BaseCaseMapper baseCaseMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductModuleMapper productModuleMapper;

    @Override
    public void operateModule(ProductModuleParamDTO paramDTO) {
        //模块及子模块下有测试用例不能被删除
        ProductModuleExample searchModuleExample = new ProductModuleExample();
        ProductModuleExample.Criteria searchModuleCriteria1 = searchModuleExample.createCriteria();
        searchModuleCriteria1.andIdEqualTo(paramDTO.getModuleId());
        ProductModuleExample.Criteria searchModuleCriteria2 = searchModuleExample.createCriteria();
        searchModuleCriteria2.andPathLike("%" + paramDTO.getModuleId() + "%");
        searchModuleExample.or(searchModuleCriteria2);
        List<ProductModule> productModuleList = productModuleMapper.selectByExample(searchModuleExample);
        productModuleList.forEach(productModule -> {
            BaseCaseExample baseCaseExample = new BaseCaseExample();
            BaseCaseExample.Criteria criteria = baseCaseExample.createCriteria();
            criteria.andModuleEqualTo(productModule.getId());
            int num = baseCaseMapper.selectByExample(baseCaseExample).size();
            if (num > 0) {
                throw new BusinessException("当前模块下有测试用例，不能删除！");
            }
        });
        //没有测试用例，删除模块及子模块
        ProductModule productModule = new ProductModule();
        productModule.setDeleted(PRODUCT_MODULE_UNABLE);
        productModuleMapper.updateByExampleSelective(productModule, searchModuleExample);
    }

    @Override
    public void operateProduct(ProductModuleParamDTO paramDTO) {
        Product product = new Product();
        product.setId(paramDTO.getProductId());
        product.setDeleted(PRODUCT_UNABLE);
        productMapper.updateByPrimaryKeySelective(product);
    }
}
