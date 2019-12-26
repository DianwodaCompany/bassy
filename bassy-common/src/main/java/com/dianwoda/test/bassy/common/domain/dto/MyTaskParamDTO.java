package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;


/**
 * Created by zcp on 2018/8/29.
 * Time always， fat thin man all miss.
 * @author zcp
 */
@Data
public class MyTaskParamDTO extends ParamDTO{

    private String tester;

    private Boolean todayTask;

    private Boolean overTimeTask;

    private String taskStatus;

    private String taskCode;

    private String programName;
}
