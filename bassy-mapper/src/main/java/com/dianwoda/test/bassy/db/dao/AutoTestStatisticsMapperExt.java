package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.AutoTestStatistics;
import com.dianwoda.test.bassy.db.entity.statistics.*;

import java.util.List;

/**
 * @author zcp
 * @date 2019/2/19 11:24
 */
public interface AutoTestStatisticsMapperExt {

    AutoTestStatistics getAllProjectRecentlyStatistics();

    List<String> getAllProjects();

    List<ExecutionTimesDTO> getAllProjectExecutionTimes();

    List<CaseNumDTO> getAllProjectTestCase();

    List<MethodSortDTO> getAllProjectFailMethodSort();

    List<ReasonSortDTO> getAllProjectFailReasonSort();

    List<PassingRateDTO> getAllProjectPassingRate();

    List<TodayFixRateDTO> getAllProjectTodayFixRate();

    AddCaseNumDTO getAllProjectAddTestCaseNum();

    FailCaseNumDTO getAllProjectFailCaseNum();
}
