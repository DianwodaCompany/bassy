package com.dianwoda.test.bassy.service.common.impl;

import com.dianwoda.test.bassy.api.UpperProjectService;
import com.dianwoda.test.bassy.common.domain.UpperProjectBugDTO;
import com.dianwoda.test.bassy.common.domain.UpperProjectBugListParamDTO;
import com.dianwoda.test.bassy.common.domain.UpperProjectDTO;
import com.dianwoda.test.bassy.common.domain.UpperProjectRequireDTO;
import com.dianwoda.test.bassy.common.result.Pagination;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcp
 * 2019/12/21 下午12:22
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
@Component
public class DefaultUpperProjectServiceImpl implements UpperProjectService {


    @Override
    public List<UpperProjectDTO> getProjectsByTitle(String projectName) {
        return buildDefaultProject();
    }

    @Override
    public List<UpperProjectDTO> getProjectStoryList(String storyName) {
        return buildDefaultProject();
    }

    @Override
    public List<UpperProjectBugDTO> getBugListByStoryId(Integer storyId) {
        ArrayList<UpperProjectBugDTO> upperProjectBugDTOS = new ArrayList<>();
        UpperProjectBugDTO upperProjectBugDTO = new UpperProjectBugDTO();
        upperProjectBugDTO.setId(1);
        upperProjectBugDTO.setStory(1);
        upperProjectBugDTO.setProject(1);
        upperProjectBugDTO.setStatus("active");
        upperProjectBugDTOS.add(upperProjectBugDTO);
        return upperProjectBugDTOS;
    }

    private List<UpperProjectDTO> buildDefaultProject(){
        ArrayList<UpperProjectDTO> upperProjectDTOS = new ArrayList<>();
        UpperProjectDTO upperProjectDTO = new UpperProjectDTO();
        upperProjectDTO.setId(1);
        upperProjectDTO.setName("默认项目");
        upperProjectDTOS.add(upperProjectDTO);
        return upperProjectDTOS;
    }

}
