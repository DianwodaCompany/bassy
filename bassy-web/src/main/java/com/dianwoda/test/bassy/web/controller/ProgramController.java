package com.dianwoda.test.bassy.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONPath;
import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.api.UpperProjectService;
import com.dianwoda.test.bassy.common.domain.StaffInfoDTO;
import com.dianwoda.test.bassy.common.domain.UpperProjectDTO;
import com.dianwoda.test.bassy.common.domain.dto.*;
import com.dianwoda.test.bassy.common.domain.dto.program.*;
import com.dianwoda.test.bassy.common.domain.vo.ProgramDocumentVO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramRequireProcessVO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramVO;
import com.dianwoda.test.bassy.common.domain.vo.RequireBoardVO;
import com.dianwoda.test.bassy.common.enums.DictionaryEn;
import com.dianwoda.test.bassy.common.enums.ProgramStatusEn;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.dao.ProgramTaskMapper;
import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.service.*;
import com.dianwoda.test.bassy.service.util.DateUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zcp on 2018/5/7.
 * Time always， fat thin man all miss.
 */
@RestController
@RequestMapping("/program")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private ProgramModuleService programModuleService;

    @Autowired
    private ProcessModuleService processModuleService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private UpperProjectService upperProjectService;

    @Autowired
    private DailyReportService dailyReportService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ProgramTaskMapper programTaskMapper;


    /**
     * get ProgramDocumentDTO list
     *
     * @return
     */
    @ApiOperation(value = "分页获取所有可用项目", notes = "")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Pagination<ProgramVO> list(@RequestBody ProgramParamDTO paramDTO) {
        return programService.list(paramDTO);
    }

    @ApiOperation(value = "获取所有可用项目", notes = "")
    @RequestMapping(value = "/list/all", method = RequestMethod.POST)
    public List<Program> allProgram() {
        return programService.list();
    }

    @ApiOperation(value = "根据项目状态获取项目列表")
    @ApiImplicitParam(name = "status", value = "项目status", required = true)
    @RequestMapping(value = "/list/{status}", method = RequestMethod.POST)
    public Pagination<ProgramVO> getProgramListByStatus(@RequestBody ParamDTO paramDTO, @PathVariable String status) {
        return programService.getProgramByStatus(paramDTO, status);
    }

    @ApiOperation(value = "根据关键字(项目名称,id)获取项目列表")
    @ApiImplicitParam(name = "keyword", value = "项目名称,id", required = true)
    @RequestMapping(value = "/nameList/{keyword}", method = RequestMethod.POST)
    public List<ProgramNameDTO> getProgramByKeyword(@PathVariable String keyword) {
        return programService.getProgramByKeyword(keyword);
    }

    /**
     * get ProgramDocumentDTO info by id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id获取项目", notes = "")
    @ApiImplicitParam(name = "id", value = "项目id", required = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProgramVO getProgramById(@PathVariable Integer id) {
        return programService.getProgramById(id);
    }

    @ApiOperation(value = "新增项目", notes = "")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Boolean addProgram(@RequestBody ProgramDTO dto) throws Exception {
        boolean exit = programService.programIsExit(dto);
        if (exit) {
            throw new Exception("项目名称已经存在！");
        }
        Boolean result = programService.insert(dto);
        if (result) {
            //查出新增的项目id，用于生成初始的项目计划
            Integer programId = programService.getProgramIdByName(dto.getProgramName());
            if (programId == null) {
                throw new BusinessException("【ProgramController-addProgram-error】项目初始化项目计划失败！");
            }
            dto.setId(programId);
            insertInitTestPlan(dto);
        } else {
            throw new BusinessException("【ProgramController-addProgram-error】insert failed");
        }
        return result;
    }

    @ApiOperation(value = "项目添加需求", notes = "")
    @RequestMapping(value = "/require/add", method = RequestMethod.POST)
    public void addRequire(@RequestBody ProgramDTO dto) throws ParseException {
        programService.addRequire(dto);
        insertInitTestPlan(dto);
    }

    @ApiOperation(value = "项目删除需求", notes = "")
    @RequestMapping(value = "/require/delete", method = RequestMethod.POST)
    public void addRequire(@RequestBody DeleteRequireDTO deleteRequireDTO) {
        programService.deleteRequire(deleteRequireDTO);
    }

    @ApiOperation(value = "项目迁移需求", notes = "")
    @RequestMapping(value = "/require/transfer", method = RequestMethod.POST)
    public void transferRequire(@RequestBody TransferRequireDTO transferRequireDTO) {
        programService.transferRequire(transferRequireDTO);
    }

    @ApiOperation(value = "项目总计需求", notes = "")
    @RequestMapping(value = "/require/{programId}", method = RequestMethod.POST)
    public List<Integer> programRequire(@PathVariable Integer programId) {
        List<ProgramRequire> programRequires = programService.getProgramRequire(programId);
        List<Integer> requires = new ArrayList<>();
        for (ProgramRequire pr : programRequires) {
            requires.add(pr.getRequireId());
        }
        return requires;
    }

    @ApiOperation(value = "编辑项目", notes = "")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Boolean editProgram(@RequestBody ProgramDTO dto) throws Exception {
        boolean exit = programService.programIsExit(dto);
        if (exit) {
            throw new Exception("项目名称已经存在！");
        }
        return programService.update(dto);
    }

    @ApiOperation(value = "由系统变更项目状态", notes = "")
    @ApiImplicitParam(name = "id", value = "项目id", required = true)
    @RequestMapping(value = "/update/{id}/{status}", method = RequestMethod.POST)
    public Boolean updateProgramStatusBySystem(@PathVariable Integer id, @PathVariable String status) {
        if (!ProgramStatusEn.contains(status)) {
            throw new BusinessException("【ProgramController-updateProgramStatusById-error】status not equals");
        }
        return programService.updateProStatusBySystem(id, status);
    }

    @ApiOperation(value = "创建测试计划", notes = "")
    @RequestMapping(value = "/testplan/create", method = RequestMethod.POST)
    public Boolean createProgramPlan(@RequestBody ProgramTaskDTO programTaskDTO) {
        return programService.createTestPlan(programTaskDTO);
    }

    @ApiOperation(value = "更新测试计划", notes = "")
    @RequestMapping(value = "/testplan/update", method = RequestMethod.POST)
    public Boolean updateProgramPlan(@RequestBody ProgramTaskDTO programTaskDTO) {
        if (programTaskDTO.getId() == null) {
            throw new BusinessException("【ProgramController-updateProgramPlan-error】params can  not be null");
        }
        return programService.updateTestPlan(programTaskDTO);
    }

    @ApiOperation(value = "批量更新测试计划", notes = "")
    @RequestMapping(value = "/testplan/update/batch", method = RequestMethod.POST)
    public TestPlanCountDTO batchUpdateProgramPlan(@RequestBody List<ProgramTaskDTO> programTaskDTOList) {
        if (programTaskDTOList == null || programTaskDTOList.size() <= 0) {
            throw new BusinessException("【ProgramController-batchUpdateProgramPlan-error】params can  not be null");
        }
        return programService.batchUpdateTestPlan(programTaskDTOList);
    }

    @ApiOperation(value = "删除测试计划", notes = "")
    @ApiImplicitParam(name = "id", value = "任务id", required = true)
    @RequestMapping(value = "/testplan/delete/{id}", method = RequestMethod.POST)
    public Boolean deleteProgramPlan(@PathVariable("id") int id) {
        return programService.deleteTestPlan(id);
    }

    @ApiOperation(value = "获取测试计划", notes = "")
    @ApiImplicitParam(name = "programId", value = "项目Id", required = true)
    @RequestMapping(value = "/testplan/{programId}", method = RequestMethod.GET)
    public TestPlanDTO getProgramPlan(@PathVariable Integer programId) {
        if (programId == null) {
            throw new BusinessException("【ProgramController-getProgramPlan-error】programId not be null");
        }
        return programService.getTestPlan(programId);
    }

    @SuppressWarnings("unchecked")
    private void insertInitTestPlan(ProgramDTO dto) throws ParseException {

        Object processTemplateNodes = null;
        Calendar cal = Calendar.getInstance();
        List<Map> coreNodes = JSONArray.parseArray(dto.getCoreNodes(), Map.class);
        List<ProgramRequireDTO> requires = dto.getRequires();
        ProgramTaskDTO programTaskDTO = new ProgramTaskDTO();
        programTaskDTO.setProgramId(dto.getId());
        programTaskDTO.setCreator(dto.getCreator());
        programTaskDTO.setModifier(dto.getModifier());
        ProgramModule programModule = programModuleService.getProgramModuleById(dto.getProgramModule());

        if (!dto.getProgramType().equals("inner") || programModule.getIsLoop().equals("N")) {
            if (!dto.getProgramType().equals("inner")) {
                //找到项目使用的流程模板及模板节点
                int procModId = dto.getProcessModule();
                ProcessModule processModule = processModuleService.getModuleById(procModId);
                processTemplateNodes = JSONArray.parse(processModule.getProcessNode());
            }
            //遍历所有流程节点
            for (Map c : coreNodes) {
                String process = c.get("projectNode").toString();
                programTaskDTO.setProgramProcess(process);
                String utcFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
                programTaskDTO.setStartTm(DateUtil.utcToCst(c.get("startTime").toString(), utcFormat));
                programTaskDTO.setEndTm(DateUtil.utcToCst(c.get("endTime").toString(), utcFormat));
                //内部项目 非循环类任务
                if (processTemplateNodes == null) {
                    Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.INNER_PROJECT_TYPE.getEname(), dto.getInnerProjectType());
                    programTaskDTO.setTaskName(process.equals("") ? dictionary.getDictValue() : process);
                    programTaskDTO.setRequireId(null);
                    programTaskDTO.setRequireRelate("");
                    programTaskDTO.setZtProjectId(null);
                    programTaskDTO.setTaskCode(dto.getInnerProjectType());
                    if (dto.getInnerProjectType().equals("RANDOM_TEST")) {
                        List<StaffInfoDTO> staffList = staffService.getStaffInfoList("");
                        for (StaffInfoDTO s : staffList) {
                            programTaskDTO.setTester(s.getCode());
                            programTaskDTO.setExpectHour(2f);
                            programService.createTestPlan(programTaskDTO, false);
                        }
                    } else {
                        PersonsDTO personsDTO = JSON.parseObject(dto.getPersons(), PersonsDTO.class);
                        programTaskDTO.setTester(personsDTO.getTO());
                        programService.createTestPlan(programTaskDTO, false);
                    }
                }
                List<Map> tasks = (List<Map>) JSONPath.eval(processTemplateNodes, "[processNodeCode=\"" + c.get("projectNode").toString() + "\"][0].task[taskNeed=true]");

                //模板中不存在的流程节点或节点中没有关联任务，都不会自动生成计划
                if (tasks == null || tasks.size() == 0) {
                    continue;
                }
                //遍历每个流程节点下的所有任务
                for (Map t : tasks) {
                    programTaskDTO.setTaskCode(t.get("taskCode").toString());
                    //如果关联需求，遍历需求，每个需求都要创建当前任务
                    if (c.get("demandNeed").equals(true) && requires.size() > 0 && !t.get("taskCode").toString().equals("QUALITY_REPORT")) {
                        for (ProgramRequireDTO r : requires) {
                            programTaskDTO.setRequireId(r.getRequireId());
                            programTaskDTO.setRequireRelate(r.getRequireName() == null ? "" : r.getRequireName());
                            programTaskDTO.setZtProjectId(r.getZtProjectId());
                            programService.createTestPlan(programTaskDTO, true);
                        }
                    } else {
                        //如果不关联需求，赋值为空，避免改变量在前面的步骤中存在值
                        programTaskDTO.setRequireId(null);
                        programTaskDTO.setRequireRelate("");
                        programTaskDTO.setZtProjectId(null);
                        programService.createTestPlan(programTaskDTO, true);
                    }
                }
            }
        } else {
            if (programModule.getIsLoop().equals("Y")) {
                Date start = dto.getStartTime();
                Date end = dto.getEndTime();
                while (DateUtil.sub(end, start, Calendar.SECOND) > 0) {
                    cal.setTime(start);
                    if (isNotWeekend(cal)) {
                        String date = new SimpleDateFormat("yyyy-MM-dd").format(start);
                        Dictionary dictionary = dictionaryService.getDictionaryByGroupAndCode(DictionaryEn.INNER_PROJECT_TYPE.getEname(), dto.getInnerProjectType());
                        if (dto.getDailyTaskNum() > 1) {
                            for (int idx = 0; idx < dto.getDailyTaskNum(); idx++) {
                                programTaskDTO.setTaskName(String.valueOf(idx) + "_" + dictionary.getDictValue());
                                programTaskDTO.setProgramProcess("");
                                programTaskDTO.setStartTm(start);
                                programTaskDTO.setEndTm(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date + " 23:59:59"));
                                programTaskDTO.setRequireId(null);
                                programTaskDTO.setRequireRelate("");
                                programTaskDTO.setZtProjectId(null);
                                programTaskDTO.setTaskCode(dto.getInnerProjectType());
                                PersonsDTO personsDTO = JSON.parseObject(dto.getPersons(), PersonsDTO.class);
                                programTaskDTO.setTester(personsDTO.getTO());
                                programTaskDTO.setExpectHour(dto.getEachTaskWorkHour());
                                programService.createTestPlan(programTaskDTO, false);
                            }
                        } else {
                            programTaskDTO.setTaskName(dictionary.getDictValue());
                            programTaskDTO.setProgramProcess("");
                            programTaskDTO.setStartTm(start);
                            programTaskDTO.setEndTm(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date + " 23:59:59"));
                            programTaskDTO.setRequireId(null);
                            programTaskDTO.setRequireRelate("");
                            programTaskDTO.setZtProjectId(null);
                            programTaskDTO.setTaskCode(dto.getInnerProjectType());
                            PersonsDTO personsDTO = JSON.parseObject(dto.getPersons(), PersonsDTO.class);
                            programTaskDTO.setTester(personsDTO.getTO());
                            programTaskDTO.setExpectHour(dto.getEachTaskWorkHour());
                            programService.createTestPlan(programTaskDTO, false);
                        }
                    }
                    start = DateUtil.add(start, Calendar.DATE, 1);
                }
            }
        }

    }

    private boolean isNotWeekend(Calendar cal) {
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        //0代表周日，6代表周六
        return week != 6 && week != 0;
    }

    @ApiOperation(value = "更新项目状态", notes = "")
    @RequestMapping(value = "/status/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateProgramStatus(@RequestBody UpdateProgramStatusDTO dto) {
        boolean end = ProgramStatusEn.END.getCode().equals(dto.getStatus());
        boolean haveTask = taskService.getNotEndTasks((int) dto.getProgramId()).size() > 0;
        //有未结束任务的项目不允许结束
        if (end && haveTask) {
            return "该项目有未结束任务，不允许结束";
        }
        return programService.updateProgramStatus(dto) ? "项目状态更新成功！" : "项目状态更新失败！";
    }

    @ApiOperation(value = "获取项目附件列表", notes = "")
    @ApiImplicitParam(name = "", value = "参数", required = true)
    @RequestMapping(value = "/document/list/{programId}", method = RequestMethod.GET)
    public List<ProgramDocumentVO> getTaskDocumentList(@PathVariable("programId") Integer programId) throws IOException {
        List<ProgramDocument> programDocuments = programService.getProgramDocument(programId);
        List<ProgramDocumentVO> programDocumentVOS = new ArrayList<>();
        for (ProgramDocument programDocument : programDocuments) {
            ProgramDocumentVO programDocumentVO = new ProgramDocumentVO();
            BeanUtils.copyProperties(programDocument, programDocumentVO);
            if (programDocument.getTaskId() != 0) {
                ProgramTask programTask = programTaskMapper.selectByPrimaryKey(programDocument.getTaskId());
                programDocumentVO.setTaskCode(programTask.getTaskCode());
            }
            programDocumentVOS.add(programDocumentVO);
        }
        return programDocumentVOS;
    }

    @ApiOperation(value = "删除项目文档", notes = "")
    @ApiImplicitParam(name = "", value = "参数", required = true)
    @RequestMapping(value = "/document/delete", method = RequestMethod.POST)
    public void deleteTaskDocument(@RequestParam("documentId") Integer documentId) {
        programService.deleteProgramDocument(documentId);
    }

    @ApiOperation(value = "新增项目日报", notes = "")
    @ApiImplicitParam(name = "", value = "参数", required = true)
    @RequestMapping(value = "/report/add", method = RequestMethod.POST)
    public void addProgramReport(@RequestBody DailyReportParamDTO dto) {
        dailyReportService.createDailyReport(dto);
    }


    @ApiOperation(value = "获取项目历史日报列表", notes = "")
    @ApiImplicitParam(name = "", value = "参数", required = true)
    @RequestMapping(value = "/report/getHistoryList", method = RequestMethod.POST)
    public BassyPagination<DailyReport> getHistoryList(@RequestBody HistoryDailyReportParamDTO dto) {
        return dailyReportService.getHistoryDailyReport(dto);
    }

    @ApiOperation(value = "根据关键字获取禅道需求及所属项目的列表", notes = "")
    @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true)
    @RequestMapping(value = "/zentao/stories/{keyword}", method = RequestMethod.GET)
    public List<UpperProjectDTO> getZtProjectStory(@PathVariable String keyword) {
        if (keyword == null) {
            throw new BusinessException("【ProgramController-getZtProjectStory-error】keyword not be null");
        }
        return upperProjectService.getProjectStoryList(keyword);
    }

    @ApiOperation(value = "根据关键字获取禅道项目", notes = "")
    @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = true)
    @RequestMapping(value = "/zentao/projects/{keyword}", method = RequestMethod.GET)
    public List<UpperProjectDTO> getZtProjects(@PathVariable String keyword) {
        if (keyword == null) {
            throw new BusinessException("【ProgramController-getZtProjects-error】keyword not be null");
        }
        return upperProjectService.getProjectsByTitle(keyword);
    }

    @ApiOperation(value = "根据项目需求获取日报信息", notes = "")
    @ApiImplicitParam(name = "projectId", value = "项目id", required = true)
    @RequestMapping(value = "/report/count", method = RequestMethod.POST)
    public ProgramRequireProcessVO getReportRequireProcess(@RequestBody ProgramRequiresDTO programRequiresDTO) {
        ProgramRequireProcessVO programRequireProcessVO = new ProgramRequireProcessVO();
        if (programRequiresDTO.getRequires().size() > 0) {
            programRequireProcessVO.setRequireTaskProcess(dailyReportService.getRequireProgress(programRequiresDTO));
        }
        List<ProgramBugInfoDTO> programBugInfo = new ArrayList<>();
        programBugInfo.add(dailyReportService.getBugSummaryByRequires(programRequiresDTO.getRequires()));
        programRequireProcessVO.setProgramBugInfo(programBugInfo);
        programRequireProcessVO.setProgramStage(dailyReportService.getProgramStage(programRequiresDTO.getProgramId()));
        return programRequireProcessVO;
    }

    @ApiOperation(value = "获取测试计划的需求视图", notes = "")
    @ApiImplicitParam(name = "programId", value = "项目Id", required = true)
    @RequestMapping(value = "/testplan/requireBoard/{programId}", method = RequestMethod.GET)
    public List<RequireBoardVO> getPlanRequireBoard(@PathVariable Integer programId) {
        if (programId == null) {
            throw new BusinessException("【ProgramController-getProgramPlan-error】programId not be null");
        }
        return programService.getPlanRequireBoard(programId);
    }

    @ApiOperation(value = "获取需求详细信息", notes = "")
    @ApiImplicitParam(name = "requireId", value = "需求Id", required = true)
    @RequestMapping(value = "/requireInfo/{requireId}", method = RequestMethod.POST)
    public ProgramRequire getRequireInfo(@PathVariable Integer requireId) {
        return programService.getRequireInfo(requireId);
    }
}
