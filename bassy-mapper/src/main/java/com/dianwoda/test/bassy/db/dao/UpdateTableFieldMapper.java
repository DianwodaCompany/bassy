package com.dianwoda.test.bassy.db.dao;

import org.apache.ibatis.annotations.Param;

public interface UpdateTableFieldMapper {
    /**
     * 更新表字段
     * @param uSql 更新sql
     */
    void updateTableField(@Param("uSql") String uSql);
}
