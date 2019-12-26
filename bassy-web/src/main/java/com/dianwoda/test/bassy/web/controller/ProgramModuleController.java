package com.dianwoda.test.bassy.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProgramModuleDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProgramModuleVO;
import com.dianwoda.test.bassy.common.enums.ProgramTypeEn;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.entity.ProgramModule;
import com.dianwoda.test.bassy.service.ProgramModuleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ProgramDocumentDTO Module Controller
 *
 * @author lichengkai
 * 2018 - 04 - 28 : 20:14
 * Copyright(c) for dianwoda
 */
@RestController
@RequestMapping("/programModule")
public class ProgramModuleController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProgramModuleService programModuleService;

    @ApiOperation(value = "获取所有模板类型", notes = "")
    @RequestMapping(value = "/getProgramType", method = RequestMethod.GET)
    public Map<String, String> getProgramType() {
        return ProgramTypeEn.getAllProgramType();
    }

    @ApiOperation(value = "根据类型获取默认模板", notes = "ProgramDocumentDTO,normal,pressing,internal")
    @RequestMapping(value = "/getDefaultModule/{typeCode}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "typeCode", value = "项目类型", required = true)
    public ProgramModuleVO getDefaultModule(@PathVariable("typeCode") String typeCode) {
        ProgramModule pm = programModuleService.getDefaultProgramModule(typeCode);
        return pm2pmv(pm);
    }

    @ApiOperation(value = "获取所有可用模板", notes = "")
    @RequestMapping(value = "/getAllEnableModules", method = RequestMethod.POST)
    public Pagination<ProgramModuleVO> getAllEnableModules(@RequestBody ParamDTO paramDTO) {
        logger.info("getAllEnableModules入参参数为" + paramDTO.toString());
        Pagination<ProgramModuleVO> pagination = new Pagination<>();
        Pagination<ProgramModule> pms = programModuleService.getAllEnableModules(paramDTO);
        List<ProgramModuleVO> programModuleVOS = new ArrayList<>();
        for (ProgramModule pm : pms.getList()) {
            ProgramModuleVO programModuleVo = pm2pmv(pm);
            programModuleVOS.add(programModuleVo);
        }
        pagination.setTotalCount(pms.getTotalCount());
        pagination.setPageSize(pms.getPageSize());
        pagination.setCurrentPage(pms.getCurrentPage());
        pagination.setList(programModuleVOS);
        return pagination;
    }

    @ApiOperation(value = "根据模板id获取模板", notes = "")
    @RequestMapping(value = "/getProgramModuleById/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "模板id", required = true)
    public ProgramModuleVO getProgramModuleById(@PathVariable("id") Integer id) {
        ProgramModule pm = programModuleService.getProgramModuleById(id);
        return pm2pmv(pm);
    }

    @ApiOperation(value = "根据模板id获取模板", notes = "")
    @RequestMapping(value = "/getProjectTemplatesByType/{type}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "模板id", required = true)
    public List<ProgramModuleVO> getProjectTemplatesByType(@PathVariable("type") String type) {
        List<ProgramModule> pms = programModuleService.getProgramModuleByParentCode(type);
        List<ProgramModuleVO> programModuleVOS = new ArrayList<>();
        for (ProgramModule pm : pms) {
            if (pm.getStatus().equals("enable")) {
                ProgramModuleVO programModuleVo = pm2pmv(pm);
                programModuleVOS.add(programModuleVo);
            }
        }
        return programModuleVOS;
    }

    @ApiOperation(value = "插入模板", notes = "")
    @RequestMapping(value = "/insertModule", method = RequestMethod.POST)
    public Boolean insertModule(@RequestBody @Valid ProgramModuleDTO dto) {
        if (dto == null) {
            throw new BusinessException("【ProgramModuleController-insertModule-error】dto can not be null");
        }
        if (!ProgramTypeEn.contains(dto.getParentCode())) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】ParentCode not exist");
        }
        if (dto.getStatus() != null) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】status need null");
        }
        if (dto.getIsCheck() != null) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】isCheck need null");
        }
        if (dto.getReadOnly() != null) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】readonly need null");
        }
        if (dto.getCreator() == null || dto.getCreator().isEmpty()) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】creator can not null or can not empty");
        }
        if (dto.getModifier() != null) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】modifuer need null or need empty");
        }
        logger.info("【ProgramModuleController-insertModule-params】{0}", JSONObject.toJSON(dto));
        return programModuleService.insertProgramModule(dto);
    }

    @ApiOperation(value = "更新模板", notes = "id必须传")
    @RequestMapping(value = "/updateModule", method = RequestMethod.POST)
    public Boolean updateModule(@RequestBody ProgramModuleDTO dto) {
        if (dto == null) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】dto can not be null");
        }
        if (dto.getReadOnly() != null) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】readonly need null");
        }
        if (dto.getCreator() != null) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】creator need null");
        }
        if (dto.getModifier() == null || dto.getModifier().isEmpty()) {
            throw new BusinessException("【ProgramModuleController-updateModule-error】modifuer can not null or can not empty");
        }
        logger.info("【ProgramModuleController-updateModule-params】{0}", JSONObject.toJSON(dto));
        return programModuleService.updateProgramModuleById(dto);
    }

    @ApiOperation(value = "删除模板", notes = "id必须传")
    @RequestMapping(value = "/deleteModule/{id}", method = RequestMethod.POST)
    @ApiImplicitParam(name = "id", value = "模板id", required = true)
    public Boolean deleteModule(@PathVariable("id") Integer id) {
        if (id == null) {
            throw new BusinessException("【ProgramModuleController-deleteModule-error】id can not be null");
        }
        return programModuleService.deleteProgramModuleById(id);
    }

    @ApiOperation(value = "检查模版名称是否存在", notes = "programModuleName存在")
    @RequestMapping(value = "/programModuleIsExit/{programModuleName}", method = RequestMethod.POST)
    @ApiImplicitParam(name = "programModuleName", value = "模板programModuleName", required = true)
    public Boolean checkProgramModuleNameIsExit(@PathVariable("programModuleName") String programModuleName) {
        if (programModuleName.isEmpty()) {
            throw new BusinessException("【ProgramModuleController-checkProgramModuleNameIsExit-error】programName can not be empty");
        }
        return programModuleService.programModuleNameIsExit(programModuleName);
    }

    private ProgramModuleVO pm2pmv(ProgramModule pm) {
        ProgramModuleVO pmv = new ProgramModuleVO();
        BeanUtils.copyProperties(pm, pmv);
        pmv.setPersons(JSONObject.parse(pm.getPersons()));
        pmv.setCoreNodes(JSONArray.parse(pm.getCoreNodes()));
        pmv.setRequires(new ArrayList<>());
        return pmv;
    }
}
