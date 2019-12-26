package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.AutoTest;
import com.dianwoda.test.bassy.db.entity.AutoTestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AutoTestMapper {
    long countByExample(AutoTestExample example);

    int deleteByExample(AutoTestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AutoTest record);

    int insertSelective(AutoTest record);

    List<AutoTest> selectByExampleWithBLOBs(AutoTestExample example);

    List<AutoTest> selectByExample(AutoTestExample example);

    AutoTest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AutoTest record, @Param("example") AutoTestExample example);

    int updateByExampleWithBLOBs(@Param("record") AutoTest record, @Param("example") AutoTestExample example);

    int updateByExample(@Param("record") AutoTest record, @Param("example") AutoTestExample example);

    int updateByPrimaryKeySelective(AutoTest record);

    int updateByPrimaryKeyWithBLOBs(AutoTest record);

    int updateByPrimaryKey(AutoTest record);
}