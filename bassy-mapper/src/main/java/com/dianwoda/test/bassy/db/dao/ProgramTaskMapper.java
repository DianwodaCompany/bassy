package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramTask;
import com.dianwoda.test.bassy.db.entity.ProgramTaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProgramTaskMapper {
    long countByExample(ProgramTaskExample example);

    int deleteByExample(ProgramTaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProgramTask record);

    int insertSelective(ProgramTask record);

    List<ProgramTask> selectByExample(ProgramTaskExample example);

    ProgramTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProgramTask record, @Param("example") ProgramTaskExample example);

    int updateByExample(@Param("record") ProgramTask record, @Param("example") ProgramTaskExample example);

    int updateByPrimaryKeySelective(ProgramTask record);

    int updateByPrimaryKey(ProgramTask record);

    /**
     * 根据项目id获取项目日报中的任务列表
     * 不统计任务状态为 init close的任务
     * 返回项目下的其余所有任务
     * 通过需求id,项目阶段，任务来进行排序
     * @param programId 项目id
     * @return
     */
    List<ProgramTask> getProgramReportTask(Integer programId);
}