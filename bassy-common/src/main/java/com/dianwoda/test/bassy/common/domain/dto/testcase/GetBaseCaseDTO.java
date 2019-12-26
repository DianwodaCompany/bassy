package com.dianwoda.test.bassy.common.domain.dto.testcase;


import java.util.List;

public class GetBaseCaseDTO  {

    public TestCaseParamDTO testCaseParamDTO;

    public GetBaseCaseDTO(TestCaseParamDTO testCaseParamDTO){
        this.testCaseParamDTO = testCaseParamDTO;
    }


    private String orderBy;

    private List<Integer> subModules;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<Integer> getSubModules() {
        return subModules;
    }

    public void setSubModules(List<Integer> subModules) {
        this.subModules = subModules;
    }
}
