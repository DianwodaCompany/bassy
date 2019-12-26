package com.dianwoda.test.bassy.db.dao;

import com.dianwoda.test.bassy.db.entity.ProgramTaskLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProgramTaskLogMapperExt {
    List<Map<String,Object>> getTaskAbnormalList(@Param("sDate") String sDate, @Param("eDate") String eDate);
    List<Map<String,Object>> getNotInnerProAbnormalList(@Param("sDate") String sDate, @Param("eDate") String eDate);
    List<Map<String,Object>> getTaskAbnormalListByCondition(@Param("sDate") String sDate, @Param("eDate") String eDate, @Param("story") String story, @Param("reasonTeam") String reasonTeam, @Param("abnormalOwner") String abnormalOwner, @Param("abnormalType") Byte abnormalType);
    List<ProgramTaskLog> getTodayWorkActFromTaskLog(@Param("start") String start, @Param("end") String end, @Param("staffCode") String staffCode);
    List<Map<String,Object>> getAbnormalTotalScore(@Param("start") String start, @Param("end") String end);
    List<Map<String,Object>> getAbnormalTopReason(@Param("start") String start, @Param("end") String end);
    List<Map<String,Object>> getAbnormalTopOwner(@Param("start") String start, @Param("end") String end);
    List<Map<String,Object>> getAbnormalDetailByDepart(@Param("start") String start, @Param("end") String end, @Param("abnormalDepart") int abnormalDepart);
    List<Map<String,Object>> getStaffWorkLoad(@Param("start") String start, @Param("end") String end);
    List<Map<String,Object>> getDepartWorkLoad(@Param("start") String start, @Param("end") String end);
}