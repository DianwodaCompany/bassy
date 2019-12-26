package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.LabelInfo;
import com.dianwoda.test.bassy.db.entity.LabelInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelInfoMapper {
    long countByExample(LabelInfoExample example);

    int deleteByExample(LabelInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LabelInfo record);

    int insertSelective(LabelInfo record);

    List<LabelInfo> selectByExample(LabelInfoExample example);

    LabelInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LabelInfo record, @Param("example") LabelInfoExample example);

    int updateByExample(@Param("record") LabelInfo record, @Param("example") LabelInfoExample example);

    int updateByPrimaryKeySelective(LabelInfo record);

    int updateByPrimaryKey(LabelInfo record);
}