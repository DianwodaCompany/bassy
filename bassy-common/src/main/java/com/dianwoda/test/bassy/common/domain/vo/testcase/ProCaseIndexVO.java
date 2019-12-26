package com.dianwoda.test.bassy.common.domain.vo.testcase;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by gaoh on 2019/8/14.
 */
@Data
public class ProCaseIndexVO {

    private Integer id;

    private String programName;

    private Date startTime;

    private Date endTime;

    private Date createTm;

    private List<ProCaseRequireInfoVO> requireInfoVOList;
}
