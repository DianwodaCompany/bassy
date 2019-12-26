package com.dianwoda.test.bassy.common.domain.vo;

import lombok.Data;

/**
 * Created by zcp on 2018/5/17.
 * Time always， fat thin man all miss.
 */
@Data
public class ProcessModuleVO {

    private Integer id;

    private String moduleName;

    private String description;

    private String programModule;

    private Object processNode;

    private String readOnly;

    private String status;

    private ProgramModuleVO programModuleVo;
}
