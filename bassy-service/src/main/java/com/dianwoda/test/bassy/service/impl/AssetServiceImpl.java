package com.dianwoda.test.bassy.service.impl;

import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.enums.AssetStatusEn;
import com.dianwoda.test.bassy.common.enums.AssetTypeEn;
import com.dianwoda.test.bassy.db.dao.AssetLogMapper;
import com.dianwoda.test.bassy.db.dao.AssetMapper;
import com.dianwoda.test.bassy.db.entity.Asset;
import com.dianwoda.test.bassy.db.entity.AssetExample;
import com.dianwoda.test.bassy.db.entity.AssetLog;
import com.dianwoda.test.bassy.db.entity.AssetLogExample;
import com.dianwoda.test.bassy.common.domain.dto.AssetParamDTO;
import com.dianwoda.test.bassy.common.domain.vo.AssetLogVO;
import com.dianwoda.test.bassy.common.domain.vo.AssetVO;
import com.dianwoda.test.bassy.service.AssetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaoh on 2019/1/24.
 */
@Service("assetService")
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private AssetLogMapper assetLogMapper;

    @Autowired
    private StaffService staffService;

    @Override
    public List<AssetVO> getAllAssets() {
        AssetExample example = new AssetExample();
        List<Asset> assets = assetMapper.selectByExample(example);
        List<AssetVO> assetList = new ArrayList<>();
        for(Asset a : assets) {
            AssetVO vo = new AssetVO();
            BeanUtils.copyProperties(a,vo);
            vo.setTypeMean(AssetTypeEn.toEnum(a.getType()).getMean());
            vo.setStatusMean(AssetStatusEn.toEnum(a.getStatus()).getMean());
            if(a.getBorrower() != null) {
                vo.setBorrowerName(staffService.getStaffInfo(a.getBorrower()).getName());
            }
            assetList.add(vo);
        }
        return assetList;
    }

    @Override
    public List<AssetLogVO> getAssetLogs(Integer assetId) {
        AssetLogExample example = new AssetLogExample();
        AssetLogExample.Criteria criteria = example.createCriteria();
        criteria.andAssetIdEqualTo(assetId);
        example.setOrderByClause("modify_tm desc");
        List<AssetLog> logs = assetLogMapper.selectByExample(example);
        List<AssetLogVO> logVos = new ArrayList<>();
        for(AssetLog log : logs) {
            AssetLogVO logVo = new AssetLogVO();
            BeanUtils.copyProperties(log, logVo);
            logVo.setStatusMean(AssetStatusEn.toEnum(log.getStatus()).getMean());
            if(log.getBorrower() != null) {
                logVo.setBorrowerName(staffService.getStaffInfo(log.getBorrower()).getName());
            }
            logVo.setModifierName(staffService.getStaffInfo(log.getModifier()).getName());
            logVos.add(logVo);
        }
        return logVos;
    }

    @Override
    public Boolean borrowAsset(AssetParamDTO param) {
        Asset asset = new Asset();
        asset.setId(param.getAsset().getId());
        asset.setInUse((short)(param.getAsset().getInUse()+1));
        asset.setBorrower(param.getAsset().getBorrower());
        asset.setBorrowTm(param.getAsset().getBorrowTm());
        asset.setStatus(AssetStatusEn.IN_USE.getCode());
        boolean updateAsset = assetMapper.updateByPrimaryKeySelective(asset) > 0;
        AssetLog log = new AssetLog();
        log.setAssetId(param.getAsset().getId());
        log.setStatus(AssetStatusEn.IN_USE.getCode());
        log.setBorrower(param.getAsset().getBorrower());
        log.setBorrowTm(param.getAsset().getBorrowTm());
        log.setRemark(param.getRemark());
        log.setModifier(param.getModifier());
        log.setModifyTm(new Date());
        boolean insetLog = assetLogMapper.insertSelective(log) > 0;
        return updateAsset && insetLog;
    }

    @Override
    public Boolean returnAsset(AssetParamDTO param) {
        Asset asset = new Asset();
        BeanUtils.copyProperties(param.getAsset(),asset);
        asset.setInUse((short)(param.getAsset().getInUse()-1));
        asset.setBorrower(null);
        asset.setBorrowTm(null);
        asset.setStatus(AssetStatusEn.UN_USE.getCode());
        boolean updateAsset = assetMapper.updateByPrimaryKey(asset) > 0;
        AssetLog log = new AssetLog();
        log.setAssetId(param.getAsset().getId());
        log.setStatus(AssetStatusEn.UN_USE.getCode());
        log.setReturnTm(param.getReturnTm());
        log.setRemark(param.getRemark());
        log.setModifier(param.getModifier());
        log.setModifyTm(new Date());
        boolean insetLog = assetLogMapper.insertSelective(log) > 0;
        return updateAsset && insetLog;
    }

    @Override
    public Boolean updateStatus(AssetParamDTO param) {
        Asset asset = new Asset();
        asset.setId(param.getAsset().getId());
        asset.setStatus(param.getAsset().getStatus());
        boolean updateAsset = assetMapper.updateByPrimaryKeySelective(asset) > 0;
        AssetLog log = new AssetLog();
        log.setAssetId(param.getAsset().getId());
        log.setStatus(param.getAsset().getStatus());
        log.setModifier(param.getModifier());
        log.setModifyTm(new Date());
        boolean insetLog = assetLogMapper.insertSelective(log) > 0;
        return updateAsset && insetLog;
    }

    @Override
    public Boolean editAsset(AssetParamDTO param) {
        Asset before = assetMapper.selectByPrimaryKey(param.getAsset().getId());
        Asset asset = new Asset();
        asset.setId(param.getAsset().getId());
        asset.setVersion(param.getAsset().getVersion());
        asset.setAmount(param.getAsset().getAmount());
        boolean updateAsset = assetMapper.updateByPrimaryKeySelective(asset) > 0;
        Asset after = assetMapper.selectByPrimaryKey(param.getAsset().getId());
        AssetLog log = new AssetLog();
        log.setAssetId(param.getAsset().getId());
        log.setStatus(param.getAsset().getStatus());
        String remark = "";
        if(!after.getVersion().equals(before.getVersion())) {
            remark += "变更系统：" + (before.getVersion() == null ? "无" : before.getVersion()) + "->" + (after.getVersion() == null ? "无" : after.getVersion()) + "；";
        }
        if(after.getAmount().intValue() != before.getAmount().intValue()) {
            remark += "变更数量：" + before.getAmount() + "->" + after.getAmount();
        }
        log.setRemark(remark);
        log.setModifier(param.getModifier());
        log.setModifyTm(new Date());
        boolean insetLog = assetLogMapper.insertSelective(log) > 0;
        return updateAsset && insetLog;
    }

    @Override
    public Boolean addAsset(AssetParamDTO param) {
        Asset asset = new Asset();
        asset.setType(param.getAsset().getType());
        asset.setBrand(param.getAsset().getBrand());
        asset.setModel(param.getAsset().getModel());
        asset.setImei(param.getAsset().getImei());
        asset.setAssetNumber(param.getAsset().getAssetNumber());
        asset.setVersion(param.getAsset().getVersion());
        asset.setSize(param.getAsset().getSize());
        asset.setAmount(param.getAsset().getAmount());
        asset.setInUse((short)0);
        asset.setStatus(AssetStatusEn.UN_USE.getCode());
        boolean addAsset = assetMapper.insertSelective(asset) > 0;
        return addAsset;
    }
}
