package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProcessTaskRelate;
import com.dianwoda.test.bassy.db.entity.ProcessTaskRelateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcessTaskRelateMapper {
    long countByExample(ProcessTaskRelateExample example);

    int deleteByExample(ProcessTaskRelateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcessTaskRelate record);

    int insertSelective(ProcessTaskRelate record);

    List<ProcessTaskRelate> selectByExample(ProcessTaskRelateExample example);

    ProcessTaskRelate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProcessTaskRelate record, @Param("example") ProcessTaskRelateExample example);

    int updateByExample(@Param("record") ProcessTaskRelate record, @Param("example") ProcessTaskRelateExample example);

    int updateByPrimaryKeySelective(ProcessTaskRelate record);

    int updateByPrimaryKey(ProcessTaskRelate record);
}