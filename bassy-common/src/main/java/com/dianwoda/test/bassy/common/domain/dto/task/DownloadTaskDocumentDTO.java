package com.dianwoda.test.bassy.common.domain.dto.task;

import lombok.Data;

/**
 * Created on 2018/8/31.
 * Time always， fat thin man all miss.
 *
 * @author zcp
 */
@Data
public class DownloadTaskDocumentDTO {

    private String programId;

    private String documentCode;

    private String fileName;

    private String localFilePath;
}
