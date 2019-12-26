package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramDocument;
import com.dianwoda.test.bassy.db.entity.ProgramDocumentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramDocumentMapper {
    long countByExample(ProgramDocumentExample example);

    int deleteByExample(ProgramDocumentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProgramDocument record);

    int insertSelective(ProgramDocument record);

    List<ProgramDocument> selectByExample(ProgramDocumentExample example);

    ProgramDocument selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProgramDocument record, @Param("example") ProgramDocumentExample example);

    int updateByExample(@Param("record") ProgramDocument record, @Param("example") ProgramDocumentExample example);

    int updateByPrimaryKeySelective(ProgramDocument record);

    int updateByPrimaryKey(ProgramDocument record);
}