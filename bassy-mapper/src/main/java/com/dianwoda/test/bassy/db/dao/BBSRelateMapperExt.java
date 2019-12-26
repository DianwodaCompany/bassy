package com.dianwoda.test.bassy.db.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BBSRelateMapperExt {

    List<Map<String, Object>> getRelateBbsByid(@Param("bbsId") String bbsId);
}