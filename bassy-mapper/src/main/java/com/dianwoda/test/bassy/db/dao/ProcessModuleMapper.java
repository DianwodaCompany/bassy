package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProcessModule;
import com.dianwoda.test.bassy.db.entity.ProcessModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProcessModuleMapper {
    long countByExample(ProcessModuleExample example);

    int deleteByExample(ProcessModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcessModule record);

    int insertSelective(ProcessModule record);

    List<ProcessModule> selectByExample(ProcessModuleExample example);

    ProcessModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProcessModule record, @Param("example") ProcessModuleExample example);

    int updateByExample(@Param("record") ProcessModule record, @Param("example") ProcessModuleExample example);

    int updateByPrimaryKeySelective(ProcessModule record);

    int updateByPrimaryKey(ProcessModule record);
}