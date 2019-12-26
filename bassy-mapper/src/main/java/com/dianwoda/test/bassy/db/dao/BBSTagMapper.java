package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BBSTag;
import com.dianwoda.test.bassy.db.entity.BBSTagExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSTagMapper {
    long countByExample(BBSTagExample example);

    int deleteByExample(BBSTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSTag record);

    int insertSelective(BBSTag record);

    List<BBSTag> selectByExample(BBSTagExample example);

    BBSTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSTag record, @Param("example") BBSTagExample example);

    int updateByExample(@Param("record") BBSTag record, @Param("example") BBSTagExample example);

    int updateByPrimaryKeySelective(BBSTag record);

    int updateByPrimaryKey(BBSTag record);
}