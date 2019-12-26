package com.dianwoda.test.bassy.common.domain.dto.testcase;

import com.dianwoda.test.bassy.common.domain.vo.testcase.BaseCaseDetailVO;
import lombok.Data;

/**
 * @author zcp
 * @date 2019/8/16 14:32
 */
@Data
public class ConflictCaseDTO {

    private Integer caseId;

    private String caseTitle;

    private BaseCaseDetailVO baseCase;

    private BaseCaseDetailVO programCase;
}
