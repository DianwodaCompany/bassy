package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramRequire;
import com.dianwoda.test.bassy.db.entity.ProgramRequireExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramRequireMapper {
    long countByExample(ProgramRequireExample example);

    int deleteByExample(ProgramRequireExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProgramRequire record);

    int insertSelective(ProgramRequire record);

    List<ProgramRequire> selectByExample(ProgramRequireExample example);

    ProgramRequire selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProgramRequire record, @Param("example") ProgramRequireExample example);

    int updateByExample(@Param("record") ProgramRequire record, @Param("example") ProgramRequireExample example);

    int updateByPrimaryKeySelective(ProgramRequire record);

    int updateByPrimaryKey(ProgramRequire record);
}