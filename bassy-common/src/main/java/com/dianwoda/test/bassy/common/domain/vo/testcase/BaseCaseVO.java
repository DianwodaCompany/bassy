package com.dianwoda.test.bassy.common.domain.vo.testcase;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zcp
 * @date 2019/5/15 16:29
 */
@Data
public class BaseCaseVO {

    /**
     * 用例id
     */
    private Integer id;

    /**
     * 所属产品
     */
    private String productName;

    /**
     * 所属模块
     */
    private String moduleName;

    /**
     * 测试用例标题
     */
    private String title;

    /**
     * 测试用例类型
     */
    private String type;


    /**
     * 基础标签
     */
    private List<String> baseLabel;

    /**
     * 系统生成标签
     */
    private List<String> systemLabel;

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

    /**
     * 用例优先级
     */
    private Byte pri;

    /**
     * 是否合并基线
     */
    private Byte pushed;

    /**
     * 关联的基线用例id
     */
    private Integer parentCase;

    /**
     * 关联的基线用例的版本
     */
    private Byte baseVersion;
}
