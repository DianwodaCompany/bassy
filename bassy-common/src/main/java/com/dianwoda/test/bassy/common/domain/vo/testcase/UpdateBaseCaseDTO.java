package com.dianwoda.test.bassy.common.domain.vo.testcase;

import com.dianwoda.test.bassy.common.domain.dto.testcase.BaseCaseLabelDTO;
import com.dianwoda.test.bassy.common.domain.dto.testcase.BaseCaseStepDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author zcp
 * @date 2019/5/22 15:56
 */
@Data
public class UpdateBaseCaseDTO {

    /**
     * 用例id
     */
    private Integer id;

    /**
     * 所属产品
     */
    private Integer product;

    /**
     * 所属模块
     */
    private Integer module;

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
    private List<BaseCaseStepDTO> step;

    /**
     * 标签
     */
    private List<BaseCaseLabelDTO> label;


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
     * 用例分类   0 基础用例  1  项目用例
     */
    private Byte family;

    /**
     * 用例所属需求
     */
    private Integer requireId;

    /**
     * 是否属于合并用例操作
     */
    private Boolean isMerge;

    /**
     * 用例是否通过
     */
    private Byte pass;
}
