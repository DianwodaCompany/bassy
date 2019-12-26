package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

@Data
public class AbnormalReasonTypeDTO {
    private Integer reasonTypeCode;
    private String reasonTypeName;
    private Integer reasonTeamCode;
    private String reasonTeamName;
}
