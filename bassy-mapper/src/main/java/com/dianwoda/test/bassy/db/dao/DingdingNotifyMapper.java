package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.DingdingNotify;
import com.dianwoda.test.bassy.db.entity.DingdingNotifyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DingdingNotifyMapper {
    long countByExample(DingdingNotifyExample example);

    int deleteByExample(DingdingNotifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DingdingNotify record);

    int insertSelective(DingdingNotify record);

    List<DingdingNotify> selectByExample(DingdingNotifyExample example);

    DingdingNotify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DingdingNotify record, @Param("example") DingdingNotifyExample example);

    int updateByExample(@Param("record") DingdingNotify record, @Param("example") DingdingNotifyExample example);

    int updateByPrimaryKeySelective(DingdingNotify record);

    int updateByPrimaryKey(DingdingNotify record);
}