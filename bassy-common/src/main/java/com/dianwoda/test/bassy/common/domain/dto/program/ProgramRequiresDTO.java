package com.dianwoda.test.bassy.common.domain.dto.program;

import lombok.Data;

import java.util.List;

/**
 * @author zcp
 * @date 2019/3/27 19:33
 */
@Data
public class ProgramRequiresDTO {

    private Integer programId;

    private List<Integer> requires;
}
