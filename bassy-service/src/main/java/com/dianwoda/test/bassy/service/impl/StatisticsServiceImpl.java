package com.dianwoda.test.bassy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.domain.StaffInfoDTO;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.common.enums.*;
import com.dianwoda.test.bassy.db.dao.*;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.common.domain.dto.statistics.*;
import com.dianwoda.test.bassy.common.domain.vo.ProgramVO;

import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.service.DictionaryService;
import com.dianwoda.test.bassy.service.ProgramService;
import com.dianwoda.test.bassy.service.StatisticsService;
import com.dianwoda.test.bassy.service.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaoh on 2018/10/23.
 */
@Service
@Slf4j
public class StatisticsServiceImpl implements StatisticsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private StaffService staffService;

    @Autowired
    private ProgramTaskMapperExt programTaskMapperExt;

    @Autowired
    private ProgramTaskMapper programTaskMapper;

    @Autowired
    private ProgramTaskLogMapperExt programTaskLogMapperExt;

    @Autowired
    private WorkDailyReportMapper workDailyReportMapper;

    @Autowired
    private WorkDailyReportMapperExt workDailyReportMapperExt;

    @Autowired
    private ProgramService programService;

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ProgramTaskLogMapper programTaskLogMapper;

    public List<WorkHourTrendDTO> getWorkHourTrend(List<String> monthList) throws ParseException {
        List<WorkHourTrendDTO> workHourTrendDTOList = new ArrayList<>();
        if (monthList == null || monthList.size() == 0) {
            return workHourTrendDTOList;
        }
        //小组列表
        List<Integer> groupList = new ArrayList<>();
        groupList.add(TeamEn.SPIDER.getCode());
        groupList.add(TeamEn.FLASH.getCode());
        groupList.add(TeamEn.HAWKEYE.getCode());
        groupList.add(TeamEn.BIGBANGTEST.getCode());
        for (String month : monthList) {
            Date monthStart = dateFormat.parse(month);
            Date monthEnd = DateUtil.getMonthEnd(monthStart);
            //当前月范围内的工作日(仅去掉周末)
            int totalWorkDay = getWorkDayCount(monthStart, monthEnd);
            monthEnd = DateUtil.add(monthEnd, Calendar.DATE, 1);
            WorkHourTrendDTO workHourTrendDTO = new WorkHourTrendDTO();
            workHourTrendDTO.setMonth(month);
            //计算每个组的平均工时
            for (int groupId : groupList) {
                List<HeapWorkHourDTO> heapWorkHourDTOList = this.getHeapWorkHourDTOS(groupId, dateFormat.format(monthStart), dateFormat.format(monthEnd));
                float totalHour = 0;
                //计算总工时
                for (HeapWorkHourDTO heapWorkHourDTO : heapWorkHourDTOList) {
                    if (heapWorkHourDTO.getHour() != null) {
                        totalHour = totalHour + heapWorkHourDTO.getHour();
                    }
                }
                //计算平均工时
                float hour = (float) (Math.round(totalHour / totalWorkDay / staffService.getDepartStaffList(String.valueOf(groupId)).size() * 100)) / 100;
                if (groupId == TeamEn.SPIDER.getCode()) {
                    workHourTrendDTO.setShangjia(hour);
                } else if (groupId == TeamEn.FLASH.getCode()) {
                    workHourTrendDTO.setQishou(hour);
                } else if (groupId == TeamEn.HAWKEYE.getCode()) {
                    workHourTrendDTO.setZhicheng(hour);
                } else if (groupId == TeamEn.BIGBANGTEST.getCode()) {
                    workHourTrendDTO.setCekai(hour);
                }
            }
            workHourTrendDTOList.add(workHourTrendDTO);
        }
        return workHourTrendDTOList;
    }

    public ProjectNumCountDTO getProjectNumCount() {
        ProgramExample example = new ProgramExample();
        List<Program> programList = programMapper.selectByExample(example);
        int initCount = 0;
        int processingCount = 0;
        int endCount = 0;
        int totalCount = 0;
        for (Program program : programList) {
            totalCount++;
            switch (program.getStatus()) {
                case "init":
                    initCount++;
                    break;
                case "scheduled":
                    initCount++;
                    break;
                case "processing":
                    processingCount++;
                    break;
                case "end":
                    endCount++;
                    break;
            }
        }
        ProjectNumCountDTO projectNumCountDTO = new ProjectNumCountDTO();
        projectNumCountDTO.setInitCount(initCount);
        projectNumCountDTO.setEndCount(endCount);
        projectNumCountDTO.setProcessingCount(processingCount);
        projectNumCountDTO.setTotalCount(totalCount);
        return projectNumCountDTO;
    }

    private int getLastTaskNormalStatus(int taskId) {
        ProgramTaskLogExample example = new ProgramTaskLogExample();
        ProgramTaskLogExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        example.setOrderByClause("id desc");
        List<ProgramTaskLog> programTaskLogList = programTaskLogMapper.selectByExample(example);
        return programTaskLogList.get(0).getIsNormal() == null ? 1 : programTaskLogList.get(0).getIsNormal();
    }

    public TaskAbnormalCountDTO getTaskAbnormalCountDTO(String startTm, String endTm) {
        List<TaskAbnormalDTO> taskAbnormalDTOList = new ArrayList<>();
        List<Map<String, Object>> programTaskLogList = programTaskLogMapperExt.getNotInnerProAbnormalList(startTm, endTm); // TODO: 2019/12/25 优化返回对象
        for (Map<String, Object> map : programTaskLogList) {
            TaskAbnormalDTO taskAbnormalDTO = new TaskAbnormalDTO();
            if (map.get("status") == null) {
                continue;
            }
            if (map.get("valid") != null && map.get("valid").toString().equals("disable")) {
                continue;
            }
            if (map.get("reason_team") == null) {
                continue;
            } else {
                //兼容以前的逻辑
                if (StringUtils.isNumeric(map.get("reason_team").toString())) {
                    taskAbnormalDTO.setReasonTeamId(map.get("reason_team").toString());
                }
            }
            if (map.get("reason_type") == null) {
                continue;
            } else {
                if (StringUtils.isNumeric(map.get("reason_type").toString())) {
                    taskAbnormalDTO.setReasonTypeId(map.get("reason_type").toString());
                }
            }
            if (map.get("abnormal_type") == null) {
                continue;
            } else {
                int abnormalType = Integer.parseInt(map.get("abnormal_type").toString());
                if (abnormalType != 1) {
                    continue;
                }
            }
            taskAbnormalDTOList.add(taskAbnormalDTO);
        }
        TaskAbnormalCountDTO taskAbnormalCountDTO = new TaskAbnormalCountDTO();
        List<TaskAbnormalCountDetailDTO> taskAbnormalCountDetailDTOList = new ArrayList<>();
        int productCount = 0;
        int developCount = 0;
        int testCount = 0;
        int operationCount = 0;
        int dbaCount = 0;
        int otherCount = 0;
        for (TaskAbnormalDTO taskAbnormalDTO : taskAbnormalDTOList) {
            if (taskAbnormalDTO.getReasonTypeId() != null) {
                TaskAbnormalCountDetailDTO taskAbnormalCountDetailDTO = new TaskAbnormalCountDetailDTO();
                taskAbnormalCountDetailDTO.setTeamId(taskAbnormalDTO.getReasonTeamId());
                taskAbnormalCountDetailDTO.setTeamName(Objects.requireNonNull(TaskAbnormalReasonTeamEn.getByCode(Integer.parseInt(taskAbnormalDTO.getReasonTeamId()))).getName());
                taskAbnormalCountDetailDTO.setTypeId(taskAbnormalDTO.getReasonTypeId());
                String typeNmae = Objects.requireNonNull(Objects.requireNonNull(TaskAbnormalReasonTypeEn.getByCode(Integer.parseInt(taskAbnormalDTO.getReasonTypeId()))).getName());
                if (!typeNmae.equals("填写有误")) {
                    taskAbnormalCountDetailDTO.setTypeName(typeNmae);
                    if (!taskAbnormalCountDetailDTOList.contains(taskAbnormalCountDetailDTO)) {
                        taskAbnormalCountDetailDTO.setCount(1);
                        taskAbnormalCountDetailDTOList.add(taskAbnormalCountDetailDTO);
                    } else {
                        TaskAbnormalCountDetailDTO existedTaskAbnormalCountDetailDTO = findTaskAbnormalCountDetailDTO(taskAbnormalCountDetailDTOList, taskAbnormalCountDetailDTO);
                        if (existedTaskAbnormalCountDetailDTO != null) {
                            existedTaskAbnormalCountDetailDTO.setCount(existedTaskAbnormalCountDetailDTO.getCount() + 1);
                        }
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
            if (taskAbnormalDTO.getReasonTeamId() != null) {
                if (taskAbnormalDTO.getReasonTeamId().equals(String.valueOf(TaskAbnormalReasonTeamEn.PRODUCT.getCode()))) {
                    productCount++;
                }
                if (taskAbnormalDTO.getReasonTeamId().equals(String.valueOf(TaskAbnormalReasonTeamEn.DEVELOP.getCode()))) {
                    developCount++;
                }
                if (taskAbnormalDTO.getReasonTeamId().equals(String.valueOf(TaskAbnormalReasonTeamEn.TEST.getCode()))) {
                    testCount++;
                }
                if (taskAbnormalDTO.getReasonTeamId().equals(String.valueOf(TaskAbnormalReasonTeamEn.OPERATION.getCode()))) {
                    operationCount++;
                }
                if (taskAbnormalDTO.getReasonTeamId().equals(String.valueOf(TaskAbnormalReasonTeamEn.DBA.getCode()))) {
                    dbaCount++;
                }
                if (taskAbnormalDTO.getReasonTeamId().equals(String.valueOf(TaskAbnormalReasonTeamEn.OTHER.getCode()))) {
                    otherCount++;
                }
            }
        }
        taskAbnormalCountDTO.setProductCount(productCount);
        taskAbnormalCountDTO.setDevelopCount(developCount);
        taskAbnormalCountDTO.setTestCount(testCount);
        taskAbnormalCountDTO.setOperationCount(operationCount);
        taskAbnormalCountDTO.setDbaCount(dbaCount);
        taskAbnormalCountDTO.setOtherCount(otherCount);
        taskAbnormalCountDTO.setTaskAbnormalCountDetailDTOList(taskAbnormalCountDetailDTOList);
        return taskAbnormalCountDTO;
    }

    public Pagination<TaskAbnormalDTO> getTaskAbnormalListByCondition(TaskAbnormalRequestDTO taskAbnormalRequestDTO) {
        Pagination<TaskAbnormalDTO> pagination = new Pagination<>();
        PageHelper.startPage(taskAbnormalRequestDTO.getPageNum(), taskAbnormalRequestDTO.getPageSize(), true);
        List<Map<String, Object>> programTaskLogList = programTaskLogMapperExt.getTaskAbnormalListByCondition(taskAbnormalRequestDTO.getsDate(), taskAbnormalRequestDTO.geteDate(), taskAbnormalRequestDTO.getStory(), taskAbnormalRequestDTO.getReasonTeam(), taskAbnormalRequestDTO.getAbnormalOwner(), taskAbnormalRequestDTO.getAbnormalType());
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(programTaskLogList);
        pagination.setCurrentPage(taskAbnormalRequestDTO.getPageNum());
        pagination.setPageSize(taskAbnormalRequestDTO.getPageSize());
        pagination.setList(convertTaskAbnormal(programTaskLogList));
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    public TaskAbnormalCollectionDTO getTaskAbnormalCollect(String startTm, String endTm) {
        TaskAbnormalCollectionDTO taskAbnormalCollectionDTO = new TaskAbnormalCollectionDTO();
        List<Map<String, Object>> programTaskLogList = programTaskLogMapperExt.getNotInnerProAbnormalList(startTm, endTm);
        List<TaskAbnormalDTO> taskAbnormalDTOList = convertTaskAbnormal(programTaskLogList);
        taskAbnormalDTOList.sort(new TaskAbnormalDTOOrderComparator());
        taskAbnormalCollectionDTO.setTaskAbnormalDTOList(taskAbnormalDTOList);
        return taskAbnormalCollectionDTO;
    }

    private List<TaskAbnormalDTO> convertTaskAbnormal(List<Map<String, Object>> programTaskLogList) { // TODO: 2019/12/25 优化该方法
        List<TaskAbnormalDTO> taskAbnormalDTOList = new ArrayList<>();
        int idx = 0;
        for (Map<String, Object> map : programTaskLogList) {
            idx++;
            if (map.get("status") == null) {
                continue;
            }
            if (map.get("valid") != null && map.get("valid").toString().equals("disable")) {
                continue;
            }
            TaskAbnormalDTO taskAbnormalDTO = new TaskAbnormalDTO();
            taskAbnormalDTO.setTaskStatus(Objects.requireNonNull(TestTaskStatusEn.getByCode(map.get("status").toString())).getName());
            if (map.get("reason_team") != null && !map.get("reason_team").toString().isEmpty()) {
                //兼容以前的逻辑
                if (StringUtils.isNumeric(map.get("reason_team").toString())) {
                    taskAbnormalDTO.setReasonTeamId(map.get("reason_team").toString());
                    taskAbnormalDTO.setReasonTeamName(Objects.requireNonNull(TaskAbnormalReasonTeamEn.getByCode(Integer.valueOf(map.get("reason_team").toString()))).getName());
                } else {
                    taskAbnormalDTO.setReasonTeamName(map.get("reason_team").toString());
                }
            } else {
                continue;
            }
            if (map.get("reason_type") != null && !"".equals(map.get("reason_type"))) {
                //兼容以前的逻辑
                if (StringUtils.isNumeric(map.get("reason_type").toString())) {
                    taskAbnormalDTO.setReasonTypeId(map.get("reason_type").toString());
                    String typeNmae = Objects.requireNonNull(TaskAbnormalReasonTypeEn.getByCode(Integer.valueOf(map.get("reason_type").toString()))).getName();
                    taskAbnormalDTO.setReasonTypeName(typeNmae);
                } else {
                    taskAbnormalDTO.setReasonTypeName(map.get("reason_type").toString());
                }
            } else {
                continue;
            }
            taskAbnormalDTO.setId(Integer.parseInt(map.get("id").toString()));
            int programId = Integer.parseInt(map.get("program_id").toString());
            if (programId != 0) {
                taskAbnormalDTO.setProjectId(programId);
                taskAbnormalDTO.setProjectName(map.get("program_name").toString());
                if (map.get("program_type").toString().equals("inner")) {
                    Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.INNER_PROJECT_TYPE.getEname(), map.get("task_code").toString());
                    taskAbnormalDTO.setTaskName(map.get("task_name") != null ? map.get("task_name").toString() : dictionary.getDictValue());
                } else {
                    Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.TEST_TASK.getEname(), map.get("task_code").toString());
                    taskAbnormalDTO.setTaskName(dictionary.getDictValue());
                }
            } else {
                taskAbnormalDTO.setTaskName(map.get("task_name") != null ? map.get("task_name").toString() : map.get("task_code").toString());
            }
            taskAbnormalDTO.setStoryId(map.get("require_id") == null ? null : Integer.parseInt(map.get("require_id").toString()));
            taskAbnormalDTO.setStoryTitle(map.get("require_relate") == null ? null : StringEscapeUtils.unescapeHtml4(map.get("require_relate").toString()));
            taskAbnormalDTO.setTaskId(Integer.parseInt(map.get("task_id").toString()));
            taskAbnormalDTO.setTesterCode(map.get("tester").toString());
            taskAbnormalDTO.setTesterName(map.get("staff_name") != null ? map.get("staff_name").toString() : "");
            taskAbnormalDTO.setReasonDetail(map.get("reason_detail") != null ? map.get("reason_detail").toString() : "");
            taskAbnormalDTO.setReasonLevel(map.get("reason_level").toString());
            taskAbnormalDTO.setCurrentNormalState(getLastTaskNormalStatus(Integer.parseInt(map.get("task_id").toString())));
            taskAbnormalDTO.setModifyTime((Date) map.get("modify_tm"));
            taskAbnormalDTO.setAbnormalType(map.get("abnormal_type") == null ? -1 : Integer.parseInt(map.get("abnormal_type").toString()));
            if (map.get("abnormal_owner") != null) {
                taskAbnormalDTO.setAbnormalOwnerCode(map.get("abnormal_owner").toString());
                StaffInfoDTO staffDTO = staffService.getStaffInfo(map.get("abnormal_owner").toString());
                if (staffDTO != null) {
                    taskAbnormalDTO.setAbnormalOwnerName("(" + staffDTO.getCode() + ")" + staffDTO.getName());
                } else {
                    taskAbnormalDTO.setAbnormalOwnerName("(" + map.get("abnormal_owner").toString() + ")" + map.get("abnormal_owner").toString());
                }
            }
            if (map.get("duplicate_abnormal") != null) {
                ProgramTaskLog programTaskLog = programTaskLogMapper.selectByPrimaryKey(Integer.parseInt(map.get("duplicate_abnormal").toString()));
                ProgramTask task = programTaskMapper.selectByPrimaryKey(programTaskLog.getTaskId());
                String detail = "(" + task.getRequireRelate() + ")【" + task.getId() + "-" + dictionaryService.getDictionaryByGroupAndCode("TEST_TASK", task.getTaskCode()).getDictValue() + "】";
                taskAbnormalDTO.setDumplicateDetail(detail);
            }
            taskAbnormalDTOList.add(taskAbnormalDTO);
        }
        return taskAbnormalDTOList;
    }

    @SuppressWarnings("unchecked")
    private void getHeapWorkRelateTaskDTO(List<HeapWorkHourDTO> heapWorkHourDTOList) throws ParseException {
        Calendar cal = Calendar.getInstance();
        for (HeapWorkHourDTO heapWorkHourDTO : heapWorkHourDTOList) {
            if (heapWorkHourDTO.getHour() != null && heapWorkHourDTO.getHour() != 0) {
                ProgramTaskExample example = new ProgramTaskExample();
                ProgramTaskExample.Criteria criteria = example.createCriteria();
                criteria.andTesterEqualTo(heapWorkHourDTO.getCode());
                List<ProgramTask> programTaskList = programTaskMapper.selectByExample(example);
                HeapWorkRelateTaskDTO heapWorkRelateTaskDTO = new HeapWorkRelateTaskDTO();
                heapWorkRelateTaskDTO.setName(heapWorkHourDTO.getName());
                heapWorkRelateTaskDTO.setDate(heapWorkHourDTO.getDate());
                List<HeapWorkTaskDTO> heapWorkTaskDTOList = new ArrayList<>();
                heapWorkRelateTaskDTO.setHeapWorkTaskDTOList(heapWorkTaskDTOList);
                for (ProgramTask programTask : programTaskList) {
                    if (!programTask.getStatus().equals("pause") && !programTask.getStatus().equals("close")) { // TODO: 2019/12/25 不要使用魔法值
                        HeapWorkTaskDTO heapWorkTaskDTO = new HeapWorkTaskDTO();
                        Date date = dateFormat.parse(heapWorkHourDTO.getDate());
                        cal.setTime(date);
                        if (isNotWeekend(cal)) {
                            String excludeDates = programTask.getExcludeDate();
                            if (excludeDates != null && !"".equals(excludeDates)) {
                                String[] excludeDateArray = excludeDates.split(",");
                                boolean isExcluded = false;
                                for (String excludeDate : excludeDateArray) {
                                    if (heapWorkHourDTO.getDate().equals(excludeDate)) {
                                        isExcluded = true;
                                        break;
                                    }
                                }
                                if (isExcluded) {
                                    continue;
                                }
                            }
                        } else {
                            String includeDates = programTask.getIncludeDate();
                            if (includeDates != null && !includeDates.isEmpty()) {
                                String[] includeDateArray = includeDates.split(",");
                                boolean isIncluded = false;
                                for (String includeDate : includeDateArray) {
                                    if (!includeDate.isEmpty() && heapWorkHourDTO.getDate().equals(includeDate)) {
                                        isIncluded = true;
                                        break;
                                    }
                                }
                                if (!isIncluded) {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        }
                        int validDays = 0;
                        float hour;
                        if (programTask.getStatus().equals("end")) {// TODO: 2019/12/25 不要使用魔法值
                            heapWorkTaskDTO.setActualStartTm(timeFormat.format(programTask.getActualStartTm()));
                            heapWorkTaskDTO.setActualEndTm(timeFormat.format(programTask.getActualEndTm()));
                            heapWorkTaskDTO.setActualHour(programTask.getActualHour());

                            Date actualStart = programTask.getActualStartTm();
                            Date actualEnd = programTask.getActualEndTm();
                            actualStart = dateFormat.parse(dateFormat.format(actualStart));
                            actualEnd = timeFormat.parse(dateFormat.format(actualEnd) + " 23:59:59");
                            if (DateUtil.sub(date, actualStart, Calendar.SECOND) < 0) {
                                continue;
                            }
                            if (DateUtil.sub(date, actualEnd, Calendar.SECOND) > 0) {
                                continue;
                            }
                            validDays = getWorkDayCount(actualStart, actualEnd, programTask);
                            if (validDays == 0) {
                                continue;
                            }
                            float actualHour = programTask.getActualHour() == null ? 0 : programTask.getActualHour();
                            hour = (float) (Math.round(actualHour / validDays * 100)) / 100;
                        } else {
                            heapWorkTaskDTO.setStartTm(timeFormat.format(programTask.getStartTm()));
                            heapWorkTaskDTO.setEndTm(timeFormat.format(programTask.getEndTm()));
                            heapWorkTaskDTO.setExpectHour(programTask.getExpectHour());

                            Date start = programTask.getStartTm();
                            Date end = programTask.getEndTm();
                            if (DateUtil.sub(date, start, Calendar.SECOND) < 0) {
                                continue;
                            }
                            if (DateUtil.sub(date, end, Calendar.SECOND) > 0) {
                                continue;
                            }
                            validDays = getWorkDayCount(start, end, programTask);
                            if (validDays == 0) {
                                continue;
                            }
                            float expectHour = programTask.getExpectHour() == null ? 0 : programTask.getExpectHour();
                            hour = (float) (Math.round(expectHour / validDays * 100)) / 100;
                        }
                        heapWorkTaskDTO.setHour(hour);

                        if (programTask.getProgramId() != 0) {
                            ProgramVO programVo = programService.getProgramById(programTask.getProgramId());
                            if (programVo.getProgramType().equals("inner")) {// TODO: 2019/12/25 不要使用魔法值
                                Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.INNER_PROJECT_TYPE.getEname(), programTask.getTaskCode());
                                heapWorkTaskDTO.setTaskName(programTask.getTaskName() != null ? programTask.getTaskName() : dictionary.getDictValue());
                            } else {
                                Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.TEST_TASK.getEname(), programTask.getTaskCode());
                                heapWorkTaskDTO.setTaskName(dictionary.getDictValue());
                            }
                            heapWorkTaskDTO.setProjectId(programVo.getId());
                            heapWorkTaskDTO.setProjectName(programVo.getProgramName());
                        } else {
                            heapWorkTaskDTO.setTaskName(programTask.getTaskName() != null ? programTask.getTaskName() : programTask.getTaskCode());
                        }
                        heapWorkTaskDTO.setTaskId(programTask.getId());
                        heapWorkTaskDTO.setStoryId(programTask.getRequireId());
                        heapWorkTaskDTO.setStoryTitle(programTask.getRequireRelate());
                        heapWorkTaskDTO.setTaskStatus(programTask.getStatus());
                        heapWorkTaskDTO.setTaskStatusName(Objects.requireNonNull(TestTaskStatusEn.getByCode(programTask.getStatus())).getName());
                        heapWorkTaskDTO.setPercent(programTask.getPercent());
                        heapWorkTaskDTO.setExcludeDate(programTask.getExcludeDate());
                        heapWorkTaskDTO.setIncludeDate(programTask.getIncludeDate());
                        heapWorkTaskDTOList.add(heapWorkTaskDTO);
                    }
                }
                heapWorkHourDTO.setHeapWorkRelateTaskDTO(heapWorkRelateTaskDTO);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HeapWorkHourDTO> getHeapDataByDepart(Integer departId, String startTm, String endTm) throws ParseException {
        List<HeapWorkHourDTO> heapWorkHourDTOList = getHeapWorkHourDTOS(departId, startTm, endTm);
        getHeapWorkRelateTaskDTO(heapWorkHourDTOList);
        return heapWorkHourDTOList;
    }

    private List<HeapWorkHourDTO> getHeapWorkHourDTOS(Integer departId, String startTm, String endTm) throws ParseException {
        Calendar cal = Calendar.getInstance();
        Date start = dateFormat.parse(startTm);
        Date end = dateFormat.parse(endTm);
        //获取过去30天内的热力数据
        List<Map<String, Object>> heapDataList = programTaskMapperExt.getHeapData(departId, dateFormat.format(DateUtil.add(start, Calendar.DATE, -30)), endTm);// TODO: 2019/12/25 优化返回对象
        List<HeapWorkHourDTO> heapWorkHourDTOList = new ArrayList<>();
        for (Date d = start; end.after(d); d = DateUtil.add(d, Calendar.DATE, 1)) {
            String date = dateFormat.format(d);
            for (Map<String, Object> heapData : heapDataList) {
                //如果task已结束，取实际开始/实际结束时间作为统计的开始/结束时间，否则用预计开始/预计结束时间进行统计
                Date start_tm = timeFormat.parse(String.valueOf(heapData.get("start_tm")));
                Date end_tm = timeFormat.parse(String.valueOf(heapData.get("end_tm")));
                String status = (String) heapData.get("status");
                HeapWorkHourDTO heapWorkHourDTO = new HeapWorkHourDTO();
                heapWorkHourDTO.setDate(date);
                heapWorkHourDTO.setCode(String.valueOf(heapData.get("tester")));
                float perHour = 0;
                if (status.equals("end")) {
                    Date actual_start_tm = timeFormat.parse(String.valueOf(heapData.get("actual_start_tm")));
                    Date actual_end_tm = timeFormat.parse(String.valueOf(heapData.get("actual_end_tm")));
                    start_tm = DateUtil.getDateStart(actual_start_tm);
                    end_tm = DateUtil.getDateEnd(actual_end_tm);
                }
                //排期的时间包含d，则计算该task在d这一天的平均工时
                if (d.getTime() >= start_tm.getTime() && d.getTime() <= end_tm.getTime()) {
                    cal.setTime(d);
                    String excludeDates = (String) heapData.get("exclude_date");
                    String includeDates = (String) heapData.get("include_date");

                    //如果d不是周末，且当前task存在特殊排除日期，判断d是否在排除列表里
                    //如果d在排除列表里则跳过当前d，继续循环下一个d；如果不在，workDayCount+1
                    if (isNotWeekend(cal) && excludeDates.contains(date)) {
                        continue;
                    } else if (!isNotWeekend(cal) && !includeDates.contains(date)) {
                        //如果d是周末，且当前task存在特殊包含日期，判断d是否在包含列表里
                        //如果d不在包含列表里，跳过当前d，继续循环下一个d
                        continue;
                    }
                    int workDayCount = getWorkDayCount(start_tm, end_tm, includeDates, excludeDates);
                    if (workDayCount == 0) {
                        continue;
                    }

                    if (!StringUtils.isEmpty(String.valueOf(heapData.get("staff_name")))) {
                        heapWorkHourDTO.setName(String.valueOf(heapData.get("staff_name")));
                    } else {
                        heapWorkHourDTO.setName(String.valueOf(heapData.get("tester")));
                    }

                    //已结束状态的任务使用actualHour，否则使用expectHour
                    if (!status.equals("end")) {
                        Float expectHour;
                        expectHour = heapData.get("expect_hour") == null ? (float) 0 : (Float) heapData.get("expect_hour");
                        perHour = expectHour / workDayCount;
                    } else {
                        Float actualHour = (Float) heapData.get("actual_hour");
                        perHour = actualHour / workDayCount;
                    }

                    //当前task在d这一天的平均工时
                    float hour = (float) (Math.round(perHour * 100)) / 100;
                    //如果heapWorkHourDTOList中已存在(员工+日期)相同的dto数据，需要与已有工时累加
                    if (heapWorkHourDTOList.size() == 0) {
                        heapWorkHourDTO.setHour(hour);
                        heapWorkHourDTOList.add(heapWorkHourDTO);
                    } else {
                        for (int i = 0; i < heapWorkHourDTOList.size(); i++) {
                            if (heapWorkHourDTO.equals(heapWorkHourDTOList.get(i))) {
                                if (heapWorkHourDTOList.get(i) != null) {
                                    float roundHour = (float) (Math.round((heapWorkHourDTOList.get(i).getHour() + hour) * 100)) / 100;
                                    heapWorkHourDTOList.get(i).setHour(roundHour);
                                }
                                break;
                            } else if (i == heapWorkHourDTOList.size() - 1) {
                                heapWorkHourDTO.setHour(hour);
                                heapWorkHourDTOList.add(heapWorkHourDTO);
                                break;
                            } else {
                                continue;
                            }
                        }
                    }
                }
            }
        }

        List<StaffInfoDTO> staffList = staffService.getDepartStaffList(String.valueOf(departId));
        for (Date d = start; end.after(d); d = DateUtil.add(d, Calendar.DATE, 1)) {
            String date = dateFormat.format(d);
            for (StaffInfoDTO staff : staffList) {
                HeapWorkHourDTO heapWorkHourDTO = new HeapWorkHourDTO();
                heapWorkHourDTO.setDate(date);
                heapWorkHourDTO.setCode(staff.getCode());
                if (StringUtils.isEmpty(staff.getName())) {
                    heapWorkHourDTO.setName(staff.getCode());
                } else {
                    heapWorkHourDTO.setName(staff.getName());
                }
                cal.setTime(d);
                for (int i = 0; i < heapWorkHourDTOList.size(); i++) {
                    HeapWorkHourDTO dto = heapWorkHourDTOList.get(i);
                    if (heapWorkHourDTO.equals(dto)) {
                        break;
                    } else if (i == heapWorkHourDTOList.size() - 1) {
                        if (isNotWeekend(cal)) {
                            heapWorkHourDTO.setHour((float) 0);
                        } else {
                            heapWorkHourDTO.setHour(null);
                        }
                        heapWorkHourDTOList.add(heapWorkHourDTO);
                    } else {
                        continue;
                    }
                }
            }
        }
        return heapWorkHourDTOList;
    }

    private int getWorkDayCount(Date startDate, Date endDate, String include, String exclude) {
        int workdays = 0;
        for (Date d = startDate; d.before(endDate); d = DateUtil.add(d, Calendar.DATE, 1)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d);
            if (isNotWeekend(calendar)) {
                if (exclude != null && !"".equals(exclude) && exclude.contains(dateFormat.format(d))) {
                    continue;
                } else {
                    workdays++;
                }
            } else {
                if (include != null && !"".equals(include) && include.contains(dateFormat.format(d))) {
                    workdays++;
                } else {
                    continue;
                }
            }
        }
        return workdays;
    }

    private int getWorkDayCount(Date startDate, Date endDate) {
        int count = 0;
        Calendar cal = Calendar.getInstance();
        while (DateUtil.sub(endDate, startDate, Calendar.SECOND) > 0) {
            cal.setTime(startDate);
            if (isNotWeekend(cal)) {
                count++;
            }
            startDate = DateUtil.add(startDate, Calendar.DATE, 1);
        }
        return count;
    }

    private int getWorkDayCount(Date startDate, Date endDate, ProgramTask programTask) {
        int count = 0;
        Calendar cal = Calendar.getInstance();
        while (DateUtil.sub(endDate, startDate, Calendar.SECOND) > 0) {
            String date = dateFormat.format(startDate);
            cal.setTime(startDate);
            if (isNotWeekend(cal)) {
                String excludeDates = programTask.getExcludeDate();
                if (excludeDates != null && !"".equals(excludeDates)) {
                    String[] excludeDateArray = excludeDates.split(",");
                    boolean isExcluded = false;
                    for (String excludeDate : excludeDateArray) {
                        if (date.equals(excludeDate)) {
                            isExcluded = true;
                            break;
                        }
                    }
                    if (!isExcluded) {
                        count++;
                    }
                } else {
                    count++;
                }
            } else {
                String includeDates = programTask.getIncludeDate();
                if (includeDates != null && !"".equals(includeDates)) {
                    String[] includeDateArray = includeDates.split(",");
                    boolean isIncluded = false;
                    for (String includeDate : includeDateArray) {
                        if (!"".equals(includeDate) && date.equals(includeDate)) {
                            isIncluded = true;
                            break;
                        }
                    }
                    if (isIncluded) {
                        count++;
                    }
                }
            }
            startDate = DateUtil.add(startDate, Calendar.DATE, 1);
        }
        return count;
    }

    private boolean isNotWeekend(Calendar cal) {
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        //0代表周日，6代表周六
        return week != 6 && week != 0;
    }

    private HeapWorkHourDTO findHeapMapDTO(List<HeapWorkHourDTO> heapWorkHourDTOList, HeapWorkHourDTO heapWorkHourDTO) {
        for (HeapWorkHourDTO heapWorkHourDTOInner : heapWorkHourDTOList) {
            if (heapWorkHourDTO.equals(heapWorkHourDTOInner)) {
                return heapWorkHourDTOInner;
            }
        }
        return null;
    }

    private TaskAbnormalCountDetailDTO findTaskAbnormalCountDetailDTO(List<TaskAbnormalCountDetailDTO> taskAbnormalCountDetailDTOList, TaskAbnormalCountDetailDTO taskAbnormalCountDetailDTO) {
        for (TaskAbnormalCountDetailDTO taskAbnormalCountDetailDTO1 : taskAbnormalCountDetailDTOList) {
            if (taskAbnormalCountDetailDTO.equals(taskAbnormalCountDetailDTO1)) {
                return taskAbnormalCountDetailDTO1;
            }
        }
        return null;
    }

    private class TaskAbnormalDTOOrderComparator implements Comparator<TaskAbnormalDTO> {
        @Override
        public int compare(TaskAbnormalDTO t1, TaskAbnormalDTO t2) {
            int level1 = Integer.parseInt(t1.getReasonLevel().substring(1));
            int level2 = Integer.parseInt(t2.getReasonLevel().substring(1));
            if (level1 > level2) {
                return 1;
            } else if (level1 == level2) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    @Override
    public List<WorkDailyReportDTO> getWorkDailyReportByDepart(Integer departId, String date) throws ParseException {
        List<WorkDailyReportDTO> workDailyReportList = new ArrayList<>();
        List<StaffInfoDTO> staffs = staffService.getDepartStaffList(departId.toString());
        for (StaffInfoDTO staffInfoDTO : staffs) {
            WorkDailyReportDTO work = new WorkDailyReportDTO();
            work.setDate(date);
            work.setCode(staffInfoDTO.getCode());
            work.setName(staffInfoDTO.getName());
            WorkDailyReportExample example1 = new WorkDailyReportExample();
            WorkDailyReportExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andStaffCodeEqualTo(staffInfoDTO.getCode());
            //日报表中前一天的"明日计划"就是这一天的"今日预期"
            criteria1.andWorkdayEqualTo(DateUtil.add(DateUtil.parse(date, "yyyy-MM-dd"), Calendar.DATE, -1)); // TODO: 2019/12/25 不要使用魔法值
            criteria1.andTypeEqualTo(20);
            List<WorkDailyReport> workDailyReports = workDailyReportMapper.selectByExample(example1);
            List<WorkReportDTO> workReportDTOS = new ArrayList<>();
            BeanUtils.copyProperties(workDailyReports, workReportDTOS);
            work.setTodayExp(workReportDTOS);

            WorkDailyReportExample example2 = new WorkDailyReportExample();
            WorkDailyReportExample.Criteria criteria2 = example2.createCriteria();
            criteria2.andStaffCodeEqualTo(staffInfoDTO.getCode());
            criteria2.andWorkdayEqualTo(DateUtil.parse(date, "yyyy-MM-dd"));
            criteria2.andTypeEqualTo(10);
            List<WorkDailyReport> workDailyReports2 = workDailyReportMapper.selectByExample(example2);
            List<WorkReportDTO> workReportDTOS2 = new ArrayList<>();
            BeanUtils.copyProperties(workDailyReports2, workReportDTOS2);
            work.setTodayAct(workReportDTOS2);

            WorkDailyReportExample example3 = new WorkDailyReportExample();
            WorkDailyReportExample.Criteria criteria3 = example3.createCriteria();
            criteria3.andStaffCodeEqualTo(staffInfoDTO.getCode());
            criteria3.andWorkdayEqualTo(DateUtil.parse(date, "yyyy-MM-dd"));
            criteria3.andTypeEqualTo(20);
            List<WorkDailyReport> workDailyReports3 = workDailyReportMapper.selectByExample(example3);
            List<WorkReportDTO> workReportDTOS3 = new ArrayList<>();
            BeanUtils.copyProperties(workDailyReports3, workReportDTOS3);
            work.setTomorrow(workReportDTOS3);
            workDailyReportList.add(work);
        }
        return workDailyReportList;
    }

    //生成每个组的明日计划，插入日报表
    public Boolean genWorkTomorrow(String date) throws ParseException {
        Date start = DateUtil.add(DateUtil.parse(date, "yyyy-MM-dd"), Calendar.DATE, 1);
        Date end = DateUtil.add(DateUtil.parse(date, "yyyy-MM-dd"), Calendar.DATE, 2);
        List<HeapWorkHourDTO> heapWorkHourDTOList1 = getHeapDataByDepart(TeamEn.SPIDER.getCode(), DateUtil.toLocaleString(start, "yyyy-MM-dd"), DateUtil.toLocaleString(end, "yyyy-MM-dd"));
        List<WorkDailyReport> workDailyReports1 = getTomorrow(heapWorkHourDTOList1, date);
        Boolean result1 = true;
        if (!workDailyReports1.isEmpty()) {
            result1 = workDailyReportMapperExt.insertBatch(workDailyReports1) > 0;
        }
        logger.info("生成商家组明日计划{}", result1);

        List<HeapWorkHourDTO> heapWorkHourDTOList2 = getHeapDataByDepart(TeamEn.FLASH.getCode(), DateUtil.toLocaleString(start, "yyyy-MM-dd"), DateUtil.toLocaleString(end, "yyyy-MM-dd"));
        List<WorkDailyReport> workDailyReports2 = getTomorrow(heapWorkHourDTOList2, date);
        Boolean result2 = true;
        if (!workDailyReports2.isEmpty()) {
            result2 = workDailyReportMapperExt.insertBatch(workDailyReports2) > 0;
        }
        logger.info("生成骑手组明日计划{}", result2);


        List<HeapWorkHourDTO> heapWorkHourDTOList3 = getHeapDataByDepart(TeamEn.HAWKEYE.getCode(), DateUtil.toLocaleString(start, "yyyy-MM-dd"), DateUtil.toLocaleString(end, "yyyy-MM-dd"));
        List<WorkDailyReport> workDailyReports3 = getTomorrow(heapWorkHourDTOList3, date);
        Boolean result3 = true;
        if (!workDailyReports3.isEmpty()) {
            result3 = workDailyReportMapperExt.insertBatch(workDailyReports3) > 0;
        }
        logger.info("生成支撑组明日计划{}", result3);

        List<HeapWorkHourDTO> heapWorkHourDTOList4 = getHeapDataByDepart(TeamEn.BIGBANGTEST.getCode(), DateUtil.toLocaleString(start, "yyyy-MM-dd"), DateUtil.toLocaleString(end, "yyyy-MM-dd"));
        List<WorkDailyReport> workDailyReports4 = getTomorrow(heapWorkHourDTOList4, date);
        Boolean result4 = true;
        if (!workDailyReports4.isEmpty()) {
            result4 = workDailyReportMapperExt.insertBatch(workDailyReports4) > 0;
        }
        logger.info("生成测开组明日计划{}", result4);
        return result1 && result2 && result3 && result4;
    }

    //生成每个组的今日实际，插入日报表
    public Boolean genWorkTodayAct(String date) throws ParseException {
        List<WorkDailyReport> workDailyReports1 = getTodayAct(TeamEn.SPIDER.getCode(), date);
        Boolean result1 = true;
        if (!workDailyReports1.isEmpty()) {
            result1 = workDailyReportMapperExt.insertBatch(workDailyReports1) > 0;
        }
        logger.info("生成商家组今日任务{}", result1);

        List<WorkDailyReport> workDailyReports2 = getTodayAct(TeamEn.FLASH.getCode(), date);
        Boolean result2 = true;
        if (!workDailyReports2.isEmpty()) {
            result2 = workDailyReportMapperExt.insertBatch(workDailyReports2) > 0;
        }
        logger.info("生成骑手组今日任务{}", result2);

        List<WorkDailyReport> workDailyReports3 = getTodayAct(TeamEn.HAWKEYE.getCode(), date);
        Boolean result3 = true;
        if (!workDailyReports3.isEmpty()) {
            result3 = workDailyReportMapperExt.insertBatch(workDailyReports3) > 0;
        }
        logger.info("生成支撑组今日任务{}", result3);

        List<WorkDailyReport> workDailyReports4 = getTodayAct(TeamEn.BIGBANGTEST.getCode(), date);
        Boolean result4 = true;
        if (!workDailyReports4.isEmpty()) {
            result4 = workDailyReportMapperExt.insertBatch(workDailyReports4) > 0;
        }
        logger.info("生成测开组今日任务{}", result4);
        return result1 && result2 && result3 && result4;
    }

    //明日计划
    private List<WorkDailyReport> getTomorrow(List<HeapWorkHourDTO> heapWorkHourDTOList, String date) throws ParseException {
        List<WorkDailyReport> tomorrowTasks = new ArrayList<>();
        //热力图中拿出date日期的任务列表
        for (HeapWorkHourDTO h : heapWorkHourDTOList) {
            List<HeapWorkTaskDTO> heapWorkTaskDTOS = new ArrayList<>();
            if (h.getHeapWorkRelateTaskDTO() != null) {
                heapWorkTaskDTOS = h.getHeapWorkRelateTaskDTO().getHeapWorkTaskDTOList();
            }
            if (!heapWorkTaskDTOS.isEmpty()) {
                for (HeapWorkTaskDTO t : h.getHeapWorkRelateTaskDTO().getHeapWorkTaskDTOList()) {
                    WorkDailyReport work = new WorkDailyReport();
                    work.setWorkday(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                    work.setStaffCode(h.getCode());
                    work.setStaffName(h.getName());
                    work.setTaskId(t.getTaskId());
                    work.setTaskName(t.getTaskName());
                    work.setRequireId(t.getStoryId());
                    work.setRequireRelate(t.getStoryTitle());
                    work.setProjectId(t.getProjectId());
                    work.setProjectName(t.getProjectName());
                    work.setTodayHour(t.getHour());
                    work.setType(20);
                    work.setPercent(t.getPercent());
                    work.setModifyTm(new Date());
                    tomorrowTasks.add(work);
                }
            }
        }
        return tomorrowTasks;
    }

    //今日实际
    private List<WorkDailyReport> getTodayAct(int departId, String date) throws ParseException {
        List<WorkDailyReport> todayActs = new ArrayList<>();
        String start = DateUtil.toLocaleString(DateUtil.getDateStart(new SimpleDateFormat("yyyy-MM-dd").parse(date)), "yyyy-MM-dd HH:mm:ss");
        String end = DateUtil.toLocaleString(DateUtil.getDateEnd(new SimpleDateFormat("yyyy-MM-dd").parse(date)), "yyyy-MM-dd HH:mm:ss");
        List<StaffInfoDTO> staffList = staffService.getDepartStaffList(String.valueOf(departId));
        for (StaffInfoDTO s : staffList) {
            //taskLog表里根据任务更新记录拿到date日期的实际工作
            List<ProgramTaskLog> programTaskLogs = programTaskLogMapperExt.getTodayWorkActFromTaskLog(start, end, s.getCode());
            for (ProgramTaskLog programTaskLog : programTaskLogs) {
                WorkDailyReport workDailyReport = new WorkDailyReport();
                workDailyReport.setWorkday(new SimpleDateFormat("yyyy-MM-dd").parse(start));
                workDailyReport.setStaffName(s.getName());
                workDailyReport.setStaffCode(s.getCode());
                workDailyReport.setTaskId(programTaskLog.getTaskId());
                workDailyReport.setRequireId(programTaskLog.getRequireId());
                ProgramTask task = programTaskMapper.selectByPrimaryKey(programTaskLog.getTaskId());
                workDailyReport.setRequireRelate(task.getRequireRelate());
                workDailyReport.setTaskCode(task.getTaskCode());
                if (programTaskLog.getProgramId() != 0) {
                    String taskName = dictionaryService.getDictionaryByDictCode(task.getTaskCode()).getDictValue();
                    workDailyReport.setTaskName(taskName);
                    Program program = programMapper.selectByPrimaryKey(programTaskLog.getProgramId());
                    workDailyReport.setProjectName(program.getProgramName());
                } else {
                    workDailyReport.setTaskName(task.getTaskName());
                }
                workDailyReport.setProjectId(programTaskLog.getProgramId());
                workDailyReport.setTodayHour(programTaskLog.getTodayHour());
                workDailyReport.setType(10);
                workDailyReport.setTaskLogId(programTaskLog.getId());
                workDailyReport.setPercent(programTaskLog.getPercent());
                workDailyReport.setTaskExplain(programTaskLog.getTaskExplain());
                workDailyReport.setReasonDetail(programTaskLog.getReasonDetail());
                workDailyReport.setIsNormal(programTaskLog.getIsNormal());
                workDailyReport.setModifyTm(new Date());
                todayActs.add(workDailyReport);
            }
        }

        return todayActs;

    }

    @Override
    public List<DepartAbnormalCountDTO> getDepartAbnormalCount(String startTm, String endTm) {
        List<DepartAbnormalCountDTO> countDTOs = new ArrayList<>();
        List<Map<String, Object>> scores = programTaskLogMapperExt.getAbnormalTotalScore(startTm, endTm);// TODO: 2019/12/25 优化返回值
        List<Map<String, Object>> topReasons = programTaskLogMapperExt.getAbnormalTopReason(startTm, endTm);// TODO: 2019/12/25 优化返回值
        List<Map<String, Object>> topOwners = programTaskLogMapperExt.getAbnormalTopOwner(startTm, endTm);// TODO: 2019/12/25 优化返回值

        for (Map p : scores) {
            DepartAbnormalCountDTO departCount = new DepartAbnormalCountDTO();
            int depart = Integer.parseInt(p.get("depart").toString());
            departCount.setDepartId(depart);
            DepartEn departEn = DepartEn.toEnum(depart);
            String departName = departEn == null ? "其他" : DepartEn.toEnum(depart).getName();
            String departParentName = departEn == null ? "其他" : DepartEn.toEnum(depart).getParentName();
            departCount.setDepartName(departName);
            departCount.setDepartParentName(departParentName);

            int number = departEn == null ? 1 : DepartEn.toEnum(depart).getNumber();
            int totalScore = Integer.parseInt(p.get("total_score").toString());
            departCount.setTotalScore(totalScore);

            String scorePerStaff = new DecimalFormat("###.00").format((float) totalScore / number);
            departCount.setScorePerStaff(Float.parseFloat(scorePerStaff));
            //找到这个部门的top异常类型和top责任人
            String topReasonType = JSONPath.eval(JSON.toJSON(topReasons), "[abnormal_depart=" + depart + "][0]['reason_type']").toString();
            String topReasonMean = "";
            String[] typeList = topReasonType.split(",");
            for (int i = 0; i < typeList.length; i++) {
                topReasonMean += TaskAbnormalReasonTypeEn.getByCode(Integer.parseInt(typeList[i])).getName() + "; ";
            }
            departCount.setTopReasonType(topReasonType);
            departCount.setTopReasonMean(topReasonMean);

            String topStaffCode = JSONPath.eval(JSON.toJSON(topOwners), "[abnormal_depart=" + depart + "][0]['abnormal_owner']").toString();
            String topStaffName = "";
            String[] codeList = topStaffCode.split(",");
            //能拿到staffName就返回name，否则(比如已离职)则返回staffCode
            for (int i = 0; i < codeList.length; i++) {
                StaffInfoDTO staffInfo = staffService.getStaffInfo(codeList[i]);
                if (staffInfo != null) {
                    topStaffName += staffInfo.getName() + "; ";
                } else {
                    topStaffName += codeList[i] + "; ";
                }
            }
            departCount.setTopStaffCode(topStaffCode);
            departCount.setTopStaffName(topStaffName);
            countDTOs.add(departCount);
        }
        return countDTOs;
    }


    @Override
    public List<TaskAbnormalDTO> getDepartAbnormalDetail(String startTm, String endTm, int departId) {
        return convertTaskAbnormal(programTaskLogMapperExt.getAbnormalDetailByDepart(startTm, endTm, departId));
    }

    @Override
    public List<WorkLoadDTO> getStaffWorkLoad(Date date) throws ParseException {
        //前端选择的月份
        Date start = DateUtil.getMonthStart(date);
        Date end = DateUtil.getMonthEnd(date);
        List<Map<String, Object>> thisMonth = programTaskLogMapperExt.getStaffWorkLoad(timeFormat.format(start), timeFormat.format(end));// TODO: 2019/12/25 优化返回值
        int workDays = DateUtil.workDays(start, end);

        //选择月份的前一个月
        Date pre = DateUtil.add(date, Calendar.MONTH, -1);
        Date preStart = DateUtil.getMonthStart(pre);
        Date preEnd = DateUtil.getMonthEnd(pre);
        List<Map<String, Object>> preMonth = programTaskLogMapperExt.getStaffWorkLoad(timeFormat.format(preStart), timeFormat.format(preEnd));// TODO: 2019/12/25 优化返回值
        int preWorkDays = DateUtil.workDays(preStart, preEnd);

        //历史所有（开始时间为系统上线日期）
        List<Map<String, Object>> hisMonth = programTaskLogMapperExt.getStaffWorkLoad("2018-12-20 00:00:00", timeFormat.format(end));// TODO: 2019/12/25 优化返回值
        int hisWorkDays = DateUtil.workDays(timeFormat.parse("2018-12-20 00:00:00"), end);

        List<WorkLoadDTO> staffWorkLoadDTOS = new ArrayList<>();
        for (Map m : thisMonth) {
            WorkLoadDTO workLoadDTO = new WorkLoadDTO();
            String staffCode = m.get("staff_code").toString();
            workLoadDTO.setStaffCode(staffCode);
            workLoadDTO.setStaffName(m.get("staff_name").toString());
            int departId = Integer.parseInt(m.get("depart_id").toString());
            String departName = TeamEn.toEnum(departId).getDesc();
            workLoadDTO.setDepartId(departId);
            workLoadDTO.setDepartName(departName);
            float total = Float.parseFloat(m.get("total_hour").toString());
            workLoadDTO.setWorkLoad(Float.parseFloat(new DecimalFormat("###.00").format(total / workDays)));
            workLoadDTO.setPlacing(Integer.parseInt(m.get("rownum").toString()));
            //找出这个staff的上个月数据及历史数据
            float preTotal = Float.parseFloat(JSONPath.eval(JSON.toJSON(preMonth), "[staff_code='" + staffCode + "'][0]['total_hour']").toString());
            workLoadDTO.setPreWorkLoad(Float.parseFloat(new DecimalFormat("###.00").format(preTotal / preWorkDays)));
            workLoadDTO.setPrePlacing(Integer.parseInt(JSONPath.eval(JSON.toJSON(preMonth), "[staff_code='" + staffCode + "'][0]['rownum']").toString()));
            float hisTotal = Float.parseFloat(JSONPath.eval(JSON.toJSON(hisMonth), "[staff_code='" + staffCode + "'][0]['total_hour']").toString());
            workLoadDTO.setHisWorkLoad(Float.parseFloat(new DecimalFormat("###.00").format(hisTotal / hisWorkDays)));
            staffWorkLoadDTOS.add(workLoadDTO);
        }
        return staffWorkLoadDTOS;
    }

    @Override
    public List<WorkLoadDTO> getDepartWorkLoad(Date date) throws ParseException {
        //前端选择的月份
        Date start = DateUtil.getMonthStart(date);
        Date end = DateUtil.getMonthEnd(date);
        List<Map<String, Object>> thisMonth = programTaskLogMapperExt.getDepartWorkLoad(timeFormat.format(start), timeFormat.format(end));// TODO: 2019/12/25 优化返回值
        int workDays = DateUtil.workDays(start, end);

        //选择月份的前一个月
        Date pre = DateUtil.add(date, Calendar.MONTH, -1);
        Date preStart = DateUtil.getMonthStart(pre);
        Date preEnd = DateUtil.getMonthEnd(pre);
        List<Map<String, Object>> preMonth = programTaskLogMapperExt.getDepartWorkLoad(timeFormat.format(preStart), timeFormat.format(preEnd));// TODO: 2019/12/25 优化返回值
        int preWorkDays = DateUtil.workDays(preStart, preEnd);

        //历史所有（开始时间为系统上线日期）
        List<Map<String, Object>> hisMonth = programTaskLogMapperExt.getDepartWorkLoad("2018-12-20 00:00:00", timeFormat.format(end));// TODO: 2019/12/25 优化返回值
        int hisWorkDays = DateUtil.workDays(timeFormat.parse("2018-12-20 00:00:00"), end);
        List<WorkLoadDTO> staffWorkLoadDTOS = new ArrayList<>();
        for (Map m : thisMonth) {
            WorkLoadDTO workLoadDTO = new WorkLoadDTO();
            int departId = Integer.parseInt(m.get("depart_id").toString());
            String departName = TeamEn.toEnum(departId).getDesc();
            workLoadDTO.setDepartId(departId);
            workLoadDTO.setDepartName(departName);
            float perHour = Float.parseFloat(m.get("per_hour").toString());
            workLoadDTO.setWorkLoad(Float.parseFloat(new DecimalFormat("###.00").format(perHour / workDays)));
            workLoadDTO.setPlacing(Integer.parseInt(m.get("rownum").toString()));
            //找出这个depart上个月数据及历史数据
            float prePerHour = Float.parseFloat(JSONPath.eval(JSON.toJSON(preMonth), "[depart_id=" + departId + "][0]['per_hour']").toString());
            workLoadDTO.setPreWorkLoad(Float.parseFloat(new DecimalFormat("###.00").format(prePerHour / preWorkDays)));
            workLoadDTO.setPrePlacing(Integer.parseInt(JSONPath.eval(JSON.toJSON(preMonth), "[depart_id=" + departId + "][0]['rownum']").toString()));
            float hisPerHour = Float.parseFloat(JSONPath.eval(JSON.toJSON(hisMonth), "[depart_id=" + departId + "][0]['per_hour']").toString());
            workLoadDTO.setHisWorkLoad(Float.parseFloat(new DecimalFormat("###.00").format(hisPerHour / hisWorkDays)));
            staffWorkLoadDTOS.add(workLoadDTO);
        }
        return staffWorkLoadDTOS;
    }

}
