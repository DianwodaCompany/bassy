package com.dianwoda.test.bassy.common.domain.dto.testcase;

import com.dianwoda.test.bassy.common.domain.dto.ParamDTO;

/**
 * @author zcp
 * @date 2019/5/21 10:19
 */

public class LabelInfoParamDTO  extends ParamDTO {

    private Integer id;

    private String name;

    private String type;

    private String keyWords;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
