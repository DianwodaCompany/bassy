package com.dianwoda.test.bassy.api;

import com.dianwoda.test.bassy.common.domain.StaffInfoDTO;
import java.util.List;

/**
 * Created by gaoh on 2018/10/22.
 */
public interface StaffService {

    /**
     * 获取小组成员
     * @param departId
     * @return
     */
    List<StaffInfoDTO> getDepartStaffList(String departId);

    /**
     * 获取员工信息
     * @param staffCode
     * @return
     */
    StaffInfoDTO getStaffInfo(String staffCode);

    /**
     * 管局关键字获取员工list
     * @param keyWord
     * @return
     */
    List<StaffInfoDTO> getStaffInfoList(String keyWord);

    /**
     * 获取员工权限
     * @param staffCode
     * @return
     */
    String getStaffResCodes(String staffCode);
}