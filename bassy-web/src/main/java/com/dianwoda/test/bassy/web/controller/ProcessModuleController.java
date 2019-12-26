package com.dianwoda.test.bassy.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.ProcessModuleDTO;
import com.dianwoda.test.bassy.common.domain.vo.ProcessModuleVO;
import com.dianwoda.test.bassy.common.domain.vo.TaskVO;
import com.dianwoda.test.bassy.common.enums.ProjectNodeEn;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.db.entity.ProcessModule;
import com.dianwoda.test.bassy.db.entity.ProcessTaskRelate;
import com.dianwoda.test.bassy.service.DictionaryService;
import com.dianwoda.test.bassy.service.ProcessModuleService;
import com.dianwoda.test.bassy.service.ProcessTaskRelateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zcp on 2018/5/7.
 * Time always， fat thin man all miss.
 */
@RestController
@RequestMapping("/processModule")
public class ProcessModuleController extends BaseController {
    @Autowired
    ProcessModuleService processModuleService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    ProcessTaskRelateService processTaskRelateService;

    @ApiOperation(value = "获取流程模版列表", notes = "默认获取所有模版")
    @RequestMapping(value = "/allModules", method = RequestMethod.POST)
    @ResponseBody
    public Pagination<ProcessModule> getAllModules(@RequestBody ParamDTO paramDTO) {
        return processModuleService.getAllModules(paramDTO);
    }

    @ApiOperation(value = "根据模版状态获取流程模版列表", notes = "状态，enable-可用；unable-不可用；")
    @RequestMapping(value = "/modules/{status}", method = RequestMethod.GET)
    @ResponseBody
    public List<ProcessModuleVO> getModulesByStatus(@NotNull @PathVariable("status") String status) {
        List<ProcessModule> pml = processModuleService.getModulesByStatus(status);
        List<ProcessModuleVO> pmvl = new ArrayList<>();
        for (ProcessModule pm : pml) {
            pmvl.add(pm2pmv(pm));
        }
        return pmvl;
    }

    @ApiOperation(value = "根据项目模板ID获取流程模版列表", notes = "状态，enable-可用；unable-不可用；")
    @RequestMapping(value = "/modules/programModuleId/{programModuleId}", method = RequestMethod.GET)
    @ResponseBody
    public List<ProcessModuleVO> getModulesByProgramModuleId(@NotNull @PathVariable("programModuleId") String programModuleId) {
        List<ProcessModule> pml = processModuleService.getModulesByProgramModuleId(programModuleId);
        List<ProcessModuleVO> pmvl = new ArrayList<>();
        for (ProcessModule pm : pml) {
            pmvl.add(pm2pmv(pm));
        }
        return pmvl;
    }

    @ApiOperation(value = "获取模版信息", notes = "根据模版id获取")
    @RequestMapping(value = "/module/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ProcessModuleVO getProcessModuleById(@PathVariable("id") Integer id) {
        ProcessModule pm = processModuleService.getModuleById(id);
        return pm2pmv(pm);
    }

    @ApiOperation(value = "更新模版数据", notes = "根据模版id更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateProcessModeleById(@RequestBody ProcessModuleDTO dto) {
        if (dto == null || dto.getId() == null) {
            throw new BusinessException("【ProgramModuleController-insertModule-error】dto can not be null or id can not be null");
        }
        if (dto.getStatus() != null) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】status need null");
        }
        if (dto.getReadOnly() != null) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】readOnly need null");
        }
        if (dto.getCreator() != null) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】creater need null");
        }
        if (dto.getModifyer() == null || dto.getModifyer().isEmpty()) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】modifyer not null or not enpty");
        }
        logger.error("【ProgramModuleController-insertModule-params】{0}", JSONObject.toJSON(dto));
        return processModuleService.updateModuleById(dto);
    }

    @ApiOperation(value = "删除模版数据", notes = "根据模版id删除")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean deleteProcessModeleById(@PathVariable("id") Integer id) {
        if (id == null) {
            throw new BusinessException("【ProgramModuleController-insertModule-error】id can not be null or id can not be null");
        }
        logger.error("【ProgramModuleController-deleteProcessModeleById-params】{0}", id);
        ProcessModuleDTO dto = new ProcessModuleDTO();
        dto.setId(id);
        return processModuleService.deleteModuleById(dto);
    }

    @ApiOperation(value = "新增模版数据", notes = "")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Boolean addProcessModele(@RequestBody @Valid ProcessModuleDTO dto) {
        if (dto == null) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】dto can not be null");
        }
        if (dto.getStatus() != null) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】status need null");
        }
        if (dto.getReadOnly() != null) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】readOnly need null");
        }
        if (dto.getCreator() == null || dto.getCreator().isEmpty()) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】creater can not null or can not empty");
        }
        if (dto.getModifyer() != null) {
            throw new BusinessException("【ProgramModuleController-addProcessModele-error】modifyer need null");
        }
        logger.error("【ProgramModuleController-addProcessModele-params】{0}", JSONObject.toJSON(dto));
        return processModuleService.installModule(dto);
    }

    @ApiOperation(value = "通过阶段node获取所有可以关联任务", notes = "")
    @RequestMapping(value = "/processTask/{processNode}", method = RequestMethod.POST)
    @ResponseBody
    public Object getProcessTaskByProcessNode(@PathVariable("processNode") String processNode) {
        if (!ProjectNodeEn.contains(processNode)) {
            throw new BusinessException("【ProgramModuleController-getProcessTaskByProcessNode-error】processNode not exist");
        }
        ProcessTaskRelate ptr = processTaskRelateService.getAllTaskByNode(processNode);
        return ptr2tv(ptr).getTasks();
    }

    @ApiOperation(value = "获取所有阶段和任务的关联关系", notes = "")
    @RequestMapping(value = "/processTask", method = RequestMethod.GET)
    @ResponseBody
    public List<TaskVO> getAllProcessTask() {
        List<ProcessTaskRelate> ptrList = processTaskRelateService.getAllProcessTask();
        List<TaskVO> taskVOList = new ArrayList<>();
        for (ProcessTaskRelate pt : ptrList) {
            taskVOList.add(ptr2tv(pt));
        }
        return taskVOList;
    }


    @ApiOperation(value = "检查模版名称是否存在", notes = "")
    @RequestMapping(value = "/processModuleIsExit/{processModuleName}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean checkProcessModuleIsExit(@PathVariable("processModuleName") String processModuleName) {
        if (processModuleName.isEmpty()) {
            throw new BusinessException("【ProgramModuleController-checkProcessModuleIsExit-error】processModuleName can not empty");
        }
        return processModuleService.processModuleIsExit(processModuleName);
    }

    /**
     * ProcessModule数据库DTO向展示层VO转换
     *
     * @param pm
     * @return
     */
    private ProcessModuleVO pm2pmv(ProcessModule pm) {
        ProcessModuleVO pmv = new ProcessModuleVO();
        BeanUtils.copyProperties(pm, pmv);
        pmv.setProcessNode(JSONArray.parse(pm.getProcessNode()));
        return pmv;
    }

    /**
     * ProcessTaskRelate数据库DTO向展示层VO转换
     *
     * @param ptr
     * @return
     */
    private TaskVO ptr2tv(ProcessTaskRelate ptr) {
        TaskVO tv = new TaskVO();
        BeanUtils.copyProperties(ptr, tv);

        Dictionary dictionary = dictionaryService.getDictionaryByDictCode(ptr.getProcessCode());
        String processValue = dictionary == null ? ptr.getProcessCode() : dictionary.getDictValue();
        tv.setProcessValue(processValue);
        List<Map> taskList = JSONObject.parseArray(ptr.getTask(), Map.class);
        List<Map> newTask = new ArrayList<>();
        for (Map p : taskList) {
            String dictValue = dictionaryService.getDictionaryByDictCode(p.get("task").toString()).getDictValue();
            p.put("taskValue", dictValue);
            newTask.add(p);
        }
        tv.setTasks(JSON.toJSON(newTask));
        return tv;
    }
}
