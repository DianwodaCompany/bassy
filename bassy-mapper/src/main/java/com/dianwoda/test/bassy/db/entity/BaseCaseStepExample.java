package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseCaseStepExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseCaseStepExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNull() {
            addCriterion("case_id is null");
            return (Criteria) this;
        }

        public Criteria andCaseIdIsNotNull() {
            addCriterion("case_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaseIdEqualTo(Integer value) {
            addCriterion("case_id =", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotEqualTo(Integer value) {
            addCriterion("case_id <>", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThan(Integer value) {
            addCriterion("case_id >", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("case_id >=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThan(Integer value) {
            addCriterion("case_id <", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdLessThanOrEqualTo(Integer value) {
            addCriterion("case_id <=", value, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdIn(List<Integer> values) {
            addCriterion("case_id in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotIn(List<Integer> values) {
            addCriterion("case_id not in", values, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdBetween(Integer value1, Integer value2) {
            addCriterion("case_id between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andCaseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("case_id not between", value1, value2, "caseId");
            return (Criteria) this;
        }

        public Criteria andStepIdIsNull() {
            addCriterion("step_id is null");
            return (Criteria) this;
        }

        public Criteria andStepIdIsNotNull() {
            addCriterion("step_id is not null");
            return (Criteria) this;
        }

        public Criteria andStepIdEqualTo(Long value) {
            addCriterion("step_id =", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdNotEqualTo(Long value) {
            addCriterion("step_id <>", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdGreaterThan(Long value) {
            addCriterion("step_id >", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdGreaterThanOrEqualTo(Long value) {
            addCriterion("step_id >=", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdLessThan(Long value) {
            addCriterion("step_id <", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdLessThanOrEqualTo(Long value) {
            addCriterion("step_id <=", value, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdIn(List<Long> values) {
            addCriterion("step_id in", values, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdNotIn(List<Long> values) {
            addCriterion("step_id not in", values, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdBetween(Long value1, Long value2) {
            addCriterion("step_id between", value1, value2, "stepId");
            return (Criteria) this;
        }

        public Criteria andStepIdNotBetween(Long value1, Long value2) {
            addCriterion("step_id not between", value1, value2, "stepId");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("`desc` is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("`desc` is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("`desc` =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("`desc` <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("`desc` >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("`desc` >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("`desc` <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("`desc` <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("`desc` like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("`desc` not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("`desc` in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("`desc` not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("`desc` between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("`desc` not between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andExpectDbIsNull() {
            addCriterion("expect_db is null");
            return (Criteria) this;
        }

        public Criteria andExpectDbIsNotNull() {
            addCriterion("expect_db is not null");
            return (Criteria) this;
        }

        public Criteria andExpectDbEqualTo(String value) {
            addCriterion("expect_db =", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbNotEqualTo(String value) {
            addCriterion("expect_db <>", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbGreaterThan(String value) {
            addCriterion("expect_db >", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbGreaterThanOrEqualTo(String value) {
            addCriterion("expect_db >=", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbLessThan(String value) {
            addCriterion("expect_db <", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbLessThanOrEqualTo(String value) {
            addCriterion("expect_db <=", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbLike(String value) {
            addCriterion("expect_db like", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbNotLike(String value) {
            addCriterion("expect_db not like", value, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbIn(List<String> values) {
            addCriterion("expect_db in", values, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbNotIn(List<String> values) {
            addCriterion("expect_db not in", values, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbBetween(String value1, String value2) {
            addCriterion("expect_db between", value1, value2, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectDbNotBetween(String value1, String value2) {
            addCriterion("expect_db not between", value1, value2, "expectDb");
            return (Criteria) this;
        }

        public Criteria andExpectUiIsNull() {
            addCriterion("expect_ui is null");
            return (Criteria) this;
        }

        public Criteria andExpectUiIsNotNull() {
            addCriterion("expect_ui is not null");
            return (Criteria) this;
        }

        public Criteria andExpectUiEqualTo(String value) {
            addCriterion("expect_ui =", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiNotEqualTo(String value) {
            addCriterion("expect_ui <>", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiGreaterThan(String value) {
            addCriterion("expect_ui >", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiGreaterThanOrEqualTo(String value) {
            addCriterion("expect_ui >=", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiLessThan(String value) {
            addCriterion("expect_ui <", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiLessThanOrEqualTo(String value) {
            addCriterion("expect_ui <=", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiLike(String value) {
            addCriterion("expect_ui like", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiNotLike(String value) {
            addCriterion("expect_ui not like", value, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiIn(List<String> values) {
            addCriterion("expect_ui in", values, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiNotIn(List<String> values) {
            addCriterion("expect_ui not in", values, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiBetween(String value1, String value2) {
            addCriterion("expect_ui between", value1, value2, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectUiNotBetween(String value1, String value2) {
            addCriterion("expect_ui not between", value1, value2, "expectUi");
            return (Criteria) this;
        }

        public Criteria andExpectResponseIsNull() {
            addCriterion("expect_response is null");
            return (Criteria) this;
        }

        public Criteria andExpectResponseIsNotNull() {
            addCriterion("expect_response is not null");
            return (Criteria) this;
        }

        public Criteria andExpectResponseEqualTo(String value) {
            addCriterion("expect_response =", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseNotEqualTo(String value) {
            addCriterion("expect_response <>", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseGreaterThan(String value) {
            addCriterion("expect_response >", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseGreaterThanOrEqualTo(String value) {
            addCriterion("expect_response >=", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseLessThan(String value) {
            addCriterion("expect_response <", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseLessThanOrEqualTo(String value) {
            addCriterion("expect_response <=", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseLike(String value) {
            addCriterion("expect_response like", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseNotLike(String value) {
            addCriterion("expect_response not like", value, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseIn(List<String> values) {
            addCriterion("expect_response in", values, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseNotIn(List<String> values) {
            addCriterion("expect_response not in", values, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseBetween(String value1, String value2) {
            addCriterion("expect_response between", value1, value2, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectResponseNotBetween(String value1, String value2) {
            addCriterion("expect_response not between", value1, value2, "expectResponse");
            return (Criteria) this;
        }

        public Criteria andExpectOtherIsNull() {
            addCriterion("expect_other is null");
            return (Criteria) this;
        }

        public Criteria andExpectOtherIsNotNull() {
            addCriterion("expect_other is not null");
            return (Criteria) this;
        }

        public Criteria andExpectOtherEqualTo(String value) {
            addCriterion("expect_other =", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherNotEqualTo(String value) {
            addCriterion("expect_other <>", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherGreaterThan(String value) {
            addCriterion("expect_other >", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherGreaterThanOrEqualTo(String value) {
            addCriterion("expect_other >=", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherLessThan(String value) {
            addCriterion("expect_other <", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherLessThanOrEqualTo(String value) {
            addCriterion("expect_other <=", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherLike(String value) {
            addCriterion("expect_other like", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherNotLike(String value) {
            addCriterion("expect_other not like", value, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherIn(List<String> values) {
            addCriterion("expect_other in", values, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherNotIn(List<String> values) {
            addCriterion("expect_other not in", values, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherBetween(String value1, String value2) {
            addCriterion("expect_other between", value1, value2, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExpectOtherNotBetween(String value1, String value2) {
            addCriterion("expect_other not between", value1, value2, "expectOther");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusIsNull() {
            addCriterion("execute_status is null");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusIsNotNull() {
            addCriterion("execute_status is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusEqualTo(Byte value) {
            addCriterion("execute_status =", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotEqualTo(Byte value) {
            addCriterion("execute_status <>", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusGreaterThan(Byte value) {
            addCriterion("execute_status >", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("execute_status >=", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusLessThan(Byte value) {
            addCriterion("execute_status <", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusLessThanOrEqualTo(Byte value) {
            addCriterion("execute_status <=", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusIn(List<Byte> values) {
            addCriterion("execute_status in", values, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotIn(List<Byte> values) {
            addCriterion("execute_status not in", values, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusBetween(Byte value1, Byte value2) {
            addCriterion("execute_status between", value1, value2, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("execute_status not between", value1, value2, "executeStatus");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}