package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BaseCase;
import com.dianwoda.test.bassy.db.entity.BaseCaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseCaseMapper {
    long countByExample(BaseCaseExample example);

    int deleteByExample(BaseCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseCase record);

    int insertSelective(BaseCase record);

    List<BaseCase> selectByExampleWithBLOBs(BaseCaseExample example);

    List<BaseCase> selectByExample(BaseCaseExample example);

    BaseCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseCase record, @Param("example") BaseCaseExample example);

    int updateByExampleWithBLOBs(@Param("record") BaseCase record, @Param("example") BaseCaseExample example);

    int updateByExample(@Param("record") BaseCase record, @Param("example") BaseCaseExample example);

    int updateByPrimaryKeySelective(BaseCase record);

    int updateByPrimaryKeyWithBLOBs(BaseCase record);

    int updateByPrimaryKey(BaseCase record);
}