package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.WorkDailyReport;

import java.util.List;

/**
 * Created by gaoh on 2019/1/15.
 */
public interface WorkDailyReportMapperExt {

    int insertBatch(List<WorkDailyReport> record);

}
