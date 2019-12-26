package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.entity.ProcessModule;
import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProcessModuleDTO;

import java.util.List;

/**
 * Created by zcp on 2018/5/7.
 * Time always， fat thin man all miss.
 */
public interface ProcessModuleService {

    /**
     * 获取所有项目流程模版列表
     * @return
     */
    Pagination<ProcessModule> getAllModules(ParamDTO paramDTO);

    /**
     * 获取所有可用的项目流程模版
     * @return
     */
    List<ProcessModule> getAllEnableModules();

    /**
     * 根据项目流程模板id获取流程模版
     * @param id
     * @return
     */
    ProcessModule getModuleById(Integer id);

    /**
     * 获取指定状态的模版列表
     * @param status
     * @return
     */
    List<ProcessModule> getModulesByStatus(String status);

    /**
     * 获取指定项目模板的流程模版列表
     * @param id
     * @return
     */
    List<ProcessModule> getModulesByProgramModuleId(String id);

    /**
     * 根据id更新项目流程模版
     * @param dto
     * @return
     */
    Boolean updateModuleById(ProcessModuleDTO dto);

    /**
     * 根据id删除项目流程模版
     * @param dto
     * @return
     */
    Boolean deleteModuleById(ProcessModuleDTO dto);

    /**
     * 插入流程模版
     * @param dto
     * @return
     */
    Boolean installModule(ProcessModuleDTO dto);

    /**
     * 检查流程模版是否已经存在
     * @param ProcessModuleName
     * @return
     */
    Boolean processModuleIsExit(String ProcessModuleName);
}
