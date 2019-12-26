package com.dianwoda.test.bassy.common.domain.dto.testcase;

import lombok.Data;

@Data
public class XMindTestCaseEditedInfoDTO {

    private Integer caseId;

    private Boolean isEdited;

    private Boolean isBaseCase;

    private Boolean hasUpVersion;

    private Byte version;
}
