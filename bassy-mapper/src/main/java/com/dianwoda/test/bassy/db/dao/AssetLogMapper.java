package com.dianwoda.test.bassy.db.dao;


import com.dianwoda.test.bassy.db.entity.AssetLog;
import com.dianwoda.test.bassy.db.entity.AssetLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetLogMapper {
    long countByExample(AssetLogExample example);

    int deleteByExample(AssetLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AssetLog record);

    int insertSelective(AssetLog record);

    List<AssetLog> selectByExample(AssetLogExample example);

    AssetLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AssetLog record, @Param("example") AssetLogExample example);

    int updateByExample(@Param("record") AssetLog record, @Param("example") AssetLogExample example);

    int updateByPrimaryKeySelective(AssetLog record);

    int updateByPrimaryKey(AssetLog record);
}