package com.dianwoda.test.bassy.common.domain.vo.testcase;

import lombok.Data;

/**
 * @author zcp
 * @date 2019/7/9 11:18
 */
@Data
public class ProductSubModuleVO {

    private Integer parentModuleId;

    private String parentModuleName;

    private String parentModuleDefender;

    private Integer moduleId;

    private String moduleName;

    private String moduleDefender;

}
