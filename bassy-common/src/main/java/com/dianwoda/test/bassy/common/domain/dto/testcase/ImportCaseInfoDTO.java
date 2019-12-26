package com.dianwoda.test.bassy.common.domain.dto.testcase;

import lombok.Data;

import java.util.Date;

/**
 * @author zcp
 * @date 2019/5/28 17:34
 */
@Data
public class ImportCaseInfoDTO {
    /**
     * 用例id
     */
    private Integer id;

    /**
     * 所属产品
     */
    private String product;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 测试用例标题
     */
    private String title;

    /**
     * 测试用例类型
     */
    private String type;

    /**
     * 测试用例优先级
     */
    private Byte pri;

    /**
     * 测试用例前置条件
     */
    private String precondition;

    /**
     * 测试用例步骤
     */
    private String step;

    /**
     * 标签
     */
    private String label;


    /**
     * 最后修改人
     */
    private String lastEditedBy;

    /**
     * 最后修改时间
     */
    private Date lastEditedDate;

    /**
     * 用例版本
     */
    private Byte version;
}
