package com.dianwoda.test.bassy.common.domain.dto.program;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 2018/9/11.
 * Time always， fat thin man all miss.
 *
 * @author zcp
 */
@Data
public class ProgramReportDTO {

    private Integer programId;

    private String status;

    private Integer percent;

    private String programExplain;

    private String bugInfo;

    private ArrayList<Integer> taskInfo;

    private String modifier;

    private Date modifyTm;
}
