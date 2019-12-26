package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.Program;
import com.dianwoda.test.bassy.db.entity.ProgramExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramMapper {
    long countByExample(ProgramExample example);

    int deleteByExample(ProgramExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Program record);

    int insertSelective(Program record);

    List<Program> selectByExample(ProgramExample example);

    Program selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Program record, @Param("example") ProgramExample example);

    int updateByExample(@Param("record") Program record, @Param("example") ProgramExample example);

    int updateByPrimaryKeySelective(Program record);

    int updateByPrimaryKey(Program record);
}