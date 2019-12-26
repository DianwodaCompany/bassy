package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AutoTestStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AutoTestStatisticsExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andDateIsNull() {
            addCriterion("`date` is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("`date` is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("`date` =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("`date` <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("`date` >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`date` >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("`date` <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("`date` <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("`date` in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("`date` not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`date` between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("`date` not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesIsNull() {
            addCriterion("execution_times is null");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesIsNotNull() {
            addCriterion("execution_times is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesEqualTo(String value) {
            addCriterion("execution_times =", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesNotEqualTo(String value) {
            addCriterion("execution_times <>", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesGreaterThan(String value) {
            addCriterion("execution_times >", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesGreaterThanOrEqualTo(String value) {
            addCriterion("execution_times >=", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesLessThan(String value) {
            addCriterion("execution_times <", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesLessThanOrEqualTo(String value) {
            addCriterion("execution_times <=", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesLike(String value) {
            addCriterion("execution_times like", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesNotLike(String value) {
            addCriterion("execution_times not like", value, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesIn(List<String> values) {
            addCriterion("execution_times in", values, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesNotIn(List<String> values) {
            addCriterion("execution_times not in", values, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesBetween(String value1, String value2) {
            addCriterion("execution_times between", value1, value2, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andExecutionTimesNotBetween(String value1, String value2) {
            addCriterion("execution_times not between", value1, value2, "executionTimes");
            return (Criteria) this;
        }

        public Criteria andCaseNumIsNull() {
            addCriterion("case_num is null");
            return (Criteria) this;
        }

        public Criteria andCaseNumIsNotNull() {
            addCriterion("case_num is not null");
            return (Criteria) this;
        }

        public Criteria andCaseNumEqualTo(String value) {
            addCriterion("case_num =", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumNotEqualTo(String value) {
            addCriterion("case_num <>", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumGreaterThan(String value) {
            addCriterion("case_num >", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumGreaterThanOrEqualTo(String value) {
            addCriterion("case_num >=", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumLessThan(String value) {
            addCriterion("case_num <", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumLessThanOrEqualTo(String value) {
            addCriterion("case_num <=", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumLike(String value) {
            addCriterion("case_num like", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumNotLike(String value) {
            addCriterion("case_num not like", value, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumIn(List<String> values) {
            addCriterion("case_num in", values, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumNotIn(List<String> values) {
            addCriterion("case_num not in", values, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumBetween(String value1, String value2) {
            addCriterion("case_num between", value1, value2, "caseNum");
            return (Criteria) this;
        }

        public Criteria andCaseNumNotBetween(String value1, String value2) {
            addCriterion("case_num not between", value1, value2, "caseNum");
            return (Criteria) this;
        }

        public Criteria andPassingRateIsNull() {
            addCriterion("passing_rate is null");
            return (Criteria) this;
        }

        public Criteria andPassingRateIsNotNull() {
            addCriterion("passing_rate is not null");
            return (Criteria) this;
        }

        public Criteria andPassingRateEqualTo(String value) {
            addCriterion("passing_rate =", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateNotEqualTo(String value) {
            addCriterion("passing_rate <>", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateGreaterThan(String value) {
            addCriterion("passing_rate >", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateGreaterThanOrEqualTo(String value) {
            addCriterion("passing_rate >=", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateLessThan(String value) {
            addCriterion("passing_rate <", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateLessThanOrEqualTo(String value) {
            addCriterion("passing_rate <=", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateLike(String value) {
            addCriterion("passing_rate like", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateNotLike(String value) {
            addCriterion("passing_rate not like", value, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateIn(List<String> values) {
            addCriterion("passing_rate in", values, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateNotIn(List<String> values) {
            addCriterion("passing_rate not in", values, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateBetween(String value1, String value2) {
            addCriterion("passing_rate between", value1, value2, "passingRate");
            return (Criteria) this;
        }

        public Criteria andPassingRateNotBetween(String value1, String value2) {
            addCriterion("passing_rate not between", value1, value2, "passingRate");
            return (Criteria) this;
        }

        public Criteria andReasonSortIsNull() {
            addCriterion("reason_sort is null");
            return (Criteria) this;
        }

        public Criteria andReasonSortIsNotNull() {
            addCriterion("reason_sort is not null");
            return (Criteria) this;
        }

        public Criteria andReasonSortEqualTo(String value) {
            addCriterion("reason_sort =", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortNotEqualTo(String value) {
            addCriterion("reason_sort <>", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortGreaterThan(String value) {
            addCriterion("reason_sort >", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortGreaterThanOrEqualTo(String value) {
            addCriterion("reason_sort >=", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortLessThan(String value) {
            addCriterion("reason_sort <", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortLessThanOrEqualTo(String value) {
            addCriterion("reason_sort <=", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortLike(String value) {
            addCriterion("reason_sort like", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortNotLike(String value) {
            addCriterion("reason_sort not like", value, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortIn(List<String> values) {
            addCriterion("reason_sort in", values, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortNotIn(List<String> values) {
            addCriterion("reason_sort not in", values, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortBetween(String value1, String value2) {
            addCriterion("reason_sort between", value1, value2, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andReasonSortNotBetween(String value1, String value2) {
            addCriterion("reason_sort not between", value1, value2, "reasonSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortIsNull() {
            addCriterion("method_sort is null");
            return (Criteria) this;
        }

        public Criteria andMethodSortIsNotNull() {
            addCriterion("method_sort is not null");
            return (Criteria) this;
        }

        public Criteria andMethodSortEqualTo(String value) {
            addCriterion("method_sort =", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortNotEqualTo(String value) {
            addCriterion("method_sort <>", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortGreaterThan(String value) {
            addCriterion("method_sort >", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortGreaterThanOrEqualTo(String value) {
            addCriterion("method_sort >=", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortLessThan(String value) {
            addCriterion("method_sort <", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortLessThanOrEqualTo(String value) {
            addCriterion("method_sort <=", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortLike(String value) {
            addCriterion("method_sort like", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortNotLike(String value) {
            addCriterion("method_sort not like", value, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortIn(List<String> values) {
            addCriterion("method_sort in", values, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortNotIn(List<String> values) {
            addCriterion("method_sort not in", values, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortBetween(String value1, String value2) {
            addCriterion("method_sort between", value1, value2, "methodSort");
            return (Criteria) this;
        }

        public Criteria andMethodSortNotBetween(String value1, String value2) {
            addCriterion("method_sort not between", value1, value2, "methodSort");
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