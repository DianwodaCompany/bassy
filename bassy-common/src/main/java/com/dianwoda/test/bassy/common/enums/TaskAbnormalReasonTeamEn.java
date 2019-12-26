package com.dianwoda.test.bassy.common.enums;

import com.dianwoda.test.bassy.common.domain.dto.statistics.AbnormalReasonTeamDTO;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public enum TaskAbnormalReasonTeamEn {

    PRODUCT(1, "产品原因"),
    DEVELOP(2, "开发原因"),
    TEST(3, "测试原因"),
    OPERATION(4, "运维原因"),
    DBA(5, "DBA原因"),
    OTHER(9, "其他原因");


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer code;
    private String name;

    public static List<AbnormalReasonTeamDTO> getAll() {
        List<AbnormalReasonTeamDTO> abnormalReasonTeamDTOList = new ArrayList<>();
        TaskAbnormalReasonTeamEn[] taskAbnormalReasonTeamEn = TaskAbnormalReasonTeamEn.values();
        for (int i = 0; i < taskAbnormalReasonTeamEn.length; i++) {
            AbnormalReasonTeamDTO abnormalReasonTeamDTO = new AbnormalReasonTeamDTO();
            abnormalReasonTeamDTO.setReasonTeamCode(taskAbnormalReasonTeamEn[i].getCode());
            abnormalReasonTeamDTO.setReasonTeamName(taskAbnormalReasonTeamEn[i].getName());
            abnormalReasonTeamDTOList.add(abnormalReasonTeamDTO);
        }
        return abnormalReasonTeamDTOList;
    }

    public static Boolean contains(Integer code) {
        TaskAbnormalReasonTeamEn[] taskAbnormalReasonTeamEn = TaskAbnormalReasonTeamEn.values();
        for (int i = 0; i < taskAbnormalReasonTeamEn.length; i++) {
            if (taskAbnormalReasonTeamEn[i].getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static TaskAbnormalReasonTeamEn getByCode(Integer code) {
        TaskAbnormalReasonTeamEn[] taskAbnormalReasonTeamEn = TaskAbnormalReasonTeamEn.values();
        for (int i = 0; i < taskAbnormalReasonTeamEn.length; i++) {
            if (taskAbnormalReasonTeamEn[i].getCode().equals(code)) {
                return taskAbnormalReasonTeamEn[i];
            }
        }
        return null;
    }

}
