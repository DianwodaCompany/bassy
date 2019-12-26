package com.dianwoda.test.bassy.service.impl;

import com.dianwoda.test.bassy.common.constants.ProgramConstant;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.common.enums.ModuleStatusEn;
import com.dianwoda.test.bassy.common.enums.ProgramStatusEn;
import com.dianwoda.test.bassy.common.enums.ReadOnlyEn;

import com.dianwoda.test.bassy.db.dao.ProgramMapper;
import com.dianwoda.test.bassy.db.dao.ProgramModuleMapperExt;
import com.dianwoda.test.bassy.db.entity.Program;
import com.dianwoda.test.bassy.db.entity.ProgramExample;
import com.dianwoda.test.bassy.db.entity.ProgramModule;
import com.dianwoda.test.bassy.db.entity.ProgramModuleExample;
import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProgramModuleDTO;
import com.dianwoda.test.bassy.service.ProgramModuleService;
import com.dianwoda.test.bassy.service.util.DateUtil;
import com.dianwoda.test.bassy.service.util.BassyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProgramModuleServiceImpl implements ProgramModuleService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProgramModuleMapperExt programModuleMapper;

    @Autowired
    private ProgramMapper programMapper;


    @Override
    public ProgramModule getDefaultProgramModule(String typeCode) {
        if (StringUtils.isBlank(typeCode)) {
            throw new BusinessException("【ProgramServiceImpl-getDefaultProgramModule-error】typeCode is not null or empty");
        }
        ProgramModuleExample example = new ProgramModuleExample();
        ProgramModuleExample.Criteria criteria = example.createCriteria();
        criteria.andParentCodeEqualTo(typeCode);
        criteria.andReadOnlyEqualTo(ProgramConstant.Y);
        criteria.andStatusEqualTo(ModuleStatusEn.ENABLE.getCode());
        List<ProgramModule> programModules = programModuleMapper.selectByExample(example);
        if (!programModules.isEmpty()) {
            return programModules.get(0);
        }
        return new ProgramModule();
    }

    @Override
    public ProgramModule getProgramModuleById(Integer id) {
        return programModuleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProgramModule> getProgramModuleByParentCode(String parentCode) {
        ProgramModuleExample example = new ProgramModuleExample();
        ProgramModuleExample.Criteria criteria = example.createCriteria();
        criteria.andParentCodeEqualTo(parentCode);
        return programModuleMapper.selectByExample(example);
    }

    @Override
    public Pagination<ProgramModule> getAllEnableModules(ParamDTO paramDTO) {

        Pagination<ProgramModule> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        List<ProgramModule> lists = programModuleMapper.getModuleList();
        PageInfo<ProgramModule> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(lists);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public Boolean insertProgramModule(ProgramModuleDTO dto) {
        if (dto == null || StringUtils.isBlank(dto.getCreator())) {
            throw new BusinessException("【ProgramModuleServiceImpl-insertProgramModule-error】dto is null or creator is null");
        }
        ProgramModule pm = BassyUtil.tranformToBean(dto, ProgramModule.class);
        pm.setCreateTm(DateUtil.getCurrentTime());
        pm.setModifyTm(DateUtil.getCurrentTime());
        pm.setModifier(pm.getCreator());
        pm.setReadOnly(ProgramConstant.N);
        pm.setStatus(ModuleStatusEn.ENABLE.getCode());
        Integer res = programModuleMapper.insertSelective(pm);
        return res > 0;
    }

    @Override
    public Boolean updateProgramModuleById(ProgramModuleDTO dto) {
        if (dto.getId() == null) {
            throw new BusinessException("【ProgramModuleServiceImpl-updateProgramModuleById-error】id can not be null");
        }
        ProgramModule pm = BassyUtil.tranformToBean(dto, ProgramModule.class);
        pm.setReadOnly(ProgramConstant.N);
        Integer res = programModuleMapper.updateByPrimaryKeySelective(pm);
        return res > 0;
    }

    @Override
    public Boolean deleteProgramModuleById(Integer id) {
        ProgramModule pm = programModuleMapper.selectByPrimaryKey(id);
        if (pm == null) {
            throw new BusinessException("【ProgramModuleServiceImpl-deleteProgramModuleById-error】ProgramModule {} is not exist");
        }
        if (ReadOnlyEn.YES.getCode().equals(pm.getReadOnly())) {
            throw new BusinessException("【ProgramModuleServiceImpl-deleteProgramModuleById-error】deleting default module is not allowed ！");
        }
        ProgramExample example = new ProgramExample();
        ProgramExample.Criteria criteria = example.createCriteria();
        List<String> status = new ArrayList<>();
        status.add(ProgramStatusEn.SCHEDULED.getCode());
        status.add(ProgramStatusEn.PROCESSING.getCode());
        criteria.andStatusIn(status);
        criteria.andProgramModuleEqualTo(id);
        List<Program> programs = programMapper.selectByExample(example);
        if (programs.size() > 0) {
            throw new BusinessException("还有未结束的项目，不允许删除！");
        }
        // 进行软删
        ProgramModule pm2 = new ProgramModule();
        pm2.setId(id);
        pm2.setStatus(ModuleStatusEn.UNABLE.getCode());
        return programModuleMapper.updateByPrimaryKeySelective(pm2) > 0;
    }


    @Override
    public Boolean programModuleNameIsExit(String programModuleName) {
        ProgramModuleExample example = new ProgramModuleExample();
        ProgramModuleExample.Criteria criteria = example.createCriteria();
        criteria.andModuleNameEqualTo(programModuleName);
        List<ProgramModule> programModules = programModuleMapper.selectByExample(example);
        return programModules.size() > 0;
    }
}
