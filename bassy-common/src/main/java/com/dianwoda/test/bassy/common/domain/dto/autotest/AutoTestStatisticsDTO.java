package com.dianwoda.test.bassy.common.domain.dto.autotest;


import com.dianwoda.test.bassy.common.domain.dto.statistics.*;
import lombok.Data;

import java.util.List;


/**
 * @author zcp
 * @date 2019/2/15 12:00
 */
@Data
public class AutoTestStatisticsDTO {

    /**
     * 总执行次数
     */
    private Integer totalExecutionTimes;

    /**
     * 平均通过率
     */
    private Double averagePassingRate;

    /**
     * 总用例数
     */
    private Integer totalCaseNum;

    /**
     * 本周新增用例数
     */
    private Integer newCaseNum;

    /**
     * 今日失败用例总数
     */
    private Integer failCaseNum;

    /**
     * 平均维护及时率
     */
    private Double averageFixRate;

    /**
     * 各项目执行次数
     */
    private List<ExecutionTimesDTO> executionTimes;

    /**
     * 各项目用例数
     */
    private List<CaseNumDTO> caseNum;

    /**
     * 各项目失败方法排行
     */
    private List<MethodSortDTO> methodSort;

    /**
     * 各项目通过率
     */
    private List<PassingRateDTO> passingRate;

    /**
     * 各项目失败原因排行
     */
    private List<ReasonSortDTO> reasonSort;

    /**
     * 各项目今日失败用例维护率
     */
    private List<TodayFixRateDTO> todayFixRate;

    public AutoTestStatisticsDTO() {

    }

    public AutoTestStatisticsDTO(List<ExecutionTimesDTO> executionTimes,
                                 List<CaseNumDTO> caseNum,
                                 List<MethodSortDTO> methodSort,
                                 List<PassingRateDTO> passingRate,
                                 List<ReasonSortDTO> reasonSort,
                                 List<TodayFixRateDTO> todayFixRate,
                                 AddCaseNumDTO addCaseNumDTO,
                                 FailCaseNumDTO failCaseNumDTO
    ) {

        this.executionTimes = executionTimes;
        this.caseNum = caseNum;
        this.methodSort = methodSort;
        this.passingRate = passingRate;
        this.reasonSort = reasonSort;
        this.todayFixRate = todayFixRate;
        this.totalExecutionTimes = 0;
        this.totalCaseNum = 0;
        this.averageFixRate = 0.0;
        this.averagePassingRate = 0.0;
        this.newCaseNum = addCaseNumDTO.getAddNum();
        this.failCaseNum = failCaseNumDTO.getFailNum();
        Double totalPassingRate = 0.0;
        Double totalFixRate = 0.0;

        for (ExecutionTimesDTO executionTimesDTO : executionTimes) {
            this.totalExecutionTimes += executionTimesDTO.getTimes();
        }

        for (CaseNumDTO caseNumDTO : caseNum) {
            this.totalCaseNum += caseNumDTO.getNum();
        }

        for (PassingRateDTO passingRateDTO : passingRate) {
            totalPassingRate += passingRateDTO.getRate();
            this.averagePassingRate = totalPassingRate / passingRate.size();
        }

        for (TodayFixRateDTO todayFixRateDTO : todayFixRate) {
            totalFixRate += todayFixRateDTO.getRate();
            this.averageFixRate = totalFixRate / todayFixRate.size();
        }
    }
}
