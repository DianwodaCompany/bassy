package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.enums.ProgramStatusEn;
import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.service.DictionaryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 18:10
 * Copyright(c) for dianwoda
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private StaffService staffService;

    @ApiOperation(value = "获取员工权限", notes = "获取员工权限")
    @RequestMapping(value = "/getAuthenticatedRescodes/{staffCode}", method = RequestMethod.GET)
    public String getAuthenticatedRescodes(@PathVariable("staffCode") String staffCode) {
        return staffService.getStaffResCodes(staffCode);
    }

    @ApiOperation(value = "获取项目状态列表", notes = "获取项目状态列表")
    @RequestMapping(value = "/program/status", method = RequestMethod.GET)
    public Map<String, String> getstatus() {
        return ProgramStatusEn.getAll();
    }

    /**
     * 获取所有项目阶段节点
     * @return
     */
    @ApiOperation(value = "获取所有项目阶段节点", notes = "")
    @RequestMapping(value = "/dictionary/projectNode", method = RequestMethod.GET)
    public List<Dictionary> getAllProcessNode() {
        return dictionaryService.getAllProcessNode();
    }

    @ApiOperation(value = "查询字典对应关系", notes = "获取字典对应关系")
    @RequestMapping(value = "/dictionary/{code}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "code", value = "字典code", required = true)
    public Dictionary getDictionaryByDictCode(@PathVariable("code") String code) {
        return dictionaryService.getDictionaryByDictCode(code);
    }
}
