package com.dianwoda.test.bassy.db.dao;


import com.dianwoda.test.bassy.db.entity.AutoTestFailResultExample;
import com.dianwoda.test.bassy.db.entity.AutoTestFailResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AutoTestFailResultMapper {
    long countByExample(AutoTestFailResultExample example);

    int deleteByExample(AutoTestFailResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AutoTestFailResult record);

    int insertSelective(AutoTestFailResult record);

    List<AutoTestFailResult> selectByExample(AutoTestFailResultExample example);

    AutoTestFailResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AutoTestFailResult record, @Param("example") AutoTestFailResultExample example);

    int updateByExample(@Param("record") AutoTestFailResult record, @Param("example") AutoTestFailResultExample example);

    int updateByPrimaryKeySelective(AutoTestFailResult record);

    int updateByPrimaryKey(AutoTestFailResult record);
}