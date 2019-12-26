package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BBSLog;
import com.dianwoda.test.bassy.db.entity.BBSLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSLogMapper {
    long countByExample(BBSLogExample example);

    int deleteByExample(BBSLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBSLog record);

    int insertSelective(BBSLog record);

    List<BBSLog> selectByExample(BBSLogExample example);

    BBSLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBSLog record, @Param("example") BBSLogExample example);

    int updateByExample(@Param("record") BBSLog record, @Param("example") BBSLogExample example);

    int updateByPrimaryKeySelective(BBSLog record);

    int updateByPrimaryKey(BBSLog record);
}