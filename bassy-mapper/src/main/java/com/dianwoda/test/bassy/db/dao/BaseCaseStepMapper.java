package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BaseCaseStep;
import com.dianwoda.test.bassy.db.entity.BaseCaseStepExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseCaseStepMapper {
    long countByExample(BaseCaseStepExample example);

    int deleteByExample(BaseCaseStepExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseCaseStep record);

    int insertSelective(BaseCaseStep record);

    List<BaseCaseStep> selectByExample(BaseCaseStepExample example);

    BaseCaseStep selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseCaseStep record, @Param("example") BaseCaseStepExample example);

    int updateByExample(@Param("record") BaseCaseStep record, @Param("example") BaseCaseStepExample example);

    int updateByPrimaryKeySelective(BaseCaseStep record);

    int updateByPrimaryKey(BaseCaseStep record);
}