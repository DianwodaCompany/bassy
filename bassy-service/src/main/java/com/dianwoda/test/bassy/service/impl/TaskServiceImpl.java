package com.dianwoda.test.bassy.service.impl;

import com.aliyun.oss.OSSClient;
import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.domain.dto.ProgramTaskDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProgramTaskParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.UpdateTaskLogParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.CalendarTaskEventDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.TestTaskDocumentDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.UpdateTaskDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.UpdateTaskStatusDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramTaskVO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramVO;
import com.dianwoda.test.bassy.common.enums.DictionaryEn;
import com.dianwoda.test.bassy.common.enums.TestTaskStatusEn;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.dao.ProgramDocumentMapper;
import com.dianwoda.test.bassy.db.dao.ProgramTaskLogMapper;
import com.dianwoda.test.bassy.db.dao.ProgramTaskMapper;
import com.dianwoda.test.bassy.db.dao.ProgramTaskMapperExt;
import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.service.DictionaryService;
import com.dianwoda.test.bassy.service.ProgramService;
import com.dianwoda.test.bassy.service.TaskService;
import com.dianwoda.test.bassy.service.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.dianwoda.test.bassy.common.constants.DataStatusConstant.DOCUMENT_ENABLE;


/**
 * Created by zcp on 2018/5/22.
 * Time always， fat thin man all miss.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ProgramTaskMapper programTaskMapper;

    @Autowired
    private ProgramTaskMapperExt programTaskMapperExt;

    @Autowired
    private ProgramDocumentMapper programDocumentMapper;

    @Autowired
    private ProgramService programService;

    @Autowired
    private ProgramTaskLogMapper programTaskLogMapper;

    @Resource
    private StaffService staffService;

    @Autowired
    private DictionaryService dictionaryService;

    @Value("${endpoint}")
    private String endpoint;

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessKeySecret;

    @Value("${bucketName}")
    private String bucketName;

    @Override
    public Pagination<ProgramTask> getProgramTasksByProgramId(ProgramTaskParamDTO paramDTO) {
        Pagination<ProgramTask> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        ProgramTaskExample example = new ProgramTaskExample();
        ProgramTaskExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(paramDTO.getProgramId());
        if (paramDTO.getStatus() != null && !paramDTO.getStatus().isEmpty()) {
            criteria.andStatusEqualTo(paramDTO.getStatus());
        }
        if (paramDTO.getEndTm() != null && !paramDTO.getEndTm().toString().isEmpty()) {
            String end = new SimpleDateFormat("yyyy-MM-dd").format(paramDTO.getEndTm());
            criteria.andStartTmLessThanOrEqualTo(DateUtil.parse(end, "yyyy-MM-dd"));
        }
        if (paramDTO.getStartTm() != null && !paramDTO.getStartTm().toString().isEmpty()) {
            String start = new SimpleDateFormat("yyyy-MM-dd").format(paramDTO.getStartTm());
            criteria.andEndTmGreaterThanOrEqualTo(DateUtil.parse(start, "yyyy-MM-dd"));
        }
        List<ProgramTask> programTasks = programTaskMapper.selectByExample(example);
        PageInfo<ProgramTask> pageInfo = new PageInfo<>(programTasks);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(programTasks);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public Pagination<ProgramTaskVO> getAllTasks(ProgramTaskParamDTO paramDTO) {
        String start = paramDTO.getStartTm() == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(paramDTO.getStartTm());
        String end = paramDTO.getEndTm() == null ? null : new SimpleDateFormat("yyyy-MM-dd").format(paramDTO.getEndTm());

        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        List<ProgramTask> taskList = programTaskMapperExt.selectByCondition(paramDTO.getSearchKey(), start, end, paramDTO.getStatus(), paramDTO.getTester(), paramDTO.getProcess());// TODO: 2019/12/25 优化返回值
        List<ProgramTaskVO> programTaskVOS = new ArrayList<>();
        for (ProgramTask programTask : taskList) {
            ProgramVO programVO = programService.getProgramById(programTask.getProgramId());
            ProgramTaskVO programTaskVO = new ProgramTaskVO();
            BeanUtils.copyProperties(programTask,programTaskVO);
            programTaskVO.setProgramName(programVO.getProgramName());
            programTaskVOS.add(programTaskVO);
        }
        PageInfo<ProgramTask> pageInfo = new PageInfo<>(taskList);
        Pagination<ProgramTaskVO> pagination = new Pagination<>();
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(programTaskVOS);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public List<ProgramTask> getNotEndTasks(Integer programId) {
        ProgramTaskExample example = new ProgramTaskExample();
        ProgramTaskExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(programId);
        criteria.andStatusNotEqualTo(TestTaskStatusEn.END.getCode());
        criteria.andStatusNotEqualTo(TestTaskStatusEn.CLOSE.getCode());
        return programTaskMapper.selectByExample(example);
    }

    public List<CalendarTaskEventDTO> getMyCalendarTasks(String code) {
        List<CalendarTaskEventDTO> calendarTaskEventDTOList = new ArrayList<>();
        ProgramTaskExample example = new ProgramTaskExample();
        ProgramTaskExample.Criteria criteria = example.createCriteria();
        criteria.andTesterEqualTo(code);
        criteria.andStatusNotEqualTo(TestTaskStatusEn.CLOSE.getCode());
        List<ProgramTask> programTaskList = programTaskMapper.selectByExample(example);
        for (ProgramTask programTask : programTaskList) {
            CalendarTaskEventDTO calendarTaskEventDTO = new CalendarTaskEventDTO();
            calendarTaskEventDTO.setId(programTask.getId());
            calendarTaskEventDTO.setStatus(programTask.getStatus());
            if (timeFormat.format(programTask.getStartTm()).endsWith("00:00:00")) {
                calendarTaskEventDTO.setStart(dateFormat.format(programTask.getStartTm()));
            } else {
                calendarTaskEventDTO.setEnd(timeFormat.format(programTask.getStartTm()));
            }
            if (timeFormat.format(programTask.getEndTm()).endsWith("23:59:59")) {
                calendarTaskEventDTO.setEnd(dateFormat.format(DateUtil.add(programTask.getEndTm(), Calendar.DATE, 1)));
            } else {
                calendarTaskEventDTO.setEnd(timeFormat.format(programTask.getEndTm()));
            }
            String title = "";
            String taskName = "";
            ProgramVO programVo = null;
            if (programTask.getProgramId() != 0) {
                programVo = programService.getProgramById(programTask.getProgramId());
                if (programVo.getProgramType().equals("inner")) {
                    Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.INNER_PROJECT_TYPE.getEname(), programTask.getTaskCode());
                    taskName = programTask.getTaskName() != null ? programTask.getTaskName() : dictionary.getDictValue();
                } else {
                    Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.TEST_TASK.getEname(), programTask.getTaskCode());
                    taskName = dictionary.getDictValue();
                }
            } else {
                taskName = programTask.getTaskName() != null ? programTask.getTaskName() : programTask.getTaskCode();
            }
            if (programTask.getRequireId() != null) {
                title = programTask.getRequireRelate() + " " + taskName;
            } else if (programTask.getProgramId() != 0) {
                if (programVo != null) {
                    title = programVo.getProgramName() + " " + taskName;
                }
            } else {
                title = taskName;
            }
            calendarTaskEventDTO.setTitle(title);
            calendarTaskEventDTO.setProgramName(programVo == null ? null : programVo.getProgramName());
            ProgramTaskDTO programTaskDTO = new ProgramTaskDTO();
            BeanUtils.copyProperties(programTask, programTaskDTO);
            calendarTaskEventDTO.setProgramTask(programTaskDTO);
            calendarTaskEventDTOList.add(calendarTaskEventDTO);
        }
        calendarTaskEventDTOList.sort(new CalendarTaskEventDTOOrderComparator());
        return calendarTaskEventDTOList;
    }

    private class CalendarTaskEventDTOOrderComparator implements Comparator<CalendarTaskEventDTO> {
        private int statusToNum(String status) {
            if (status.equals(TestTaskStatusEn.INIT.getCode())) {
                return 5;
            } else if (status.equals(TestTaskStatusEn.PROCESSING.getCode())) {
                return 4;
            } else if (status.equals(TestTaskStatusEn.PAUSE.getCode())) {
                return 3;
            } else if (status.equals(TestTaskStatusEn.END.getCode())) {
                return 2;
            } else {
                return 1;
            }
        }

        @Override
        public int compare(CalendarTaskEventDTO t1, CalendarTaskEventDTO t2) {
            String status1 = t1.getStatus();
            String status2 = t2.getStatus();
            if (statusToNum(status2) > statusToNum(status1)) {
                return 1;
            } else if (statusToNum(status2) == statusToNum(status1)) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    @Override
    @Transactional
    public Boolean updateTask(UpdateTaskDTO taskDTO) {
        ProgramTaskLog programTaskLog = new ProgramTaskLog();
        BeanUtils.copyProperties(taskDTO, programTaskLog);
        ProgramTask programTask = programTaskMapper.selectByPrimaryKey(taskDTO.getTaskId());
        BeanUtils.copyProperties(programTask, programTaskLog, "id");
        Date modify = new Date();
        programTaskLog.setTaskId(programTask.getId());
        programTaskLog.setActualHour(taskDTO.getActualHour() + taskDTO.getTodayHour());
        programTaskLog.setPercent(taskDTO.getPercent());
        programTaskLog.setModifier(taskDTO.getModifier());
        programTaskLog.setModifyTm(modify);
        programTaskLog.setValid("enable");
        if (taskDTO.getAbnormalOwner() != null && !"".equals(taskDTO.getAbnormalOwner())) {
            programTaskLog.setAbnormalDepart(staffService.getStaffInfo(taskDTO.getAbnormalOwner()).getDepart().shortValue());
        }
        programTaskLog.setDuplicateAbnormal(taskDTO.getDuplicateAbnormal());
        ProgramTask p = new ProgramTask();
        p.setModifyTm(modify);
        p.setId(taskDTO.getTaskId());
        p.setStatus(taskDTO.getTaskStatus());
        p.setPercent(taskDTO.getPercent());
        p.setActualHour(taskDTO.getActualHour() + taskDTO.getTodayHour());
        if (TestTaskStatusEn.END.getCode().equals(taskDTO.getTaskStatus()) || TestTaskStatusEn.CLOSE.getCode().equals(taskDTO.getTaskStatus())) {
            p.setActualEndTm(new Date());
            UpdateTaskStatusDTO dto = new UpdateTaskStatusDTO();
            dto.setTaskId(taskDTO.getTaskId());
            dto.setModifier(taskDTO.getModifier());
            dto.setStatus(taskDTO.getTaskStatus());
        }
        return programTaskLogMapper.insert(programTaskLog) > 0 && programTaskMapper.updateByPrimaryKeySelective(p) > 0;
    }

    @Override
    public Pagination<ProgramTaskLog> getUpdateTaskLogByTaskId(UpdateTaskLogParamDTO paramDTO) {
        Pagination<ProgramTaskLog> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        ProgramTaskLogExample example = new ProgramTaskLogExample();
        ProgramTaskLogExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(paramDTO.getTaskId());
        example.setOrderByClause("id DESC");
        List<ProgramTaskLog> programTaskLogs = programTaskLogMapper.selectByExample(example);
        PageInfo<ProgramTaskLog> pageInfo = new PageInfo<>(programTaskLogs);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(programTaskLogs);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public List<ProgramTaskLog> getAbnormalByTaskId(Integer taskId) {
        ProgramTaskLogExample example = new ProgramTaskLogExample();
        ProgramTaskLogExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        criteria.andIsNormalEqualTo((byte) 0);
        criteria.andValidEqualTo("enable");
        criteria.andAbnormalTypeEqualTo((byte) 1);
        criteria.andDuplicateAbnormalIsNull();
        example.setOrderByClause("id DESC");
        return programTaskLogMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public Boolean startTask(UpdateTaskStatusDTO dto) {
        ProgramTask programTask = new ProgramTask();
        Integer taskId = dto.getTaskId();
        programTask.setId(taskId);
        programTask.setStatus(dto.getStatus());
        programTask.setActualStartTm(new Date());
        boolean updateProgramTaskStatusResult = programTaskMapper.updateByPrimaryKeySelective(programTask) > 0;
        boolean updateProgramTaskLogResult = false;
        ProgramTaskLog programTaskLog = this.getLastProgramTaskLog(taskId);
        //如果当前没有操作记录，需要查出task的信息并copy值到log
        if (programTaskLog.getId() == null) {
            ProgramTask p = programTaskMapper.selectByPrimaryKey(taskId);
            BeanUtils.copyProperties(p, programTaskLog, "id");
            programTaskLog.setTaskId(taskId);
        }
        programTaskLog.setTaskStatus(dto.getStatus());
        programTaskLog.setId(null);
        programTaskLog.setModifier(dto.getModifier());
        programTaskLog.setModifyTm(new Date());
        programTaskLog.setIsNormal((byte) 1);
        programTaskLog.setReasonTeam(null);
        programTaskLog.setReasonType(null);
        programTaskLog.setReasonLevel(null);
        programTaskLog.setReasonDetail(null);
        programTaskLog.setValid("enable");
        updateProgramTaskLogResult = programTaskLogMapper.insert(programTaskLog) > 0;

        return updateProgramTaskStatusResult && updateProgramTaskLogResult;
    }

    @Override
    @Transactional
    public Boolean updateTaskStatus(UpdateTaskStatusDTO dto) {
        ProgramTask programTask = new ProgramTask();
        Integer taskId = dto.getTaskId();
        programTask.setId(taskId);
        programTask.setStatus(dto.getStatus());
        boolean updateProgramTaskStatusResult = programTaskMapper.updateByPrimaryKeySelective(programTask) > 0;
        ProgramTaskLog programTaskLog = this.getLastProgramTaskLog(taskId);
        //如果当前没有操作记录，需要查出task的信息并copy值到log
        if (programTaskLog.getId() == null) {
            ProgramTask p = programTaskMapper.selectByPrimaryKey(taskId);
            BeanUtils.copyProperties(p, programTaskLog, "id");
            programTaskLog.setTaskId(taskId);
        }
        programTaskLog.setTaskStatus(dto.getStatus());
        programTaskLog.setId(null);
        programTaskLog.setModifier(dto.getModifier());
        programTaskLog.setModifyTm(new Date());
        programTaskLog.setIsNormal((byte) 1);
        programTaskLog.setReasonTeam(null);
        programTaskLog.setReasonType(null);
        programTaskLog.setReasonLevel(null);
        programTaskLog.setReasonDetail(null);
        programTaskLog.setValid("enable");
        boolean updateProgramTaskLogResult = programTaskLogMapper.insert(programTaskLog) > 0;
        return updateProgramTaskStatusResult && updateProgramTaskLogResult;
    }

    @Override
    public void pushTaskDocument(Integer programId, Integer requireId, Integer taskId, String creator,
                                 ArrayList<TestTaskDocumentDTO> files) throws IOException {

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //无需求任务文档默认为000000000
        requireId = (requireId == -1) ? 0 : requireId;
        // 上传文件流。
        for (TestTaskDocumentDTO file : files) {
            String filePathName = "bassy/project-test/" + programId + "/" + requireId + "/" + taskId + "/"
                    + file.getFileName();
            ossClient.putObject(bucketName, filePathName, file.getInputStream());
            ProgramDocument programDocument = new ProgramDocument();
            programDocument.setProgramId(programId);
            programDocument.setRequireId(requireId);
            programDocument.setTaskId(taskId);
            programDocument.setDocumentName(file.getFileName());
            programDocument.setDocumentPath(filePathName);
            programDocument.setStatus(DOCUMENT_ENABLE);
            programDocument.setCreator(creator);
            programDocument.setCreateTm(new Date());
            programDocumentMapper.insert(programDocument);
        }
        ossClient.shutdown();
    }

    @Override
    public ProgramTaskLog getLastProgramTaskLog(Integer taskId) {
        ProgramTaskLogExample example = new ProgramTaskLogExample();
        ProgramTaskLogExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        example.setOrderByClause("id DESC limit 1");
        List<ProgramTaskLog> programTaskLogList = programTaskLogMapper.selectByExample(example);
        return programTaskLogList.isEmpty() ? new ProgramTaskLog() : programTaskLogList.get(0);
    }

    @Override
    @Transactional
    public Boolean editTask(ProgramTaskDTO programTaskDTO) {
        //定义更新计划result、插入操作记录result
        Boolean updateTaskResult, insertLogResult = true;
        ProgramTask programTask = new ProgramTask();
        BeanUtils.copyProperties(programTaskDTO, programTask);
        programTask.setModifyTm(DateUtil.getCurrentTime());
        //更新一条测试计划
        updateTaskResult = programTaskMapper.updateByPrimaryKeySelective(programTask) > 0;
        logger.info("更新任务{}-{}", programTask.getId(), updateTaskResult);
        if (updateTaskResult) {
            //插入task的操作记录
            ProgramTaskLogExample example = new ProgramTaskLogExample();
            ProgramTaskLogExample.Criteria criteria = example.createCriteria();
            criteria.andTaskIdEqualTo(programTask.getId());
            example.setOrderByClause("id DESC limit 1");
            //找到该task的最后一条更新记录
            List<ProgramTaskLog> lastProgramTaskLog = programTaskLogMapper.selectByExample(example);
            ProgramTaskLog programTaskLog = new ProgramTaskLog();
            //如果当前插入的不是第一条操作记录，需要拷贝上一条记录的相关值，但不拷贝今日耗时
            if (!lastProgramTaskLog.isEmpty()) {
                programTaskLog = lastProgramTaskLog.get(0);
                programTaskLog.setId(null);
                programTaskLog.setTodayHour(0f);
            } else {
                programTaskLog.setTaskId(programTaskDTO.getId());
            }
            //copy 前端传过来的值到操作记录，不copy id和taskId这两个字段
            BeanUtils.copyProperties(programTaskDTO, programTaskLog, "id", "taskId");
            if (programTaskDTO.getAbnormalOwner() != null && !"".equals(programTaskDTO.getAbnormalOwner())) {
                programTaskLog.setAbnormalDepart(staffService.getStaffInfo(programTaskDTO.getAbnormalOwner()).getDepart().shortValue());
            }
            programTaskLog.setTaskStatus(programTaskDTO.getStatus());
            programTaskLog.setModifyTm(new Date());
            programTaskLog.setValid("enable");
            //插入最新的操作记录
            insertLogResult = programTaskLogMapper.insertSelective(programTaskLog) > 0;
            logger.info("插入任务{}操作记录-{}", programTask.getId(), insertLogResult);
        }
        return updateTaskResult && insertLogResult;
    }

}
