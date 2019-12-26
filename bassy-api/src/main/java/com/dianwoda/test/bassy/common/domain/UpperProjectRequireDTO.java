package com.dianwoda.test.bassy.common.domain;

import java.io.Serializable;

/**
 * @author zcp
 * 2019/12/21 下午12:14
 * Most is the gentleness which that one lowers the head,
 * looks like a water lotus flower extremely cool breeze charming.
 */
public class UpperProjectRequireDTO implements Serializable {
    private Integer project;

    private Integer product;

    private Integer story;

    private Short version;

    private Short order;

    private static final long serialVersionUID = 1L;

    public Integer getProject() {
        return project;
    }

    public void setProject(Integer project) {
        this.project = project;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getStory() {
        return story;
    }

    public void setStory(Integer story) {
        this.story = story;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Short getOrder() {
        return order;
    }

    public void setOrder(Short order) {
        this.order = order;
    }
}
