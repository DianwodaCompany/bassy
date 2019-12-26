package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.common.domain.dto.program.ProgramTaskPercentDTO;
import com.dianwoda.test.bassy.db.entity.ProgramTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProgramTaskMapperExt {
    List<Map<String, Object>> getHeapData(@Param("departId") Integer departId, @Param("sDate") String sDate, @Param("eDate") String eDate);

    List<ProgramTask> selectByCondition(@Param("searchKey") String searchKey, @Param("startTm") String startTm, @Param("endTm") String endTm, @Param("status") String status, @Param("tester") String tester, @Param("process") String process);

    /**
     * 根据项目id获取项目日报中的任务列表
     * 不统计任务状态为 init close的任务
     * 返回项目下的其余所有任务
     * 通过需求id,项目阶段，任务来进行排序
     *
     * @param programId 项目id
     * @return
     */
    List<ProgramTask> getProgramReportTask(Integer programId);

    /**
     * 获取项目去修进度
     * @param programId
     * @param requires
     * @return
     */
    List<ProgramTaskPercentDTO> getProgramTaskProcessByrRequire(@Param("programId") Integer programId, @Param("requires") List requires);


    /**
     * 获取项目所有需求
     * @param programId
     * @return
     */
    List<Map<String,Object>> getPlanRequires(Integer programId);

    /**
     * 测试计划的需求视图
     * @param programId
     * @param requireId
     * @return
     */
    List<Map<String, Object>> getPlanRequireBoard(@Param("programId") Integer programId, @Param("requireId") Integer requireId);
}
