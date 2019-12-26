package com.dianwoda.test.bassy.db.dao;


import com.dianwoda.test.bassy.db.entity.BaseCaseStep;
import org.apache.ibatis.annotations.Param;

public interface BaseCaseStepMapperExt {

    int insertFromBaseStep(@Param("baseCaseId") Integer baseCaseId, @Param("caseId") Integer caseId);

    int insert(BaseCaseStep record);

    int insertSelective(BaseCaseStep record);
}