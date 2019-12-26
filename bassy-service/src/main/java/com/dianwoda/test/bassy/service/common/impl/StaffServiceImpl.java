package com.dianwoda.test.bassy.service.common.impl;

import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.domain.StaffInfoDTO;
import com.dianwoda.test.bassy.common.enums.TeamEn;
import com.dianwoda.test.bassy.db.dao.StaffMapper;
import com.dianwoda.test.bassy.db.entity.Staff;
import com.dianwoda.test.bassy.db.entity.StaffExample;
import com.dianwoda.test.bassy.service.common.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 18:56
 * Copyright(c) for dianwoda
 */
@Service
@Slf4j
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private RedisService redisService;

    @Override
    @SuppressWarnings("unchecked")
    public List<StaffInfoDTO> getDepartStaffList(String departId) {
        List<StaffInfoDTO> staffInfoDTOS = new ArrayList<>();
        StaffInfoDTO staffInfoDTO = new StaffInfoDTO();
        staffInfoDTO.setCode("00001");
        staffInfoDTO.setName("admin");
        staffInfoDTOS.add(staffInfoDTO);
        return staffInfoDTOS;
    }

    @Override
    public StaffInfoDTO getStaffInfo(String staffCode) {
        StaffInfoDTO staffInfoDTO = new StaffInfoDTO();
        staffInfoDTO.setCode("00001");
        staffInfoDTO.setName("admin");
        return staffInfoDTO;
    }

    @Override
    public List<StaffInfoDTO> getStaffInfoList(String keyWord) {
        List<StaffInfoDTO> staffInfoDTOS = new ArrayList<>();
        StaffInfoDTO staffInfoDTO = new StaffInfoDTO();
        staffInfoDTO.setCode("00001");
        staffInfoDTO.setName("admin");
        staffInfoDTOS.add(staffInfoDTO);
        return staffInfoDTOS;
    }

    @Override
    public String getStaffResCodes(String staffCode) {
//        String result = "";
//        ResponseDTO<StaffDTO> staff = staffProvider.findByCode(staffCode);
//        if (!ResponseUtil.hasNotNullResult(staff)) {
//            log.warn("Get Logined Staff Authenticated Menu, the logined staff get null.");
//            return result;
//        }
//        ResponseDTO<List<String>> response = authenticationV2Provider.getAuthenticatedRescodes(staff.getData().getId(),
//                appCode);
//        if (response.isSuccess() && CollectionUtils.isNotEmpty(response.getData())) {
//            String authList = JSON.toJSONString(response.getData());
//            if (authList.contains("BASSY_ADMIN")) {
//                return "BASSY_ADMIN";
//            } else if (authList.contains("BASSY_LEADER")) {
//                return "BASSY_LEADER";
//            } else if (authList.contains("BASSY_COMMON")) {
//                return "BASSY_COMMON";
//            } else {
//                return "BASSY_GUEST";
//            }
//        }
//        log.warn("Get Logined Staff Authenticated Menu, the authenticationProvider return [r = {}, msg = {}]",
//                response.isSuccess(), response.getMsg());
        return "BASSY_ADMIN";
    }
}
