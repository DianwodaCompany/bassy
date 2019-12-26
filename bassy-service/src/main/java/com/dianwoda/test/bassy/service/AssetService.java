package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.domain.dto.AssetParamDTO;
import com.dianwoda.test.bassy.common.domain.vo.AssetLogVO;
import com.dianwoda.test.bassy.common.domain.vo.AssetVO;

import java.util.List;

/**
 * Created by gaoh on 2019/1/24.
 */
public interface AssetService {

    /**
     * 获取所有资产信息
     * @return
     */
    List<AssetVO> getAllAssets();

    /**
     * 获取资产变更历史
     * @param assetId
     * @return
     */
    List<AssetLogVO> getAssetLogs(Integer assetId);

    /**
     * 领用资产
     * @param param
     * @return
     */
    Boolean borrowAsset(AssetParamDTO param);

    /**
     * 归还资产
     * @param param
     * @return
     */
    Boolean returnAsset(AssetParamDTO param);

    /**
     * 更新资产状态
     * @param param
     * @return
     */
    Boolean updateStatus(AssetParamDTO param);

    /**
     * 编辑资产
     * @param param
     * @return
     */
    Boolean editAsset(AssetParamDTO param);

    /**
     * 新增资产
     * @param param
     * @return
     */
    Boolean addAsset(AssetParamDTO param);
}
