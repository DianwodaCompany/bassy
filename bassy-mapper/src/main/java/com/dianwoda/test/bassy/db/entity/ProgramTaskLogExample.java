package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProgramTaskLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProgramTaskLogExample() {
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

        public Criteria andProgramIdIsNull() {
            addCriterion("program_id is null");
            return (Criteria) this;
        }

        public Criteria andProgramIdIsNotNull() {
            addCriterion("program_id is not null");
            return (Criteria) this;
        }

        public Criteria andProgramIdEqualTo(Integer value) {
            addCriterion("program_id =", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotEqualTo(Integer value) {
            addCriterion("program_id <>", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThan(Integer value) {
            addCriterion("program_id >", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("program_id >=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThan(Integer value) {
            addCriterion("program_id <", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdLessThanOrEqualTo(Integer value) {
            addCriterion("program_id <=", value, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdIn(List<Integer> values) {
            addCriterion("program_id in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotIn(List<Integer> values) {
            addCriterion("program_id not in", values, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdBetween(Integer value1, Integer value2) {
            addCriterion("program_id between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andProgramIdNotBetween(Integer value1, Integer value2) {
            addCriterion("program_id not between", value1, value2, "programId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andRequireIdIsNull() {
            addCriterion("require_id is null");
            return (Criteria) this;
        }

        public Criteria andRequireIdIsNotNull() {
            addCriterion("require_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequireIdEqualTo(Integer value) {
            addCriterion("require_id =", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdNotEqualTo(Integer value) {
            addCriterion("require_id <>", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdGreaterThan(Integer value) {
            addCriterion("require_id >", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("require_id >=", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdLessThan(Integer value) {
            addCriterion("require_id <", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdLessThanOrEqualTo(Integer value) {
            addCriterion("require_id <=", value, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdIn(List<Integer> values) {
            addCriterion("require_id in", values, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdNotIn(List<Integer> values) {
            addCriterion("require_id not in", values, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdBetween(Integer value1, Integer value2) {
            addCriterion("require_id between", value1, value2, "requireId");
            return (Criteria) this;
        }

        public Criteria andRequireIdNotBetween(Integer value1, Integer value2) {
            addCriterion("require_id not between", value1, value2, "requireId");
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

        public Criteria andTaskStatusIsNull() {
            addCriterion("task_status is null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIsNotNull() {
            addCriterion("task_status is not null");
            return (Criteria) this;
        }

        public Criteria andTaskStatusEqualTo(String value) {
            addCriterion("task_status =", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotEqualTo(String value) {
            addCriterion("task_status <>", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThan(String value) {
            addCriterion("task_status >", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusGreaterThanOrEqualTo(String value) {
            addCriterion("task_status >=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThan(String value) {
            addCriterion("task_status <", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLessThanOrEqualTo(String value) {
            addCriterion("task_status <=", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusLike(String value) {
            addCriterion("task_status like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotLike(String value) {
            addCriterion("task_status not like", value, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusIn(List<String> values) {
            addCriterion("task_status in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotIn(List<String> values) {
            addCriterion("task_status not in", values, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusBetween(String value1, String value2) {
            addCriterion("task_status between", value1, value2, "taskStatus");
            return (Criteria) this;
        }

        public Criteria andTaskStatusNotBetween(String value1, String value2) {
            addCriterion("task_status not between", value1, value2, "taskStatus");
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

        public Criteria andWithTesterIsNull() {
            addCriterion("with_tester is null");
            return (Criteria) this;
        }

        public Criteria andWithTesterIsNotNull() {
            addCriterion("with_tester is not null");
            return (Criteria) this;
        }

        public Criteria andWithTesterEqualTo(String value) {
            addCriterion("with_tester =", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotEqualTo(String value) {
            addCriterion("with_tester <>", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterGreaterThan(String value) {
            addCriterion("with_tester >", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterGreaterThanOrEqualTo(String value) {
            addCriterion("with_tester >=", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterLessThan(String value) {
            addCriterion("with_tester <", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterLessThanOrEqualTo(String value) {
            addCriterion("with_tester <=", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterLike(String value) {
            addCriterion("with_tester like", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotLike(String value) {
            addCriterion("with_tester not like", value, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterIn(List<String> values) {
            addCriterion("with_tester in", values, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotIn(List<String> values) {
            addCriterion("with_tester not in", values, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterBetween(String value1, String value2) {
            addCriterion("with_tester between", value1, value2, "withTester");
            return (Criteria) this;
        }

        public Criteria andWithTesterNotBetween(String value1, String value2) {
            addCriterion("with_tester not between", value1, value2, "withTester");
            return (Criteria) this;
        }

        public Criteria andExpectHourIsNull() {
            addCriterion("expect_hour is null");
            return (Criteria) this;
        }

        public Criteria andExpectHourIsNotNull() {
            addCriterion("expect_hour is not null");
            return (Criteria) this;
        }

        public Criteria andExpectHourEqualTo(Float value) {
            addCriterion("expect_hour =", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourNotEqualTo(Float value) {
            addCriterion("expect_hour <>", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourGreaterThan(Float value) {
            addCriterion("expect_hour >", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourGreaterThanOrEqualTo(Float value) {
            addCriterion("expect_hour >=", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourLessThan(Float value) {
            addCriterion("expect_hour <", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourLessThanOrEqualTo(Float value) {
            addCriterion("expect_hour <=", value, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourIn(List<Float> values) {
            addCriterion("expect_hour in", values, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourNotIn(List<Float> values) {
            addCriterion("expect_hour not in", values, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourBetween(Float value1, Float value2) {
            addCriterion("expect_hour between", value1, value2, "expectHour");
            return (Criteria) this;
        }

        public Criteria andExpectHourNotBetween(Float value1, Float value2) {
            addCriterion("expect_hour not between", value1, value2, "expectHour");
            return (Criteria) this;
        }

        public Criteria andActualHourIsNull() {
            addCriterion("actual_hour is null");
            return (Criteria) this;
        }

        public Criteria andActualHourIsNotNull() {
            addCriterion("actual_hour is not null");
            return (Criteria) this;
        }

        public Criteria andActualHourEqualTo(Float value) {
            addCriterion("actual_hour =", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourNotEqualTo(Float value) {
            addCriterion("actual_hour <>", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourGreaterThan(Float value) {
            addCriterion("actual_hour >", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourGreaterThanOrEqualTo(Float value) {
            addCriterion("actual_hour >=", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourLessThan(Float value) {
            addCriterion("actual_hour <", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourLessThanOrEqualTo(Float value) {
            addCriterion("actual_hour <=", value, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourIn(List<Float> values) {
            addCriterion("actual_hour in", values, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourNotIn(List<Float> values) {
            addCriterion("actual_hour not in", values, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourBetween(Float value1, Float value2) {
            addCriterion("actual_hour between", value1, value2, "actualHour");
            return (Criteria) this;
        }

        public Criteria andActualHourNotBetween(Float value1, Float value2) {
            addCriterion("actual_hour not between", value1, value2, "actualHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourIsNull() {
            addCriterion("today_hour is null");
            return (Criteria) this;
        }

        public Criteria andTodayHourIsNotNull() {
            addCriterion("today_hour is not null");
            return (Criteria) this;
        }

        public Criteria andTodayHourEqualTo(Float value) {
            addCriterion("today_hour =", value, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourNotEqualTo(Float value) {
            addCriterion("today_hour <>", value, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourGreaterThan(Float value) {
            addCriterion("today_hour >", value, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourGreaterThanOrEqualTo(Float value) {
            addCriterion("today_hour >=", value, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourLessThan(Float value) {
            addCriterion("today_hour <", value, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourLessThanOrEqualTo(Float value) {
            addCriterion("today_hour <=", value, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourIn(List<Float> values) {
            addCriterion("today_hour in", values, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourNotIn(List<Float> values) {
            addCriterion("today_hour not in", values, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourBetween(Float value1, Float value2) {
            addCriterion("today_hour between", value1, value2, "todayHour");
            return (Criteria) this;
        }

        public Criteria andTodayHourNotBetween(Float value1, Float value2) {
            addCriterion("today_hour not between", value1, value2, "todayHour");
            return (Criteria) this;
        }

        public Criteria andPercentIsNull() {
            addCriterion("`percent` is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("`percent` is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(Integer value) {
            addCriterion("`percent` =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(Integer value) {
            addCriterion("`percent` <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(Integer value) {
            addCriterion("`percent` >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(Integer value) {
            addCriterion("`percent` >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(Integer value) {
            addCriterion("`percent` <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(Integer value) {
            addCriterion("`percent` <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<Integer> values) {
            addCriterion("`percent` in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<Integer> values) {
            addCriterion("`percent` not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(Integer value1, Integer value2) {
            addCriterion("`percent` between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(Integer value1, Integer value2) {
            addCriterion("`percent` not between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andIsNormalIsNull() {
            addCriterion("is_normal is null");
            return (Criteria) this;
        }

        public Criteria andIsNormalIsNotNull() {
            addCriterion("is_normal is not null");
            return (Criteria) this;
        }

        public Criteria andIsNormalEqualTo(Byte value) {
            addCriterion("is_normal =", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalNotEqualTo(Byte value) {
            addCriterion("is_normal <>", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalGreaterThan(Byte value) {
            addCriterion("is_normal >", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_normal >=", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalLessThan(Byte value) {
            addCriterion("is_normal <", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalLessThanOrEqualTo(Byte value) {
            addCriterion("is_normal <=", value, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalIn(List<Byte> values) {
            addCriterion("is_normal in", values, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalNotIn(List<Byte> values) {
            addCriterion("is_normal not in", values, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalBetween(Byte value1, Byte value2) {
            addCriterion("is_normal between", value1, value2, "isNormal");
            return (Criteria) this;
        }

        public Criteria andIsNormalNotBetween(Byte value1, Byte value2) {
            addCriterion("is_normal not between", value1, value2, "isNormal");
            return (Criteria) this;
        }

        public Criteria andReasonTeamIsNull() {
            addCriterion("reason_team is null");
            return (Criteria) this;
        }

        public Criteria andReasonTeamIsNotNull() {
            addCriterion("reason_team is not null");
            return (Criteria) this;
        }

        public Criteria andReasonTeamEqualTo(String value) {
            addCriterion("reason_team =", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotEqualTo(String value) {
            addCriterion("reason_team <>", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamGreaterThan(String value) {
            addCriterion("reason_team >", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamGreaterThanOrEqualTo(String value) {
            addCriterion("reason_team >=", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamLessThan(String value) {
            addCriterion("reason_team <", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamLessThanOrEqualTo(String value) {
            addCriterion("reason_team <=", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamLike(String value) {
            addCriterion("reason_team like", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotLike(String value) {
            addCriterion("reason_team not like", value, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamIn(List<String> values) {
            addCriterion("reason_team in", values, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotIn(List<String> values) {
            addCriterion("reason_team not in", values, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamBetween(String value1, String value2) {
            addCriterion("reason_team between", value1, value2, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTeamNotBetween(String value1, String value2) {
            addCriterion("reason_team not between", value1, value2, "reasonTeam");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIsNull() {
            addCriterion("reason_type is null");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIsNotNull() {
            addCriterion("reason_type is not null");
            return (Criteria) this;
        }

        public Criteria andReasonTypeEqualTo(String value) {
            addCriterion("reason_type =", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotEqualTo(String value) {
            addCriterion("reason_type <>", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeGreaterThan(String value) {
            addCriterion("reason_type >", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeGreaterThanOrEqualTo(String value) {
            addCriterion("reason_type >=", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLessThan(String value) {
            addCriterion("reason_type <", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLessThanOrEqualTo(String value) {
            addCriterion("reason_type <=", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeLike(String value) {
            addCriterion("reason_type like", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotLike(String value) {
            addCriterion("reason_type not like", value, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeIn(List<String> values) {
            addCriterion("reason_type in", values, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotIn(List<String> values) {
            addCriterion("reason_type not in", values, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeBetween(String value1, String value2) {
            addCriterion("reason_type between", value1, value2, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonTypeNotBetween(String value1, String value2) {
            addCriterion("reason_type not between", value1, value2, "reasonType");
            return (Criteria) this;
        }

        public Criteria andReasonLevelIsNull() {
            addCriterion("reason_level is null");
            return (Criteria) this;
        }

        public Criteria andReasonLevelIsNotNull() {
            addCriterion("reason_level is not null");
            return (Criteria) this;
        }

        public Criteria andReasonLevelEqualTo(String value) {
            addCriterion("reason_level =", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotEqualTo(String value) {
            addCriterion("reason_level <>", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelGreaterThan(String value) {
            addCriterion("reason_level >", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelGreaterThanOrEqualTo(String value) {
            addCriterion("reason_level >=", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelLessThan(String value) {
            addCriterion("reason_level <", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelLessThanOrEqualTo(String value) {
            addCriterion("reason_level <=", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelLike(String value) {
            addCriterion("reason_level like", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotLike(String value) {
            addCriterion("reason_level not like", value, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelIn(List<String> values) {
            addCriterion("reason_level in", values, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotIn(List<String> values) {
            addCriterion("reason_level not in", values, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelBetween(String value1, String value2) {
            addCriterion("reason_level between", value1, value2, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonLevelNotBetween(String value1, String value2) {
            addCriterion("reason_level not between", value1, value2, "reasonLevel");
            return (Criteria) this;
        }

        public Criteria andReasonDetailIsNull() {
            addCriterion("reason_detail is null");
            return (Criteria) this;
        }

        public Criteria andReasonDetailIsNotNull() {
            addCriterion("reason_detail is not null");
            return (Criteria) this;
        }

        public Criteria andReasonDetailEqualTo(String value) {
            addCriterion("reason_detail =", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotEqualTo(String value) {
            addCriterion("reason_detail <>", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailGreaterThan(String value) {
            addCriterion("reason_detail >", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailGreaterThanOrEqualTo(String value) {
            addCriterion("reason_detail >=", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailLessThan(String value) {
            addCriterion("reason_detail <", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailLessThanOrEqualTo(String value) {
            addCriterion("reason_detail <=", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailLike(String value) {
            addCriterion("reason_detail like", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotLike(String value) {
            addCriterion("reason_detail not like", value, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailIn(List<String> values) {
            addCriterion("reason_detail in", values, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotIn(List<String> values) {
            addCriterion("reason_detail not in", values, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailBetween(String value1, String value2) {
            addCriterion("reason_detail between", value1, value2, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andReasonDetailNotBetween(String value1, String value2) {
            addCriterion("reason_detail not between", value1, value2, "reasonDetail");
            return (Criteria) this;
        }

        public Criteria andTaskExplainIsNull() {
            addCriterion("task_explain is null");
            return (Criteria) this;
        }

        public Criteria andTaskExplainIsNotNull() {
            addCriterion("task_explain is not null");
            return (Criteria) this;
        }

        public Criteria andTaskExplainEqualTo(String value) {
            addCriterion("task_explain =", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotEqualTo(String value) {
            addCriterion("task_explain <>", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainGreaterThan(String value) {
            addCriterion("task_explain >", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainGreaterThanOrEqualTo(String value) {
            addCriterion("task_explain >=", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainLessThan(String value) {
            addCriterion("task_explain <", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainLessThanOrEqualTo(String value) {
            addCriterion("task_explain <=", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainLike(String value) {
            addCriterion("task_explain like", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotLike(String value) {
            addCriterion("task_explain not like", value, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainIn(List<String> values) {
            addCriterion("task_explain in", values, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotIn(List<String> values) {
            addCriterion("task_explain not in", values, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainBetween(String value1, String value2) {
            addCriterion("task_explain between", value1, value2, "taskExplain");
            return (Criteria) this;
        }

        public Criteria andTaskExplainNotBetween(String value1, String value2) {
            addCriterion("task_explain not between", value1, value2, "taskExplain");
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

        public Criteria andAutoTestResultIsNull() {
            addCriterion("auto_test_result is null");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultIsNotNull() {
            addCriterion("auto_test_result is not null");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultEqualTo(String value) {
            addCriterion("auto_test_result =", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultNotEqualTo(String value) {
            addCriterion("auto_test_result <>", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultGreaterThan(String value) {
            addCriterion("auto_test_result >", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultGreaterThanOrEqualTo(String value) {
            addCriterion("auto_test_result >=", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultLessThan(String value) {
            addCriterion("auto_test_result <", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultLessThanOrEqualTo(String value) {
            addCriterion("auto_test_result <=", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultLike(String value) {
            addCriterion("auto_test_result like", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultNotLike(String value) {
            addCriterion("auto_test_result not like", value, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultIn(List<String> values) {
            addCriterion("auto_test_result in", values, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultNotIn(List<String> values) {
            addCriterion("auto_test_result not in", values, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultBetween(String value1, String value2) {
            addCriterion("auto_test_result between", value1, value2, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andAutoTestResultNotBetween(String value1, String value2) {
            addCriterion("auto_test_result not between", value1, value2, "autoTestResult");
            return (Criteria) this;
        }

        public Criteria andDocumentIsNull() {
            addCriterion("document is null");
            return (Criteria) this;
        }

        public Criteria andDocumentIsNotNull() {
            addCriterion("document is not null");
            return (Criteria) this;
        }

        public Criteria andDocumentEqualTo(String value) {
            addCriterion("document =", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotEqualTo(String value) {
            addCriterion("document <>", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentGreaterThan(String value) {
            addCriterion("document >", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentGreaterThanOrEqualTo(String value) {
            addCriterion("document >=", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLessThan(String value) {
            addCriterion("document <", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLessThanOrEqualTo(String value) {
            addCriterion("document <=", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentLike(String value) {
            addCriterion("document like", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotLike(String value) {
            addCriterion("document not like", value, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentIn(List<String> values) {
            addCriterion("document in", values, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotIn(List<String> values) {
            addCriterion("document not in", values, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentBetween(String value1, String value2) {
            addCriterion("document between", value1, value2, "document");
            return (Criteria) this;
        }

        public Criteria andDocumentNotBetween(String value1, String value2) {
            addCriterion("document not between", value1, value2, "document");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifyTmIsNull() {
            addCriterion("modify_tm is null");
            return (Criteria) this;
        }

        public Criteria andModifyTmIsNotNull() {
            addCriterion("modify_tm is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTmEqualTo(Date value) {
            addCriterion("modify_tm =", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotEqualTo(Date value) {
            addCriterion("modify_tm <>", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThan(Date value) {
            addCriterion("modify_tm >", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_tm >=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThan(Date value) {
            addCriterion("modify_tm <", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThanOrEqualTo(Date value) {
            addCriterion("modify_tm <=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmIn(List<Date> values) {
            addCriterion("modify_tm in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotIn(List<Date> values) {
            addCriterion("modify_tm not in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmBetween(Date value1, Date value2) {
            addCriterion("modify_tm between", value1, value2, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotBetween(Date value1, Date value2) {
            addCriterion("modify_tm not between", value1, value2, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdIsNull() {
            addCriterion("zt_task_id is null");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdIsNotNull() {
            addCriterion("zt_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdEqualTo(Integer value) {
            addCriterion("zt_task_id =", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdNotEqualTo(Integer value) {
            addCriterion("zt_task_id <>", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdGreaterThan(Integer value) {
            addCriterion("zt_task_id >", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("zt_task_id >=", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdLessThan(Integer value) {
            addCriterion("zt_task_id <", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("zt_task_id <=", value, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdIn(List<Integer> values) {
            addCriterion("zt_task_id in", values, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdNotIn(List<Integer> values) {
            addCriterion("zt_task_id not in", values, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("zt_task_id between", value1, value2, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andZtTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("zt_task_id not between", value1, value2, "ztTaskId");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeIsNull() {
            addCriterion("abnormal_type is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeIsNotNull() {
            addCriterion("abnormal_type is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeEqualTo(Byte value) {
            addCriterion("abnormal_type =", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeNotEqualTo(Byte value) {
            addCriterion("abnormal_type <>", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeGreaterThan(Byte value) {
            addCriterion("abnormal_type >", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("abnormal_type >=", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeLessThan(Byte value) {
            addCriterion("abnormal_type <", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeLessThanOrEqualTo(Byte value) {
            addCriterion("abnormal_type <=", value, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeIn(List<Byte> values) {
            addCriterion("abnormal_type in", values, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeNotIn(List<Byte> values) {
            addCriterion("abnormal_type not in", values, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeBetween(Byte value1, Byte value2) {
            addCriterion("abnormal_type between", value1, value2, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("abnormal_type not between", value1, value2, "abnormalType");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerIsNull() {
            addCriterion("abnormal_owner is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerIsNotNull() {
            addCriterion("abnormal_owner is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerEqualTo(String value) {
            addCriterion("abnormal_owner =", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerNotEqualTo(String value) {
            addCriterion("abnormal_owner <>", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerGreaterThan(String value) {
            addCriterion("abnormal_owner >", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("abnormal_owner >=", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerLessThan(String value) {
            addCriterion("abnormal_owner <", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerLessThanOrEqualTo(String value) {
            addCriterion("abnormal_owner <=", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerLike(String value) {
            addCriterion("abnormal_owner like", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerNotLike(String value) {
            addCriterion("abnormal_owner not like", value, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerIn(List<String> values) {
            addCriterion("abnormal_owner in", values, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerNotIn(List<String> values) {
            addCriterion("abnormal_owner not in", values, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerBetween(String value1, String value2) {
            addCriterion("abnormal_owner between", value1, value2, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andAbnormalOwnerNotBetween(String value1, String value2) {
            addCriterion("abnormal_owner not between", value1, value2, "abnormalOwner");
            return (Criteria) this;
        }

        public Criteria andValidIsNull() {
            addCriterion("`valid` is null");
            return (Criteria) this;
        }

        public Criteria andValidIsNotNull() {
            addCriterion("`valid` is not null");
            return (Criteria) this;
        }

        public Criteria andValidEqualTo(String value) {
            addCriterion("`valid` =", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotEqualTo(String value) {
            addCriterion("`valid` <>", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThan(String value) {
            addCriterion("`valid` >", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidGreaterThanOrEqualTo(String value) {
            addCriterion("`valid` >=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThan(String value) {
            addCriterion("`valid` <", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLessThanOrEqualTo(String value) {
            addCriterion("`valid` <=", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidLike(String value) {
            addCriterion("`valid` like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotLike(String value) {
            addCriterion("`valid` not like", value, "valid");
            return (Criteria) this;
        }

        public Criteria andValidIn(List<String> values) {
            addCriterion("`valid` in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotIn(List<String> values) {
            addCriterion("`valid` not in", values, "valid");
            return (Criteria) this;
        }

        public Criteria andValidBetween(String value1, String value2) {
            addCriterion("`valid` between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andValidNotBetween(String value1, String value2) {
            addCriterion("`valid` not between", value1, value2, "valid");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartIsNull() {
            addCriterion("abnormal_depart is null");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartIsNotNull() {
            addCriterion("abnormal_depart is not null");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartEqualTo(Short value) {
            addCriterion("abnormal_depart =", value, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartNotEqualTo(Short value) {
            addCriterion("abnormal_depart <>", value, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartGreaterThan(Short value) {
            addCriterion("abnormal_depart >", value, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartGreaterThanOrEqualTo(Short value) {
            addCriterion("abnormal_depart >=", value, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartLessThan(Short value) {
            addCriterion("abnormal_depart <", value, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartLessThanOrEqualTo(Short value) {
            addCriterion("abnormal_depart <=", value, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartIn(List<Short> values) {
            addCriterion("abnormal_depart in", values, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartNotIn(List<Short> values) {
            addCriterion("abnormal_depart not in", values, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartBetween(Short value1, Short value2) {
            addCriterion("abnormal_depart between", value1, value2, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andAbnormalDepartNotBetween(Short value1, Short value2) {
            addCriterion("abnormal_depart not between", value1, value2, "abnormalDepart");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalIsNull() {
            addCriterion("duplicate_abnormal is null");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalIsNotNull() {
            addCriterion("duplicate_abnormal is not null");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalEqualTo(Integer value) {
            addCriterion("duplicate_abnormal =", value, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalNotEqualTo(Integer value) {
            addCriterion("duplicate_abnormal <>", value, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalGreaterThan(Integer value) {
            addCriterion("duplicate_abnormal >", value, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalGreaterThanOrEqualTo(Integer value) {
            addCriterion("duplicate_abnormal >=", value, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalLessThan(Integer value) {
            addCriterion("duplicate_abnormal <", value, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalLessThanOrEqualTo(Integer value) {
            addCriterion("duplicate_abnormal <=", value, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalIn(List<Integer> values) {
            addCriterion("duplicate_abnormal in", values, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalNotIn(List<Integer> values) {
            addCriterion("duplicate_abnormal not in", values, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalBetween(Integer value1, Integer value2) {
            addCriterion("duplicate_abnormal between", value1, value2, "duplicateAbnormal");
            return (Criteria) this;
        }

        public Criteria andDuplicateAbnormalNotBetween(Integer value1, Integer value2) {
            addCriterion("duplicate_abnormal not between", value1, value2, "duplicateAbnormal");
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