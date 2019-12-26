package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class HeapWorkRelateTaskDTO implements Serializable {

    private String date;
    private String name; //员工名
    private List<HeapWorkTaskDTO> heapWorkTaskDTOList;

}
