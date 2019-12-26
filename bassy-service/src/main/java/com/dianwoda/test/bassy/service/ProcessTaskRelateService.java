package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.db.entity.ProcessTaskRelate;

import java.util.List;

/**
 * Created by zcp on 2018/5/17.
 * Time always， fat thin man all miss.
 */
public interface ProcessTaskRelateService {

    /**
     * 根据流程节点获取对应所有可关联任务
     * @param processCode
     * @return
     */
    ProcessTaskRelate getAllTaskByNode(String processCode);

    /**
     * 获取所有阶段的关联任务
     * @return
     */
    List<ProcessTaskRelate> getAllProcessTask();
}
