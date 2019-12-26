package com.dianwoda.test.bassy.common.enums;

import com.dianwoda.test.bassy.common.domain.dto.statistics.AbnormalReasonTypeDTO;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public enum TaskAbnormalReasonTypeEn {

    //code=[100,199]区间留给这类
    STORY_CHANGE(1,"需求变更",1, "产品原因"),
    STORY_PAUSE(2,"需求暂停",1, "产品原因"),
    STORY_DROP(3,"需求废弃",1, "产品原因"),
    PRODUCT_ACCEPT_TIMEOUT(4,"产品验收时效",1, "产品原因"),
    UI_ACCEPT_TIMEOUT(5,"UI/交互验收时效",1, "产品原因"),
    STORY_PRIORITY_CHANGE(100,"需求优先级变更",1, "产品原因"),

    //code=[200,299]区间留给这类
    SUBMIT_TEST_TIMEOUT(6,"提测延期",2, "开发原因"),
    SMOKE_TEST_FAILED(7,"冒烟不通过",2, "开发原因"),
    BUG_FIXED_TIMEOUT(8,"bug修复时效超时",2, "开发原因"),
    ADD_REQUIREMENT(9,"加内部优化需求",2, "开发原因"),
    CODE_MERGE_TIMEOUT(10,"代码合并时效超时",2, "开发原因"),
    DESIGN_CHANGE(11,"设计实现变更",2, "开发原因"),
    CODE_REVIEW_TIMEOUT(12,"REVIEW超时",2, "开发原因"),
    RELEASE_ISSUES(40,"发布操作问题",2, "开发原因"),
    BROKEN_TEST_RULES(200,"未按流程约定提测",2, "开发原因"),

    //code=[300,399]区间留给这类
    TEST_CASE_TIMEOUT(13,"测分编写滞后",3, "测试原因"),
    BUG_ACCEPT_TIMEOUT(14,"bug验收不及时",3, "测试原因"),
    TEST_EXECUTE_TIMEOUT(15,"测试执行滞后",3, "测试原因"),

    //code=[400,499]区间留给这类
    SERVER_ISSUE(16,"服务器问题", 4, "运维原因"),
    NETWORK_ISSUE(17,"网络问题", 4, "运维原因"),

    //code=[500,599]区间留给这类
    SCRIPT_EXECUTE_TIMEOUT(18,"脚本配合执行超时",5, "DBA原因"),

    //code=[20,99]区间留给这类
    BUSINESS_COORDINATE(19,"业务方配合问题",9, "其他原因"),
    THIRD_REASON(20,"第三方原因",9, "其他原因"),
    PM_SCHEDULING_REASON(21,"PM排期问题",9, "其他原因"),
    WRITE_NOT_RIGHT(22,"填写有误",9, "其他原因"),
    DEV_FINISH_ADVANCE(23,"开发提前提测",9, "其他原因");

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

    public Integer getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(Integer teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    private Integer teamCode;
    private String teamName;

    public static List<AbnormalReasonTypeDTO> getAll() {
        List<AbnormalReasonTypeDTO> abnormalReasonTypeDTOList = new ArrayList<>();
        TaskAbnormalReasonTypeEn[] taskAbnormalReasonTypeEns = TaskAbnormalReasonTypeEn.values();
        for (int i = 0; i < taskAbnormalReasonTypeEns.length; i++) {
            AbnormalReasonTypeDTO abnormalReasonTypeDTO = new AbnormalReasonTypeDTO();
            abnormalReasonTypeDTO.setReasonTypeCode(taskAbnormalReasonTypeEns[i].getCode());
            abnormalReasonTypeDTO.setReasonTypeName(taskAbnormalReasonTypeEns[i].getName());
            abnormalReasonTypeDTO.setReasonTeamCode(taskAbnormalReasonTypeEns[i].getTeamCode());
            abnormalReasonTypeDTO.setReasonTeamName(taskAbnormalReasonTypeEns[i].getTeamName());
            abnormalReasonTypeDTOList.add(abnormalReasonTypeDTO);
        }
        return abnormalReasonTypeDTOList;
    }

    public static Boolean contains(Integer code) {
        TaskAbnormalReasonTypeEn[] taskAbnormalReasonTypeEns = TaskAbnormalReasonTypeEn.values();
        for (int i = 0; i < taskAbnormalReasonTypeEns.length; i++) {
            if (taskAbnormalReasonTypeEns[i].getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static TaskAbnormalReasonTypeEn getByCode(Integer code) {
        TaskAbnormalReasonTypeEn[] taskAbnormalReasonTypeEns = TaskAbnormalReasonTypeEn.values();
        for (int i = 0; i < taskAbnormalReasonTypeEns.length; i++) {
            if (taskAbnormalReasonTypeEns[i].getCode().equals(code)) {
                return taskAbnormalReasonTypeEns[i];
            }
        }
        return null;
    }
}
