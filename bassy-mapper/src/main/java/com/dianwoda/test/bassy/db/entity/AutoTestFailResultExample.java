package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoTestFailResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AutoTestFailResultExample() {
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

        public Criteria andAutoTestIdIsNull() {
            addCriterion("auto_test_id is null");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdIsNotNull() {
            addCriterion("auto_test_id is not null");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdEqualTo(Integer value) {
            addCriterion("auto_test_id =", value, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdNotEqualTo(Integer value) {
            addCriterion("auto_test_id <>", value, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdGreaterThan(Integer value) {
            addCriterion("auto_test_id >", value, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_test_id >=", value, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdLessThan(Integer value) {
            addCriterion("auto_test_id <", value, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdLessThanOrEqualTo(Integer value) {
            addCriterion("auto_test_id <=", value, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdIn(List<Integer> values) {
            addCriterion("auto_test_id in", values, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdNotIn(List<Integer> values) {
            addCriterion("auto_test_id not in", values, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdBetween(Integer value1, Integer value2) {
            addCriterion("auto_test_id between", value1, value2, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andAutoTestIdNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_test_id not between", value1, value2, "autoTestId");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNull() {
            addCriterion("test_name is null");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNotNull() {
            addCriterion("test_name is not null");
            return (Criteria) this;
        }

        public Criteria andTestNameEqualTo(String value) {
            addCriterion("test_name =", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotEqualTo(String value) {
            addCriterion("test_name <>", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThan(String value) {
            addCriterion("test_name >", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThanOrEqualTo(String value) {
            addCriterion("test_name >=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThan(String value) {
            addCriterion("test_name <", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThanOrEqualTo(String value) {
            addCriterion("test_name <=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLike(String value) {
            addCriterion("test_name like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotLike(String value) {
            addCriterion("test_name not like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameIn(List<String> values) {
            addCriterion("test_name in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotIn(List<String> values) {
            addCriterion("test_name not in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameBetween(String value1, String value2) {
            addCriterion("test_name between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotBetween(String value1, String value2) {
            addCriterion("test_name not between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestTypeIsNull() {
            addCriterion("test_type is null");
            return (Criteria) this;
        }

        public Criteria andTestTypeIsNotNull() {
            addCriterion("test_type is not null");
            return (Criteria) this;
        }

        public Criteria andTestTypeEqualTo(String value) {
            addCriterion("test_type =", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotEqualTo(String value) {
            addCriterion("test_type <>", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeGreaterThan(String value) {
            addCriterion("test_type >", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("test_type >=", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeLessThan(String value) {
            addCriterion("test_type <", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeLessThanOrEqualTo(String value) {
            addCriterion("test_type <=", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeLike(String value) {
            addCriterion("test_type like", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotLike(String value) {
            addCriterion("test_type not like", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeIn(List<String> values) {
            addCriterion("test_type in", values, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotIn(List<String> values) {
            addCriterion("test_type not in", values, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeBetween(String value1, String value2) {
            addCriterion("test_type between", value1, value2, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotBetween(String value1, String value2) {
            addCriterion("test_type not between", value1, value2, "testType");
            return (Criteria) this;
        }

        public Criteria andFailMethodIsNull() {
            addCriterion("fail_method is null");
            return (Criteria) this;
        }

        public Criteria andFailMethodIsNotNull() {
            addCriterion("fail_method is not null");
            return (Criteria) this;
        }

        public Criteria andFailMethodEqualTo(String value) {
            addCriterion("fail_method =", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodNotEqualTo(String value) {
            addCriterion("fail_method <>", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodGreaterThan(String value) {
            addCriterion("fail_method >", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodGreaterThanOrEqualTo(String value) {
            addCriterion("fail_method >=", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodLessThan(String value) {
            addCriterion("fail_method <", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodLessThanOrEqualTo(String value) {
            addCriterion("fail_method <=", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodLike(String value) {
            addCriterion("fail_method like", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodNotLike(String value) {
            addCriterion("fail_method not like", value, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodIn(List<String> values) {
            addCriterion("fail_method in", values, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodNotIn(List<String> values) {
            addCriterion("fail_method not in", values, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodBetween(String value1, String value2) {
            addCriterion("fail_method between", value1, value2, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailMethodNotBetween(String value1, String value2) {
            addCriterion("fail_method not between", value1, value2, "failMethod");
            return (Criteria) this;
        }

        public Criteria andFailReasonIsNull() {
            addCriterion("fail_reason is null");
            return (Criteria) this;
        }

        public Criteria andFailReasonIsNotNull() {
            addCriterion("fail_reason is not null");
            return (Criteria) this;
        }

        public Criteria andFailReasonEqualTo(Integer value) {
            addCriterion("fail_reason =", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotEqualTo(Integer value) {
            addCriterion("fail_reason <>", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonGreaterThan(Integer value) {
            addCriterion("fail_reason >", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonGreaterThanOrEqualTo(Integer value) {
            addCriterion("fail_reason >=", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLessThan(Integer value) {
            addCriterion("fail_reason <", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonLessThanOrEqualTo(Integer value) {
            addCriterion("fail_reason <=", value, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonIn(List<Integer> values) {
            addCriterion("fail_reason in", values, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotIn(List<Integer> values) {
            addCriterion("fail_reason not in", values, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonBetween(Integer value1, Integer value2) {
            addCriterion("fail_reason between", value1, value2, "failReason");
            return (Criteria) this;
        }

        public Criteria andFailReasonNotBetween(Integer value1, Integer value2) {
            addCriterion("fail_reason not between", value1, value2, "failReason");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andTesterCodeIsNull() {
            addCriterion("tester_code is null");
            return (Criteria) this;
        }

        public Criteria andTesterCodeIsNotNull() {
            addCriterion("tester_code is not null");
            return (Criteria) this;
        }

        public Criteria andTesterCodeEqualTo(String value) {
            addCriterion("tester_code =", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeNotEqualTo(String value) {
            addCriterion("tester_code <>", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeGreaterThan(String value) {
            addCriterion("tester_code >", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("tester_code >=", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeLessThan(String value) {
            addCriterion("tester_code <", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeLessThanOrEqualTo(String value) {
            addCriterion("tester_code <=", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeLike(String value) {
            addCriterion("tester_code like", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeNotLike(String value) {
            addCriterion("tester_code not like", value, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeIn(List<String> values) {
            addCriterion("tester_code in", values, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeNotIn(List<String> values) {
            addCriterion("tester_code not in", values, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeBetween(String value1, String value2) {
            addCriterion("tester_code between", value1, value2, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterCodeNotBetween(String value1, String value2) {
            addCriterion("tester_code not between", value1, value2, "testerCode");
            return (Criteria) this;
        }

        public Criteria andTesterNameIsNull() {
            addCriterion("tester_name is null");
            return (Criteria) this;
        }

        public Criteria andTesterNameIsNotNull() {
            addCriterion("tester_name is not null");
            return (Criteria) this;
        }

        public Criteria andTesterNameEqualTo(String value) {
            addCriterion("tester_name =", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameNotEqualTo(String value) {
            addCriterion("tester_name <>", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameGreaterThan(String value) {
            addCriterion("tester_name >", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameGreaterThanOrEqualTo(String value) {
            addCriterion("tester_name >=", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameLessThan(String value) {
            addCriterion("tester_name <", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameLessThanOrEqualTo(String value) {
            addCriterion("tester_name <=", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameLike(String value) {
            addCriterion("tester_name like", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameNotLike(String value) {
            addCriterion("tester_name not like", value, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameIn(List<String> values) {
            addCriterion("tester_name in", values, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameNotIn(List<String> values) {
            addCriterion("tester_name not in", values, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameBetween(String value1, String value2) {
            addCriterion("tester_name between", value1, value2, "testerName");
            return (Criteria) this;
        }

        public Criteria andTesterNameNotBetween(String value1, String value2) {
            addCriterion("tester_name not between", value1, value2, "testerName");
            return (Criteria) this;
        }

        public Criteria andUpdateTmIsNull() {
            addCriterion("update_tm is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTmIsNotNull() {
            addCriterion("update_tm is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTmEqualTo(Date value) {
            addCriterion("update_tm =", value, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmNotEqualTo(Date value) {
            addCriterion("update_tm <>", value, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmGreaterThan(Date value) {
            addCriterion("update_tm >", value, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmGreaterThanOrEqualTo(Date value) {
            addCriterion("update_tm >=", value, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmLessThan(Date value) {
            addCriterion("update_tm <", value, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmLessThanOrEqualTo(Date value) {
            addCriterion("update_tm <=", value, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmIn(List<Date> values) {
            addCriterion("update_tm in", values, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmNotIn(List<Date> values) {
            addCriterion("update_tm not in", values, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmBetween(Date value1, Date value2) {
            addCriterion("update_tm between", value1, value2, "updateTm");
            return (Criteria) this;
        }

        public Criteria andUpdateTmNotBetween(Date value1, Date value2) {
            addCriterion("update_tm not between", value1, value2, "updateTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmIsNull() {
            addCriterion("create_tm is null");
            return (Criteria) this;
        }

        public Criteria andCreateTmIsNotNull() {
            addCriterion("create_tm is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTmEqualTo(Date value) {
            addCriterion("create_tm =", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotEqualTo(Date value) {
            addCriterion("create_tm <>", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmGreaterThan(Date value) {
            addCriterion("create_tm >", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmGreaterThanOrEqualTo(Date value) {
            addCriterion("create_tm >=", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmLessThan(Date value) {
            addCriterion("create_tm <", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmLessThanOrEqualTo(Date value) {
            addCriterion("create_tm <=", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmIn(List<Date> values) {
            addCriterion("create_tm in", values, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotIn(List<Date> values) {
            addCriterion("create_tm not in", values, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmBetween(Date value1, Date value2) {
            addCriterion("create_tm between", value1, value2, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotBetween(Date value1, Date value2) {
            addCriterion("create_tm not between", value1, value2, "createTm");
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