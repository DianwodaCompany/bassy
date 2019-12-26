package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.WorkDailyReport;
import com.dianwoda.test.bassy.db.entity.WorkDailyReportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkDailyReportMapper {
    long countByExample(WorkDailyReportExample example);

    int deleteByExample(WorkDailyReportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WorkDailyReport record);

    int insertSelective(WorkDailyReport record);

    List<WorkDailyReport> selectByExample(WorkDailyReportExample example);

    WorkDailyReport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WorkDailyReport record, @Param("example") WorkDailyReportExample example);

    int updateByExample(@Param("record") WorkDailyReport record, @Param("example") WorkDailyReportExample example);

    int updateByPrimaryKeySelective(WorkDailyReport record);

    int updateByPrimaryKey(WorkDailyReport record);
}