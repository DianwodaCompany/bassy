package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.domain.dto.ProgramTaskParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.UpdateTaskLogParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.statistics.TaskAbnormalDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.CalendarTaskEventDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.TestTaskDocumentDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.UpdateTaskDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.UpdateTaskStatusDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramTaskDetailVO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramTaskVO;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.dao.ProgramTaskLogMapper;
import com.dianwoda.test.bassy.db.entity.ProgramTask;
import com.dianwoda.test.bassy.db.entity.ProgramTaskLog;
import com.dianwoda.test.bassy.service.DictionaryService;
import com.dianwoda.test.bassy.service.TaskService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zcp on 2018/5/7.
 * Time always， fat thin man all miss.
 */
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProgramTaskLogMapper programTaskLogMapper;

    @Autowired
    DictionaryService dictionaryService;

    @Resource
    private StaffService staffService;

    @ApiOperation(value = "获取测试任务列表", notes = "")
    @ApiImplicitParam(name = "ProgramTaskParamDTO", value = "查询和分页参数", required = true)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Pagination<ProgramTaskVO> getAllTasks(@RequestBody ProgramTaskParamDTO paramDTO) {
        return taskService.getAllTasks(paramDTO);
    }

    @ApiOperation(value = "根据项目获取测试计划", notes = "")
    @ApiImplicitParam(name = "programId", value = "项目Id", required = true)
    @RequestMapping(value = "/list/program", method = RequestMethod.POST)
    public Pagination<ProgramTaskDetailVO> getProgramTasks(@RequestBody ProgramTaskParamDTO paramDTO) {
        Pagination<ProgramTaskDetailVO> pagination = new Pagination<>();
        Pagination<ProgramTask> programTaskPagination = taskService.getProgramTasksByProgramId(paramDTO);
        BeanUtils.copyProperties(programTaskPagination, pagination, "list");
        List<ProgramTask> programTasks = programTaskPagination.getList();
        List<ProgramTaskDetailVO> programTaskDetailVOS = new ArrayList<>();
        for (ProgramTask programTask : programTasks) {
            ProgramTaskDetailVO programTaskDetailVO = new ProgramTaskDetailVO();
            BeanUtils.copyProperties(programTask, programTaskDetailVO);
            if (programTask.getTester() != null) {
                String doTester = staffService.getStaffInfo(programTask.getTester()).getName();
                programTaskDetailVO.setTester(doTester);
            }
            ProgramTaskLog programTaskLog = taskService.getLastProgramTaskLog(programTask.getId());
            programTaskDetailVO.setActualHour(programTaskLog.getActualHour());
            programTaskDetailVO.setPercent(programTaskLog.getPercent());
            programTaskDetailVO.setIsNormal(programTaskLog.getIsNormal());
            programTaskDetailVOS.add(programTaskDetailVO);
        }
        pagination.setList(programTaskDetailVOS);
        return pagination;
    }

    @ApiOperation(value = "获取我的任务", notes = "")
    @ApiImplicitParam(name = "MyTaskParamDTO", value = "分页参数", required = true)
    @RequestMapping(value = "/list/myCalendarTask/{code}", method = RequestMethod.POST)
    public List<CalendarTaskEventDTO> getMyCalendarTasks(@PathVariable("code") String code) {
        return taskService.getMyCalendarTasks(code);
    }

    @ApiOperation(value = "更新任务", notes = "")
    @ApiImplicitParam(name = "UpdateTaskDTO", value = "参数", required = true)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Boolean updateTask(@RequestBody UpdateTaskDTO paramDTO) {
        logger.info("更新任务");
        return taskService.updateTask(paramDTO);
    }

    @ApiOperation(value = "获取更新任务记录", notes = "")
    @ApiImplicitParam(name = "UpdateTaskLogParamDTO", value = "参数", required = true)
    @RequestMapping(value = "/update/history", method = RequestMethod.POST)
    public Pagination<ProgramTaskLog> getUpdateTaskLog(@RequestBody UpdateTaskLogParamDTO paramDTO) throws Exception {
        logger.info("获取更新任务历史记录");
        return taskService.getUpdateTaskLogByTaskId(paramDTO);
    }

    @ApiOperation(value = "获取任务异常列表", notes = "")
    @ApiImplicitParam(name = "taskId", value = "参数", required = true)
    @RequestMapping(value = "/list/abnormal", method = RequestMethod.POST)
    public List<ProgramTaskLog> getTaskAbnormalLog(@RequestBody Integer taskId) {
        logger.info("获取任务异常历史");
        return taskService.getAbnormalByTaskId(taskId);
    }

    @ApiOperation(value = "开始任务", notes = "")
    @ApiImplicitParam(name = "UpdateTaskStatusDTO", value = "参数", required = true)
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public Boolean startTask(@RequestBody UpdateTaskStatusDTO paramDTO) {
        logger.info("更新任务状态");
        return taskService.startTask(paramDTO);
    }

    @ApiOperation(value = "上传任务附件", notes = "")
    @ApiImplicitParam(name = "", value = "参数", required = true)
    @RequestMapping(value = "/document/push", method = RequestMethod.POST)
    public void updateTaskDocument(@RequestParam("programId") Integer programId,
                                   @RequestParam("requireId") Integer requireId,
                                   @RequestParam("taskId") Integer taskId,
                                   @RequestParam("creator") String creator,
                                   @RequestParam("files") MultipartFile[] uploadFiles) throws IOException {
        ArrayList<TestTaskDocumentDTO> files = new ArrayList<>();

        for(MultipartFile multipartFile : uploadFiles){
            TestTaskDocumentDTO testTaskDocumentDTO = new TestTaskDocumentDTO();
            testTaskDocumentDTO.setFileName(multipartFile.getOriginalFilename());
            testTaskDocumentDTO.setInputStream(multipartFile.getInputStream());
            files.add(testTaskDocumentDTO);
        }
        taskService.pushTaskDocument(programId, requireId, taskId, creator, files);
    }

    @ApiOperation(value = "作废任务日志", notes = "")
    @ApiImplicitParam(name = "", value = "参数", required = true)
    @RequestMapping(value = "/invalid/taskLog/{id}", method = RequestMethod.POST)
    public Boolean invalidTaskLog(@PathVariable("id") Integer id) {
        ProgramTaskLog programTaskLog = programTaskLogMapper.selectByPrimaryKey(id);
        programTaskLog.setValid("disable");
        return programTaskLogMapper.updateByPrimaryKey(programTaskLog) > 0;
    }

    @ApiOperation(value = "更新任务日志", notes = "")
    @ApiImplicitParam(name = "", value = "参数", required = true)
    @RequestMapping(value = "/taskLog/update", method = RequestMethod.POST)
    public Boolean updateTaskLog(@RequestBody TaskAbnormalDTO taskAbnormalDTO) {
        ProgramTaskLog programTaskLog = programTaskLogMapper.selectByPrimaryKey(taskAbnormalDTO.getId());
        programTaskLog.setReasonTeam(taskAbnormalDTO.getReasonTeamId());
        programTaskLog.setReasonType(String.valueOf(taskAbnormalDTO.getReasonTypeId()));
        programTaskLog.setReasonDetail(taskAbnormalDTO.getReasonDetail());
        programTaskLog.setAbnormalOwner(taskAbnormalDTO.getAbnormalOwnerName());
        if (taskAbnormalDTO.getAbnormalOwnerName() != null && !taskAbnormalDTO.getAbnormalOwnerName().isEmpty()) {
            programTaskLog.setAbnormalDepart(staffService.getStaffInfo(taskAbnormalDTO.getAbnormalOwnerName()).getDepart().shortValue());
        }
        programTaskLog.setDuplicateAbnormal(taskAbnormalDTO.getDuplicateAbnormal());
        programTaskLog.setModifyTm(new Date());
        return programTaskLogMapper.updateByPrimaryKey(programTaskLog) > 0;
    }
}
