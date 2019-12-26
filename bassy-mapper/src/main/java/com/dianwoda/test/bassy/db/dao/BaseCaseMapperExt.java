package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.BaseCase;

/**
 * @author zcp
 * @date 2019/5/24 16:27
 */
public interface BaseCaseMapperExt {

    int insert(BaseCase record);

    int insertFromBaseCase(BaseCase record);
}
