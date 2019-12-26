package com.dianwoda.test.bassy.common.domain.dto.testcase;

import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author zcp
 * @date 2019/5/15 16:08
 */
@Data
public class TestCaseParamDTO extends ParamDTO {

    /**
     * 测试用例id
     */
    private Integer id;

    /**
     * 测试用例标题
     */
    private String title;

    /**
     * 用例所属产品id
     */
    private Integer product;

    /**
     * 用例所属模块id
     */
    private Integer module;

    /**
     * 测试用例类型 0 功能用例， 1 接口用例
     */
    private String type;

    /**
     * 用例分类   0 基础用例  1  项目用例
     */
    private Byte family;

    /**
     * 测试用例最后更新人
     */
    private String lastEditedBy;

    /**
     * 标签
     */
    private ArrayList<LabelInfoDTO> label;

    /**
     * 用例所属需求
     */
    private Integer requireId;

    /**
     * 用例通过状态 0 未通过(包含 未执行 阻塞 存疑)， 1 已通过
     */
    private Boolean notPass;
}
