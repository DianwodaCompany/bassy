package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.common.domain.dto.AssetParamDTO;
import com.dianwoda.test.bassy.common.domain.vo.AssetLogVO;
import com.dianwoda.test.bassy.common.domain.vo.AssetVO;
import com.dianwoda.test.bassy.service.AssetService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by gaoh on 2019/1/25.
 */
@RestController
@RequestMapping("/asset")
public class AssetController extends BaseController{

    @Resource
    private AssetService assetService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "获取资产列表", notes = "")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<AssetVO> getAsset() {
        return assetService.getAllAssets();
    }

    @ApiOperation(value = "资产借出", notes = "")
    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    public Boolean borrowAsset(@RequestBody AssetParamDTO dto) {
        return assetService.borrowAsset(dto);
    }

    @ApiOperation(value = "资产归还", notes = "")
    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public Boolean returnAsset(@RequestBody AssetParamDTO dto) {
        return assetService.returnAsset(dto);
    }

    @ApiOperation(value = "资产状态变更", notes = "")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public Boolean updateAssetStatus(@RequestBody AssetParamDTO dto) {
        return assetService.updateStatus(dto);
    }

    @ApiOperation(value = "获取资产操作日志", notes = "")
    @RequestMapping(value = "/assetLogs/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<AssetLogVO> getAssetLogs(@NotNull @PathVariable("id") Integer id) {
        return assetService.getAssetLogs(id);
    }

    @ApiOperation(value = "增加资产", notes = "")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Boolean addAsset(@RequestBody AssetParamDTO dto) {
        return assetService.addAsset(dto);
    }

    @ApiOperation(value = "编辑资产", notes = "")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Boolean editAsset(@RequestBody AssetParamDTO dto) {
        return assetService.editAsset(dto);
    }

}
