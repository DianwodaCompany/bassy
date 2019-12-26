package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.common.domain.dto.statistics.*;
import com.dianwoda.test.bassy.common.enums.TaskAbnormalReasonTeamEn;
import com.dianwoda.test.bassy.common.enums.TaskAbnormalReasonTypeEn;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gaoh on 2018/10/25.
 */
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation(value = "获取各状态项目数量", notes = "")
    @RequestMapping(value = "/getProjectNumCount", method = RequestMethod.GET)
    public ProjectNumCountDTO getProjectNumCount() {
        return statisticsService.getProjectNumCount();
    }

    @ApiOperation(value = "资源排期热力图数据", notes = "")
    @RequestMapping(value = "/getHeapWorkHourList", method = RequestMethod.POST)
    public List<HeapWorkHourDTO> getHeapWorkHourList(@RequestParam("departId") Integer departId,
                                            @RequestParam("startTm") String startTm,
                                            @RequestParam("endTm") String endTm) throws ParseException {
        return statisticsService.getHeapDataByDepart(departId,startTm,endTm);
    }

    @ApiOperation(value = "各组每月平均工时", notes = "")
    @RequestMapping(value = "/getWorkHourTrend", method = RequestMethod.POST)
    public List<WorkHourTrendDTO> getWorkHourTrend(@RequestBody List<String> monthList) throws ParseException {
        return statisticsService.getWorkHourTrend(monthList);
    }

    @ApiOperation(value = "查询任务异常数据", notes = "")
    @RequestMapping(value = "/getTaskAbnormalCollect", method = RequestMethod.POST)
    public TaskAbnormalCollectionDTO getTaskAbnormalCollect(@RequestParam("startTm") String startTm,
                                                     @RequestParam("endTm") String endTm) throws ParseException {
        return statisticsService.getTaskAbnormalCollect(startTm,endTm);
    }

    @ApiOperation(value = "分页查询任务异常数据", notes = "")
    @RequestMapping(value = "/getTaskAbnormalListByCondition", method = RequestMethod.POST)
    public Pagination<TaskAbnormalDTO> getTaskAbnormalListByCondition(@RequestBody TaskAbnormalRequestDTO taskAbnormalRequestDTO) throws ParseException {
        return statisticsService.getTaskAbnormalListByCondition(taskAbnormalRequestDTO);
    }

    @ApiOperation(value = "任务异常数据统计", notes = "")
    @RequestMapping(value = "/getTaskAbnormalCountDTO", method = RequestMethod.POST)
    public TaskAbnormalCountDTO getTaskAbnormalCountDTO(@RequestParam("startTm") String startTm,
                                                            @RequestParam("endTm") String endTm) throws ParseException {
        return statisticsService.getTaskAbnormalCountDTO(startTm,endTm);
    }

    @RequestMapping(value = "/getTaskAbnormalReasonTeamList", method = RequestMethod.GET)
    public List<AbnormalReasonTeamDTO> getTaskAbnormalReasonTeamList() {
        return TaskAbnormalReasonTeamEn.getAll();
    }

    @RequestMapping(value = "/getTaskAbnormalReasonTypeList", method = RequestMethod.GET)
    public List<AbnormalReasonTypeDTO> getTaskAbnormalReasonTypeList() {
        return TaskAbnormalReasonTypeEn.getAll();
    }

    @ApiOperation(value = "获取小组日报", notes = "")
    @RequestMapping(value = "/getWorkDailyReport", method = RequestMethod.POST)
    public List<WorkDailyReportDTO> getWorkDailyReport(@RequestParam("departId") int departId,
                                                   @RequestParam("date") String date) throws ParseException {
        return statisticsService.getWorkDailyReportByDepart(departId, date);
    }

    @ApiOperation(value = "部门维度的异常统计", notes = "")
    @RequestMapping(value = "/getDepartAbnormalCount", method = RequestMethod.POST)
    public List<DepartAbnormalCountDTO> getDepartAbnormalCount(@RequestParam("startTm") String startTm,
                                                               @RequestParam("endTm") String endTm) {
        return statisticsService.getDepartAbnormalCount(startTm, endTm);
    }

    @ApiOperation(value = "部门异常明细", notes = "")
    @RequestMapping(value = "/getDepartAbnormalDetail", method = RequestMethod.POST)
    public List<TaskAbnormalDTO> getDepartAbnormalDetail(@RequestParam("startTm") String startTm,
                                                            @RequestParam("endTm") String endTm,
                                                            @RequestParam("abnormalDepart") int abnormalDepart) {
        return statisticsService.getDepartAbnormalDetail(startTm, endTm, abnormalDepart);
    }

    @ApiOperation(value = "员工工作量统计", notes = "")
    @RequestMapping(value = "/getStaffWorkLoadCount", method = RequestMethod.POST)
    public List<WorkLoadDTO> getStaffWorkLoadCount(@RequestParam("date") String date) throws ParseException{
        return statisticsService.getStaffWorkLoad(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
    }

    @ApiOperation(value = "部门工作量统计", notes = "")
    @RequestMapping(value = "/getDepartWorkLoadCount", method = RequestMethod.POST)
    public List<WorkLoadDTO> getDepartWorkLoadCount(@RequestParam("date") String date) throws ParseException{
        return statisticsService.getDepartWorkLoad(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
    }
}
