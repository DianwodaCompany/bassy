package com.dianwoda.test.bassy.service;

import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.common.domain.dto.BBSParamDTO;
import com.dianwoda.test.bassy.common.domain.vo.BBSLogVO;
import com.dianwoda.test.bassy.common.domain.vo.BBSVO;
import com.dianwoda.test.bassy.common.domain.vo.BBSWeeklyVO;

import java.util.List;

/**
 * Created by LiuJJ on 2019/5/5.
 */
public interface BBSService {

    Pagination<BBSVO> getBBSPosts(BBSParamDTO paramDTO);

    Boolean editBBS(BBSParamDTO paramDTO);

    BBSVO getBBSById(int id, BBSParamDTO paramDTO) ;

    List<BBSVO> countPersCtrb();

    List<BBSLogVO> getBBSLog();

    Boolean likeBBS(BBSParamDTO paramDTO);

    List<BBSVO> countStudyTimes(BBSParamDTO paramDTO);

    boolean createWeekly();

    BBSWeeklyVO getWeeklyById(int id, BBSParamDTO dto);

}