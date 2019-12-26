package com.dianwoda.test.bassy.common.domain.dto;

import lombok.Data;

/**
 * Created by zcp on 2018/8/7.
 * Time always， fat thin man all miss.
 */
@Data
public class UploadParamDTO {

    private String docunmentName;

    private String programId;

    private String taskId;

    private String filePath;

    private String fileType;
}
