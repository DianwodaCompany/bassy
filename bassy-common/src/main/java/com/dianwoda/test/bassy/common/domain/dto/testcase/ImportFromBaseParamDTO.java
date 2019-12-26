package com.dianwoda.test.bassy.common.domain.dto.testcase;

import lombok.Data;

import java.util.List;

/**
 * Created by gaoh on 2019/8/19.
 */
@Data
public class ImportFromBaseParamDTO {

    private List<Integer> baseIds;
    private String lastEditedBy;
    private Integer require;
    private String lastEditedDate;
}
