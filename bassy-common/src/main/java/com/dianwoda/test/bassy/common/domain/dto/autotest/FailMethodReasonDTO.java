package com.dianwoda.test.bassy.common.domain.dto.autotest;

import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zcp on 2018/12/11.
 * Time always， fat thin man all miss.
 */
@Data
public class FailMethodReasonDTO extends ParamDTO {

    private Boolean onlyToday;

    private ArrayList<Integer> ids;

    private Integer id;

    private Integer autoTestId;

    private String testName;

    private String testType;

    private Integer failReason;

    private ArrayList<Date> startEndTm;

    private String remark;

    /**
     * 测试用例维护人
     */
    private String tester;
}
