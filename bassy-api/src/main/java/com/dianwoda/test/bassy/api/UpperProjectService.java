package com.dianwoda.test.bassy.api;

import com.dianwoda.test.bassy.common.domain.UpperProjectBugDTO;
import com.dianwoda.test.bassy.common.domain.UpperProjectBugListParamDTO;
import com.dianwoda.test.bassy.common.domain.UpperProjectDTO;
import com.dianwoda.test.bassy.common.domain.UpperProjectRequireDTO;

import java.util.List;

/**
 * @author zcp
 * 2019/12/21 下午12:06
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public interface UpperProjectService {

    /**
     * 根据名称模糊匹配禅道项目
     * @param projectName
     * @return
     */
    List<UpperProjectDTO> getProjectsByTitle(String projectName);

    /**
     * 根据需求名模糊获取禅道项目列表
     */
    List<UpperProjectDTO> getProjectStoryList(String storyName);

    /**
     * 根据禅道需求id获取禅道Bug列表
     */
    List<UpperProjectBugDTO> getBugListByStoryId(Integer storyId);

}
