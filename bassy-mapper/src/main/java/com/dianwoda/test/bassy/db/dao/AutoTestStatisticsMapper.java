package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.AutoTestStatistics;
import com.dianwoda.test.bassy.db.entity.AutoTestStatisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AutoTestStatisticsMapper {
    long countByExample(AutoTestStatisticsExample example);

    int deleteByExample(AutoTestStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AutoTestStatistics record);

    int insertSelective(AutoTestStatistics record);

    List<AutoTestStatistics> selectByExample(AutoTestStatisticsExample example);

    AutoTestStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AutoTestStatistics record, @Param("example") AutoTestStatisticsExample example);

    int updateByExample(@Param("record") AutoTestStatistics record, @Param("example") AutoTestStatisticsExample example);

    int updateByPrimaryKeySelective(AutoTestStatistics record);

    int updateByPrimaryKey(AutoTestStatistics record);
}