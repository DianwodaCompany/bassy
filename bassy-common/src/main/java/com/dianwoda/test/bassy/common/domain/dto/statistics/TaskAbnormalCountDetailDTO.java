package com.dianwoda.test.bassy.common.domain.dto.statistics;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by gaoh on 2018/10/23.
 */
@Data
public class TaskAbnormalCountDetailDTO implements Serializable {

    private String teamId;
    private String teamName;
    private String typeId;
    private String typeName;
    private Integer count;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskAbnormalCountDetailDTO taskAbnormalCountDetailDTO = (TaskAbnormalCountDetailDTO) o;
        return this.getTypeId().equals(taskAbnormalCountDetailDTO.getTypeId());
    }

}
