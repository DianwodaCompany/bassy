package com.dianwoda.test.bassy.common.domain.dto.task;

import com.dianwoda.test.bassy.common.domain.dto.ProgramTaskDTO;
import lombok.Data;

@Data
public class CalendarTaskEventDTO {

    private Integer id;
    private String title;
    private String status;
    private String start;
    private String end;
    private String programName;
    private ProgramTaskDTO programTask;
}
