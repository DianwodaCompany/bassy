package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramModule;
import com.dianwoda.test.bassy.db.entity.ProgramModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramModuleMapper {
    long countByExample(ProgramModuleExample example);

    int deleteByExample(ProgramModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProgramModule record);

    int insertSelective(ProgramModule record);

    List<ProgramModule> selectByExample(ProgramModuleExample example);

    ProgramModule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProgramModule record, @Param("example") ProgramModuleExample example);

    int updateByExample(@Param("record") ProgramModule record, @Param("example") ProgramModuleExample example);

    int updateByPrimaryKeySelective(ProgramModule record);

    int updateByPrimaryKey(ProgramModule record);
}