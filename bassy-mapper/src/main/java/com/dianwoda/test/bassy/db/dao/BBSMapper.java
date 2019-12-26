package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BBS;
import com.dianwoda.test.bassy.db.entity.BBSExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BBSMapper {
    long countByExample(BBSExample example);

    int deleteByExample(BBSExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BBS record);

    int insertSelective(BBS record);

    List<BBS> selectByExampleWithBLOBs(BBSExample example);

    List<BBS> selectByExample(BBSExample example);

    BBS selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BBS record, @Param("example") BBSExample example);

    int updateByExampleWithBLOBs(@Param("record") BBS record, @Param("example") BBSExample example);

    int updateByExample(@Param("record") BBS record, @Param("example") BBSExample example);

    int updateByPrimaryKeySelective(BBS record);

    int updateByPrimaryKeyWithBLOBs(BBS record);

    int updateByPrimaryKey(BBS record);
}