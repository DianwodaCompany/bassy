package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BaseCaseLabel;
import com.dianwoda.test.bassy.db.entity.BaseCaseLabelExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseCaseLabelMapper {
    long countByExample(BaseCaseLabelExample example);

    int deleteByExample(BaseCaseLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseCaseLabel record);

    int insertSelective(BaseCaseLabel record);

    List<BaseCaseLabel> selectByExample(BaseCaseLabelExample example);

    BaseCaseLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseCaseLabel record, @Param("example") BaseCaseLabelExample example);

    int updateByExample(@Param("record") BaseCaseLabel record, @Param("example") BaseCaseLabelExample example);

    int updateByPrimaryKeySelective(BaseCaseLabel record);

    int updateByPrimaryKey(BaseCaseLabel record);
}