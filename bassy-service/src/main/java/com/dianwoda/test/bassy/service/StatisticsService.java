package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.common.domain.dto.statistics.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by gaoh on 2018/10/22.
 */
public interface StatisticsService {
    Pagination<TaskAbnormalDTO> getTaskAbnormalListByCondition(TaskAbnormalRequestDTO taskAbnormalRequestDTO);

    List<WorkHourTrendDTO> getWorkHourTrend(List<String> monthList) throws ParseException;

    TaskAbnormalCountDTO getTaskAbnormalCountDTO(String startTm, String endTm);

    ProjectNumCountDTO getProjectNumCount();

    TaskAbnormalCollectionDTO getTaskAbnormalCollect(String startTm, String endTm) throws ParseException;

    List<HeapWorkHourDTO> getHeapDataByDepart(Integer departId, String startTm, String endTm) throws ParseException;

    /**
     * 获取小组日报
     * @param departId
     * @param date
     * @return
     * @throws ParseException
     */
    List<WorkDailyReportDTO> getWorkDailyReportByDepart(Integer departId, String date) throws ParseException;

    /**
     * 生成日报的明日计划
     * @param date
     * @return
     * @throws ParseException
     */
    Boolean genWorkTomorrow(String date) throws ParseException;

    /**
     * 生成日报的今天进度
     * @param date
     * @return
     * @throws ParseException
     */
    Boolean genWorkTodayAct(String date) throws ParseException;

    /**
     * 异常所属部门统计
     * @param startTm
     * @param endTm
     * @return
     */
    List<DepartAbnormalCountDTO> getDepartAbnormalCount(String startTm, String endTm);

    /**
     * 部门异常明细
     * @param startTm
     * @param endTm
     * @param departId
     * @return
     */
    List<TaskAbnormalDTO> getDepartAbnormalDetail(String startTm, String endTm, int departId);

    /**
     * 员工工作量统计
     * @param date
     * @return
     */
    List<WorkLoadDTO> getStaffWorkLoad(Date date) throws ParseException;

    /**
     * 部门工作量统计
     * @param date
     * @return
     * @throws ParseException
     */
    List<WorkLoadDTO> getDepartWorkLoad(Date date) throws ParseException;
}
