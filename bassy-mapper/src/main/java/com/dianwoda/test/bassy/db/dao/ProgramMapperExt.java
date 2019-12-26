package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.Program;
import com.dianwoda.test.bassy.db.entity.ProgramNameDTO;

import java.util.List;

/**
 * @author zcp
 * @date 2019/3/26 11:09
 */
public interface ProgramMapperExt {

    /**
     * 插入项目，返回主键id
     * @param record
     * @return
     */
    int insert(Program record);

    /**
     * 关键字查询项目
     * @param keyWord
     * @return
     */
    List<ProgramNameDTO> selectProgramNameByKeyword(String keyWord);

    /**
     * 用例没有全部合基线的项目
     * @param programName
     * @return
     */
    List<Program> selectUnPushedProgram(String programName);

    /**
     * 用例全部合基线的项目
     * @param programName
     * @return
     */
    List<Program> selectAllPushedProgram(String programName);
}
