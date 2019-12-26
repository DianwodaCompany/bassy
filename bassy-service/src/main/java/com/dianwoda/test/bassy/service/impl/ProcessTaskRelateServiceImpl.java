package com.dianwoda.test.bassy.service.impl;

import com.dianwoda.test.bassy.common.enums.ProcessTaskRelateEn;
import com.dianwoda.test.bassy.db.dao.ProcessTaskRelateMapper;
import com.dianwoda.test.bassy.db.entity.ProcessTaskRelate;
import com.dianwoda.test.bassy.db.entity.ProcessTaskRelateExample;
import com.dianwoda.test.bassy.service.ProcessTaskRelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zcp on 2018/5/17.
 * Time always， fat thin man all miss.
 */
@Service
public class ProcessTaskRelateServiceImpl implements ProcessTaskRelateService{

    @Autowired
    ProcessTaskRelateMapper processTaskRelateMapper;

    @Override
    public ProcessTaskRelate getAllTaskByNode(String processCode) {
        ProcessTaskRelateExample example = new ProcessTaskRelateExample();
        ProcessTaskRelateExample.Criteria criteria = example.createCriteria();
        criteria.andProcessCodeEqualTo(processCode);
        criteria.andStatusEqualTo(ProcessTaskRelateEn.ENABLE.getCode());
        return processTaskRelateMapper.selectByExample(example).get(0);
    }

    @Override
    public List<ProcessTaskRelate> getAllProcessTask() {
        ProcessTaskRelateExample example = new ProcessTaskRelateExample();
        ProcessTaskRelateExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(ProcessTaskRelateEn.ENABLE.getCode());
        return processTaskRelateMapper.selectByExample(example);
    }
}
