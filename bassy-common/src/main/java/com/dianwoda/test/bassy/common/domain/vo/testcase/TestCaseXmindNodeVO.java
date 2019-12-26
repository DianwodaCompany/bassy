package com.dianwoda.test.bassy.common.domain.vo.testcase;

import com.dianwoda.test.bassy.common.domain.dto.testcase.BaseCaseLabelDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * xmind测试用例节点结构
 *
 * @author zcp
 * @date 2019/6/20 15:04
 */
@Data
public class TestCaseXmindNodeVO {


    private String id;

    private String parentId;

    private Integer productId;

    private Integer moduleId;

    private Integer requireId;

    private Integer parentCaseId;

    private Integer caseId;

    /**
     * 步骤id
     */
    private Integer stepId;
    /**
     * 步骤排序id
     */
    private Long stepStepId;

    /**
     * 标签id集合
     */
    private ArrayList<BaseCaseLabelDTO> labels;

    private String text;

    private String type;

    private Byte version;

    private String lastEditedBy;

    private Byte pass;

    private boolean showChildren;

    private boolean hasEdited;

    private List<TestCaseXmindNodeVO> children;

}
