package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramLog;
import com.dianwoda.test.bassy.db.entity.ProgramLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramLogMapper {
    long countByExample(ProgramLogExample example);

    int deleteByExample(ProgramLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProgramLog record);

    int insertSelective(ProgramLog record);

    int insertBatch(List<ProgramLog> record);

    List<ProgramLog> selectByExample(ProgramLogExample example);

    ProgramLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProgramLog record, @Param("example") ProgramLogExample example);

    int updateByExample(@Param("record") ProgramLog record, @Param("example") ProgramLogExample example);

    int updateByPrimaryKeySelective(ProgramLog record);

    int updateByPrimaryKey(ProgramLog record);
}