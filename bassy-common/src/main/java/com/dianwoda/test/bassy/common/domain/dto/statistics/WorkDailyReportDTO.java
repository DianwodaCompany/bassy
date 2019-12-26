package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.util.List;

/**
 * Created by gaoh on 2019/1/14.
 */
@Data
public class WorkDailyReportDTO {

    private String date;

    private String code;

    private String name; //员工名

    private List<WorkReportDTO> todayExp;

    private List<WorkReportDTO> todayAct;

    private List<WorkReportDTO> tomorrow;

}
