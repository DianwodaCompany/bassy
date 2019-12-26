package com.dianwoda.test.bassy.common.domain.dto.testcase;

import com.dianwoda.test.bassy.common.domain.vo.testcase.UpdateBaseCaseDTO;

/**
 * @author zcp
 * @date 2019/8/16 16:49
 */
public class SolveCaseResultDTO extends UpdateBaseCaseDTO {

    private Integer baseCaseId;

    private Integer programCaseId;

    public Integer getBaseCaseId() {
        return baseCaseId;
    }

    public void setBaseCaseId(Integer baseCaseId) {
        this.baseCaseId = baseCaseId;
    }

    public Integer getProgramCaseId() {
        return programCaseId;
    }

    public void setProgramCaseId(Integer programCaseId) {
        this.programCaseId = programCaseId;
    }
}
