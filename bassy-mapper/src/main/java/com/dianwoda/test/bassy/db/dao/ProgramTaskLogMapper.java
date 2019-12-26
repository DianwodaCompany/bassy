package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramTaskLog;
import com.dianwoda.test.bassy.db.entity.ProgramTaskLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramTaskLogMapper {
    long countByExample(ProgramTaskLogExample example);

    int deleteByExample(ProgramTaskLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProgramTaskLog record);

    int insertSelective(ProgramTaskLog record);

    List<ProgramTaskLog> selectByExample(ProgramTaskLogExample example);

    ProgramTaskLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProgramTaskLog record, @Param("example") ProgramTaskLogExample example);

    int updateByExample(@Param("record") ProgramTaskLog record, @Param("example") ProgramTaskLogExample example);

    int updateByPrimaryKeySelective(ProgramTaskLog record);

    int updateByPrimaryKey(ProgramTaskLog record);
}