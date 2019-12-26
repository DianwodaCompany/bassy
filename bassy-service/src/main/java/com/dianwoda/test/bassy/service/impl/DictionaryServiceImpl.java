package com.dianwoda.test.bassy.service.impl;

import com.dianwoda.test.bassy.common.enums.DictionaryEn;
import com.dianwoda.test.bassy.common.enums.ModuleStatusEn;
import com.dianwoda.test.bassy.db.dao.DictionaryMapper;
import com.dianwoda.test.bassy.db.entity.Dictionary;
import com.dianwoda.test.bassy.db.entity.DictionaryExample;
import com.dianwoda.test.bassy.service.DictionaryService;
import com.dianwoda.test.bassy.service.common.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 18:56
 * Copyright(c) for dianwoda
 */
@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public List<Dictionary> getDictByGroup(String group) {
        DictionaryExample example = new DictionaryExample();
        DictionaryExample.Criteria criteria = example.createCriteria();
        criteria.andDictGroupEqualTo(group);
        criteria.andStatusEqualTo(ModuleStatusEn.ENABLE.getCode());
        return dictionaryMapper.selectByExample(example);
    }

    @Override
    public List<Dictionary> getAllProcessNode() {
        DictionaryExample dictionaryExample = new DictionaryExample();
        DictionaryExample.Criteria criteria = dictionaryExample.createCriteria();
        criteria.andDictGroupEqualTo(String.valueOf(DictionaryEn.PROJECT_NODE.getEname()));
        criteria.andStatusEqualTo(ModuleStatusEn.ENABLE.getCode());
        return dictionaryMapper.selectByExample(dictionaryExample);
    }

    @Override
    public List<Dictionary> getAllDict() {
        DictionaryExample dictionaryExample = new DictionaryExample();
        return dictionaryMapper.selectByExample(dictionaryExample);
    }

    @Override
    public Dictionary getDictionaryByDictCode(String dictCode) {
        DictionaryExample dictionaryExample = new DictionaryExample();
        DictionaryExample.Criteria criteria = dictionaryExample.createCriteria();
        criteria.andDictCodeEqualTo(dictCode);
        criteria.andStatusEqualTo(ModuleStatusEn.ENABLE.getCode());
        List<Dictionary> dictionaries = dictionaryMapper.selectByExample(dictionaryExample);
        if (dictionaries.size() <= 0) {
            return null;
        }
        return dictionaries.get(0);
    }

    @Override
    public Dictionary getDictionaryByGroupAndCode(String group, String dictCode) {
        String key = this.getClass().getSimpleName()+"_"+group+"_"+dictCode;
        Object dic = redisService.get(key);
        if(dic != null){
            log.info("get {} from redis with key {}",dic,key);
            return (Dictionary) dic;
        }
        List<Dictionary> dictionaryVoList = getDictByGroup(group);
        for (Dictionary dictionary : dictionaryVoList) {
            if (dictCode.equals(dictionary.getDictCode())) {
                redisService.set(key,dictionary,(long)3600*24);
                log.info("set {} to redis with key {}",dictionary,key);
                return dictionary;
            }
        }
        return null;
    }
}
