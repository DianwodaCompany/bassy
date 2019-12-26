package com.dianwoda.test.bassy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.domain.dto.*;
import com.dianwoda.test.bassy.common.domain.dto.program.DeleteRequireDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.ProgramReportDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.TransferRequireDTO;
import com.dianwoda.test.bassy.common.domain.dto.task.UpdateTaskStatusDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramVO;
import com.dianwoda.test.bassy.common.domain.vo.RequireBoardVO;
import com.dianwoda.test.bassy.common.enums.ProgramStatusEn;
import com.dianwoda.test.bassy.common.enums.ProgramTypeEn;
import com.dianwoda.test.bassy.common.enums.TestTaskStatusEn;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.dao.*;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.service.ProgramModuleService;
import com.dianwoda.test.bassy.service.ProgramService;
import com.dianwoda.test.bassy.service.TaskService;
import com.dianwoda.test.bassy.service.util.BassyUtil;
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
import java.util.*;

import static com.dianwoda.test.bassy.common.constants.DataStatusConstant.*;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 11:47
 * Copyright(c) for dianwoda
 */
@Service
public class ProgramServiceImpl implements ProgramService {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProgramMapper programMapper;

    @Autowired
    private ProgramMapperExt programMapperExt;

    @Autowired
    private ProgramTaskMapper programTaskMapper;

    @Autowired
    private ProgramTaskMapperExt programTaskMapperExt;

    @Autowired
    private ProgramLogMapper programLogMapper;

    @Value("${endpoint}")
    private String endpoint;

    @Value("${accessKeyId}")
    private String accessKeyId;

    @Value("${accessKeySecret}")
    private String accessKeySecret;

    @Value("${bucketName}")
    private String bucketName;

    @Autowired
    private ProgramTaskLogMapper programTaskLogMapper;

    @Autowired
    private TaskService taskService;

    @Resource
    private StaffService staffService;

    @Autowired
    private ProgramModuleService programModuleService;

    @Autowired
    private ProgramRequireMapper programRequireMapper;

    @Autowired
    private ProgramDocumentMapper programDocumentMapper;

    @Override
    public Pagination<ProgramVO> list(ProgramParamDTO paramDTO) {
        Pagination<ProgramVO> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        if (paramDTO.getStatus() != null && !paramDTO.getStatus().isEmpty()) {
            criteria.andStatusEqualTo(paramDTO.getStatus());
        }
        if (paramDTO.getName() != null && !paramDTO.getName().isEmpty()) {
            criteria.andProgramNameLike("%" + paramDTO.getName() + "%");
        }
        if (paramDTO.getProgramType() != null && paramDTO.getProgramType().length != 0) {
            criteria.andProgramTypeIn(Arrays.asList(paramDTO.getProgramType()));
        }
        //添加查询当前登录用户或者TO项目的条件
        if (paramDTO.getCreator() != null && !paramDTO.getCreator().isEmpty() &&
                paramDTO.getTo() != null && !paramDTO.getTo().isEmpty()) {
            criteria.andOrQueryAll(paramDTO.getCreator(), paramDTO.getTo());
        } else if (paramDTO.getCreator() != null && !paramDTO.getCreator().isEmpty()) {
            //添加查询当前登录用户项目的条件
            criteria.andCreatorEqualTo(paramDTO.getCreator());
        } else if (paramDTO.getTo() != null && !paramDTO.getTo().isEmpty()) {
            //添加to查询条件
            String to = "\"TO" + "\":" + "\"" + paramDTO.getTo() + "\"";
            criteria.andPersonsLike("%" + to + "%");
        }
        if (paramDTO.getStatus() == null && paramDTO.getName() == null && paramDTO.getCreator() == null && paramDTO.getTo() == null) {
            criteria.andStatusNotEqualTo(ProgramStatusEn.INIT.getCode());
        }
        String order = "FIND_IN_SET(`status`,'processing,scheduled,init,pause,close,end'), create_tm desc";
        example.setOrderByClause(order);
        List<Program> lists = programMapper.selectByExample(example);
        PageInfo<Program> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(pm2pmv(lists));
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public List<Program> list() {
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        return programMapper.selectByExample(example);
    }

    @Override
    public Pagination<Program> listUnpushedCaseProgram(ProgramParamDTO paramDTO) {
        Pagination<Program> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        List<Program> lists = programMapperExt.selectUnPushedProgram(paramDTO.getName());
        PageInfo<Program> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(lists);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public Pagination<Program> listAllpushedCaseProgram(ProgramParamDTO paramDTO) {
        Pagination<Program> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        List<Program> lists = programMapperExt.selectAllPushedProgram(paramDTO.getName());
        PageInfo<Program> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(lists);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public ProgramVO getProgramById(Integer id) {
        Program program = programMapper.selectByPrimaryKey(id);
        ProgramVO programVo = new ProgramVO();
        BeanUtils.copyProperties(program, programVo);
        programVo.setPersons(JSONObject.parseObject(program.getPersons()));
        programVo.setCoreNodes(JSONObject.parse(program.getCoreNodes()));
        ProgramRequireExample example = new ProgramRequireExample();
        ProgramRequireExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(programVo.getId());
        criteria.andStatusEqualTo(REQUIRES_ENABLE);
        List<ProgramRequire> requires = programRequireMapper.selectByExample(example);
        programVo.setRequires(requires);
        ProgramModule programModule = programModuleService.getProgramModuleById(program.getProgramModule());
        programVo.setIsLoop(programModule.getIsLoop());
        return programVo;
    }

    @Override
    public Boolean insert(ProgramDTO dto) {
        Program program = BassyUtil.tranformToBean(dto, Program.class, "requires");
        program.setCreateTm(DateUtil.getCurrentTime());
        program.setModifyTm(DateUtil.getCurrentTime());
        program.setCreator(dto.getCreator());
        program.setModifier(dto.getCreator());
        program.setStatus(ProgramStatusEn.INIT.getCode());
        Boolean insertProResult = programMapperExt.insert(program) > 0;
        List<ProgramRequireDTO> requires = dto.getRequires();
        for (ProgramRequireDTO pr : requires) {
            pr.setProgramId(program.getId());
            pr.setStatus(REQUIRES_ENABLE);
            ProgramRequire programRequire = new ProgramRequire();
            BeanUtils.copyProperties(pr, programRequire);
            programRequireMapper.insert(programRequire);
        }
        Boolean insertLogResult = false;
        if (insertProResult) {
            ProgramLog programLog = new ProgramLog();
            BeanUtils.copyProperties(program, programLog, "id");
            programLog.setProgramId(program.getId());
            insertLogResult = programLogMapper.insertSelective(programLog) > 0;
        }
        return insertProResult && insertLogResult;
    }

    @Override
    public Boolean update(ProgramDTO dto) {
        Program program = BassyUtil.tranformToBean(dto, Program.class);
        program.setModifyTm(DateUtil.getCurrentTime());
        program.setModifier(dto.getModifier());
        return programMapper.updateByPrimaryKeySelective(program) > 0;
    }

    @Override
    public Integer getProgramIdByName(String programName) {
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        criteria.andProgramNameEqualTo(programName);
        return programMapper.selectByExample(example).get(0).getId();
    }

    @Override
    public List<Program> getProgramIdLikeName(String programName) {
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        criteria.andProgramNameLike("%" + programName + "%");
        return programMapper.selectByExample(example);
    }

    @Override
    public List<ProgramNameDTO> getProgramByKeyword(String keyword) {
        return programMapperExt.selectProgramNameByKeyword(keyword);
    }


    @Override
    public Pagination<ProgramVO> getProgramByStatus(ParamDTO paramDTO, String status) {
        Pagination<ProgramVO> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        List<Program> lists = programMapper.selectByExample(example);
        PageInfo<Program> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setList(pm2pmv(lists));
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public Pagination<Program> getProgramByStatus(ProgramParamDTO paramDTO, String[] status) {
        Pagination<Program> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        criteria.andStatusIn(Arrays.asList(status));
        if (paramDTO.getName() != null && !paramDTO.getName().isEmpty()) {
            criteria.andProgramNameLike("%" + paramDTO.getName() + "%");
        }
        String[] proType = {ProgramTypeEn.PROGRAM.getCode(), ProgramTypeEn.PRESSING.getCode(), ProgramTypeEn.NORMAL.getCode()};
        criteria.andProgramTypeIn(Arrays.asList(proType));
        String order = "create_tm desc";
        example.setOrderByClause(order);
        List<Program> lists = programMapper.selectByExample(example);
        PageInfo<Program> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(lists);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    /**
     * 系统自动调整项目状态：如goodjob，或待排期/暂停 由于计划变更自动变为已排期
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public Boolean updateProStatusBySystem(Integer id, String status) {
        Program program = programMapper.selectByPrimaryKey(id);
        program.setStatus(status);
        program.setModifier("system");
        program.setModifyTm(new Date());
        Boolean updateStatusResult = programMapper.updateByPrimaryKey(program) > 0;
        Boolean insertProLogResult = false;
        if (updateStatusResult) {
            ProgramLogExample example = new ProgramLogExample();
            ProgramLogExample.Criteria criteria = example.createCriteria();
            criteria.andProgramIdEqualTo(id);
            example.setOrderByClause("id DESC limit 1");
            ProgramLog programLog = programLogMapper.selectByExample(example).get(0);
            ProgramLog newProLog = new ProgramLog();
            newProLog.setProgramId(id);
            newProLog.setIsNormal((byte) 1);
            newProLog.setPercent(programLog.getPercent());
            newProLog.setStatus(status);
            newProLog.setModifier("system");
            newProLog.setModifyTm(new Date());
            insertProLogResult = programLogMapper.insertSelective(newProLog) > 0;
        }
        return updateStatusResult && insertProLogResult;
    }

    /**
     * 新增测试计划
     *
     * @param programTaskDTO
     * @param isInit         是否是插入初始化模板，true：是 false/不传：否；如果是模板，该动作不会更新项目计划
     * @return
     */
    @Override
    @Transactional
    public Boolean createTestPlan(ProgramTaskDTO programTaskDTO, boolean... isInit) {
        //定义新增计划result、该条记录初始值插入操作记录result、更新项目状态result
        boolean insertTaskResult = true, insertLogResult = true, updateProStatusResult = true, updateTaskStatus = true;
        Integer programId = programTaskDTO.getProgramId();
        ProgramTask programTask = new ProgramTask();
        BeanUtils.copyProperties(programTaskDTO, programTask);
        //新增的计划初始状态均为init
        programTask.setStatus(TestTaskStatusEn.INIT.getCode());
        programTask.setActualHour((float) 0);
        programTask.setCreateTm(DateUtil.getCurrentTime());
        programTask.setModifyTm(DateUtil.getCurrentTime());
        programTask.setCreator(programTaskDTO.getCreator());
        programTask.setModifier(programTaskDTO.getCreator());
        insertTaskResult = programTaskMapper.insertSelective(programTask) > 0;
        if (!insertTaskResult) {
            return false;
        }
        //插入的不是计划模板时，变更项目状态，并插入操作记录
        if (isInit.length == 0 || !isInit[0]) {
            ProgramTaskLog programTaskLog = new ProgramTaskLog();
            BeanUtils.copyProperties(programTask, programTaskLog, "id");
            programTaskLog.setTaskId(programTask.getId());
            programTaskLog.setTaskStatus(programTask.getStatus());
            programTaskLog.setActualHour((float) 0);
            programTaskLog.setTodayHour((float) 0);
            programTaskLog.setPercent(0);
            programTaskLog.setModifier(programTask.getCreator());
            programTaskLog.setModifyTm(programTask.getCreateTm());
            programTaskLog.setValid("enable");
            insertLogResult = programTaskLogMapper.insertSelective(programTaskLog) > 0;
            logger.info("插入任务{}操作记录，结果{}", programTask.getId(), insertLogResult);

            //programId == 0表示自建任务，自建任务武无关联项目，无需更新项目状态
            if (programId != 0) {
                String status = programMapper.selectByPrimaryKey(programId).getStatus();
                //项目状态为待排期时，如果新增计划，项目状态更新为已排期
                if (status.equals(ProgramStatusEn.INIT.getCode())) {
                    updateProStatusResult = this.updateProStatusBySystem(programId, ProgramStatusEn.SCHEDULED.getCode());
                    logger.info("更新项目{}状态：由{}更新为已排期-{}", programId, status, updateProStatusResult);
                }
                //项目状态为已暂停时，如果新增计划，项目状态更新为已排期,所有暂停的任务变为init
                if (status.equals(ProgramStatusEn.PAUSE.getCode())) {
                    updateProStatusResult = this.updateProStatusBySystem(programId, ProgramStatusEn.SCHEDULED.getCode());
                    logger.info("更新项目{}状态：由{}更新为已排期-{}", programId, status, updateProStatusResult);
                    ProgramTaskExample example = new ProgramTaskExample();
                    ProgramTaskExample.Criteria criteria = example.createCriteria();
                    criteria.andProgramIdEqualTo(programId);
                    criteria.andStatusEqualTo(TestTaskStatusEn.PAUSE.getCode());
                    List<ProgramTask> tasks = programTaskMapper.selectByExample(example);

                    for (ProgramTask task : tasks) {
                        UpdateTaskStatusDTO dto = new UpdateTaskStatusDTO();
                        dto.setStatus(TestTaskStatusEn.INIT.getCode());
                        dto.setModifier(programTaskDTO.getCreator());
                        dto.setTaskId(task.getId());
                        boolean updateResult = taskService.updateTaskStatus(dto);
                        logger.info("更新任务{}状态：由{}更新为未开始-{}", task.getId(), status, updateResult);
                        updateTaskStatus = updateTaskStatus && updateResult;
                    }
                }
            }
        }
        logger.info("新建1条项目{}测试计划：任务{}", programId, programTaskDTO.getTaskCode());
        return insertTaskResult && insertLogResult && updateProStatusResult && updateTaskStatus;
    }

    @Override
    @Transactional
    public Boolean updateTestPlan(ProgramTaskDTO programTaskDTO) {
        //定义更新计划result、插入操作记录result，以及更新项目状态result
        Boolean updateTaskResult, updateProStatusResult = true, updateTaskStatus = true;
        updateTaskResult = taskService.editTask(programTaskDTO);
        Integer programId = programTaskDTO.getProgramId();
        //非自定义任务(programId!=0)更新项目状态
        if (updateTaskResult && programId != 0) {
            String status = programMapper.selectByPrimaryKey(programId).getStatus();
            //若当前项目为待排期状态时，有计划被更新，则将项目置为已排期状态
            if (status.equals(ProgramStatusEn.INIT.getCode())) {
                updateProStatusResult = this.updateProStatusBySystem(programId, ProgramStatusEn.SCHEDULED.getCode());
                logger.info("更新项目{}状态：由{}更新为已排期-{}", programId, status, updateProStatusResult);
            }
            //项目状态为已暂停时，如果新增计划，项目状态更新为已排期,所有暂停的任务变为init
            if (status.equals(ProgramStatusEn.PAUSE.getCode())) {
                updateProStatusResult = this.updateProStatusBySystem(programId, ProgramStatusEn.SCHEDULED.getCode());
                logger.info("更新项目{}状态：由{}更新为已排期-{}", programId, status, updateProStatusResult);
                ProgramTaskExample example = new ProgramTaskExample();
                ProgramTaskExample.Criteria criteria = example.createCriteria();
                criteria.andProgramIdEqualTo(programId);
                criteria.andStatusEqualTo(TestTaskStatusEn.PAUSE.getCode());
                List<ProgramTask> tasks = programTaskMapper.selectByExample(example);

                for (ProgramTask task : tasks) {
                    UpdateTaskStatusDTO dto = new UpdateTaskStatusDTO();
                    dto.setStatus(TestTaskStatusEn.INIT.getCode());
                    dto.setModifier(programTaskDTO.getCreator());
                    dto.setTaskId(task.getId());
                    boolean updateResult = taskService.updateTaskStatus(dto);
                    logger.info("更新任务{}状态：由{}更新为未开始-{}", task.getId(), status, updateResult);
                    updateTaskStatus = updateTaskStatus && updateResult;
                }
            }
        }
        return updateTaskResult && updateProStatusResult;
    }

    @Override
    public TestPlanCountDTO batchUpdateTestPlan(List<ProgramTaskDTO> programTaskDTOList) {
        //循环调用单条记录的更新语句
        int successCount = 0, failCount = 0;
        Boolean updateProStatusResult = true, updateTaskStatus = true;
        for (ProgramTaskDTO programTaskDTO : programTaskDTOList) {
            Boolean result = taskService.editTask(programTaskDTO);
            if (result == true) {
                //成功则更新成功的条数+1
                successCount += 1;
            } else {
                //失败则更新失败的条数+1
                failCount += 1;
            }
        }
        if (successCount > 0) {
            Integer programId = programTaskDTOList.get(0).getProgramId();
            String status = programMapper.selectByPrimaryKey(programId).getStatus();
            //若当前项目为待排期状态时，有计划被更新，则将项目置为已排期状态
            if (status.equals(ProgramStatusEn.INIT.getCode())) {
                updateProStatusResult = this.updateProStatusBySystem(programId, ProgramStatusEn.SCHEDULED.getCode());
                logger.info("更新项目{}状态：由{}更新为已排期-{}", programId, status, updateProStatusResult);
            }
            //项目状态为已暂停时，如果新增计划，项目状态更新为已排期,所有暂停的任务变为init
            if (status.equals(ProgramStatusEn.PAUSE.getCode())) {
                updateProStatusResult = this.updateProStatusBySystem(programId, ProgramStatusEn.SCHEDULED.getCode());
                logger.info("更新项目{}状态：由{}更新为已排期-{}", programId, status, updateProStatusResult);
                ProgramTaskExample example = new ProgramTaskExample();
                ProgramTaskExample.Criteria criteria = example.createCriteria();
                criteria.andProgramIdEqualTo(programId);
                criteria.andStatusEqualTo(TestTaskStatusEn.PAUSE.getCode());
                List<ProgramTask> tasks = programTaskMapper.selectByExample(example);

                for (ProgramTask task : tasks) {
                    UpdateTaskStatusDTO dto = new UpdateTaskStatusDTO();
                    dto.setStatus(TestTaskStatusEn.INIT.getCode());
                    dto.setModifier(programTaskDTOList.get(0).getModifier());
                    dto.setTaskId(task.getId());
                    boolean updateResult = taskService.updateTaskStatus(dto);
                    logger.info("更新任务{}状态：由{}更新为未开始-{}", task.getId(), status, updateResult);
                    updateTaskStatus = updateTaskStatus && updateResult;
                }
            }
        }
        TestPlanCountDTO testPlanCountDTO = new TestPlanCountDTO();
        testPlanCountDTO.setTotalCount(programTaskDTOList.size());
        testPlanCountDTO.setSuccessCount(successCount);
        testPlanCountDTO.setFailCount(failCount);
        testPlanCountDTO.setProjectStatusUpdate(updateProStatusResult);
        return testPlanCountDTO;
    }

    @Override
    @Transactional
    public Boolean deleteTestPlan(int id) {
        ProgramTask programTask = programTaskMapper.selectByPrimaryKey(id);
        //非init状态不允许删除
        if (!TestTaskStatusEn.INIT.getCode().equalsIgnoreCase(programTask.getStatus())) {
            logger.info("任务{}不是初始化状态，不允许删除！", id);
            return false;
        }
        //目前是物理删除
        int num1 = programTaskMapper.deleteByPrimaryKey(id);
        if (num1 > 0) {
            logger.info("删除任务{}成功！", id);
            return true;
        } else {
            logger.info("删除任务{}失败或待删除任务已不存在！", id);
            return false;
        }
    }

    @Override
    public TestPlanDTO getTestPlan(Integer programId) {
        ProgramTaskExample example = new ProgramTaskExample();
        ProgramTaskExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(programId);
        criteria.andStatusNotEqualTo(TestTaskStatusEn.CLOSE.getCode());
        example.setOrderByClause("start_tm");
        List<ProgramTask> programTasks = programTaskMapper.selectByExample(example);
        TestPlanDTO testPlanDTO = new TestPlanDTO();
        List<ProgramTaskDTO> programTaskDTOs = new ArrayList<>();
        for (ProgramTask pt : programTasks) {
            ProgramTaskDTO dto = new ProgramTaskDTO();
            BeanUtils.copyProperties(pt, dto);
            if (pt.getTester() != null) {
                String testName = staffService.getStaffInfo(pt.getTester()).getName();
                dto.setTesterName(testName);
            }
            if (pt.getWithTester() != null) {
                String withTesterName = staffService.getStaffInfo(pt.getWithTester()).getName();
                dto.setWithTesterName(withTesterName);
            }
            programTaskDTOs.add(dto);
        }
        testPlanDTO.setProgramId(programId);
        testPlanDTO.setProgramTasks(programTaskDTOs);
        return testPlanDTO;
    }

    @Override
    public Boolean programIsExit(ProgramDTO dto) {
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        if (dto.getId() != null) {
            criteria.andIdNotEqualTo(dto.getId());
        }
        criteria.andProgramNameEqualTo(dto.getProgramName());
        List<Program> lists = programMapper.selectByExample(example);
        return lists.size() > 0;
    }

    private List<ProgramVO> pm2pmv(List<Program> lists) {
        List<ProgramVO> programVOS = new ArrayList<>();
        for (Program program : lists) {
            ProgramVO programVo = new ProgramVO();
            BeanUtils.copyProperties(program, programVo);
            programVo.setPersons(JSONObject.parseObject(program.getPersons()));
            programVo.setCoreNodes(JSONObject.parse(program.getCoreNodes()));
            ProgramRequireExample example = new ProgramRequireExample();
            ProgramRequireExample.Criteria criteria = example.createCriteria();
            criteria.andProgramIdEqualTo(programVo.getId());
            List<ProgramRequire> requires = programRequireMapper.selectByExample(example);
            programVo.setRequires(requires);
            programVOS.add(programVo);
        }
        return programVOS;
    }

    /**
     * 变更项目状态
     *
     * @param dto
     * @return
     */
    @Override
    public Boolean updateProgramStatus(UpdateProgramStatusDTO dto) {
        //更新项目状态
        Program program = programMapper.selectByPrimaryKey(dto.getProgramId());
        program.setStatus(dto.getStatus());
        program.setModifyTm(new Date());
        Boolean updateStatusResult = programMapper.updateByPrimaryKey(program) > 0;
        logger.info("更新项目{}状态为{}-{}", dto.getProgramId(), dto.getStatus(), updateStatusResult);
        //添加操作log
        if (updateStatusResult) {
            ProgramLog programLog = new ProgramLog();
            BeanUtils.copyProperties(dto, programLog);
            programLog.setStatus(dto.getStatus());
            programLog.setModifyTm(new Date());
            programLog.setModifier(dto.getPerson());
            Boolean insertProgramLog = programLogMapper.insert(programLog) > 0;
            logger.info("添加项目{}log-{}", dto.getProgramId(), insertProgramLog);
        }
        //更新项目下的任务的状态
        ProgramTask programTask = new ProgramTask();
        programTask.setStatus(dto.getStatus());
        ProgramTaskExample programTaskExample = new ProgramTaskExample();
        ProgramTaskExample.Criteria criteria = programTaskExample.createCriteria();
        criteria.andProgramIdEqualTo(dto.getProgramId());
        criteria.andStatusNotIn(Arrays.asList(TestTaskStatusEn.END.getCode(), TestTaskStatusEn.CLOSE.getCode()));
        int taskNum = programTaskMapper.updateByExampleSelective(programTask, programTaskExample);
        logger.info("更新项目{}任务状态{}条", dto.getProgramId(), taskNum);
        return updateStatusResult;
    }

    @Override
    public List<ProgramDocument> getProgramDocument(Integer programId) {
        ProgramDocumentExample example = new ProgramDocumentExample();
        ProgramDocumentExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(programId);
        criteria.andStatusEqualTo(DOCUMENT_ENABLE);
        return programDocumentMapper.selectByExample(example);
    }

    @Override
    public void deleteProgramDocument(Integer documentId) {
        ProgramDocumentExample example = new ProgramDocumentExample();
        ProgramDocumentExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(documentId);
        ProgramDocument programDocument = new ProgramDocument();
        programDocument.setStatus(DOCUMENT_UNABLE);
        programDocumentMapper.updateByExampleSelective(programDocument, example);
    }

    @Override
    public Boolean updateProgramReport(ProgramReportDTO dto) {
        ProgramLog programLog = new ProgramLog();
        BeanUtils.copyProperties(dto, programLog);
        programLog.setTaskInfo(dto.getTaskInfo().toString());
        programLog.setModifyTm(new Date());
        Boolean result = programLogMapper.insert(programLog) > 0;
        logger.info("更新项目{}日报：{} - {}", dto.getProgramId(), dto.toString(), result);
        return result;
    }

    @Override
    public List<ProgramLog> getHistoryProgramReport(ProgramReportDTO dto) {
        ProgramLogExample example = new ProgramLogExample();
        ProgramLogExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(dto.getProgramId());
        if (dto.getModifyTm() != null) {
            criteria.andModifyTmEqualTo(dto.getModifyTm());
        }
        criteria.andTaskInfoIsNotNull();
        return programLogMapper.selectByExample(example);
    }

    @Override
    public Boolean startPrograms(String oldStatus, String newStatus) {
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(oldStatus);
        criteria.andStartTimeLessThanOrEqualTo(new Date());
        //先查出待开始的所有项目
        List<Program> programList = programMapper.selectByExample(example);
        Program program = new Program();
        program.setStatus(newStatus);
        program.setModifier("system");
        program.setModifyTm(new Date());
        logger.info("批量更新项目状态：由{}更新为{}", oldStatus, newStatus);
        //将所有待开始的项目置为已开始
        Boolean updateResult = programMapper.updateByExampleSelective(program, example) > 0;
        Boolean insertLogResult = false;
        //批量插入操作记录
        if (updateResult) {
            List<ProgramLog> programLogList = new ArrayList<>();
            for (Program p : programList) {
                ProgramLog programLog = new ProgramLog();
                BeanUtils.copyProperties(p, programLog, "id");
                programLog.setProgramId(p.getId());
                programLog.setStatus(newStatus);
                programLog.setModifier("system");
                programLog.setModifyTm(new Date());
                programLogList.add(programLog);
            }
            insertLogResult = programLogMapper.insertBatch(programLogList) > 0;
        }
        return updateResult && insertLogResult;
    }

    @Override
    public void addRequire(ProgramDTO programDTO) {
        List<ProgramRequireDTO> requires = programDTO.getRequires();
        for (ProgramRequireDTO pr : requires) {
            pr.setProgramId(programDTO.getId());
            pr.setStatus(REQUIRES_ENABLE);
            pr.setCreateTm(new Date());
            ProgramRequire programRequire = new ProgramRequire();
            BeanUtils.copyProperties(pr, programRequire);
            programRequireMapper.insert(programRequire);
        }
    }

    @Override
    public void deleteRequire(DeleteRequireDTO deleteRequireDTO) {
        //修改需求状态
        ProgramRequireExample example = new ProgramRequireExample();
        ProgramRequireExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(deleteRequireDTO.getProgramId());
        criteria.andRequireIdEqualTo(deleteRequireDTO.getRequireId());
        ProgramRequire pr = new ProgramRequire();
        pr.setStatus(REQUIRES_UNABLE);
        programRequireMapper.updateByExampleSelective(pr, example);

        //修改任务状态
        ProgramTaskExample example1 = new ProgramTaskExample();
        ProgramTaskExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andProgramIdEqualTo(deleteRequireDTO.getProgramId());
        criteria1.andRequireIdEqualTo(deleteRequireDTO.getRequireId());
        ProgramTask programTask = new ProgramTask();
        programTask.setStatus(TestTaskStatusEn.CLOSE.getCode());
        programTaskMapper.updateByExampleSelective(programTask, example1);

        //更新任务日志
        List<ProgramTask> programTasks = programTaskMapper.selectByExample(example1);
        for (ProgramTask pt : programTasks) {
            UpdateTaskStatusDTO dto = new UpdateTaskStatusDTO();
            dto.setTaskId(pt.getId());
            dto.setStatus(TestTaskStatusEn.CLOSE.getCode());
            dto.setModifier(deleteRequireDTO.getModifier());
            taskService.updateTaskStatus(dto);
        }

        //更新项目需求关联文档
        ProgramDocumentExample example2 = new ProgramDocumentExample();
        ProgramDocumentExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andProgramIdEqualTo(deleteRequireDTO.getProgramId());
        criteria2.andRequireIdEqualTo(deleteRequireDTO.getRequireId());
        ProgramDocument programDocument = new ProgramDocument();
        programDocument.setStatus(DOCUMENT_UNABLE);
        programDocumentMapper.updateByExampleSelective(programDocument, example2);
    }

    @Override
    public void transferRequire(TransferRequireDTO transferRequireDTO) {
        Program program = programMapper.selectByPrimaryKey(transferRequireDTO.getTargetProgramId());
        if (ProgramTypeEn.INTERNAL.getCode().equals(program.getProgramType())) {
            throw new BusinessException("不允许迁移需求到内部项目！");
        }
        //修改需求对应项目id
        ProgramRequireExample example = new ProgramRequireExample();
        ProgramRequireExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(transferRequireDTO.getSourcesProgramId());
        criteria.andRequireIdEqualTo(transferRequireDTO.getRequireId());
        ProgramRequire pr = new ProgramRequire();
        pr.setProgramId(transferRequireDTO.getTargetProgramId());
        programRequireMapper.updateByExampleSelective(pr, example);

        //修改任务对应项目id
        ProgramTaskExample example1 = new ProgramTaskExample();
        ProgramTaskExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andProgramIdEqualTo(transferRequireDTO.getSourcesProgramId());
        criteria1.andRequireIdEqualTo(transferRequireDTO.getRequireId());
        ProgramTask programTask = new ProgramTask();
        programTask.setProgramId(transferRequireDTO.getTargetProgramId());
        programTaskMapper.updateByExampleSelective(programTask, example1);

        //更新任务日志
        List<ProgramTask> programTasks = programTaskMapper.selectByExample(example1);
        for (ProgramTask pt : programTasks) {
            ProgramTaskLogExample programTaskLogExample = new ProgramTaskLogExample();
            ProgramTaskLogExample.Criteria criteria2 = programTaskLogExample.createCriteria();
            criteria2.andTaskIdEqualTo(pt.getId());
            ProgramTaskLog programTaskLog = new ProgramTaskLog();
            programTaskLog.setProgramId(transferRequireDTO.getTargetProgramId());
            programTaskLogMapper.updateByExampleSelective(programTaskLog, programTaskLogExample);
        }

        //更新项目需求关联文档
        ProgramDocumentExample example2 = new ProgramDocumentExample();
        ProgramDocumentExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andProgramIdEqualTo(transferRequireDTO.getRequireId());
        criteria2.andRequireIdEqualTo(transferRequireDTO.getRequireId());
        ProgramDocument programDocument = new ProgramDocument();
        programDocument.setProgramId(transferRequireDTO.getTargetProgramId());
        programDocumentMapper.updateByExampleSelective(programDocument, example2);
    }

    @Override
    public List<ProgramRequire> getProgramRequire(Integer programId) {
        ProgramRequireExample example = new ProgramRequireExample();
        ProgramRequireExample.Criteria criteria = example.createCriteria();
        criteria.andProgramIdEqualTo(programId);
        criteria.andStatusEqualTo(REQUIRES_ENABLE);
        return programRequireMapper.selectByExample(example);
    }

    @Override
    public List<RequireBoardVO> getPlanRequireBoard(Integer programId) {
        List<RequireBoardVO> boardVos = new ArrayList<>();
        List<Map<String, Object>> requires = programTaskMapperExt.getPlanRequires(programId); // TODO: 2019/12/25 优化返回对象
        for (Map r : requires) {
            if (r.get("require_id") == null) {
                continue;
            }
            Integer requireId = Integer.parseInt(r.get("require_id").toString());
            RequireBoardVO vo = new RequireBoardVO();
            vo.setRequireId(requireId);
            vo.setRequireRelate(r.get("require_relate").toString());
            List<Map<String, Object>> requireBoard = programTaskMapperExt.getPlanRequireBoard(programId, requireId);// TODO: 2019/12/25 优化返回对象
            List<Map<String, Object>> nodes = new ArrayList<>();
            Integer current = 0;
            for (int i = 0; i < requireBoard.size(); i++) {
                Map m = requireBoard.get(i);
                Map node = new HashMap();
                node.put("processName", m.get("dict_value").toString());
                node.put("details", m.get("details").toString().split(";"));
                if (Integer.parseInt(m.get("rank").toString()) == 1) {
                    if ("end,processing".contains(m.get("status").toString()) && i > current) {
                        current = i;
                    }
                    node.put("status", m.get("status").toString());
                } else {
                    if (BassyUtil.matchCount(m.get("details").toString(), "\\b(init)\\b") == Integer.parseInt(m.get("rank").toString())) {
                        node.put("status", "init");
                    } else if (BassyUtil.matchCount(m.get("details").toString(), "\\b(end)\\b") == Integer.parseInt(m.get("rank").toString())) {
                        current = i > current ? i : current;
                        node.put("status", "end");
                    } else {
                        current = i > current ? i : current;
                        node.put("status", "processing");
                    }
                }
                nodes.add(node);
            }
            vo.setCurrent(current);
            vo.setNodes(nodes);
            boardVos.add(vo);
        }
        return boardVos;
    }

    @Override
    public ProgramRequire getRequireInfo(Integer requireId) {
        ProgramRequireExample example = new ProgramRequireExample();
        ProgramRequireExample.Criteria criteria = example.createCriteria();
        criteria.andRequireIdEqualTo(requireId);
        return programRequireMapper.selectByExample(example).get(0);
    }
}
