package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.entity.ProgramModule;
import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProgramModuleDTO;

import java.util.List;


public interface ProgramModuleService {
    /**
     * 通过模板类型获取默认模板
     * @param typeCode
     * @return
     */
    ProgramModule getDefaultProgramModule(String typeCode);

    /**
     * 根据id获取模板信息
     * @param id
     * @return
     */
    ProgramModule getProgramModuleById(Integer id);

    /**
     * 根据parentCode获取模板信息
     * @param parentCode
     * @return
     */
    List<ProgramModule> getProgramModuleByParentCode(String parentCode);

    /**
     * 获取所有状态正常的模板列表
     * @return
     */
    Pagination<ProgramModule> getAllEnableModules(ParamDTO paramDTO);

    /**
     * 插入模板信息
     * @param dto
     * @return
     */
    Boolean insertProgramModule(ProgramModuleDTO dto);

    /**
     * 根据id更新模板信息
     * @param dto
     * @return
     */
    Boolean updateProgramModuleById(ProgramModuleDTO dto);

    /**
     * 根据id删除模板信息
     * @param id
     * @return
     */
    Boolean deleteProgramModuleById(Integer id);

    /**
     * 项目模版名称是否存在
     * @param programModuleName
     * @return
     */
    Boolean programModuleNameIsExit(String programModuleName);
}
