package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

/**
 * Created by zcp on 2018/7/26.
 * Time always， fat thin man all miss.
 */
@Data
public class UpdateProgramStatusDTO {

    private Integer programId;

    private String status;

    private String reasonTeam;

    private String reasonType;

    private String reasonLevel;

    private String reasonDetail;

    private String person;
}
