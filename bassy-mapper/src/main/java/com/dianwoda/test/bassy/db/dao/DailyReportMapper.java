package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.DailyReport;
import com.dianwoda.test.bassy.db.entity.DailyReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DailyReportMapper {
    long countByExample(DailyReportExample example);

    int deleteByExample(DailyReportExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DailyReport record);

    int insertSelective(DailyReport record);

    List<DailyReport> selectByExample(DailyReportExample example);

    DailyReport selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DailyReport record, @Param("example") DailyReportExample example);

    int updateByExample(@Param("record") DailyReport record, @Param("example") DailyReportExample example);

    int updateByPrimaryKeySelective(DailyReport record);

    int updateByPrimaryKey(DailyReport record);
}