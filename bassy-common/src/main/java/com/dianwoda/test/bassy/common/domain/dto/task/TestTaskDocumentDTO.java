package com.dianwoda.test.bassy.common.domain.dto.task;

import lombok.Data;

import java.io.InputStream;

/**
 * Created by zcp on 2018/8/27.
 * Time always， fat thin man all miss.
 */
@Data
public class TestTaskDocumentDTO {

    private String fileName;

    private InputStream inputStream;
}
