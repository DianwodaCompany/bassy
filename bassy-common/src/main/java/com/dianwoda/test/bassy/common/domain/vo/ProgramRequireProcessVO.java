package com.dianwoda.test.bassy.common.domain.vo;

import com.dianwoda.test.bassy.common.domain.dto.program.ProgramBugInfoDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.ProgramStageDTO;
import com.dianwoda.test.bassy.common.domain.dto.program.ProgramTaskPercentDTO;
import lombok.Data;

import java.util.List;

/**
 * @author zcp
 * @date 2019/3/28 17:12
 */
@Data
public class ProgramRequireProcessVO {

    /**
     * 需求任务进度
     */
    private List<ProgramTaskPercentDTO> requireTaskProcess;

    /**
     * 项目bug信息
     */
    private List<ProgramBugInfoDTO> programBugInfo;

    /**
     * 项目阶段信息
     */
    private ProgramStageDTO programStage;

}
