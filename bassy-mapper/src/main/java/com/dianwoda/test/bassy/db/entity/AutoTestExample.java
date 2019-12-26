package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoTestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AutoTestExample() {
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

        public Criteria andSuiteIdIsNull() {
            addCriterion("suite_id is null");
            return (Criteria) this;
        }

        public Criteria andSuiteIdIsNotNull() {
            addCriterion("suite_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuiteIdEqualTo(Integer value) {
            addCriterion("suite_id =", value, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdNotEqualTo(Integer value) {
            addCriterion("suite_id <>", value, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdGreaterThan(Integer value) {
            addCriterion("suite_id >", value, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("suite_id >=", value, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdLessThan(Integer value) {
            addCriterion("suite_id <", value, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdLessThanOrEqualTo(Integer value) {
            addCriterion("suite_id <=", value, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdIn(List<Integer> values) {
            addCriterion("suite_id in", values, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdNotIn(List<Integer> values) {
            addCriterion("suite_id not in", values, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdBetween(Integer value1, Integer value2) {
            addCriterion("suite_id between", value1, value2, "suiteId");
            return (Criteria) this;
        }

        public Criteria andSuiteIdNotBetween(Integer value1, Integer value2) {
            addCriterion("suite_id not between", value1, value2, "suiteId");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIsNull() {
            addCriterion("execute_type is null");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIsNotNull() {
            addCriterion("execute_type is not null");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeEqualTo(String value) {
            addCriterion("execute_type =", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotEqualTo(String value) {
            addCriterion("execute_type <>", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeGreaterThan(String value) {
            addCriterion("execute_type >", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeGreaterThanOrEqualTo(String value) {
            addCriterion("execute_type >=", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLessThan(String value) {
            addCriterion("execute_type <", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLessThanOrEqualTo(String value) {
            addCriterion("execute_type <=", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeLike(String value) {
            addCriterion("execute_type like", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotLike(String value) {
            addCriterion("execute_type not like", value, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeIn(List<String> values) {
            addCriterion("execute_type in", values, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotIn(List<String> values) {
            addCriterion("execute_type not in", values, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeBetween(String value1, String value2) {
            addCriterion("execute_type between", value1, value2, "executeType");
            return (Criteria) this;
        }

        public Criteria andExecuteTypeNotBetween(String value1, String value2) {
            addCriterion("execute_type not between", value1, value2, "executeType");
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

        public Criteria andExecuteStatusEqualTo(String value) {
            addCriterion("execute_status =", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotEqualTo(String value) {
            addCriterion("execute_status <>", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusGreaterThan(String value) {
            addCriterion("execute_status >", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusGreaterThanOrEqualTo(String value) {
            addCriterion("execute_status >=", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusLessThan(String value) {
            addCriterion("execute_status <", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusLessThanOrEqualTo(String value) {
            addCriterion("execute_status <=", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusLike(String value) {
            addCriterion("execute_status like", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotLike(String value) {
            addCriterion("execute_status not like", value, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusIn(List<String> values) {
            addCriterion("execute_status in", values, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotIn(List<String> values) {
            addCriterion("execute_status not in", values, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusBetween(String value1, String value2) {
            addCriterion("execute_status between", value1, value2, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andExecuteStatusNotBetween(String value1, String value2) {
            addCriterion("execute_status not between", value1, value2, "executeStatus");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatTmIsNull() {
            addCriterion("creat_tm is null");
            return (Criteria) this;
        }

        public Criteria andCreatTmIsNotNull() {
            addCriterion("creat_tm is not null");
            return (Criteria) this;
        }

        public Criteria andCreatTmEqualTo(Date value) {
            addCriterion("creat_tm =", value, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmNotEqualTo(Date value) {
            addCriterion("creat_tm <>", value, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmGreaterThan(Date value) {
            addCriterion("creat_tm >", value, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmGreaterThanOrEqualTo(Date value) {
            addCriterion("creat_tm >=", value, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmLessThan(Date value) {
            addCriterion("creat_tm <", value, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmLessThanOrEqualTo(Date value) {
            addCriterion("creat_tm <=", value, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmIn(List<Date> values) {
            addCriterion("creat_tm in", values, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmNotIn(List<Date> values) {
            addCriterion("creat_tm not in", values, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmBetween(Date value1, Date value2) {
            addCriterion("creat_tm between", value1, value2, "creatTm");
            return (Criteria) this;
        }

        public Criteria andCreatTmNotBetween(Date value1, Date value2) {
            addCriterion("creat_tm not between", value1, value2, "creatTm");
            return (Criteria) this;
        }

        public Criteria andStartTmIsNull() {
            addCriterion("start_tm is null");
            return (Criteria) this;
        }

        public Criteria andStartTmIsNotNull() {
            addCriterion("start_tm is not null");
            return (Criteria) this;
        }

        public Criteria andStartTmEqualTo(Date value) {
            addCriterion("start_tm =", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotEqualTo(Date value) {
            addCriterion("start_tm <>", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmGreaterThan(Date value) {
            addCriterion("start_tm >", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmGreaterThanOrEqualTo(Date value) {
            addCriterion("start_tm >=", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmLessThan(Date value) {
            addCriterion("start_tm <", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmLessThanOrEqualTo(Date value) {
            addCriterion("start_tm <=", value, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmIn(List<Date> values) {
            addCriterion("start_tm in", values, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotIn(List<Date> values) {
            addCriterion("start_tm not in", values, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmBetween(Date value1, Date value2) {
            addCriterion("start_tm between", value1, value2, "startTm");
            return (Criteria) this;
        }

        public Criteria andStartTmNotBetween(Date value1, Date value2) {
            addCriterion("start_tm not between", value1, value2, "startTm");
            return (Criteria) this;
        }

        public Criteria andEndTmIsNull() {
            addCriterion("end_tm is null");
            return (Criteria) this;
        }

        public Criteria andEndTmIsNotNull() {
            addCriterion("end_tm is not null");
            return (Criteria) this;
        }

        public Criteria andEndTmEqualTo(Date value) {
            addCriterion("end_tm =", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotEqualTo(Date value) {
            addCriterion("end_tm <>", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmGreaterThan(Date value) {
            addCriterion("end_tm >", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmGreaterThanOrEqualTo(Date value) {
            addCriterion("end_tm >=", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmLessThan(Date value) {
            addCriterion("end_tm <", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmLessThanOrEqualTo(Date value) {
            addCriterion("end_tm <=", value, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmIn(List<Date> values) {
            addCriterion("end_tm in", values, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotIn(List<Date> values) {
            addCriterion("end_tm not in", values, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmBetween(Date value1, Date value2) {
            addCriterion("end_tm between", value1, value2, "endTm");
            return (Criteria) this;
        }

        public Criteria andEndTmNotBetween(Date value1, Date value2) {
            addCriterion("end_tm not between", value1, value2, "endTm");
            return (Criteria) this;
        }

        public Criteria andAllNumIsNull() {
            addCriterion("all_num is null");
            return (Criteria) this;
        }

        public Criteria andAllNumIsNotNull() {
            addCriterion("all_num is not null");
            return (Criteria) this;
        }

        public Criteria andAllNumEqualTo(Integer value) {
            addCriterion("all_num =", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotEqualTo(Integer value) {
            addCriterion("all_num <>", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumGreaterThan(Integer value) {
            addCriterion("all_num >", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("all_num >=", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumLessThan(Integer value) {
            addCriterion("all_num <", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumLessThanOrEqualTo(Integer value) {
            addCriterion("all_num <=", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumIn(List<Integer> values) {
            addCriterion("all_num in", values, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotIn(List<Integer> values) {
            addCriterion("all_num not in", values, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumBetween(Integer value1, Integer value2) {
            addCriterion("all_num between", value1, value2, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotBetween(Integer value1, Integer value2) {
            addCriterion("all_num not between", value1, value2, "allNum");
            return (Criteria) this;
        }

        public Criteria andPassNumIsNull() {
            addCriterion("pass_num is null");
            return (Criteria) this;
        }

        public Criteria andPassNumIsNotNull() {
            addCriterion("pass_num is not null");
            return (Criteria) this;
        }

        public Criteria andPassNumEqualTo(Integer value) {
            addCriterion("pass_num =", value, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumNotEqualTo(Integer value) {
            addCriterion("pass_num <>", value, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumGreaterThan(Integer value) {
            addCriterion("pass_num >", value, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("pass_num >=", value, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumLessThan(Integer value) {
            addCriterion("pass_num <", value, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumLessThanOrEqualTo(Integer value) {
            addCriterion("pass_num <=", value, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumIn(List<Integer> values) {
            addCriterion("pass_num in", values, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumNotIn(List<Integer> values) {
            addCriterion("pass_num not in", values, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumBetween(Integer value1, Integer value2) {
            addCriterion("pass_num between", value1, value2, "passNum");
            return (Criteria) this;
        }

        public Criteria andPassNumNotBetween(Integer value1, Integer value2) {
            addCriterion("pass_num not between", value1, value2, "passNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumIsNull() {
            addCriterion("skipt_num is null");
            return (Criteria) this;
        }

        public Criteria andSkiptNumIsNotNull() {
            addCriterion("skipt_num is not null");
            return (Criteria) this;
        }

        public Criteria andSkiptNumEqualTo(Integer value) {
            addCriterion("skipt_num =", value, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumNotEqualTo(Integer value) {
            addCriterion("skipt_num <>", value, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumGreaterThan(Integer value) {
            addCriterion("skipt_num >", value, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("skipt_num >=", value, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumLessThan(Integer value) {
            addCriterion("skipt_num <", value, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumLessThanOrEqualTo(Integer value) {
            addCriterion("skipt_num <=", value, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumIn(List<Integer> values) {
            addCriterion("skipt_num in", values, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumNotIn(List<Integer> values) {
            addCriterion("skipt_num not in", values, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumBetween(Integer value1, Integer value2) {
            addCriterion("skipt_num between", value1, value2, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andSkiptNumNotBetween(Integer value1, Integer value2) {
            addCriterion("skipt_num not between", value1, value2, "skiptNum");
            return (Criteria) this;
        }

        public Criteria andFailNumIsNull() {
            addCriterion("fail_num is null");
            return (Criteria) this;
        }

        public Criteria andFailNumIsNotNull() {
            addCriterion("fail_num is not null");
            return (Criteria) this;
        }

        public Criteria andFailNumEqualTo(Integer value) {
            addCriterion("fail_num =", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumNotEqualTo(Integer value) {
            addCriterion("fail_num <>", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumGreaterThan(Integer value) {
            addCriterion("fail_num >", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("fail_num >=", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumLessThan(Integer value) {
            addCriterion("fail_num <", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumLessThanOrEqualTo(Integer value) {
            addCriterion("fail_num <=", value, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumIn(List<Integer> values) {
            addCriterion("fail_num in", values, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumNotIn(List<Integer> values) {
            addCriterion("fail_num not in", values, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumBetween(Integer value1, Integer value2) {
            addCriterion("fail_num between", value1, value2, "failNum");
            return (Criteria) this;
        }

        public Criteria andFailNumNotBetween(Integer value1, Integer value2) {
            addCriterion("fail_num not between", value1, value2, "failNum");
            return (Criteria) this;
        }

        public Criteria andTesterIsNull() {
            addCriterion("tester is null");
            return (Criteria) this;
        }

        public Criteria andTesterIsNotNull() {
            addCriterion("tester is not null");
            return (Criteria) this;
        }

        public Criteria andTesterEqualTo(String value) {
            addCriterion("tester =", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotEqualTo(String value) {
            addCriterion("tester <>", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterGreaterThan(String value) {
            addCriterion("tester >", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterGreaterThanOrEqualTo(String value) {
            addCriterion("tester >=", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterLessThan(String value) {
            addCriterion("tester <", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterLessThanOrEqualTo(String value) {
            addCriterion("tester <=", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterLike(String value) {
            addCriterion("tester like", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotLike(String value) {
            addCriterion("tester not like", value, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterIn(List<String> values) {
            addCriterion("tester in", values, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotIn(List<String> values) {
            addCriterion("tester not in", values, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterBetween(String value1, String value2) {
            addCriterion("tester between", value1, value2, "tester");
            return (Criteria) this;
        }

        public Criteria andTesterNotBetween(String value1, String value2) {
            addCriterion("tester not between", value1, value2, "tester");
            return (Criteria) this;
        }

        public Criteria andReportNameIsNull() {
            addCriterion("report_name is null");
            return (Criteria) this;
        }

        public Criteria andReportNameIsNotNull() {
            addCriterion("report_name is not null");
            return (Criteria) this;
        }

        public Criteria andReportNameEqualTo(String value) {
            addCriterion("report_name =", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameNotEqualTo(String value) {
            addCriterion("report_name <>", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameGreaterThan(String value) {
            addCriterion("report_name >", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameGreaterThanOrEqualTo(String value) {
            addCriterion("report_name >=", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameLessThan(String value) {
            addCriterion("report_name <", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameLessThanOrEqualTo(String value) {
            addCriterion("report_name <=", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameLike(String value) {
            addCriterion("report_name like", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameNotLike(String value) {
            addCriterion("report_name not like", value, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameIn(List<String> values) {
            addCriterion("report_name in", values, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameNotIn(List<String> values) {
            addCriterion("report_name not in", values, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameBetween(String value1, String value2) {
            addCriterion("report_name between", value1, value2, "reportName");
            return (Criteria) this;
        }

        public Criteria andReportNameNotBetween(String value1, String value2) {
            addCriterion("report_name not between", value1, value2, "reportName");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCcEmailIsNull() {
            addCriterion("cc_email is null");
            return (Criteria) this;
        }

        public Criteria andCcEmailIsNotNull() {
            addCriterion("cc_email is not null");
            return (Criteria) this;
        }

        public Criteria andCcEmailEqualTo(String value) {
            addCriterion("cc_email =", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailNotEqualTo(String value) {
            addCriterion("cc_email <>", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailGreaterThan(String value) {
            addCriterion("cc_email >", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailGreaterThanOrEqualTo(String value) {
            addCriterion("cc_email >=", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailLessThan(String value) {
            addCriterion("cc_email <", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailLessThanOrEqualTo(String value) {
            addCriterion("cc_email <=", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailLike(String value) {
            addCriterion("cc_email like", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailNotLike(String value) {
            addCriterion("cc_email not like", value, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailIn(List<String> values) {
            addCriterion("cc_email in", values, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailNotIn(List<String> values) {
            addCriterion("cc_email not in", values, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailBetween(String value1, String value2) {
            addCriterion("cc_email between", value1, value2, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andCcEmailNotBetween(String value1, String value2) {
            addCriterion("cc_email not between", value1, value2, "ccEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailIsNull() {
            addCriterion("receive_email is null");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailIsNotNull() {
            addCriterion("receive_email is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailEqualTo(String value) {
            addCriterion("receive_email =", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailNotEqualTo(String value) {
            addCriterion("receive_email <>", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailGreaterThan(String value) {
            addCriterion("receive_email >", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailGreaterThanOrEqualTo(String value) {
            addCriterion("receive_email >=", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailLessThan(String value) {
            addCriterion("receive_email <", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailLessThanOrEqualTo(String value) {
            addCriterion("receive_email <=", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailLike(String value) {
            addCriterion("receive_email like", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailNotLike(String value) {
            addCriterion("receive_email not like", value, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailIn(List<String> values) {
            addCriterion("receive_email in", values, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailNotIn(List<String> values) {
            addCriterion("receive_email not in", values, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailBetween(String value1, String value2) {
            addCriterion("receive_email between", value1, value2, "receiveEmail");
            return (Criteria) this;
        }

        public Criteria andReceiveEmailNotBetween(String value1, String value2) {
            addCriterion("receive_email not between", value1, value2, "receiveEmail");
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