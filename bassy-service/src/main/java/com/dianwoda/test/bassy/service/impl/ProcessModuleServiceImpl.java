package com.dianwoda.test.bassy.service.impl;

import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.common.enums.ModuleStatusEn;

import com.dianwoda.test.bassy.db.dao.ProcessModuleMapper;
import com.dianwoda.test.bassy.db.dao.ProcessModuleMapperExt;
import com.dianwoda.test.bassy.db.entity.ProcessModule;
import com.dianwoda.test.bassy.db.entity.ProcessModuleExample;
import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProcessModuleDTO;
import com.dianwoda.test.bassy.service.ProcessModuleService;
import com.dianwoda.test.bassy.service.util.DateUtil;
import com.dianwoda.test.bassy.service.util.BassyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zcp on 2018/5/7.
 * Time always， fat thin man all miss.
 */
@Service
public class ProcessModuleServiceImpl implements ProcessModuleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static String READ_ONLY_Y = "Y";

    @Autowired
    private ProcessModuleMapper processModuleMapper;

    @Autowired
    private ProcessModuleMapperExt processModuleMapperExt;

    @Override
    public Pagination<ProcessModule> getAllModules(ParamDTO paramDTO) {
        Pagination<ProcessModule> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        List<ProcessModule> lists = processModuleMapperExt.getAllProcessModule();
        PageInfo<ProcessModule> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(lists);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public List<ProcessModule> getAllEnableModules() {
        ProcessModuleExample example = new ProcessModuleExample();
        ProcessModuleExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(ModuleStatusEn.ENABLE.getCode());
        return processModuleMapper.selectByExample(example);
    }

    @Override
    public ProcessModule getModuleById(Integer id) {
        return processModuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProcessModule> getModulesByStatus(String status) {
        ProcessModuleExample example = new ProcessModuleExample();
        ProcessModuleExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        return processModuleMapper.selectByExample(example);
    }

    @Override
    public List<ProcessModule> getModulesByProgramModuleId(String id) {
        ProcessModuleExample example = new ProcessModuleExample();
        ProcessModuleExample.Criteria criteria = example.createCriteria();
        criteria.andProgramModuleEqualTo(id);
        return processModuleMapper.selectByExample(example);
    }

    @Override
    public Boolean updateModuleById(ProcessModuleDTO dto) {
        if (dto == null || dto.getId() == null) {
            throw new BusinessException("【ProcessModuleServiceImpl-updateModuleById-error】dto is null or id is null");
        }
        ProcessModule pm = BassyUtil.tranformToBean(dto, ProcessModule.class);
        pm.setmodifyTm(DateUtil.getCurrentTime());
        pm.setModifier(dto.getModifyer());
        return processModuleMapper.updateByPrimaryKeySelective(pm) >0 ;
    }

    @Override
    public Boolean installModule(ProcessModuleDTO dto) {
        if (dto == null || StringUtils.isBlank(dto.getCreator())) {
            throw new BusinessException("【ProcessModuleServiceImpl-installModule-error】dto is null or creator is null");
        }
        ProcessModule pm = BassyUtil.tranformToBean(dto, ProcessModule.class);
        pm.setCreateTm(DateUtil.getCurrentTime());
        pm.setmodifyTm(DateUtil.getCurrentTime());
        pm.setModifier(pm.getCreator());
        pm.setStatus(ModuleStatusEn.ENABLE.getCode());
        return processModuleMapper.insertSelective(pm) > 0;
    }

    @Override
    public Boolean deleteModuleById(ProcessModuleDTO dto) {
        if (dto == null || dto.getId() == null) {
            throw new BusinessException("【ProcessModuleServiceImpl-deleteModuleById-error】dto is null or id is null");
        }
        ProcessModuleExample example = new ProcessModuleExample();
        ProcessModuleExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(dto.getId());
        String readOnly = processModuleMapper.selectByExample(example).get(0).getReadOnly();
        if (READ_ONLY_Y.equals(readOnly)) {
            throw new BusinessException("【ProcessModuleServiceImpl-deleteModuleById-error】readOnly is Y,can not delete");
        }
        ProcessModule pm = BassyUtil.tranformToBean(dto, ProcessModule.class);
        pm.setModifier(dto.getModifyer());
        pm.setStatus(ModuleStatusEn.UNABLE.getCode());
        return processModuleMapper.updateByPrimaryKeySelective(pm) > 0;
    }

    /**
     * 检查流程模版是否已经存在
     *
     * @param ProcessModuleName
     * @return
     */
    @Override
    public Boolean processModuleIsExit(String ProcessModuleName) {
        ProcessModuleExample example = new ProcessModuleExample();
        ProcessModuleExample.Criteria criteria = example.createCriteria();
        criteria.andModuleNameEqualTo(ProcessModuleName);
        return processModuleMapper.selectByExample(example).size()>0;
    }
}
