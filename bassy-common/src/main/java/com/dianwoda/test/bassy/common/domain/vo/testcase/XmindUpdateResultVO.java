package com.dianwoda.test.bassy.common.domain.vo.testcase;


import com.dianwoda.test.bassy.common.domain.dto.testcase.ConflictCaseDTO;

import java.util.List;

public class XmindUpdateResultVO {

    private List<ConflictCaseDTO> conflictCases;

    private TestCaseXmindNodeVO testCaseXmindNodeVO;

    public List<ConflictCaseDTO> getConflictCases() {
        return conflictCases;
    }

    public void setConflictCases(List<ConflictCaseDTO> conflictCases) {
        this.conflictCases = conflictCases;
    }

    public TestCaseXmindNodeVO getTestCaseXmindNodeVO() {
        return testCaseXmindNodeVO;
    }

    public void setTestCaseXmindNodeVO(TestCaseXmindNodeVO testCaseXmindNodeVO) {
        this.testCaseXmindNodeVO = testCaseXmindNodeVO;
    }
}
