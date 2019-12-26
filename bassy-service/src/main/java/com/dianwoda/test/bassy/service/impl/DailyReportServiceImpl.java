package com.dianwoda.test.bassy.service.impl;

import com.alibaba.fastjson.JSON;
import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.api.UpperProjectService;
import com.dianwoda.test.bassy.common.domain.UpperProjectBugDTO;
import com.dianwoda.test.bassy.common.enums.DictionaryEn;
import com.dianwoda.test.bassy.db.dao.DailyReportMapper;
import com.dianwoda.test.bassy.db.dao.ProgramMapper;
import com.dianwoda.test.bassy.db.dao.ProgramTaskLogMapper;
import com.dianwoda.test.bassy.db.dao.ProgramTaskMapperExt;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.common.domain.dto.CoreNodeDTO;
import com.dianwoda.test.bassy.common.domain.dto.BassyPagination;
import com.dianwoda.test.bassy.common.domain.dto.program.ProgramBugInfoDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.ProgramRequiresDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.ProgramStageDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramVO;
import com.dianwoda.test.bassy.service.*;
import com.dianwoda.test.bassy.common.domain.dto.program.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zcp on 2018/5/22.
 * Time always， fat thin man all miss.
 */
@Service
public class DailyReportServiceImpl implements DailyReportService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UpperProjectService upperProjectService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ProgramTaskMapperExt programTaskMapperExt;

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private ProgramTaskLogMapper programTaskLogMapper;

    @Autowired
    private DailyReportMapper dailyReportMapper;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ProgramService programService;

    @Override
    public ProgramBugInfoDTO getBugSummaryByRequires(List<Integer> requires) {
        ProgramBugInfoDTO programBugInfoDTO = new ProgramBugInfoDTO();
        Integer totalBug = 0;
        //已确认但未解决bug总数
        int notResolvedTotalBug = 0;
        int notResolvedTotalBugMoreThanOneDay = 0;
        int todayNewTotalBug = 0;
        int todayCloseTotalBug = 0;
        //超过24小时未验证
        int resolvedTotalBugMoreThanOneDay = 0;
        for (Integer requireId : requires) {
            List<UpperProjectBugDTO> ztBugList = upperProjectService.getBugListByStoryId(requireId);
            Integer requireTotalBug = ztBugList.size();
            totalBug += requireTotalBug;
            for (UpperProjectBugDTO upperProjectBugDTO : ztBugList) {
                if (upperProjectBugDTO.getStatus().equals("active")) {
                    notResolvedTotalBug++;
                    if (upperProjectBugDTO.getOpeneddate().getTime() < ((new Date()).getTime() - 3600 * 24 * 1000)) {
                        notResolvedTotalBugMoreThanOneDay++;
                    }
                }
                if (upperProjectBugDTO.getStatus().equals("closed")) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    Date zero = calendar.getTime();
                    if (upperProjectBugDTO.getCloseddate().getTime() > zero.getTime()) {
                        todayCloseTotalBug++;
                    }
                }
                if (upperProjectBugDTO.getStatus().equals("resolved")) {
                    if (upperProjectBugDTO.getResolveddate().getTime() < ((new Date()).getTime() - 3600 * 24 * 1000)) {
                        resolvedTotalBugMoreThanOneDay++;
                    }
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date zero = calendar.getTime();
                if (upperProjectBugDTO.getOpeneddate().getTime() > zero.getTime()) {
                    todayNewTotalBug++;
                }
            }
        }
        programBugInfoDTO.setTodayTotal(totalBug);
        programBugInfoDTO.setNotResolvedTotalBug(notResolvedTotalBug);
        programBugInfoDTO.setNotResolvedTotalBugMoreThanOneDay(notResolvedTotalBugMoreThanOneDay);
        programBugInfoDTO.setTodayNewTotalBug(todayNewTotalBug);
        programBugInfoDTO.setTodayCloseTotalBug(todayCloseTotalBug);
        programBugInfoDTO.setResolvedTotalBugMoreThanOneDay(resolvedTotalBugMoreThanOneDay);
        return programBugInfoDTO;
    }

    @Override
    public List<ProgramTaskPercentDTO> getRequireProgress(ProgramRequiresDTO dto) {
        List<ProgramTaskPercentDTO> programTasks = programTaskMapperExt.getProgramTaskProcessByrRequire(dto.getProgramId(), dto.getRequires());
        List<ProgramTaskPercentDTO> programTaskPercentDTOList = new ArrayList<>();
        List<Map<Integer, String>> requireTesters = new ArrayList<>();
        for (ProgramTaskPercentDTO programTaskPercentDTO : programTasks) {
            //一个需求和tester组成一个主键，返回最新的一条任务状态
            Map<Integer, String> requireTester = new HashMap<>();
            requireTester.put(programTaskPercentDTO.getRequireId(), programTaskPercentDTO.getTester());
            if (requireTesters.contains(requireTester)) {
                continue;
            }
            ProgramTaskLogExample programTaskLogExample = new ProgramTaskLogExample();
            ProgramTaskLogExample.Criteria taskLogExampleCriteria = programTaskLogExample.createCriteria();
            taskLogExampleCriteria.andTaskIdEqualTo(programTaskPercentDTO.getTaskId());
            programTaskLogExample.setOrderByClause("id DESC");
            List<ProgramTaskLog> programTaskLogList = programTaskLogMapper.selectByExample(programTaskLogExample);
            if (programTaskLogList.size() > 0) {
                ProgramTaskLog programTaskLog = programTaskLogList.get(0);
                programTaskPercentDTO.setTaskPercent(programTaskLog.getPercent());
                programTaskPercentDTO.setTaskExplain(programTaskLog.getTaskExplain());
                programTaskPercentDTO.setTesterName(staffService.getStaffInfo(programTaskPercentDTO.getTester()).getName());
                programTaskPercentDTOList.add(programTaskPercentDTO);
            }
            requireTesters.add(requireTester);
        }
        return programTaskPercentDTOList;
    }

    public ProgramStageDTO getProgramStage(Integer programId) {
        ProgramVO programVo = programService.getProgramById(programId);
        List<CoreNodeDTO> coreNodes = JSON.parseArray(programVo.getCoreNodes().toString(), CoreNodeDTO.class);
        ProgramStageDTO programStageDTO = new ProgramStageDTO();
        for (CoreNodeDTO coreNodeDTO : coreNodes) {
            if (coreNodeDTO.getEndTime().after(new Date())) {
                Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.PROJECT_NODE.getEname(), coreNodeDTO.getProjectNode());
                programStageDTO.setStageValue(dictionary.getDictValue());
                programStageDTO.setStageCode(dictionary.getDictCode());
                programStageDTO.setStageOrder(dictionary.getOrder());
            }
        }
        return programStageDTO;
    }

    public void createDailyReport(DailyReportParamDTO dailyReportParamDTO) {
        DailyReport dailyReport = new DailyReport();
        Program program = programMapper.selectByPrimaryKey(dailyReportParamDTO.getProjectId());
        dailyReport.setProjectId(dailyReportParamDTO.getProjectId());
        dailyReport.setProjectName(program.getProgramName());
        dailyReport.setReportDate(new Date());
        dailyReport.setProjectStage(dailyReportParamDTO.getProjectStage());
        dailyReport.setTaskProgress(dailyReportParamDTO.getTaskPercent());
        dailyReport.setBugSummary(dailyReportParamDTO.getBugSummary());
        dailyReport.setProjectRisk(dailyReportParamDTO.getProjectRisk());
        dailyReport.setCreator(dailyReportParamDTO.getCreator());
        dailyReport.setModifier(dailyReportParamDTO.getCreator());
        dailyReport.setCreateTime(new Date());
        dailyReport.setModifyTime(new Date());
        dailyReportMapper.insert(dailyReport);
    }

    public BassyPagination<DailyReport> getHistoryDailyReport(HistoryDailyReportParamDTO historyDailyReportParamDTO) {
        BassyPagination<DailyReport> pagination = new BassyPagination<>();
        DailyReportExample dailyReportExample = new DailyReportExample();
        DailyReportExample.Criteria criteria = dailyReportExample.createCriteria();
        criteria.andProjectIdEqualTo(historyDailyReportParamDTO.getProjectId());
        dailyReportExample.setOrderByClause("id desc");
        PageHelper.startPage(historyDailyReportParamDTO.getCurrent(), historyDailyReportParamDTO.getPageSize(), true);
        List<DailyReport> dailyReportList = dailyReportMapper.selectByExample(dailyReportExample);
        dailyReportList.stream().sorted(Comparator.comparing(DailyReport::getReportDate));
        PageInfo<DailyReport> pageInfo = new PageInfo<>(dailyReportList);
        pagination.setCurrent(historyDailyReportParamDTO.getCurrent());
        pagination.setPageSize(historyDailyReportParamDTO.getPageSize());
        pagination.setList(dailyReportList);
        pagination.setTotal(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

}
