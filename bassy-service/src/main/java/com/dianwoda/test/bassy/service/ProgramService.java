package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.common.domain.dto.*;
import com.dianwoda.test.bassy.common.domain.dto.program.DeleteRequireDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.ProgramReportDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.TransferRequireDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramVO;
import com.dianwoda.test.bassy.common.domain.vo.RequireBoardVO;

import java.util.List;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 11:47
 * Copyright(c) for dianwoda
 */
public interface ProgramService {

    /**
     * 分页获取项目数据
     * @param paramDTO
     * @return
     */
    Pagination<ProgramVO> list(ProgramParamDTO paramDTO);

    /**
     * list all enable ProgramDocumentDTO
     * @return
     */
    List<Program> list();

    /**
     * 分页获取用例没有push到基线的项目
     * @param paramDTO
     * @return
     */
    Pagination<Program> listUnpushedCaseProgram(ProgramParamDTO paramDTO);

    /**
     * 分页获取用例全部push到基线的项目
     * @param paramDTO
     * @return
     */
    Pagination<Program> listAllpushedCaseProgram(ProgramParamDTO paramDTO);

    /**
     * get ProgramDTO info by id
     * @param id
     * @return
     */
    ProgramVO getProgramById(Integer id);

    /**
     * insert ProgramDTO
     * @param programDTO
     * @return
     */
    Boolean insert(ProgramDTO programDTO);

    /**
     * update ProgramDTO
     * @param programDTO
     * @return
     */
    Boolean update(ProgramDTO programDTO);

    /**
     * get programId by programName
     * @param programName
     * @return
     */
    Integer getProgramIdByName(String programName);

    /**
     * get programId which like programName
     * @param programName
     * @return
     */
    List<Program> getProgramIdLikeName(String programName);

    /**
     * get programList by keyword
     * @param
     * @return
     */
    List<ProgramNameDTO>  getProgramByKeyword(String keyword);

    /**
     * get ProgramDTO info by status
     * @param
     * @returnf
     */
    Pagination<ProgramVO> getProgramByStatus(ParamDTO paramDTO, String status);

    /**
     * get ProgramDTO info by several status
     * @param
     * @returnf
     */
    Pagination<Program> getProgramByStatus(ProgramParamDTO paramDTO, String[] status);



    /**
     * 更新项目状态ById
     * @param id
     * @param status
     * @return
     */
    Boolean updateProStatusBySystem(Integer id, String status);

    /**
     * 创建测试计划
     * @param programTaskDTO
     * @return
     */
    Boolean createTestPlan(ProgramTaskDTO programTaskDTO, boolean... isInit);

    /**
     * 编辑测试计划
     * @return
     */
    Boolean updateTestPlan(ProgramTaskDTO programTaskDTO);

    /**
     * 批量编辑测试计划
     * @param programTaskDTOList
     * @return
     */
    TestPlanCountDTO batchUpdateTestPlan(List<ProgramTaskDTO> programTaskDTOList);

    /**
     * 删除测试计划
     * @return
     */
    Boolean deleteTestPlan(int taskId);

    /**
     * 查看测试计划
     * @param programId 项目id
     * @return
     */
    TestPlanDTO getTestPlan(Integer programId);

    /**
     * 校验项目名称是否存在
     * @return
     */
    Boolean programIsExit(ProgramDTO dto);

    /**
     * 更新项目状态
     * @return
     */
    Boolean updateProgramStatus(UpdateProgramStatusDTO dto);

    /**
     * 获取项目相关文档
     */
    List<ProgramDocument> getProgramDocument(Integer programId);

    /**
     * 删除项目相关文档
     * @param documentId 项目id
     */
    void deleteProgramDocument(Integer documentId);


    /**
     * 更新项目报告
     * @param dto 日报参数
     * @return
     */
    Boolean updateProgramReport(ProgramReportDTO dto);

    /**
     * 获取项目历史日报
     * @param dto
     * @return
     */
    List<ProgramLog> getHistoryProgramReport(ProgramReportDTO dto);

    /**
     * 批量更新项目状态
     * @param oldStatus 原状态
     * @param newStatus 新状态
     * @return
     */
    Boolean startPrograms(String oldStatus, String newStatus);

    /**
     * 项目新增需求
     * @param programDTO
     */
    void addRequire(ProgramDTO programDTO);

    /**
     * 项目新增需求
     * @param deleteRequireDTO
     */
    void deleteRequire(DeleteRequireDTO deleteRequireDTO);

    /**
     * 迁移需求
     * @param transferRequireDTO
     */
    void transferRequire(TransferRequireDTO transferRequireDTO);

    /**
     * 获取项目需求
     * @param programId
     */
    List<ProgramRequire> getProgramRequire(Integer programId);

    /**
     * 获取测试计划的需求视图数据
     * @param programId
     * @return
     */
    List<RequireBoardVO> getPlanRequireBoard(Integer programId);

    /**
     * 获取需求详细信息
     * @param requireId
     * @return
     */
    ProgramRequire getRequireInfo(Integer requireId);
}
