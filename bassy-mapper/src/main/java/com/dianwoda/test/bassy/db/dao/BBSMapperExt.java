package com.dianwoda.test.bassy.db.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BBSMapperExt {

    List<Map<String, Object>> selectByCondition(@Param("type") Byte type, @Param("authorCode") String authorCode, @Param("keyword") String keyword, @Param("sort") String sort, @Param("noteType") Byte noteType);

    List<Map<String, Object>> countPersCtrb();

    int likeStatus(@Param("staffCode") String staffCode, @Param("bbsId") String bbsId);

    int updateLike(@Param("id") int id, @Param("likeStatus") boolean likeStatus);

    List<Map<String, Object>> countStudyTimes(@Param("week") String week);

    int countStudyTimes2(@Param("week") String week);

}
