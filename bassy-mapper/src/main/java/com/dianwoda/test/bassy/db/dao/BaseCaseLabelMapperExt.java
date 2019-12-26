package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.common.domain.dto.testcase.BaseCaseLabelDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author zcp
 * @date 2019/5/16 10:07
 */
public interface BaseCaseLabelMapperExt {

    /**
     * 获取用例的标签信息
     * @return
     */
    List<BaseCaseLabelDTO> getTestCaseLabelInfo(Integer caseId);

    int insertFromBaseLabel(@Param("baseCaseId") Integer baseCaseId, @Param("caseId") Integer caseId, @Param("creator") String creator, @Param("creatorTm") String creatorTm);

    /**
     * 删除测试用例指定类型的标签
     * @param type
     * @return
     */
    int deleteByLabelType(@Param("caseId") Integer caseId, @Param("type") String type);
}
