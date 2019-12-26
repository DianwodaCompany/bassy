package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.domain.StaffInfoDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * staff info controller
 *
 * @author lichengkai
 */
@RestController
@RequestMapping("/staff")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @ApiOperation(value = "获取用户信息", notes = "根据用户Code")
    @ApiImplicitParam(name = "code", value = "用户code", required = true)
    @RequestMapping(value = "/staffInfo/{code}", method = RequestMethod.GET)
    @ResponseBody
    public StaffInfoDTO staffInfo(@PathVariable("code") String code) {
        return staffService.getStaffInfo(code);
    }


    @ApiOperation(value = "获取用户信息", notes = "根据id获取")
    @ApiImplicitParam(name = "keyWord", value = "关键字keyWord", required = true)
    @RequestMapping(value = "/staffList/{keyWord}", method = RequestMethod.GET)
    @ResponseBody
    public List<StaffInfoDTO> staffInfoList(@PathVariable("keyWord") @NotNull @NotEmpty String keyWord) {
        return staffService.getStaffInfoList(keyWord);
    }

    @ApiOperation(value = "获取组员", notes = "根据id获取")
    @ApiImplicitParam(name = "departId", value = "部门departId", required = true)
    @RequestMapping(value = "/departStaff/{departId}", method = RequestMethod.GET)
    @ResponseBody
    public List<StaffInfoDTO> departStaffList(@PathVariable("departId") @NotNull @NotEmpty String departId) {
        return staffService.getDepartStaffList(departId);
    }

}
