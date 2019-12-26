package com.dianwoda.test.bassy.db.dao;


import com.dianwoda.test.bassy.db.entity.BBSWeekly;
import com.dianwoda.test.bassy.db.entity.BBSWeeklyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSWeeklyMapper {
    long countByExample(BBSWeeklyExample example);

    int deleteByExample(BBSWeeklyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSWeekly record);

    int insertSelective(BBSWeekly record);

    List<BBSWeekly> selectByExample(BBSWeeklyExample example);

    BBSWeekly selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSWeekly record, @Param("example") BBSWeeklyExample example);

    int updateByExample(@Param("record") BBSWeekly record, @Param("example") BBSWeeklyExample example);

    int updateByPrimaryKeySelective(BBSWeekly record);

    int updateByPrimaryKey(BBSWeekly record);
}