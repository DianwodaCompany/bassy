package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BBSWeeklyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BBSWeeklyExample() {
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

        public Criteria andWeeklyIdIsNull() {
            addCriterion("weekly_id is null");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdIsNotNull() {
            addCriterion("weekly_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdEqualTo(Integer value) {
            addCriterion("weekly_id =", value, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdNotEqualTo(Integer value) {
            addCriterion("weekly_id <>", value, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdGreaterThan(Integer value) {
            addCriterion("weekly_id >", value, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("weekly_id >=", value, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdLessThan(Integer value) {
            addCriterion("weekly_id <", value, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdLessThanOrEqualTo(Integer value) {
            addCriterion("weekly_id <=", value, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdIn(List<Integer> values) {
            addCriterion("weekly_id in", values, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdNotIn(List<Integer> values) {
            addCriterion("weekly_id not in", values, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdBetween(Integer value1, Integer value2) {
            addCriterion("weekly_id between", value1, value2, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andWeeklyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("weekly_id not between", value1, value2, "weeklyId");
            return (Criteria) this;
        }

        public Criteria andBbsIdIsNull() {
            addCriterion("bbs_id is null");
            return (Criteria) this;
        }

        public Criteria andBbsIdIsNotNull() {
            addCriterion("bbs_id is not null");
            return (Criteria) this;
        }

        public Criteria andBbsIdEqualTo(Integer value) {
            addCriterion("bbs_id =", value, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdNotEqualTo(Integer value) {
            addCriterion("bbs_id <>", value, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdGreaterThan(Integer value) {
            addCriterion("bbs_id >", value, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bbs_id >=", value, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdLessThan(Integer value) {
            addCriterion("bbs_id <", value, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdLessThanOrEqualTo(Integer value) {
            addCriterion("bbs_id <=", value, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdIn(List<Integer> values) {
            addCriterion("bbs_id in", values, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdNotIn(List<Integer> values) {
            addCriterion("bbs_id not in", values, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdBetween(Integer value1, Integer value2) {
            addCriterion("bbs_id between", value1, value2, "bbsId");
            return (Criteria) this;
        }

        public Criteria andBbsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bbs_id not between", value1, value2, "bbsId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIsNull() {
            addCriterion("note_type is null");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIsNotNull() {
            addCriterion("note_type is not null");
            return (Criteria) this;
        }

        public Criteria andNoteTypeEqualTo(Byte value) {
            addCriterion("note_type =", value, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNotEqualTo(Byte value) {
            addCriterion("note_type <>", value, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeGreaterThan(Byte value) {
            addCriterion("note_type >", value, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("note_type >=", value, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeLessThan(Byte value) {
            addCriterion("note_type <", value, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeLessThanOrEqualTo(Byte value) {
            addCriterion("note_type <=", value, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIn(List<Byte> values) {
            addCriterion("note_type in", values, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNotIn(List<Byte> values) {
            addCriterion("note_type not in", values, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeBetween(Byte value1, Byte value2) {
            addCriterion("note_type between", value1, value2, "noteType");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("note_type not between", value1, value2, "noteType");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("week is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(String value) {
            addCriterion("week =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(String value) {
            addCriterion("week <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(String value) {
            addCriterion("week >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(String value) {
            addCriterion("week >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(String value) {
            addCriterion("week <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(String value) {
            addCriterion("week <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLike(String value) {
            addCriterion("week like", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotLike(String value) {
            addCriterion("week not like", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<String> values) {
            addCriterion("week in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<String> values) {
            addCriterion("week not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(String value1, String value2) {
            addCriterion("week between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(String value1, String value2) {
            addCriterion("week not between", value1, value2, "week");
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