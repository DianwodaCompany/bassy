package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.db.entity.Dictionary;

import java.util.List;

/**
 * @author lichengkai
 * 2018 - 05 - 16 : 18:55
 * Copyright(c) for dianwoda
 */
public interface DictionaryService {

    /**
     * 根据字典分组获取节点
     *
     * @param group
     * @return
     */
    List<Dictionary> getDictByGroup(String group);

    /**
     * 获取所有项目阶段节点
     *
     * @return
     */
    List<Dictionary> getAllProcessNode();

    /**
     * 获取所有项目阶段节点
     *
     * @return
     */
    List<Dictionary> getAllDict();


    Dictionary getDictionaryByDictCode(String dictCode);

    Dictionary getDictionaryByGroupAndCode(String group, String dictCode);
}
