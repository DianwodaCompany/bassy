package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.domain.dto.program.*;
import com.dianwoda.test.bassy.db.entity.DailyReport;
import com.dianwoda.test.bassy.common.domain.dto.BassyPagination;

import java.util.List;

/**
 * The interface Daily report service.
 */
public interface DailyReportService {

    /**
     * 按需求获取项目bug信息
     * @param requires
     * @return
     */
    ProgramBugInfoDTO getBugSummaryByRequires(List<Integer> requires);

    /**
     * 获取项目的需求进度
     * @param programRequiresDTO
     * @return
     */
    List<ProgramTaskPercentDTO> getRequireProgress(ProgramRequiresDTO programRequiresDTO);

    /**
     * 根据项目Id获取项目阶段
     * @param programId the program id
     * @return the project stage
     */
    ProgramStageDTO getProgramStage(Integer programId);

    /**
     * 创建日报
     *
     * @param dailyReportParamDTO the daily report param dto
     * @return 实体id integer
     */
    void createDailyReport(DailyReportParamDTO dailyReportParamDTO);


    /**
     * Gets history daily report.
     *
     * @param historyDailyReportParamDTO the history daily report param dto
     * @return the history daily report
     */
    BassyPagination<DailyReport> getHistoryDailyReport(HistoryDailyReportParamDTO historyDailyReportParamDTO);
}
