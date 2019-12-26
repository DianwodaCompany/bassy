package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProductModule;
import com.dianwoda.test.bassy.db.entity.ProductModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductModuleMapper {
    long countByExample(ProductModuleExample example);

    int deleteByExample(ProductModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductModule record);

    int insertSelective(ProductModule record);

    List<ProductModule> selectByExample(ProductModuleExample example);

    ProductModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductModule record, @Param("example") ProductModuleExample example);

    int updateByExample(@Param("record") ProductModule record, @Param("example") ProductModuleExample example);

    int updateByPrimaryKeySelective(ProductModule record);

    int updateByPrimaryKey(ProductModule record);
}