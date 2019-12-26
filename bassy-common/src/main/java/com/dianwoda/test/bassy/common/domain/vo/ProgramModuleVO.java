package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

/**
 * Created by zcp on 2018/5/17.
 * Time always， fat thin man all miss.
 */
@Data
public class ProgramModuleVO {
    private Integer id;

    private String moduleName;

    private String description;

    private String parentCode;

    private Object persons;

    private Object coreNodes;

    //TODO 字段需要重新定义一下
    private Object requires;

    private String remark;

    private String readOnly;

    private String workId;

    private String pressingReason;

    private String isCheck;

    private String status;

    private String innerProjectType;

    private String isLoop;

    private Integer eachTaskWorkHour;

    private Integer dailyTaskNum;
}
