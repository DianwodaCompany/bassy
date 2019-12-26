package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BBSRelate;
import com.dianwoda.test.bassy.db.entity.BBSRelateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSRelateMapper {
    long countByExample(BBSRelateExample example);

    int deleteByExample(BBSRelateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSRelate record);

    int insertSelective(BBSRelate record);

    List<BBSRelate> selectByExample(BBSRelateExample example);

    BBSRelate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSRelate record, @Param("example") BBSRelateExample example);

    int updateByExample(@Param("record") BBSRelate record, @Param("example") BBSRelateExample example);

    int updateByPrimaryKeySelective(BBSRelate record);

    int updateByPrimaryKey(BBSRelate record);
}