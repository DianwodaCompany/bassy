package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.AutoTest;

/**
 * @author zcp
 * @date 2019/2/15 22:08
 */
public interface AutoTestMapperExt {
    /**
     * 插入数据，返回主键
     * @param record
     * @return
     */
    int insert(AutoTest record);
}
