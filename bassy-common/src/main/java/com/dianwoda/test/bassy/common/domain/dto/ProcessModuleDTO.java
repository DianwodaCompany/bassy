package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

/**
 * Created by zcp on 2018/5/7.
 * Time always， fat thin man all miss.
 */
@Data
public class ProcessModuleDTO {
    private Integer id;

    private String moduleName;

    private String description;

    private String programModule;

    private String processNode;

    private String readOnly;

    private String status;

    private String creator;

    private String modifyer;
}
