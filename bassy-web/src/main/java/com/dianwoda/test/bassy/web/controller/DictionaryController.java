package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.common.enums.DictionaryEn;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.service.DictionaryService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 18:55
 * Copyright(c) for dianwoda
 */
@RestController
@RequestMapping("/dict")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @ApiOperation(value = "根据类型获取数据字典")
    @RequestMapping(value = "/{group}", method = RequestMethod.GET)
    public List<Dictionary> getDictByGroup(@PathVariable String group) {
        if (StringUtils.isBlank(group)) {
            throw new BusinessException("group can not be empty");
        }
        return dictionaryService.getDictByGroup(group);
    }

    @ApiOperation(value = "获取所有项目流程节点")
    @RequestMapping(value = "/allProcess", method = RequestMethod.GET)
    public List<Dictionary> getAllProgramProcess() {
        return dictionaryService.getAllProcessNode();
    }

    @ApiOperation(value = "获取所有项目流程节点")
    @RequestMapping(value = "/allTestTask", method = RequestMethod.GET)
    public List<Dictionary> getAllTestTask() {
        return dictionaryService.getDictByGroup(String.valueOf(DictionaryEn.TEST_TASK.getEname()));
    }

    @ApiOperation(value = "获取所有字典信息")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Dictionary> getAllDict() {
        return dictionaryService.getAllDict();
    }

}
