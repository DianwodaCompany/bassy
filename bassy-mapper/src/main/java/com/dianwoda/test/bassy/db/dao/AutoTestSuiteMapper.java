package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.AutoTestSuite;
import com.dianwoda.test.bassy.db.entity.AutoTestSuiteExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AutoTestSuiteMapper {
    long countByExample(AutoTestSuiteExample example);

    int deleteByExample(AutoTestSuiteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AutoTestSuite record);

    int insertSelective(AutoTestSuite record);

    List<AutoTestSuite> selectByExampleWithBLOBs(AutoTestSuiteExample example);

    List<AutoTestSuite> selectByExample(AutoTestSuiteExample example);

    AutoTestSuite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AutoTestSuite record, @Param("example") AutoTestSuiteExample example);

    int updateByExampleWithBLOBs(@Param("record") AutoTestSuite record, @Param("example") AutoTestSuiteExample example);

    int updateByExample(@Param("record") AutoTestSuite record, @Param("example") AutoTestSuiteExample example);

    int updateByPrimaryKeySelective(AutoTestSuite record);

    int updateByPrimaryKeyWithBLOBs(AutoTestSuite record);

    int updateByPrimaryKey(AutoTestSuite record);
}