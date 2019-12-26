package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramModule;

import java.util.List;

/**
 * @author lichengkai
 * 2018 - 05 - 02 : 11:37
 * Copyright(c) for dianwoda
 */
public interface ProgramModuleMapperExt extends ProgramModuleMapper {

    List<ProgramModule> getModuleList();
}
