package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class HeapWorkHourDTO implements Serializable {

    private String date;

    private String code;

    private String name; //员工名

    private Float hour; //每天分配的小时

    private HeapWorkRelateTaskDTO heapWorkRelateTaskDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeapWorkHourDTO heapWorkHourDTO = (HeapWorkHourDTO) o;
        return this.getDate().equals(heapWorkHourDTO.getDate()) && this.getName().equals(heapWorkHourDTO.getName());
    }
}
