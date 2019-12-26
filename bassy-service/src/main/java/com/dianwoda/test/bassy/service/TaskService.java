package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.domain.dto.task.TestTaskDocumentDTO;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.entity.ProgramTask;
import com.dianwoda.test.bassy.db.entity.ProgramTaskLog;
import com.dianwoda.test.bassy.common.domain.dto.ProgramTaskDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProgramTaskParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.UpdateTaskLogParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.CalendarTaskEventDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.UpdateTaskDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.UpdateTaskStatusDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramTaskVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcp on 2018/5/22.
 * Time always， fat thin man all miss.
 */
public interface TaskService {

    /**
     * 根据项目ID获取任务列表
     */
    Pagination<ProgramTask> getProgramTasksByProgramId(ProgramTaskParamDTO paramDTO);

    /**
     * 获取所有任务列表
     * @param paramDTO
     * @return
     */
    Pagination<ProgramTaskVO> getAllTasks(ProgramTaskParamDTO paramDTO);

    /**
     * 根据项目Id获取未结束状态任务
     *
     * @param programId
     * @return
     */
    List<ProgramTask> getNotEndTasks(Integer programId);

    /**
     * 更新任务进度
     */
    Boolean updateTask(UpdateTaskDTO taskDTO);
    /**
     * 获取任务更新记录
     */
    Pagination<ProgramTaskLog> getUpdateTaskLogByTaskId(UpdateTaskLogParamDTO paramDTO);

    /**
     * 获取任务的异常记录
     * @param taskId
     * @return
     */
    List<ProgramTaskLog> getAbnormalByTaskId(Integer taskId);


    /**
     * 开始任务
     */
    Boolean startTask(UpdateTaskStatusDTO dto);

    /**
     * 更新任务状态
     */
    Boolean updateTaskStatus(UpdateTaskStatusDTO dto);

    /**
     * 上传任务附件
     */
    void pushTaskDocument(Integer programId, Integer requireId, Integer taskId, String creator, ArrayList<TestTaskDocumentDTO> files) throws IOException;

    /**
     * 获取任务最新的更新记录
     * @param taskId 任务Id
     * @return
     */
    ProgramTaskLog getLastProgramTaskLog(Integer taskId);

    /**
     * 编辑任务
     */
    Boolean editTask(ProgramTaskDTO programTaskDTO);

    /**
     * 获取我的任务
     */
    List<CalendarTaskEventDTO> getMyCalendarTasks(String code);
}
