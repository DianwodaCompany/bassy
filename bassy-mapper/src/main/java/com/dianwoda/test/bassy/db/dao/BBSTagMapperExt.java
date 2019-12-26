package com.dianwoda.test.bassy.db.dao;


import com.dianwoda.test.bassy.db.entity.BBSTag;

import java.util.List;

public interface BBSTagMapperExt {

    int insertBatch(List<BBSTag> record);

}
